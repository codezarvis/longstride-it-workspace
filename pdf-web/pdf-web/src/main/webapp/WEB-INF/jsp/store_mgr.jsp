<%@ page import="org.apache.shiro.subject.support.DefaultSubjectContext" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="utf-8">
    <title>Administrator</title>
  <link rel="stylesheet" type="text/css" href="../css/jquery.dataTables.new.css">
  <link rel="stylesheet" type="text/css" href="../css/dataTables.tableTools.css">
   <link rel="stylesheet" type="text/css" href="../css/editor.dataTables.css">
   <link rel="stylesheet" type="text/css" href="../css/select.dataTables.css">
   
  <link rel="stylesheet" type="text/css" href="../css/shCore.css">
  <link rel="stylesheet" type="text/css" href="../css/tablestyles.css">
  <link rel="stylesheet" href="../css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.notify.css">	
   <link rel="stylesheet" href="../css/new_menu_styles.css">
   <link rel="stylesheet" type="text/css" href="../css/themes/tooltipster-light.css" />
   <link rel="stylesheet" type="text/css" href="../css/tooltipster.css" />
   
   <link href="../css/tips.css" media="screen" rel="stylesheet" type="text/css" />
   
     <script src="../js/jquery.js"></script>
     <script src="../js/jquery-ui.js"></script>
     <script src="../js/jquery.notify.min.js"></script>
  <script type="text/javascript" src="../js/jquery.tooltipster.js"></script>
   
   <script src="../js/tips.js" type="text/javascript"></script> 
