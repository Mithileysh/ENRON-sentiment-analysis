package nl.vu.ai.lsde.enron.parser

import java.io.InputStream
import nl.vu.ai.lsde.enron.Commons

// run with:
// sbt parser/test:run
object Test {

    // scalastyle:off
    def main (args: Array[String]) {
        // scalastyle:off line.size.limit
        val tests = Seq(
            "Date: Wed, 7 Feb 2001 14:11:00 -0800 (PST)\nFrom: Harry Arora\nTo: Enron\nSubject: Allan Sommer\nX-SDOC: 528063\nX-ZLID: zl-edrm-enron-v2-arora-h-914.eml\n\nHello, email!\n\n***********\nEDRM Enron Email Data Set has been produced in EML, PST and NSF format by ZL Technologies, Inc. This Data Set is licensed under a Creative Commons Attribution 3.0 United States License <http://creativecommons.org/licenses/by/3.0/us/> . To provide attribution, please cite to \"ZL Technologies, Inc. (http://www.zlti.com).\"\n***********",
            "Date: Tue, 29 Jan 2002 14:45:09 -0800 (PST)\nSubject: Notice to Employees of Bankruptcy Court Order\nFrom: Legal - James Derrick Jr. <mbx_annclegal@ENRON.com>\nTo: DL-GA-all_enron_worldwide3 <DL-GA-all_enron_worldwide3@ENRON.com>\nX-SDOC: 105986\nX-ZLID: zl-edrm-enron-v2-townsend-j-127.eml\n\nThis is to inform you that on January 25, 2002 the Honorable Arthur Gonzalez, United States Bankruptcy Judge for the Southern District of New York, entered an order as follows:\n\nIt is hereby Ordered that until further Order of this Court, Enron Corp., its affiliated debtors-in-possession in these jointly administered chapter 11 proceedings (collectively \"Enron\"), and Enron's employees shall preserve, and refrain from destroying or disposing of, any of Enron's records, either in electronic or paper form.\n\nObviously, any violation of this order will carry serious consequences.  We know that we can depend on you to comply with it fully.\n\nThank you.\n\n***********\nEDRM Enron Email Data Set has been produced in EML, PST and NSF format by ZL Technologies, Inc. This Data Set is licensed under a Creative Commons Attribution 3.0 United States License <http://creativecommons.org/licenses/by/3.0/us/> . To provide attribution, please cite to \"ZL Technologies, Inc. (http://www.zlti.com).\"\n***********",
            "Date: Wed, 30 Jan 2002 22:00:00 -0800 (PST)\nSubject: TE CASHOUT PRICES - January, 2002\nFrom: INFOPOST@DUKE-ENERGY.COM@ENRON <IMCEANOTES-INFOPOST+40DUKE-ENERGY+2ECOM+40ENRON@ENRON.com>\nTo: INFOPOST@DUKE-ENERGY.COM\nX-SDOC: 105971\nX-ZLID: zl-edrm-enron-v2-townsend-j-112.eml\n\nJanuary 31, 2002\n\n           DETERMINATION OF CASHOUT PRICES BY ZONE ($/MMBTU)\n                              January, 2002\n\n.                     5 Week Avg           High         Low\nSTX Zone                $2.288            $2.450      $2.220\nETX Zone                $2.315            $2.520      $2.170\nWLA Zone                $2.310            $2.540      $2.080\nELA Zone                $2.323            $2.550      $2.090\nMARKET AREA             $2.309            $2.515      $2.145\n\n\n\n\n\n*******************************************************************\n\nNOTE:\n\nDuke Energy Gas Transmission respects your online time and privacy.\nYou have received this email because you elected to subscribe. To\nunsubscribe, login to the E-mail Notification Subscription page at\nhttp://www.link.duke-energy.com/script2/Notification.asp\nuncheck the appropriate checkbox, and click the Submit button.\n\n***********\nEDRM Enron Email Data Set has been produced in EML, PST and NSF format by ZL Technologies, Inc. This Data Set is licensed under a Creative Commons Attribution 3.0 United States License <http://creativecommons.org/licenses/by/3.0/us/> . To provide attribution, please cite to \"ZL Technologies, Inc. (http://www.zlti.com).\"\n***********",
            "Date: Mon, 14 May 2001 06:26:00 -0700 (PDT)\nFrom: Outlook Migration Team\nTo: V Charles Weldon, Ed McMichael, Eric Boyt, Eric Moon, Geraldine Irvine, Jin\n\t Guo, Louis Dicarlo, Maria Garza, Mark Breese, Sandy Olitsky, Santiago Garcia,\n\t Steven Curlee, Alexandra Villarreal, Andrea Ring, Brad McKay, Chris Germany,\n\t Scott Hendrickson, Eric Thode, Jennifer Walker, Judy Townsend, Kathie Grabstald,\n\t Kimberly Bates, Mark Koenig, Peter F Keavey, Rebecca Ford, Sandra F Brawner,\n\t Scott Goodell, Scott Neal, Sean Boyle, Susan W Pereira, Victoria Versen, Vladi\n\t Pimenov, Craig Taylor, David Jones, Frank W Vickers, Gil Muhl, John Hodge,\n\t Maureen Smith, Nelson Ferries, Phil DeMoes, Robin Barbe, Ruth Concannon, Tammi\n\t DePaolis, Daniel Lisk, Hillary Mack, Jan Sutherland, Jason Wolfe, Shannon Groenewold,\n\t Shelly Mendel, Stacey J Brewer, Suzanne Christiansen, Ted Evans, Dina Snow,\n\t Kay Young, Lance Schuler-Legal, Vineet Arora, Beth A Ryan, Bill Berkeland,\n\t David A Terlip, Doug Leach, Marc De La Roche, Randal Maffett, Tushar Dhruv,\n\t Yolanda Cordova-Gilbert, Amy Villasenor, Debra Young, Jeff Arnold, John Reese,\n\t Josh Mayes, Larry Fenstad, Mark Adleman, Mary Ann Johnson, Mary Lynne Dix,\n\t Mike Thomas, Morris Brassfield, Rinetia Turner, Robert Geheb, Ruthann Frausto,\n\t Tyrone Brown\nSubject: 3 - URGENT - TO PREVENT LOSS OF INFORMATION\nX-SDOC: 1093157\nX-ZLID: zl-edrm-enron-v2-townsend-j-488.eml\n\nCritical Migration Information:\n\n1. Your scheduled Outlook Migration Date is THE EVENING OF : May 17th\n2. You need to press the \"Save My Data\" button (only once) to send us your \npre-migration information.\n3. You must be connected to the network before you press the button.\n4. If a POP-UP BOX appears, prompting you to \"ABORT, CANCEL OR TRUST SIGNER\" \nplease  select TRUST SIGNER.\n5. Any information you Add to your Personal Address Book, Journal or calendar \nafter you click on the button will need to be manually re-added into Outlook \nafter you have been migrated.\n6. Clicking this button does not complete your migration to Outlook. Your \nmigration will be completed  the evening of your migration date.\n\n\n\n    Failure to click on the button means you WILL NOT get your Calendar, \nContacts, Journal and ToDo information imported into Outlook the day of your \nmigration and could result in up to a 2 week delay to restore this \ninformation.\n\nIf you encounter any errors please contact the resolution center @ \n713-853-1411 \n\n***********\nEDRM Enron Email Data Set has been produced in EML, PST and NSF format by ZL Technologies, Inc. This Data Set is licensed under a Creative Commons Attribution 3.0 United States License <http://creativecommons.org/licenses/by/3.0/us/> . To provide attribution, please cite to \"ZL Technologies, Inc. (http://www.zlti.com).\"\n***********",
            "Date: Mon, 6 Nov 2000 03:25:00 -0800 (PST)\nFrom: Judy Townsend\nTo: Edward D Gottlob, James McKay\nSubject: HPL Meter\nX-SDOC: 1093281\nX-ZLID: zl-edrm-enron-v2-townsend-j-601.eml\n\n---------------------- Forwarded by Judy Townsend/HOU/ECT on 11/06/2000 11:18 \nAM ---------------------------\n\n\nlamoss@duke-energy.com on 11/01/2000 08:44:48 AM\nTo: judy.townsend@enron.com\ncc:  \nSubject: HPL Meter\n\n\n     Alan Lambeth out of our Baytown office is who they can talk to about\nthat meter.\nHist # is 281.424.6812.\n\n\n\n\n\n***********\nEDRM Enron Email Data Set has been produced in EML, PST and NSF format by ZL Technologies, Inc. This Data Set is licensed under a Creative Commons Attribution 3.0 United States License <http://creativecommons.org/licenses/by/3.0/us/> . To provide attribution, please cite to \"ZL Technologies, Inc. (http://www.zlti.com).\"\n***********",
            "Date: Fri, 12 Oct 2001 13:16:47 -0700 (PDT)\nFrom: Lamadrid, Victor </O=ENRON/OU=NA/CN=RECIPIENTS/CN=VLAMADR>\nTo: Olinger, Kimberly S. </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Kolinge>, Ames, Chuck </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Cames>, Brawner, Sandra F. </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Sbrawne>, Germany, Chris </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Cgerman>, Goodell, Scott </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Sgoodel>, Hodge, John </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Jhodge2>, Keavey, Peter F. </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Pkeavey>, Mckay, Brad </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Bmckay>, Mckay, Jonathan </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Jmckay1>, Neal, Scott </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Sneal>, Pereira, Susan W. </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Spereir>, Pimenov, Vladi </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Vpimenov>, Ring, Andrea </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Aring>, Savvas, Leonidas </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Lsavvas>, Smith, Maureen </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Mgreena>, Taylor, Craig </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Jtaylo2>, Townsend, Judy </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Jtownse>, Versen, Victoria </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Vversen>, Vickers, Frank W. </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Fvicker>\nCc: Gillespie, Steve </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Sgillesp>, Garcia, Clarissa </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Cgarcia>, Homco, Meredith </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Mmitch2>, Allwein, Robert </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Rallwei>, Collins, Joann </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Jcollin>, Ordway, Chris </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Cordway>, Villarreal, Jesse </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Jvillar>, Smith, George\n\t F. </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Gsmith>, Kelly, Katherine L. </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Kkelly>, Khandker, Dayem </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Dkhandke>\nSubject: FW: GSS/LSS Rate changes Effective 11/01\nX-SDOC: 788196\nX-ZLID: zl-edrm-enron-v2-townsend-j-276.eml\n\nfyi\n\n -----Original Message-----\nFrom: \tCustomerNotices, WGPHOU [mailto:WGPHOU.CustomerNotices@Williams.com] \nSent:\tFriday, October 12, 2001 3:01 PM\nSubject:\tGSS/LSS Rate changes Effective 11/01\n\n\nOctober 12, 2001\n\nTo:  LSS and GSS Customers\n\nRe:  DTI Changes Effective 11/1/01\n\nOn September 21, 2001, Dominion Transmission, Inc. (DTI) \nfiled revised tariff sheets in Docket No. RP01-632 in order \nto implement its Settlement which was approved by the \nCommission in a letter order issued September 13, 2001.  DTI \nanticipates an effective date of November 1, 2001.  Included \nin DTI's filing are revised rates under its Rate Schedule \nGSS which Transco uses to render service to its customers \nunder its Rate Schedules LSS and GSS. If accepted as \nproposed, the revised DTI rates would result in the \nfollowing changes to Transco's current Rate Schedules LSS \nand GSS rates:\n \nGSS Deliverability Charge -       $0.0006 increase \nGSS Injection Charge -            $0.0171 decrease \nGSS Withdrawal Charge -           $0.0171 decrease \nGSS Excess Delivery Charge -      $0.0170 decrease\nLSS Demand Charge -               $0.0007 increase\nLSS Injection Charge -            $0.0193 decrease\nLSS Withdrawal Charge -           $0.0194 decrease\n\nIn addition, on September 21, 2001, DTI filed revised tariff \nsheets in Docket No. RP01-611 in order to comply with the \nStipulation and Agreement in Docket Nos. RP97-406, et al.  \nThe filing reflects a phased conversion of Rate Schedule \nGSS-II entitlements to Rate Schedule GSS with a proposed \neffective date of November 1, 2001. Included in DTI's filing \nare revised rates under its Rate Schedule GSS which Transco \nuses to render service to its customers under its Rate \nSchedules LSS and GSS. If accepted as proposed, the revised \nDTI rates would result in the following changes to Transco's \ncurrent Rate Schedules LSS and GSS rates: \n\nGSS Deliverability Charge -       $0.0100 increase \nGSS Capacity Charge -             $0.0002 increase \nGSS Injection Charge -            $0.0001 decrease \nGSS Excess Delivery Charge -      $0.0043 increase\nLSS Demand Charge -               $0.0120 increase\nLSS Capacity Charge -             $0.0002 increase\nLSS Injection Charge -            $0.0001 decrease\n\nOn September 28, 2001, DTI also filed revised tariff sheets \nin Docket No. RP01-625 in order to update its Electric Power \nCost Adjustment.  Included in DTI's filing are revised rates \nunder its Rate Schedule GSS which Transco uses to render \nservice to its customers under its Rate Schedules LSS and \nGSS. If accepted as proposed, the revised DTI rates would \nresult in the following changes in Transco's current Rate \nSchedules LSS and GSS rates effective November 1, 2001: \n\nGSS Deliverability Charge -       $0.0023 increase \nGSS Injection Charge -            $0.0004 decrease\nGSS Withdrawal Charge -           $0.0001 increase \nGSS Excess Delivery Charge -      $0.0005 increase\nLSS Demand Charge -               $0.0028 increase\nLSS Injection Charge -            $0.0004 decrease\nLSS Withdrawal Charge -           $0.0001 increase\n\nOn September 28, 2001, DTI also filed revised tariff sheets \nin Docket No. RP01-626 in order to update its Transportation \nCost Rate Adjustment.  Included in DTI's filing are revised \nrates under its Rate Schedule GSS which Transco uses to \nrender service to its customers under its Rate Schedules LSS \nand GSS. If accepted as proposed, the revised DTI rates \nwould result in the following changes to Transco's current \nRate Schedules LSS and GSS rates: \n\nGSS Deliverability Charge -       $0.0145 decrease \nGSS Injection Charge -            $0.0007 decrease \nGSS Withdrawal Charge -           $0.0007 decrease \nGSS Excess Delivery Charge -      $0.0034 decrease\nLSS Demand Charge -               $0.0174 decrease\nLSS Injection Charge -            $0.0008 decrease\nLSS Withdrawal Charge -           $0.0008 decrease\n\nPlease refer any questions to Mike Heeney, Rate Department, \nat (713) 215-2424.\n\n***********\nEDRM Enron Email Data Set has been produced in EML, PST and NSF format by ZL Technologies, Inc. This Data Set is licensed under a Creative Commons Attribution 3.0 United States License <http://creativecommons.org/licenses/by/3.0/us/> . To provide attribution, please cite to \"ZL Technologies, Inc. (http://www.zlti.com).\"\n***********",
            "Date: Tue, 20 Nov 2001 08:35:29 -0800 (PST)\nFrom: Townsend, Judy </O=ENRON/OU=NA/CN=RECIPIENTS/CN=JTOWNSE>\nTo: Fletcher, Brenda H. </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Bfletch>\nSubject: RE: TETCO invoice - October production\nX-SDOC: 1357608\nX-ZLID: zl-edrm-enron-v2-townsend-j-649.eml\n\nsorry,  tetco is right....  .035  Do I need to change the ticket?\n\n -----Original Message-----\nFrom: \tFletcher, Brenda H.  \nSent:\tTuesday, November 20, 2001 10:15 AM\nTo:\tTownsend, Judy\nSubject:\tTETCO invoice - October production\n\nContract 910262, Deal # 1072014\n\nPipe invoiced a demand rate of .035 ($1.085) on volume of 20,000.  The rate in Sitara is .03.  The amount of variance is  $3,100.\n\nWhich is correct?\n\nThanks,\nBrenda\n\n***********\nEDRM Enron Email Data Set has been produced in EML, PST and NSF format by ZL Technologies, Inc. This Data Set is licensed under a Creative Commons Attribution 3.0 United States License <http://creativecommons.org/licenses/by/3.0/us/> . To provide attribution, please cite to \"ZL Technologies, Inc. (http://www.zlti.com).\"\n***********",
            "Date: Tue, 20 Nov 2001 08:36:15 -0800 (PST)\nSubject: RE: TETCO invoice - October production\nFrom: Fletcher  Brenda H. <Brenda.H.Fletcher@ENRON.com>\nTo: Townsend  Judy <Judy.Townsend@ENRON.com>\nX-SDOC: 1357581\nX-ZLID: zl-edrm-enron-v2-townsend-j-624.eml\n\nI'll change it.\nThanks\n\n -----Original Message-----\nFrom: \tTownsend, Judy  \nSent:\tTuesday, November 20, 2001 10:35 AM\nTo:\tFletcher, Brenda H.\nSubject:\tRE: TETCO invoice - October production\n\nsorry,  tetco is right....  .035  Do I need to change the ticket?\n\n -----Original Message-----\nFrom: \tFletcher, Brenda H.  \nSent:\tTuesday, November 20, 2001 10:15 AM\nTo:\tTownsend, Judy\nSubject:\tTETCO invoice - October production\n\nContract 910262, Deal # 1072014\n\nPipe invoiced a demand rate of .035 ($1.085) on volume of 20,000.  The rate in Sitara is .03.  The amount of variance is  $3,100.\n\nWhich is correct?\n\nThanks,\nBrenda\n\n***********\nEDRM Enron Email Data Set has been produced in EML, PST and NSF format by ZL Technologies, Inc. This Data Set is licensed under a Creative Commons Attribution 3.0 United States License <http://creativecommons.org/licenses/by/3.0/us/> . To provide attribution, please cite to \"ZL Technologies, Inc. (http://www.zlti.com).\"\n***********",
            "Date: Tue, 20 Nov 2001 08:15:25 -0800 (PST)\nSubject: TETCO invoice - October production\nFrom: Fletcher  Brenda H. <Brenda.H.Fletcher@ENRON.com>\nTo: Townsend  Judy <Judy.Townsend@ENRON.com>\nX-SDOC: 1357582\nX-ZLID: zl-edrm-enron-v2-townsend-j-625.eml\n\nContract 910262, Deal # 1072014\n\nPipe invoiced a demand rate of .035 ($1.085) on volume of 20,000.  The rate in Sitara is .03.  The amount of variance is  $3,100.\n\nWhich is correct?\n\nThanks,\nBrenda\n\n***********\nEDRM Enron Email Data Set has been produced in EML, PST and NSF format by ZL Technologies, Inc. This Data Set is licensed under a Creative Commons Attribution 3.0 United States License <http://creativecommons.org/licenses/by/3.0/us/> . To provide attribution, please cite to \"ZL Technologies, Inc. (http://www.zlti.com).\"\n***********",
            "Date: Thu, 21 Dec 2000 03:20:00 -0800 (PST)\nFrom: Judy Townsend\nTo: Chris Germany\nSubject: FW: Service Request\nX-SDOC: 1093234\nX-ZLID: zl-edrm-enron-v2-townsend-j-560.eml\n\n---------------------- Forwarded by Judy Townsend/HOU/ECT on 12/21/2000 11:13 \nAM ---------------------------\n\n\nCraig Breslau\n12/19/2000 01:25 PM\nTo: Judy Townsend/HOU/ECT@ECT, Victoria Versen/HOU/ECT@ECT\ncc:  \nSubject: FW: Service Request\n\n\n---------------------- Forwarded by Craig Breslau/HOU/ECT on 12/19/2000 01:24 \nPM ---------------------------\n\n\n\"Kilby, Daniel\" <KilbyD@kochind.com> on 12/12/2000 09:51:07 AM\nTo: \"'craig.breslau@enron.com'\" <craig.breslau@enron.com>\ncc:  \nSubject: FW: Service Request\n\n\nCraig,\n\nAttached is Gateway's service request form. You will need to complete this\ndocument as follows in order for us to post the deal (which takes 5 days).\nIn the interim I will work on the Letter Agreement that is the formal\ntransportation contract.\n\nPage 1 - Fill out everything with the exception of 1a.\nPage 2 - New Service Section: 1, 2 & 3.\n             Rate Information: 1, 2, 3 & 4.\nPage 3 - Signature Info.\nPage 4 - As Needed.\n\n> -----Original Message-----\n> From: Engler, Cheryl\n> Sent: Tuesday,December 12,2000 9:34 AM\n> To: Kilby, Daniel\n> Subject: Service Request\n> Importance: High\n>\n>  <<SER_REQ 0309.doc>>\n> Cheryl Engler\n> Asset Modification Manager\n> Koch Gateway Pipeline Co.\n> * englerc@kochind.com\n> * (713) 544-4739\n> *  (713) 544-9170\n>\n\n - SER_REQ 0309.doc\n---------------------- Forwarded by Craig Breslau/HOU/ECT on 12/19/2000 01:24 \nPM ---------------------------\n\n\n\"Kilby, Daniel\" <KilbyD@kochind.com> on 12/15/2000 08:45:45 AM\nTo: \"'craig.breslau@enron.com'\" <craig.breslau@enron.com>\ncc:  \nSubject: Ormet Letter Agreement\n\n\nCraig, please find attached a rough draft of the letter agreement for the\nOrmet Deal. We need get rolling on this so let me know your thoughts on this\nand the service request (sent earlier this week) as soon as you can.\n\nThanks.\n\n <<Enron-OrmetLetterAgreement.doc>>\n\n - Enron-OrmetLetterAgreement.doc\n\n\n\n\n***********\nEDRM Enron Email Data Set has been produced in EML, PST and NSF format by ZL Technologies, Inc. This Data Set is licensed under a Creative Commons Attribution 3.0 United States License <http://creativecommons.org/licenses/by/3.0/us/> . To provide attribution, please cite to \"ZL Technologies, Inc. (http://www.zlti.com).\"\n***********\nAttachment: SER_REQ 0309.doc type=application/msword\nAttachment: Enron-OrmetLetterAgreement.doc type=application/msword",
            "Date: Mon, 14 Jan 2002 22:00:00 -0800 (PST)\nSubject: TE Storage Inventory Levels\nFrom: INFOPOST@DUKE-ENERGY.COM@ENRON <IMCEANOTES-INFOPOST+40DUKE-ENERGY+2ECOM+40ENRON@ENRON.com>\nTo: INFOPOST@DUKE-ENERGY.COM\nX-SDOC: 105904\nX-ZLID: zl-edrm-enron-v2-townsend-j-46.eml\n\nOn  November 15, 2001, Texas Eastern Transmission, LP (\"Texas  Eastern\")\nnotified firm storage customers that, as required by the Federal  Energy\nRegulatory Commission's November 30, 1994 order, in Docket No.  RP95-15,\nTexas  Eastern would monitor customer storage activity and  would  issue\ncustomer-specific Operational Flow Orders (\"OFOs\") prior to the issuance\nof a system-wide storage OFO pursuant to Section 4.3  (L) of the General\nTerms  and Conditions of Texas Eastern's  FERC Gas Tariff, Sixth Revised\nVolume No. 1    (\"Tariff\").\n\nAs indicated in Texas Eastern's November 15, 2001 letter, the need for a\ncustomer  specific  OFO  would be determined based  on  such  customer's\nvariance  from  the suggested storage withdrawal plan specified  in  the\nNovember  15, 2001 mailing.  The Withdrawal Plan indicates  the  maximum\nrecommended storage withdrawals for each of four (4) withdrawal  periods\nand  is  designed to reflect in the aggregate Texas Eastern's historical\nutilization of its storage facilities prior to the implementation of the\nCommission's Order No. 636\n\nTexas Eastern is not issuing a Customer-Specific OFO at this time due to\nthe  high  aggregate storage position at 12/31/2001. Texas Eastern  will\ncontinue to monitor individual customer storage activity and may need to\nissue  customer-specific  OFOs  if  required  by  future  circumstances.\nCustomer-specific OFOs will be based upon each customer's variance  from\nthe suggested storage withdrawal plan provided to you and the other firm\nstorage customers by letter dated November 15, 2001.\n\nIf  you  have  any  questions  regarding the foregoing  matters,  please\ncontact your Account Manager.\n\n\n\n\n\n\n*******************************************************************\n\nNOTE:\n\nDuke Energy Gas Transmission respects your online time and privacy.\nYou have received this email because you elected to subscribe. To\nunsubscribe, login to the E-mail Notification Subscription page at\nhttp://www.link.duke-energy.com/script2/Notification.asp\nuncheck the appropriate checkbox, and click the Submit button.\n\n***********\nEDRM Enron Email Data Set has been produced in EML, PST and NSF format by ZL Technologies, Inc. This Data Set is licensed under a Creative Commons Attribution 3.0 United States License <http://creativecommons.org/licenses/by/3.0/us/> . To provide attribution, please cite to \"ZL Technologies, Inc. (http://www.zlti.com).\"\n***********"
        )

        val tests2 = Seq(
            "Date: Tue, 20 Nov 2001 08:35:29 -0800 (PST)\nFrom: Townsend, Judy </O=ENRON/OU=NA/CN=RECIPIENTS/CN=JTOWNSE>\nTo: Fletcher, Brenda H. </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Bfletch>\nSubject: RE: TETCO invoice - October production\nX-SDOC: 1357608\nX-ZLID: zl-edrm-enron-v2-townsend-j-649.eml\n\nsorry,  tetco is right....  .035  Do I need to change the ticket?\n\n -----Original Message-----\nFrom: \tFletcher, Brenda H.  \nSent:\tTuesday, November 20, 2001 10:15 AM\nTo:\tTownsend, Judy\nSubject:\tTETCO invoice - October production\n\nContract 910262, Deal # 1072014\n\nPipe invoiced a demand rate of .035 ($1.085) on volume of 20,000.  The rate in Sitara is .03.  The amount of variance is  $3,100.\n\nWhich is correct?\n\nThanks,\nBrenda\n\n***********\nEDRM Enron Email Data Set has been produced in EML, PST and NSF format by ZL Technologies, Inc. This Data Set is licensed under a Creative Commons Attribution 3.0 United States License <http://creativecommons.org/licenses/by/3.0/us/> . To provide attribution, please cite to \"ZL Technologies, Inc. (http://www.zlti.com).\"\n***********"
        )

        // scalastyle:on line.size.limit

        println(tests2(0))
        println("---------------\n\n\n")

        var custodians = Commons.getCustodians

        var parsed = tests2.map { testMail =>
            EmailParser.parse(testMail, custodians)
        }
//        parsed foreach println
//        println("\nparsed(0).to:")
//        parsed(0).to foreach println
//        println(parsed(0).to)
//        println(parsed(0).bcc)
//        println(parsed(0).cc)
//        println(parsed(0).from)
    }
}
