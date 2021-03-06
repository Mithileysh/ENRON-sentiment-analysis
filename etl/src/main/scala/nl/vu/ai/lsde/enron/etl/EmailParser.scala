package nl.vu.ai.lsde.enron.etl

import java.text.SimpleDateFormat

import nl.vu.ai.lsde.enron.{Custodian, Email}

object EmailParser {

    val datePattern = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z '('z')'")
    val enronDatasetFooter = "\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\nEDRM Enron"
    val forwardedBy = ("\\s?---------------------- Forwarded by ", "(\\w*\\s)?---------------------------")
    val forwardedByReg = forwardedBy._1 + "(.*)\\n" + forwardedBy._2
    val subjectFwd = "^F[Ww]: [\\w\\W\\s]*$"
    val subjectReply = "^R[Ee]: [\\w\\W\\s]*$"
    val originalMsg = ">?\\s? -----Original Message-----"

    def parse(text: String, custodians: Seq[Custodian]): Email = {
        // get headers and body
        val (headers, body) = text.split("\n\n") match {
            case s: Array[String] => (s.head.split("\n"), s.tail.mkString("\n\n"))
        }

        // get date
        val date = filterHeader(headers, "Date: ") match {
            case Some(s) =>
                try Some(new java.sql.Timestamp(datePattern.parse(s).getTime))
                catch { case e: java.text.ParseException => None }
            case _ => None
        }
        if (date.isEmpty) throw new EmailParsingException(s"Unable to parse DATE header in email:\n$text")

        // get header entries
        val from = filterHeaderList(headers, "From: ", custodians)
        if (from.isEmpty) throw new EmailParsingException(s"Unable to parse FROM header in email:\n$text")

        val to = filterHeaderList(headers, "To: ", custodians)
        val cc = filterHeaderList(headers, "Cc: ", custodians)
        val bcc = filterHeaderList(headers, "Bcc: ", custodians)

        val subject = filterHeader(headers, "Subject: ")
        if (subject.isEmpty) throw new EmailParsingException(s"Unable to parse SUBJECT header in email:\n$text")

         // filter fixed footer for the body
         val bodyNoFooter = body.split(enronDatasetFooter).head

         // remove just the "forwarded by" tag
         val bodyNoFwd = bodyNoFooter.replaceAll(forwardedByReg, "")

         // remove all the "original messages" text
         val bodyNoOrig = if (subject.get.matches(subjectFwd)) { // check if email's subject reports something like Re: tag
             bodyNoFwd.replaceAll(originalMsg, "")
         } else {
             // get the part above the originalMsg tag
             try { bodyNoFwd.split(originalMsg).head }
             catch { case e: NoSuchElementException => throw new EmailParsingException(s"Unable to process body of email:\n$text") }
         }

         // clean body from headers
         val bodyNoHeaders = bodyNoOrig.split('\n').map(_
             .replaceAll(">?\\s?From:\\s[^\\n]*$", "")
             .replaceAll(">?\\s?To:\\s[^\\n]*$", "")
             .replaceAll(">?\\s?[Cc]c:\\s[^\\n]*$", "")
             .replaceAll(">?\\s?[Bb]cc:\\s[^\\n]*$", "")
             .replaceAll(">?\\s?Subject:\\s[^\\n]*$", "")
             .replaceAll(">?\\s?Sent:\\s[^\\n]*$", "")
             .replaceAll(">?\\s?Sent by:\\s[^\\n]*$", "")
         ).mkString("\n")

        Email(date.get, from.get, to.getOrElse(Seq()), cc.getOrElse(Seq()), bcc.getOrElse(Seq()), subject.get, bodyNoHeaders)
    }

    /** Returns a header entry from headers.
      *
      * @param headers raw email header
      * @param filter identifier of the entry to retrieve
      * @return the entry related to @filter attribute
      */
    private def filterHeader(headers: Seq[String], filter: String): Option[String] = {
        headers.filter(h => h.startsWith(filter)) match {
            case Seq() => None
            case x: Seq[String] =>
                try Some(x.head.split(filter)(1))
                catch { case e: java.lang.ArrayIndexOutOfBoundsException => None }
        }
    }

    /** Returns all the comma-separated entries for a specific header attribute.
      * Useful for header attributes like To, Cc, etc.
      *
      * @param headers raw email header
      * @param filter identifier of the entry to retrieve
      * @return list of entries related to @filter attribute
      */
    private def filterHeaderList(headers: Seq[String], filter: String, custodians: Seq[Custodian]): Option[Seq[Custodian]] = {
        // get the useful part of the attribute
        filterHeader(headers, filter) match {
            case Some(s) =>
                // clean the attribute
                val attribute = s.toLowerCase.trim.replaceAll("[^a-zA-Z@]","")

                // gets the custodians actually present within the header attribute
                val custodiansPresent = custodians.flatMap(checkCustodianInString(_, attribute))
                Some(custodiansPresent)

            case _ => None
        }
    }

    private def checkCustodianInString(custodian: Custodian, text: String) : Option[Custodian] = {
        // get clean names parts
        val nameParts = custodian.completeName.toLowerCase.split(" ")
        val name = nameParts(0)
        val surname = nameParts(1)

        nameParts.length match {
            // surnames are not unique, therefore surname@enron cannot be used
            // thus, look for both surname AND name
            case 2 =>
                if(text.contains(surname) && text.contains(name)) Some(custodian) else None
            // multiple surnames custodian: search for both (surname,name) pairs
            case 3 =>
                val surname_2 = nameParts(2)
                if((text.contains(surname) && text.contains(name)) ||
                    (text.contains(surname_2) && text.contains(name))) Some(custodian) else None
        }
        // NOTE: CHANGELOG of the custodians file:
        // multiple dashed surnames (i.e., Gilberth-Smith, Mims-Thurston) "-" have been replaced by a " ", note that Smith has omonimi
        // 122,124 initials have been removed
        // 144 (i.e. Bill Williams III), III has been removed
        // 146 (i.e. Y'Barbo), "Y'" has been removed
        // 3 "Harpreet Arora" -> "Harry Arora"
    }

    type EmailParsingException = RuntimeException
}
