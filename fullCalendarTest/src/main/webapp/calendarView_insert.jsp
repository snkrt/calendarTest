<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FullCalendarTest</title>

<!-- FullCalendar -->
	<link rel='stylesheet' href='css/fullcalendar.css' />
	<link href='css/fullcalendar.print.css' rel='stylesheet' media='print' />
	<script src='js/jquery.min.js'></script>
	<script src='js/moment.min.js'></script>
	<script src='js/fullcalendar.js'></script>
	<script src='js/jquery-ui.custom.min.js'></script>
	<script src='js/fullcalendar.min.js'></script>
	
<!-- fancybox -->
	<link rel="stylesheet" type="text/css" href="css/jquery.fancybox.css"> 	
	<script src='js/jquery.fancybox.pack.js'></script> 


<script>
$(document).ready(function() {
	
	$('#external-events .fc-event').each(function() {
		// store data so the calendar knows to render an event upon drop
		$(this).data('event', {
			title: $.trim($(this).text()), // use the element's text as the event title
			stick: true // maintain when user navigates (see docs on the renderEvent method)
		});

		// make the event draggable using jQuery UI
		$(this).draggable({
			zIndex: 999,
			revert: true,      // will cause the event to go back to its
			revertDuration: 0  //  original position after the drag
		});

	});

	$('#my-button').click(function() {
	    var moment = $('#calendar').fullCalendar('getDate');
	    alert("The current date of the calendar is " + moment.format());
	});
	
	$('#calendar').fullCalendar({
        theme: false,
        editable: false,
        events:"CalendarJsonServlet", /* AddEditEvent*/
       		
        	header: {
    			left : 'prev,next today',
    			center :'title',
    			right :'month,agendaWeek,agendaDay',
    			prev: 'circle-triangle-w', 
    			next: 'circle-triangle-e'
    			},
    			dayClick: function(start, end, jsEvent, view) {
		    				start=moment(start).format('YYYY-MM-DD');
		    				end=moment(start).format('YYYY-MM-DD')
    						console.log(start);
		    				console.log(end);
     			            var selDate =$.fullCalendar.moment().format(start,"YYYY-MM-DD");//格式化日期 
//    			            var selDate =$.fullCalendar.moment(date); 
     			            console.log(selDate);
    			            $.fancybox({//调用fancybox弹出层 
    			                'type':'ajax', 
    			                'href':'Event.jsp?action=add&start='+start+'&end='+end //	
    			            });  
    			            
    			        },
    			
    			eventClick: function(calEvent, jsEvent, view) {
    				console.log(calEvent.id);
    						$.fancybox({
    							'type':'ajax',
    							'href':'Event.jsp?action=edit&id='+calEvent.id
    						});
    			 		},
    				
    			editable : true,
    			
    			droppable : true, // this allows things to be dropped onto the calendar
    			
    			drop: function() {
    				// is the "remove after drop" checkbox checked?
    				if ($('#drop-remove').is(':checked')) {
    					// if so, remove the element from the "Draggable Events" list
    					$(this).remove();
    				}
    			},
    			selectable: true,
    			selectHelper: true,
    	/* 		select: function(start, end) {
    				var title = prompt('Event Title:');
    				var eventData;
    				if (title) {
    					eventData = {
    						title: title,
    						start: start,
    						end: end,
    						overlap: false,
    					};
    					$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
    				}
    				$('#calendar').fullCalendar('unselect');
    			} ,
*/    			editable: true,
    			
	    });
    
	
	
	/*  var calendar = $('#calendar').fullCalendar('getCalendar');
	var m = calendar.moment();
	//alert(m);
	moment(calendar, "yyyy-MM-dd'T'HH:mm:ss.SSSZ"); 
	
	 moment.createFromInputFallback = function(config) {
		  // unreliable string magic, or
		  config._d = new Date(config._i);
		};  */
	});
</script>
<style>

	body {
		margin-top: 40px;
		text-align: center;
		font-size: 14px;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
	}
		
	#wrap {
		width: 1100px;
		margin: 0 auto;
	}
		
	#external-events {
		float: left;
		width: 150px;
		padding: 0 10px;
		border: 1px solid #ccc;
		background: #eee;
		text-align: left;
	}
		
	#external-events h4 {
		font-size: 16px;
		margin-top: 0;
		padding-top: 1em;
	}
		
	#external-events .fc-event {
		margin: 10px 0;
		cursor: pointer;
	}
		
	#external-events p {
		margin: 1.5em 0;
		font-size: 11px;
		color: #666;
	}
		
	#external-events p input {
		margin: 0;
		vertical-align: middle;
	}

	#calendar {
		float: auto;
		width: 1100px;
	}

</style>




</head>
	<body>
	
	<div id='top'>	

		<div class='clear'></div>

	</div>
	
	<div id='wrap'>

		<!-- <div id='external-events'>
			<h4>Draggable Events</h4>
			<div class='fc-event'>My Event 1</div>
			<div class='fc-event'>My Event 2</div>
			<div class='fc-event'>My Event 3</div>
			<div class='fc-event'>My Event 4</div>
			<div class='fc-event'>My Event 5</div>
			<p>
				<input type='checkbox' id='drop-remove' />
				<label for='drop-remove'>remove after drop</label>
			</p>
		</div> -->

		<div id='calendar'></div>

		<div style='clear:both'></div>
		<button id='my-button'>check</button>
	</div>

	</body>
</html>