selector_to_datafile = (value) ->
	if value is "FULL"
		'data/part-00000'
	else
		"data/sentiment_resume_#{value}.json/part-00000"


show_chart = (selected_mailbox) ->
	$('#chart').html('')

	margin = 
		top: 20
		right: 120
		bottom: 30
		left: 120

	width = window.innerWidth - 30 - margin.left - margin.right
	height = 500 - margin.top - margin.bottom

	# time axis
	x = d3.time.scale().range([0, width])
	# stocks axis
	y1 = d3.scale.linear().range([height, 0])
	# sentiment axis
	y2 = d3.scale.linear().range([height, 0])

	x_axis = d3.svg.axis().scale(x).orient 'bottom'
	y1_axis = d3.svg.axis().scale(y1).orient 'left'
	y2_axis = d3.svg.axis().scale(y2).orient 'right'

	# create stocks line
	line_stocks = d3.svg.line()
		.x((d) -> x(d.date))
		.y((d) -> y1(d.close))
	# create sentiment line
	line_sentiment = d3.svg.line()
		.x((d) -> x(d.date))
		.y((d) -> y2(d.sentiment))

	# create SVG container element
	svg = d3.select('#chart').append('svg')
		.attr('width', width + margin.left + margin.right)
		.attr('height', height + margin.top + margin.bottom)
		.append('g').attr('transform', "translate(#{margin.left}, #{margin.top})")

	# get data and show chart
	$.get selector_to_datafile(selected_mailbox), {}, (data) ->
		data = JSON.parse data
		console.debug 'Before', data.length
		data = data.filter (d) -> d.date? and d.close? and d.sentiment? and d.sentiment isnt 'NaN'
		console.debug 'After', data.length
		
		data.sort (a, b) ->
			if a.date > b.date then 1
			else if a.date < b.date then -1
			else 0

		# preprocess data
		data.forEach (d) ->
			d.date = d3.time.format('%Y-%m-%d').parse(d.date)
			d.close = d.close
			d.sentiment = d.sentiment

		# set axes range
		x.domain(d3.extent(data, (d) -> d.date))
		y1.domain([0, d3.max(data, (d) -> d.close)])
		y2.domain([0, d3.max(data, (d) -> d.sentiment)])

		# add stocks line
		svg.append('path').style('stroke', 'steelblue').attr('d', line_stocks(data))
		# add sentiment line
		svg.append('path').style('stroke', 'red').attr('d', line_sentiment(data))

		# create X axis
		svg.append('g').attr('class', 'x axis')
			.attr('transform', "translate(0, #{height})").call(x_axis)

		# create Y1 axis (stocks)
		svg.append('g').attr('class', 'y1 axis').style('fill', 'steelblue').call(y1_axis)
			.append('text').attr('transform', 'rotate(-90)').attr('y', 6)
			.attr('dy', '.71em').style('text-anchor', 'end').text('Stock price')

		# create Y2 axis (sentiment)
		svg.append('g').attr('class', 'y2 axis').attr('transform', "translate(#{width}, 0)")
			.style('fill', 'red').call(y2_axis)
			.append('text').attr('transform', 'rotate(-90)').attr('y', 6)
			.attr('dy', '.71em').style('text-anchor', 'end').text('Sentiment')


show_hist = ->
	$('#corr-chart').html('')

	margin = 
		top: 20
		right: 120
		bottom: 30
		left: 120

	width = window.innerWidth - 30 - margin.left - margin.right
	height = 500 - margin.top - margin.bottom

	svg = d3.select('#corr-chart').append('svg')
		.attr('width', width + margin.left + margin.right)
		.attr('height', height + margin.top + margin.bottom)
		.append('g').attr('transform', "translate(#{margin.left}, #{margin.top})")

	tip = d3.tip()
		.attr('class', 'd3-tip')
		.offset([-10, 0])
		.html((d) -> "<strong>Name:</strong> <span class='d3-tip-number text-yellow'>#{d.name}</span><br />
			<strong>Correlation:</strong> <span class='d3-tip-number text-green'>#{d.y}</span><br />
			<strong>\# points:</strong> <span class='d3-tip-number text-orange'>#{d.p}</span>")

	svg.call(tip)

	$.get 'data/corr_per_user.json', {}, (data) ->

		# preprocess
		corrs = data.stock_sentiment_corr
		names = (k for k, v of corrs)

		corrs_values = (corr for k, corr of corrs)

		hist = ({ name: k, y: v } for k, v of corrs)
		hist = _.zip(hist, _.values(data.points)).map (ary) ->
			h = ary[0]
			h.p = ary[1]
			h


		# set x and y axes
		x = d3.scale.ordinal()
			.domain(hist.map (d) -> d.name)
			.rangeRoundBands([0, width], .2)
		y = d3.scale.linear()
			.domain(d3.extent(hist, (d) -> d.y))
			.range([height, 0]).nice()
		x_axis = d3.svg.axis().scale(x).orient 'bottom'
		y_axis = d3.svg.axis().scale(y).orient 'left'

		color_scale = d3.scale.linear()
			.domain(d3.extent(hist, (d) -> d.y))
			.range([1, 0])
		points_scale = d3.scale.linear()
			.domain(d3.extent(hist, (d) -> d.p))
			.range([.8, .2])

		console.debug hist.length


		bar = svg.selectAll('.bar').data(hist).enter()

		bar.append('rect').attr('class', 'bar')
			.style('fill', (d) -> d3.hsl((if d.y > 0 then 180 else 0), color_scale(d.y), points_scale(d.p)))
			.attr('x', (d) -> x(d.name))
			.attr('y', (d) -> y(Math.max(0, d.y)))
			.attr('width', x.rangeBand())
			.attr('height', (d) -> Math.abs(y(d.y) - y(0)))
			.on('mouseover', tip.show)
			.on('mouseout', tip.hide)
			.on('click', (d) -> 
				mailbox_selector = $('#mailbox-selector')
				mailbox_selector.val(d.name)
				mailbox_selector.trigger 'change'
			)

		svg.append('g')
			.attr('class', 'x axis')
			.attr('transform', "translate(0, #{y(0)})")
			.call(x_axis)

		svg.append('g')
			.attr('class', 'y axis')
			.attr('transform', "translate(#{width}, 0)")
			.call(y_axis)




jQuery(document).ready ($) ->

	mailboxes = null
	selected_mailbox = "FULL"
	mailbox_selector = $('#mailbox-selector')

	$.get 'data/mailboxes.txt', {}, (data) ->
		mailboxes = data.split('\n')
		options = mailboxes.map (mb) ->
			"<option value='#{mb}'>#{mb}</option>"
		mailbox_selector.html(options)


	plot_charts = ->
		show_hist()
		show_chart selected_mailbox

	plot_charts()
	$(window).on 'resize', plot_charts



	mailbox_selector.on 'change', (evt) ->
		selected_mailbox = mailbox_selector.children(':selected').val()
		show_chart selected_mailbox

