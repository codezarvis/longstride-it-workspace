<%@ page import="org.apache.shiro.subject.support.DefaultSubjectContext" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Airline Booking Details</title>
  <link rel="stylesheet" type="text/css" href="../css/jquery.dataTables.new.css">
  <link rel="stylesheet" type="text/css" href="../css/dataTables.tableTools.css">
  <link rel="stylesheet" type="text/css" href="../css/shCore.css">
  <link rel="stylesheet" type="text/css" href="../css/tablestyles.css">
  <link rel="stylesheet" href="../css/jquery-ui.css">
  <link rel="stylesheet" href="../css/new_menu_styles.css">
  <link rel="stylesheet" type="text/css" href="../css/jquery.notify.css">
  <script src="../js/jquery.js"></script>
   <script src="../js/new_menu_script.js"></script>
   <script src="../js/jquery.notify.min.js"></script>
  <script src="../js/moment.js"></script>
  <script src="../js/jquery-ui.js"></script>
	<script type="text/javascript" language="javascript" src="../js/jquery.dataTables.new.js"></script>
	<script type="text/javascript" language="javascript" src="../js/shCore.js"></script>
	<script type="text/javascript" language="javascript" src="../js/dataTables.tableTools.js"></script>
	
  <!--  link rel="stylesheet" href="/resources/demos/style.css" -->
  <script>
  var monthDate = moment();
  var maxMonths = 12;
  //var currMonth = 0;
  $(function() {
    $( "#rtabs" ).tabs();
    //$( "#refreshDay" ).button();
    $( "#refreshDay" ).button({
        text: false,
        icons: {
           primary: "ui-icon-refresh"
        }
     });
    
    $( "#refreshMonth" ).button({
        text: false,
        icons: {
           primary: "ui-icon-refresh"
        }
     });
    
    $( "#curMonth" ).button({
        text: false,
        icons: {
        	primary: "ui-icon-calendar"
        }
     });
    
    $( "#prvMonth" ).button({
        text: false,
        icons: {
           primary: "ui-icon-triangle-1-w"
        }
     });
    
    $( "#nextMonth" ).button({
        text: false,
        disabled: true,
        icons: {
           primary: "ui-icon-triangle-1-e"
        }
     });

    var date = moment();
    $("#daytexts").text(date.format("LL")+" -- Bookings");
    $("#monthtexts").text(date.format('MMMM, YYYY')+" -- Bookings");
  });
  
  $(document).ready(function() {
	  
		if ( $.fn.dataTable.isDataTable( '#day' ) ) {
		    table = $('#day').DataTable();
		    table.destroy();
		}
		
		$('#day').dataTable( {
			"sDom": 'T<"clear">lfrtip',
			"oTableTools": {
				"sSwfPath": "../swf/copy_csv_xls_pdf.swf",
	            "aButtons": [
	                "copy",
	                {
	                	"sExtends": "print",
	                    "mColumns": [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
	                },
	                {
	                	"sExtends": "xls",
	                    "mColumns": [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
	                },
	                {
	                	"sExtends": "pdf",
	                    "mColumns": [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
	                }
	            ]
	        },
			"ordering": false,
			"lengthMenu": [ [10, 25, 50, 100, -1], [10, 25, 50, 100, "All"] ],
			"ajax": '../jersey/api/bookingdetails/array/day?query=today',
			"columnDefs": [ 
				{
					"render": function ( data, type, row ) {
						return '<a href="../jersey/api/bookingdetails/getpdf?id='+data+'" target="_blank">View Itinerary</a>';
						//return '<a href="'+data+'">'+data +'</a>';
					},
					"targets": 10
				}
			]
		} );
		
		if ( $.fn.dataTable.isDataTable( '#month' ) ) {
		    table = $('#month').DataTable();
		    table.destroy();
		}
		
		$('#month').dataTable( {
			"sDom": 'T<"clear">lfrtip',
			"oTableTools": {
				"sSwfPath": "../swf/copy_csv_xls_pdf.swf",
	            "aButtons": [
	                "copy",
	                {
	                	"sExtends": "print",
	                    "mColumns": [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
	                },
	                {
	                	"sExtends": "xls",
	                    "mColumns": [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
	                },
	                {
	                	"sExtends": "pdf",
	                    "mColumns": [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
	                }
	            ]
	        },
			"ordering": false,
			"lengthMenu": [ [10, 25, 50, 100, -1], [10, 25, 50, 100, "All"] ],
			"ajax": '../jersey/api/bookingdetails/array/month?query=thismonth',
			"columnDefs": [ 
				{
					"render": function ( data, type, row ) {
						return '<a href="../jersey/api/bookingdetails/getpdf?id='+data+'" target="_blank">View Itinerary</a>';
						//return '<a href="'+data+'">'+data +'</a>';
					},
					"targets": 10
				}
			]
		} );
		
		if ( $.fn.dataTable.isDataTable( '#search' ) ) {
		    table = $('#search').DataTable();
		    //table.destroy();
		}
		else {
		$('#search').dataTable( {
			"sDom": 'T<"clear">lfrtip',
			"oTableTools": {
				"sSwfPath": "../swf/copy_csv_xls_pdf.swf",
	            "aButtons": [
	                "copy",
	                {
	                	"sExtends": "print",
	                    "mColumns": [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
	                },
	                {
	                	"sExtends": "xls",
	                    "mColumns": [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
	                },
	                {
	                	"sExtends": "pdf",
	                    "mColumns": [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
	                }
	            ]
	        },
			"ordering": false,
			"lengthMenu": [ [10, 25, 50, 100, -1], [10, 25, 50, 100, "All"] ],
			"ajax": '../arrays.txt',
			"columnDefs": [ 
				{
					"render": function ( data, type, row ) {
						return '<a href="../jersey/api/bookingdetails/getpdf?id='+data+'" target="_blank">View Itinerary</a>';
						//return '<a href="'+data+'">'+data +'</a>';
					},
					"targets": 10
				}
			]
		} );
	}
	} );
  
  function IsNumeric(num){
	  return (num ==0 || num > 0);
	}

  
  $(function(){
		$("#submitBtn").click(function() {
		var searchKey = $('#reference').val();
		//alert(searchKey)
		var isValid=true;
		var dropDownItem = $('#dropDownList').val();
		//alert(dropDownItem)
		if( (searchKey == "keyword" || searchKey == null) && (dropDownItem == "SELECT" || dropDownItem == null)){
			document.getElementById("error").innerHTML = "Select a Value from DropDown and enter a Search Keyword";
			isValid=false;
		}
		else if(searchKey == "keyword" || searchKey == null){
			document.getElementById("error").innerHTML = "Enter a Keyword";
			isValid=false;
		}else if(dropDownItem == "SELECT" || dropDownItem == null){
			document.getElementById("error").innerHTML = "Select a Value from DropDown";
			isValid=false;
		}else if(dropDownItem == "amount"){
			console.log(searchKey.indexOf("-"));
			if(searchKey.indexOf("-") >= 0){
				//console.log("has - ");
				var vals = searchKey.split("-");
				if(vals.length>2){
					document.getElementById("error").innerHTML = "Invalid Amount Range, Ex:200-300";
					isValid=false;
				}else if(!IsNumeric(vals[0]) || !IsNumeric(vals[1])){
					document.getElementById("error").innerHTML = "Invalid Amount Range, Ex:200-300";
					isValid=false;
				}else if((vals[0]-0) >= (vals[1]-0)){
					document.getElementById("error").innerHTML = "Invalid Amount Range, Ex:200-300";
					isValid=false;
				}
			}else {
				document.getElementById("error").innerHTML = "Invalid Amount Range, Ex:200-300";
				isValid=false;
			}
		}
		
		if(isValid) {
			document.getElementById("error").innerHTML="";
			var url="../jersey/api/bookingdetails/array/search?type="+dropDownItem+"&query="+searchKey;
			if ( $.fn.dataTable.isDataTable( '#search' ) ) {
			    table = $('#search').DataTable();
			    table.destroy();
			}
			
			$('#search').dataTable( {
				"sDom": 'T<"clear">lfrtip',
				"oTableTools": {
					"sSwfPath": "../swf/copy_csv_xls_pdf.swf",
		            "aButtons": [
		                "copy",
		                {
		                	"sExtends": "print",
		                	//"sTitle": "HealthForms"
		                    "mColumns": [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
		                },
		                {
		                	"sExtends": "xls",
		                    "mColumns": [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
		                },
		                {
		                	"sExtends": "pdf",
		                    "mColumns": [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
		                }
		            ]
		        },
				"ordering": false,
				"lengthMenu": [ [10, 25, 50, 100, -1], [10, 25, 50, 100, "All"] ],
				"ajax": url,
				"columnDefs": [ 
					{
						"render": function ( data, type, row ) {
							return '<a href="../jersey/api/bookingdetails/getpdf?id='+data+'" target="_blank">View Itinerary</a>';
						},
						"targets": 10
					}
				]
			} );
		}
		});
		$("#daydropDownList").change(function() {
			//alert($('#daydropDownList').val()); day?query=today
			var qry=$('#daydropDownList').val();
			var url="../jersey/api/bookingdetails/array/day?query="+qry;
			if ( $.fn.dataTable.isDataTable( '#day' ) ) {
			    table = $('#day').DataTable();
			    table.destroy();
			}
			var date1 = moment();
			var date2 = moment();
			var text=date1.format("LL")
			if(qry=="last-2"){
				$("#daytexts").text(date2.subtract(2, 'days').format("LL") +" To "+date1.format("LL")+" -- Bookings");
			}
			else if(qry=="last-5"){
				$("#daytexts").text(date2.subtract(5, 'days').format("LL") +" To "+date1.format("LL")+" -- Bookings");
			}
			else if(qry=="last-10"){
				$("#daytexts").text(date2.subtract(10, 'days').format("LL") +" To "+date1.format("LL")+" -- Bookings");
			}
			else if(qry=="last-15"){
				$("#daytexts").text(date2.subtract(15, 'days').format("LL") +" To "+date1.format("LL")+" -- Bookings");
			}
			else if(qry=="last-20"){
				$("#daytexts").text(date2.subtract(20, 'days').format("LL") +" To "+date1.format("LL")+" -- Bookings");
			}
			else if(qry=="last-25"){
				$("#daytexts").text(date2.subtract(25, 'days').format("LL") +" To "+date1.format("LL")+" -- Bookings");
			}
			else{
				$("#daytexts").text(date1.format("LL")+" -- Bookings");
			}
		    
			$('#day').dataTable( {
				"sDom": 'T<"clear">lfrtip',
				"oTableTools": {
					"sSwfPath": "../swf/copy_csv_xls_pdf.swf",
		            "aButtons": [
		                "copy",
		                {
		                	"sExtends": "print",
		                	//"sTitle": "HealthForms"
		                    "mColumns": [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
		                },
		                {
		                	"sExtends": "xls",
		                    "mColumns": [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
		                },
		                {
		                	"sExtends": "pdf",
		                    "mColumns": [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
		                }
		            ]
		        },
				"ordering": false,
				"lengthMenu": [ [10, 25, 50, 100, -1], [10, 25, 50, 100, "All"] ],
				"ajax": url,
				"columnDefs": [ 
					{
						"render": function ( data, type, row ) {
							return '<a href="../jersey/api/bookingdetails/getpdf?id='+data+'" target="_blank">View Itinerary</a>';
						},
						"targets": 10
					}
				]
			} );
		});
		$("#dropDownList").change(function() {
			var dropDownItem = $('#dropDownList').val();
			$("#reference").datepicker("destroy");
			if(dropDownItem=="travelDate"){
				$( "#reference" ).datepicker({
					   showButtonPanel: true,
		               dateFormat:"mm/dd/yy"
		            }).focus(function() {
		            	$(".ui-datepicker-calendar").show();
		            	$("#ui-datepicker-div").position({
		                    my: "center top",
		                    at: "center bottom",
		                    of: $(this)
		                });
		        	});
			}else if(dropDownItem=="bookingDate"){
				$( "#reference" ).datepicker({
					    showButtonPanel: true,
					    changeMonth: true,
						changeYear: true,
		                dateFormat:"MM yy",
		                onClose: function(dateText, inst) {  
		                    var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val(); 
		                    var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val(); 
		                    $(this).val($.datepicker.formatDate('mm/yy', new Date(year, month, 1)));
		                }
		            }).focus(function() {
		            	$(".ui-datepicker-calendar").hide();
		                $("#ui-datepicker-div").position({
		                    my: "center top",
		                    at: "center bottom",
		                    of: $(this)
		                });
		        	});
			}
		});
		$("#refreshDay").click(function() {
			var dayTable = $('#day').DataTable();
			dayTable.ajax.reload();
		});
		$("#refreshMonth").click(function() {
			var monthTable = $('#month').DataTable();
			monthTable.ajax.reload();
		});
		
		$("#prvMonth").click(function() {
			//alert(maxMonths);
			$("#monthtexts").text(monthDate.subtract(1, 'months').format('MMMM, YYYY')+" -- Bookings");
			if(maxMonths > 0){
				maxMonths--;
			}
			if(maxMonths==0){
				$( "#prvMonth" ).button("disable");
			}else{
				$( "#nextMonth" ).button("enable");
			}
			getMonthData(monthDate.format('MM/YYYY'));
		});
		$("#nextMonth").click(function() {
			$("#monthtexts").text(monthDate.add(1, 'months').format('MMMM, YYYY')+" -- Bookings");
			if(maxMonths<12){
				maxMonths++;
			}
			if(maxMonths==12){
				$( "#nextMonth" ).button("disable");
			}else{
				$( "#prvMonth" ).button("enable");
			}
			getMonthData(monthDate.format('MM/YYYY'));
		});
		$("#curMonth").click(function() {
			if(maxMonths<12){
				maxMonths=12;
				monthDate = moment();
				$( "#nextMonth" ).button("disable");
				$( "#prvMonth" ).button("enable");
				$("#monthtexts").text(monthDate.format('MMMM, YYYY')+" -- Bookings");
				getMonthData(monthDate.format('MM/YYYY'));
			}
		});
	} );
  
  function getMonthData(qry){
	  var url="../jersey/api/bookingdetails/array/month?query="+qry;
	  if ( $.fn.dataTable.isDataTable( '#month' ) ) {
		    table = $('#month').DataTable();
		    table.destroy();
		}
		$('#month').dataTable( {
			"sDom": 'T<"clear">lfrtip',
			"oTableTools": {
				"sSwfPath": "../swf/copy_csv_xls_pdf.swf",
	            "aButtons": [
	                "copy",
	                {
	                	"sExtends": "print",
	                	//"sTitle": "HealthForms"
	                    "mColumns": [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
	                },
	                {
	                	"sExtends": "xls",
	                    "mColumns": [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
	                },
	                {
	                	"sExtends": "pdf",
	                    "mColumns": [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
	                }
	            ]
	        },
			"ordering": false,
			"lengthMenu": [ [10, 25, 50, 100, -1], [10, 25, 50, 100, "All"] ],
			"ajax": url,
			"columnDefs": [ 
				{
					"render": function ( data, type, row ) {
						return '<a href="../jersey/api/bookingdetails/getpdf?id='+data+'" target="_blank">View Itinerary</a>';
					},
					"targets": 10
				}
			]
		} );
	}
  </script>
</head>
<body>
<!--  
<div id='cssmenu'>
<ul>
   <li><a href='#'>Home</a></li>
   <li class='active'><a href='#'>Reports</a></li>
   <li><a href='../admin/manage.htm'>Admin</a></li>
</ul>
</div>
-->
<div id='cssmenu'>
<ul>
   <li><a href='../home/services.htm'>Services</a></li>
   <li class='active'><a href='#'>Reports</a></li>
   <shiro:hasAnyRoles name="Admin,Affiliate,Store Manager,District Manager">
   		<li><a href='../admin/manage.htm'>Admin</a></li>
   </shiro:hasAnyRoles>
</ul>
</div>
<%@include file="header.jsp" %>
 <%
 Date todaysDate = new Date();
 DateFormat df1 = new SimpleDateFormat("E, MMM dd yyyy");
 DateFormat df2 = new SimpleDateFormat("yyyy");
 String today=df1.format(todaysDate);
 String curryear=df2.format(todaysDate);
 String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
	        "August", "September", "October", "November", "December" };
 Calendar c = Calendar.getInstance();
 String currMonth = monthName[c.get(Calendar.MONTH)];
 //System.out.println(c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH ) );
 %>
<div id="rtabs">
  <ul>
    <li><a href="#daytab">Today</a></li>
    <li><a href="#monthtab">Month</a></li>
    <li><a href="#searchtab">Custom Search</a></li>
  </ul>

	<div class="container" id="monthtab">
	<table id="monthrf" class="display" cellspacing="0" width="100%">
		<tr>
		<td align="left" width="20">
			<button id="curMonth">Current Month</button>
		</td>
		<td align="right" width="15%">
			<button id="prvMonth">Previous Month</button>
		</td>
		<td align="center" width="20%"> 
			<div id="monthtexts" style="display:inline; white-space:nowrap; color: brown;font-size: 17; font-weight: bold;"> </div>
    	</td>
		<td align="left" width="25%"><button id="nextMonth">Next Month</button></td>
		<td align="right" width="20%">
			<!--  input id="refreshMonth" type="button" value="Refresh" / -->
			<button id="refreshMonth">Refresh</button>
		</td>
		</tr>
	</table>
		<section>
			<table id="month" class="display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>Cashier ID</th>
						<th>Cashier</th>
						<th>PNR</th>
						<th>Booking Date</th>
						<th>Passengers</th>
						<th>Travel Date</th>
						<th>From</th>
						<th>To</th>
						<th>Airline</th>
						<th>Amount</th>
						<th>Itinerary</th>
					</tr>
				</thead>
			</table>
		</section>
	</div>
	
	<div class="container" id="daytab">
	<table id="dayrf" class="display" cellspacing="0" width="100%">
		<tr>
		<td align="left" width="25%">
			<select id="daydropDownList">
				<option value="today">Today</option>
				<option value="last-2">Last 2 Days</option>
				<option value="last-5">Last 5 Days</option>
				<option value="last-10">Last 10 Days</option>
				<option value="last-15">Last 15 Days</option>
				<option value="last-20">Last 20 Days</option>
				<option value="last-25">Last 25 Days</option>
			</select>
		</td>
		<td align="center" width="35%">
			<div id="daytexts" style="display:inline; white-space:nowrap; color: brown;font-size: 17; font-weight: bold;"> 
    		</div>
		</td>
		<td align="right" width="40%">
			<!--  input id="refreshDay" type="button" value="Refresh" / -->
			<button id="refreshDay">Refresh</button>
		</td>
		</tr>
	</table>
		<section>
			<table id="day" class="display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>Cashier ID</th>
						<th>Cashier</th>
						<th>PNR</th>
						<th>Booking Date</th>
						<th>Passengers</th>
						<th>Travel Date</th>
						<th>From</th>
						<th>To</th>
						<th>Airline</th>
						<th>Amount</th>
						<th>Itinerary</th>
					</tr>
				</thead>
			</table>
		</section>
	</div>
	
	<div id="searchtab">
	<div class="searchArea">
		<table id="searchTable" align="left">
				<tr>
					<td>
						<select id="dropDownList">
						<option>SELECT</option>
						<option value="userId">Cashier ID</option>
						<option value="pnr">PNR</option>
						<option value="travellerName">Passenger Name</option>
						<option value="travelDate">Travel Date</option>
						<option value="bookingDate">Booking Month</option>
						<option value="fromLocation">From</option>
						<option value="toLocation">To</option>
						<option value="airline">Airline</option>
						<option value="amount">Amount</option>
						</select>
					</td>
					<td>
						<input id="reference" type="text"  value="keyword"  
							onblur="if (this.value == '') {this.value = 'keyword';}"
							onfocus="if (this.value == 'keyword') {this.value = '';}" 	/>
					</td>
					<td>
						<input id="submitBtn" type="button" value="SEARCH" />
					</td>
					<td>
						<p id="error" style="color:red"> </p>
					</td>
				</tr>
		</table>
		</div>
		<br/>
	<div class="container">
		<section>
			<table id="search" class="display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>Cashier ID</th>
						<th>Cashier</th>
						<th>PNR</th>
						<th>Booking Date</th>
						<th>Passengers</th>
						<th>Travel Date</th>
						<th>From</th>
						<th>To</th>
						<th>Airline</th>
						<th>Amount</th>
						<th>Itinerary</th>
					</tr>
				</thead>
			</table>
		</section>
	</div>
	</div>
</div>
 
 
</body>
</html>