<script src="../js/tips-global.js" type="text/javascript"></script>


   <script src="../js/new_menu_script.js"></script>
	
  <script src="../js/moment.js"></script>
	<script type="text/javascript" language="javascript" src="../js/jquery.dataTables.new.js"></script>
	<script type="text/javascript" language="javascript" src="../js/shCore.js"></script>
	<script type="text/javascript" language="javascript" src="../js/dataTables.tableTools.js"></script>
	<script type="text/javascript" language="javascript" src="../js/dataTables.select.js"></script>	
	<script type="text/javascript" language="javascript" src="../js/dataTables.editor.js"></script>
	<script type="text/javascript" language="javascript" src="../js/dataTables.responsive.js"></script>
	<!-- 
	<script type="text/javascript" language="javascript" src="../js/selectize.js"></script>
	<script type="text/javascript" language="javascript" src="../js/selectize.jquery.js"></script>
	 -->
	
  <!--  link rel="stylesheet" href="/resources/demos/style.css" -->
  <script>
	var afliates;
  //var currMonth = 0;
  $(function() {
    $( "#atabs" ).tabs();
    //$( "#refreshDay" ).button();
  });
  
  $(document).ready(function() {
	  
	  
	  afliates=getAllAffiliates();
	  userEditor = new $.fn.dataTable.Editor( {
		  	responsive: true,
	        ajax: "../jersey/admin/user/manage",
	        table: "#user",
	        fields: [ {
                label: "Affiliate:<span style='color:red'>*</span>",
                name: "affiliateId",
                type: "select"
                //ipOpts: getAllAffiliates()
            }, {
                label: "District:<span style='color:red'>*</span>",
                name: "district",
                type: "select"
            },{
            	label: "Store:<span style='color:red'>*</span>",
                name: "storeId",
                type: "select"
            }, {
                label: "User ID:<span style='color:red'>*</span>",
                name: "userId",
                attr: {
                	  "maxlength": 50
                	}
            }, {
                label: "Customer Code:<span style='color:red'>*</span>",
                name: "customerCode",
                attr: {
                	  "maxlength": 50
                	}
            }, {
                label: "Change Password:<span style='color:red'>*</span>",
                name: "changepwd",
                type: "select",
                options: [
                          { label: "No", value: "no" },
                          { label: "Yes",  value: "yes" }
                      ]
            }, {
                label: "Password:<span style='color:red'>*</span>",
                name: "password",
                type: "password",
                attr: {
                	  "maxlength": 20
                	}
            },{
                label: "Ody User ID:<span style='color:red'>*</span>",
                name: "odyUserId",
                attr: {
                	  "maxlength": 50
                	}
            } , {
                label: "Ody Password:<span style='color:red'>*</span>",
                name: "odyPassword",
                type: "password",
                attr: {
                	  "maxlength": 20
                	}
            }, {
                label: "User Role:<span style='color:red'>*</span>",
                name: "role",
                type: "select"
            }, {
                label: "First Name:<span style='color:red'>*</span>",
                name: "firstName",
                attr: {
                	  "maxlength": 100
                	}
            }, {
                label: "Last Name:<span style='color:red'>*</span>",
                name: "lastName",
                attr: {
                	  "maxlength": 100
                	}
            }, {
                label: "IATA:",
                name: "iata"
            }, {
                label: "Email:<span style='color:red'>*</span>",
                name: "email",
                attr: {
                	  "maxlength": 100
                	}
            }, {
                label: "Address:<span style='color:red'>*</span>",
                name: "address",
                attr: {
                	  "maxlength": 500
                	}
            }, {
                label: "City:<span style='color:red'>*</span>",
                name: "city",
                attr: {
                	  "maxlength": 100
                	}
            }, {
                label: "Country:<span style='color:red'>*</span>",
                name: "country",
                attr: {
                	  "maxlength": 100
                	}
            }, {
                label: "State:<span style='color:red'>*</span>",
                name: "state",
                attr: {
                	  "maxlength": 100
                	}
            }, {
                label: "Phone:<span style='color:red'>*</span>",
                name: "phone",
                attr: {
                	  "maxlength": 20
                	}
            }, {
                label: "Extention:",
                name: "extn",
                attr: {
                	  "maxlength": 10
                	}
            }, {
                label: "Fax:",
                name: "fax",
                attr: {
                	  "maxlength": 20
                	}
            }, {
                label: "Zip Code:<span style='color:red'>*</span>",
                name: "zip",
                attr: {
                	  "maxlength": 10
                	}
            }
        ],
	        i18n: {
	            create: {
	                title:  "Create New User"
	            },
	            edit: {
	                title:  "Edit User",
	            },
	            remove: {
	                confirm: {
	                    _: "Are you sure you wish to delete %d Users?",
	                    1: "Are you sure you wish to delete 1 User?"
	                }
	            },
	            error: {
	                system: "Server Error please contact System Admin"
	            }
	        }
	    } );
	  
	  userEditor.on(
				'preSubmit',
				function(e, o, action) {
					if (action !== 'remove') {
						var affiliateId = userEditor.field('affiliateId');
						var district = userEditor.field('district');
						var storeId = userEditor.field('storeId');
						var userId = userEditor.field('userId');
						var password = userEditor.field('password');
						var odyUserId = userEditor.field('odyUserId');
						var odyPassword = userEditor.field('odyPassword');
						var role = userEditor.field('role');
						var firstName = userEditor.field('firstName');
						var lastName = userEditor.field('lastName');
						var email = userEditor.field('email');
						var state = userEditor.field('state');
						var address = userEditor.field('address');
						var city = userEditor.field('city');
						var country = userEditor.field('country');
						var phone = userEditor.field('phone');
						var extn = userEditor.field('extn');
						var fax = userEditor.field('fax');
						var zip = userEditor.field('zip');
						var changepwd = userEditor.field('changepwd');
						var customerCode = userEditor.field('customerCode');
						
							if (!affiliateId.isMultiValue()) {
								if (!affiliateId.val()) {
									affiliateId.error('Affiliate must be given');
								}
							}
							if (!district.isMultiValue()) {
								if (!district.val()) {
									district.error('District must be given');
								}
							}
							if (!storeId.isMultiValue()) {
								if (!storeId.val()) {
									storeId.error('Store must be given');
								}
							}
							if (!userId.isMultiValue()) {
								if (!userId.val()) {
									userId.error('User ID must be given');
								}
							}
							if (!customerCode.isMultiValue()) {
								if (!customerCode.val()) {
									customerCode.error('Customer Code must be given');
								}
							}
							if (!password.isMultiValue()) {
								if (!password.val() && action == 'create') {
									password.error('Password must be given');
								}else if(action == 'edit'){
									if (!changepwd.isMultiValue()) {
										if (changepwd.val() == 'yes' && (!password.val() || password.val()=='') ) {
											password.error('Password must be given');
										}
									}
								}
							}
							if (!odyUserId.isMultiValue()) {
								if (!odyUserId.val()) {
									odyUserId.error('Ody User ID must be given');
								}
							}
							if (!odyPassword.isMultiValue()) {
								if (!odyPassword.val()) {
									odyPassword.error('Ody Password must be given');
								}
							}
							if (!role.isMultiValue()) {
								if (!role.val()) {
									role.error('User Role must be given');
								}
							}
							if (!firstName.isMultiValue()) {
								if (!firstName.val()) {
									firstName.error('First Name must be given');
								}
							}
							if (!lastName.isMultiValue()) {
								if (!lastName.val()) {
									lastName.error('Last Name must be given');
								}
							}
							if (!email.isMultiValue()) {
								if (!email.val()) {
									email.error('Email must be given');
								}
							}
							if (!state.isMultiValue()) {
								if (!state.val()) {
									state.error('State must be given');
								}
							}
							if (!address.isMultiValue()) {
								if (!address.val()) {
									address.error('Address must be given');
								}
							}
							if (!city.isMultiValue()) {
								if (!city.val()) {
									city.error('City must be given');
								}
							}
							if (!country.isMultiValue()) {
								if (!country.val()) {
									country.error('Country must be given');
								}
							}
							if (!phone.isMultiValue()) {
								if (!phone.val()) {
									phone.error('Phone must be given');
								}
							}
							if (!zip.isMultiValue()) {
								if (!zip.val()) {
									zip.error('Zip must be given');
								}
							}
							/*
							if (!extn.isMultiValue()) {
								if (!extn.val()) {
									extn.error('Extn must be given');
								}
							}
							if (!fax.isMultiValue()) {
								if (!fax.val()) {
									fax.error('Fax must be given');
								}
							}
							*/
						if (this.inError()) {
							return false;
						}
					}
				});
	  
	  /*
	  storeEditor.on('onInitCreate', function () {
		  var empty = [];
		  storeEditor.field('district').update(empty);
		} );
	  */
	
	  
	  userEditor.on('onInitEdit', function () {
		  userEditor.field('affiliateId').update(afliates);
		  userEditor.field('district').update( getAllDistricts( userEditor.get('affiliateId') , 'user'));
		  userEditor.field('storeId').update( getAllStores( userEditor.get('district'), userEditor.get('affiliateId') ));
		  userEditor.field('role').update(getAllRoles());
		  userEditor.disable('userId');
		  userEditor.disable('odyUserId');
		  userEditor.disable('odyPassword');
		  userEditor.hide('password');
		  //userEditor.field('changepwd').update('[{"value":"yes","label":"Yes"},{"value":"no","label":"No"}]');
		  userEditor.show('changepwd');
		} );
	  
	  userEditor.on('onInitCreate', function () {
		  userEditor.field('affiliateId').update(afliates);
		  userEditor.field('district').update( getAllDistricts( userEditor.get('affiliateId'), 'user' ));
		  userEditor.field('storeId').update( getAllStores( userEditor.get('district'), userEditor.get('affiliateId') ));
		  userEditor.field('role').update(getAllRoles());
		  userEditor.enable('userId');
		  //userEditor.enable('password');
		  userEditor.enable('odyUserId');
		  userEditor.enable('odyPassword');
		  userEditor.show('password');
		  userEditor.hide('changepwd');
		} );
	  
	  userEditor.dependent( 'changepwd', function ( val ) {
		  return val === 'yes' ?
		            { show: ['password'] } :
		            { hide: ['password'] };
	    } );
	  
	  userEditor.dependent( 'affiliateId', function ( val ) {
		  if(val){
			  var dists = getAllDistricts(val, 'user');
			  userEditor.field('district').update(dists);
			  userEditor.field('storeId').update( getAllStores( userEditor.get('district'), val ));
		  }
	    } );
	  
	  userEditor.dependent( 'district', function ( dist ) {
		  if(dist){
			  var aff = userEditor.get('affiliateId');
			  var stores = getAllStores(dist, aff);
			  userEditor.field('storeId').update(stores);
		  }
	    } );
	  
	 
	  
	  $('#user').DataTable( {
	        dom: 'T<"wrapper"frtip>',
	        ajax: "../jersey/admin/user",
	        columns: [
	            { data: "affiliateName" },
	            { data: "userId" },
	            { data: "customerCode" },
	            { data: "odyUserId" },
	            { data: "firstName" },
	            { data: "lastName" },
	            { data: "role" },
	            { data: "district" },
	            { data: "storeName" },
	            { data: "email" },
	            { data: "phone" },
	            { data: "address" },
	            { data: "city" },
	            { data: "state" },
	            { data: "country" },
	            { data: "zip" }
	        ],
	        tableTools: {
	            sRowSelect: 'os',
	            aButtons: [
	                { sExtends: 'editor_create', editor: userEditor },
	                { sExtends: 'editor_edit',   editor: userEditor },
	                { sExtends: 'editor_remove', editor: userEditor }
	            ]
	        }
	    } );
	} );

  function getAllAffiliates(){
	  var aList;
	  $.ajax({
	        type: 'GET',
	        dataType: 'json',
	        cache: false,
	        url: '../jersey/admin/dropdown/affiliate',
	        async: false,   // forces synchronous call
	        success: function (data) {
	        	aList=data;
	        },
	        error: function (data) { }
	    });
	  return aList;
  }
  
  function getAllDistricts(afl, page){
	  var aList;
	  $.ajax({
	        type: 'GET',
	        dataType: 'json',
	        cache: false,
	        url: '../jersey/admin/dropdown/district?affiliate='+afl+'&page='+page,
	        async: false,   // forces synchronous call
	        success: function (data) {
	        	aList=data;
	        },
	        error: function (data) { }
	    });
	  return aList;
  }
  
  function getAllStores(dist, afl){
	  var aList;
	  $.ajax({
	        type: 'GET',
	        dataType: 'json',
	        cache: false,
	        url: '../jersey/admin/dropdown/store?district='+dist+'&affiliate='+afl,
	        async: false,   // forces synchronous call
	        success: function (data) {
	        	aList=data;
	        },
	        error: function (data) { }
	    });
	  return aList;
  }
  
  function getAllRoles(){
	  var aList;
	  $.ajax({
	        type: 'GET',
	        dataType: 'json',
	        cache: false,
	        url: '../jersey/admin/dropdown/role',
	        async: false,   // forces synchronous call
	        success: function (data) {
	        	aList=data;
	        },
	        error: function (data) { }
	    });
	  return aList;
  }
  </script>
</head>
<body>
<div id='cssmenu'>
<ul>
   <li><a href='../home/services.do'>Services</a></li>
   <li><a href='../reports/bookings.do'>Reports</a></li>
   <shiro:hasAnyRoles name="Admin,Affiliate,Store Manager,District Manager">
   		<li class='active'><a href='#'>Admin</a></li>
   </shiro:hasAnyRoles>
   
</ul>
</div>
<%@include file="header.jsp" %>
<div id="atabs">
  <ul>
    <li><a href="#usertab">User</a></li>
  </ul>
	
	<div id="usertab">
	<div class="container">
		<section>
			<table id="user" class="display" cellspacing="0" width="100%" style="width:100% !important">
				<thead>
					<tr>
						<th>Affiliate</th>
						<th>User ID</th>
						<th>Customer Code</th>
						<th>Ody UserID</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Role</th>
						<th>District</th>
						<th>Store Name</th>
						<th>Email</th>
						<th>Phone</th>
						<th>Address</th>
						<th>City</th>
						<th>State</th>
						<th>Country</th>
						<th>Zip</th>
					</tr>
				</thead>
			</table>
		</section>
	</div>
	</div>
</div>
 
 
</body>
</html>
