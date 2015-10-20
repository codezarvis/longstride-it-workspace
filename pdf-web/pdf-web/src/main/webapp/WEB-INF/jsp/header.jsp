<%@ page import="org.apache.shiro.subject.support.DefaultSubjectContext" %>
<%@ page import="com.casa.booking.dao.UserDAO" %>
<%@ page import="com.casa.booking.model.User" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Casa Header</title>


<script type="text/javascript">
//var dropDownItem;
	$(document).ready(function() {
		checkDefaultPwd();
		$("#btnShowModal").click(showDialog);

		//variable to reference window
		$myWindow = jQuery('#MyDialog');
		/*
		$("#dropDownList").change(function (event) {
			dropDownItem = $(this).val();
        });
		*/
		//instantiate the dialog
		$myWindow.dialog({
			height : 300,
			width : 450,
			modal : true,
			//position : 'center',
			autoOpen : false,
			title : 'Preferences',
			/*overlay : {
				opacity : 1,
				background : 'red'
			},*/
			buttons: [
						{
						    text: "Save",
						    "class": 'saveButtonClass',
						    click: function() {
						        // Save code here
						        var option = $('#optionList').val();
						        //var dropDownItem = $("select[id*=dropDownList]").val()
						        //var e = document.getElementById("dropDownList");
								//var strUser = e.options[e.selectedIndex].value;
								//alert(option)
						        //$("#uploadForm").submit();
								var formData = $("#uploadForm").serialize();
						        $.ajax({
						            url: '../jersey/api/bookingdetails/upload',
						            type: 'POST',
						            data: formData,
						            async: false,
						            cache: false,
						            contentType: false,
						            processData: false,
						            success: function () {
						                alert('Form Submitted!');
						            },
						            error: function(){
						                alert("error in ajax form submission");
						            }
						        });
						        //$myWindow.dialog("close");
						    }
						},
			          	{
			              	text: "Cancel",
			              	"class": 'cancelButtonClass',
			              	click: function() {
			                  // Cancel code here
			              		$myWindow.dialog("close");
			             	}
			          	}
			      	]

		});

	});
	
	$(document).ready(function() {
		$('#changepwdForm').trigger("reset");
		$("#btnChangePwd").click(showDialog);
		$myWindow = jQuery('#changepwd');
		//instantiate the dialog
		$myWindow.dialog({
			height : 300,
			width : 420,
			modal : true,
			//position : 'center',
			autoOpen : false,
			title : 'Change Password',
			/*overlay : {
				opacity : 1,
				background : 'red'
			},*/
			buttons: [
						{
						    text: "Update",
						    "class": 'saveButtonClass',
						    click: function() {
								var curpwd;
								var newpwd;
								var cnewpwd;
								var valid=true;
								var s_data = $("#changepwdForm").serializeArray();
							    //transform into simple data/value object
							    for(var i = 0; i<s_data.length; i++){
							        var record = s_data[i];
							        if(record.name=='currpwd')
							        	curpwd=record.value;
							        else if(record.name=='newpwd')
								    	newpwd=record.value;
							        else if(record.name=='confnewpwd')
								    	 cnewpwd=record.value;
							    }
							    if (!curpwd || curpwd == "") {
                                    //alert("password");
							    	$("#errors").text( "Current password should not be null" );
							    	$("#errors").show();
							    	valid=false;
                              	}
							    
							    if (!newpwd || newpwd == "") {
                                  //alert("password");
							    	$("#errors").text( "New password should not be null" );
							    	$("#errors").show();
							    	valid=false;
                            	}
							    
							    else if (newpwd.length<6) {
                                  //alert("password");
							    	$("#errors").text( "New password length should  be minimum 6 characters" );
							    	$("#errors").show();
							    	valid=false;
                            	}
							    	
							    if (cnewpwd == "") {
                                  //alert("password");
							    	$("#errors").text( "Confirm new password should not be null" );
							    	$("#errors").show();
							    	valid=false;
                            	}
							    else if (cnewpwd.length<6) {
                                  //alert("password");
							    	$("#errors").text( "Confirm new password length should  be minimum 6 characters" );
							    	$("#errors").show();
							    	valid=false;
                            	}
							    if(newpwd && newpwd.length>5 && cnewpwd && cnewpwd.length>5){
								    if(newpwd!=cnewpwd){
								    	$("#errors").text( "New Password and Confirm Password didn't match" );
								    	$("#errors").show();
								    	valid=false;
								    }
							    }
							    //Add Rest of the Client side Validations
							    
							    var formData = $("#changepwdForm").serialize();
							    if(valid){
							        $.ajax({
							            url: '../jersey/admin/changepwd',
							            type: 'POST',
							            data: formData,
							            async: false,
							            cache: false,
							            contentType: false,
							            processData: false,
							            success: function (data) {
							            	if(data=='success'){
							            		$myWindow.dialog("close");
							            		notify({
							                        type: "success", //alert | success | error | warning | info
							                        title: "Success",
							    					position: {
							                            x: "right", //right | left | center
							                            y: "bottom" //top | bottom | center
							                        },
							                        autoHide: true, //true | false
							                        delay: 10000,
							                        icon: '<img src="../images/paper_plane.png" />',
							                        message: "Password has been updated, please logout and login"
							                    });
							            	}else{
							            		//Show Server Side Validation Error
							            		$("#errors").text( data );
							    				$("#errors").show();
							            	}
							            },
							            error: function(error){
							                //Show Server Side Error
							            	$("#errors").text( "Server Exception, please try after sometime" );
							    			$("#errors").show();
							            }
							        });
							    }
						        //$myWindow.dialog("close");
						    }
						},
			          	{
			              	text: "Cancel",
			              	"class": 'cancelButtonClass',
			              	click: function() {
			                  // Cancel code here
			              		$myWindow.dialog("close");
			             	}
			          	}
			      	]

		});

	});
	
	//function to show dialog   
	var showDialog = function() {
		//if the contents have been hidden with css, you need this
		//$('#dropDownList').val('Affiliate');
		$("#errors").hide();
		$myWindow.show();
		//open the dialog
		$myWindow.dialog("open");
	}

	//function to close dialog, probably called by a button in the dialog
	var closeDialog = function() {
		$myWindow.dialog("close");
	}
	
	$(function() {
	    $( "#datepicker" ).datepicker();
	  });
	
	function checkDefaultPwd(){
		$.ajax({
	        type: 'GET',
	        cache: true,
	        url: '../jersey/admin/isdefaultpwd',
	        async: true,
	        success: function (data) {
	        	if(data && data=='true'){
	        		notify({
                        type: "warning", //alert | success | error | warning | info
                        title: "Alert",
    					position: {
                            x: "right", //right | left | center
                            y: "top" //top | bottom | center
                        },
                        autoHide: true, //true | false
                        delay: 10000,
                        icon: '<img src="../images/paper_plane.png" />',
                        message: "Please Change your Password Now"
                    });
	        	}
	        },
	        error: function (data) { }
	    });
	}
</script>
</head>
<body>

 <%
    String user = request.getSession(false).getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY).toString().toLowerCase();
    if(request.getSession(false).getAttribute("USER_DETAILS")==null){
    	UserDAO dao = new UserDAO();
    	User userObj = dao.getUserById(user);
    	request.getSession(false).setAttribute("USER_DETAILS", userObj);
    	String name=null;
    	if(userObj.getFirstName()!=null){
    		name=userObj.getFirstName();
    	}
    	if(userObj.getLastName()!=null){
    		if(name!=null)
    			name=name+" "+userObj.getLastName();
    		else
    			name=userObj.getLastName();
    	}
    	request.getSession(false).setAttribute("username", name);
    	request.getSession(false).setAttribute("company", userObj.getStore().getCompany().getCompanyName());
    	request.getSession(false).setAttribute("companyLogo", userObj.getStore().getCompany().getLogo());
    	request.getSession(false).setAttribute("companyId", new Long(userObj.getStore().getCompany().getCompanyId()));
    }
    %>
    
<table style="width:100%">
<tr>
<td align="left" width="40%">
<!--  -->
<%if(request.getSession(false).getAttribute("companyLogo")!=null && !"".equals(request.getSession(false).getAttribute("companyLogo"))) {%>
	<div id="image" style="display:inline;">
        <img src="../jersey/admin/getlogo?size=actual&logo=<%= request.getSession(false).getAttribute("companyLogo") %>"/>
    </div>
<%}%>
</td>
<td align="left" width="30%">
<div style="display:inline; text-align: left; color: #8A2BE2;font-size: 25; !important; font-weight: bold">Welcome  <%=request.getSession(false).getAttribute("username")%> </div>
</td>
<td align="right" width="25%">
    <div style="display:inline; text-align: right;">
    <a href="../logout">Log Out</a>
    </div>
    <shiro:hasRole name="Affiliate">
    	<div style="text-align: right;">
   	 		You are Logged in as an Affiliate
   		</div>
     </shiro:hasRole>
     <shiro:hasRole name="Store Manager">
    	<div style="text-align: right;">
   	 		You are Logged in as a Store Manager
   		</div>
     </shiro:hasRole>
     <shiro:hasRole name="District Manager">
    	<div style="text-align: right;">
   	 		You are Logged in as a District Manager
   		</div>
     </shiro:hasRole>
     <shiro:hasRole name="Cashier">
    	<div style="text-align: right;">
   	 		You are Logged in as a Cashier
   		</div>
     </shiro:hasRole>
     <shiro:hasRole name="Admin">
    	<div style="text-align: right;">
   	 		You are Logged in as an Administrator
   		</div>
     </shiro:hasRole>
    <!-- 
    <shiro:hasRole name="AFFILIATE">
    <div style="display: inline; text-align: right;">
			<input type="image" name="btnShowModal" id="btnShowModal"
				style="height: 22px; width: 22px;" src="../images/settings.png" />
	</div>
	</shiro:hasRole>
	<shiro:hasRole name="STORE_MANAGER">
    <div style="display: inline; text-align: right;">
			<input type="image" name="btnShowModal" id="btnShowModal"
				style="height: 22px; width: 22px;" src="../images/settings.png" />
	</div>
	</shiro:hasRole>
	 -->
	 <div style="display: inline; text-align: right;">
			<input type="image" name="btnChangePwd" id="btnChangePwd"
				style="height: 22px; width: 22px;" src="../images/settings.png" alt="Change Password"/>
	</div>
</td>
</tr>
</table>

<div id="MyDialog" title="Preferences" style="display: none">
<br/>
<form action="javascript:;" id="uploadForm" method="post" enctype="multipart/form-data" accept-charset="utf-8">
			<table>
				<shiro:hasRole name="AFFILIATE">
				<tr>
					<td><label>PDF Template</label></td>
					<td></td>
					<td><select id="optionList" name="optionList">
							<option value="Store">Store</option>
							<option value="Region">Region</option>
							<option value="Affiliate">Affiliate</option>
						</select>
					</td>
				</tr>
				</shiro:hasRole>
				<shiro:hasRole name="STORE_MANAGER">
				<tr>
					<td><label>PDF Template</label></td>
					<td></td>
					<td><select id="optionList" name="optionList">
							<option value="Store">Store</option>
						</select>
					</td>
				</tr>
				</shiro:hasRole>
				<tr>
					<td><label>Expiry Date</label></td>
					<td></td>
					<td><input type="text" id="datepicker" name="datepicker"></td>
				</tr>
				<tr>
					<td><label>Template</label></td>
					<td></td>
					<td><input type="file" name="file" id="file"></td>
				</tr>
			</table>
</form>		 
</div>

<div id="changepwd" title="Change Password" style="display: none">
<br/>
<form action="javascript:;" id="changepwdForm" method="post" accept-charset="utf-8">
			<table>
				<tr>
					<td><label>Current Password <span style='color:red'>*</span></label></td>
					<td></td>
					<td><input type="password" id="currpwd" name="currpwd"></td>
				</tr>
				<tr>
					<td><label>New Password <span style='color:red'>*</span></label></td>
					<td></td>
					<td><input type="password" id="newpwd" name="newpwd"></td>
				</tr>
				<tr>
					<td><label>Confirm New Password <span style='color:red'>*</span></label></td>
					<td></td>
					<td><input type="password" id="confnewpwd" name="confnewpwd"></td>
				</tr>
			</table>
</form>	
<div id="errors" style="color:red;font-size: 11px"></div>	 
</div>
<!--  
	<div id="container" style="white-space:nowrap">
    <div id="image" style="display:inline;">
        <img src="../images/header.png" height="90px" width="90px"/>
    </div>
    <div id="texts" style="display:inline; white-space:nowrap; color: brown;font-size: 40; font-weight: bold;"> 
       Casa Travel 
    </div>
   
    <div style="display:inline; text-align: right;">Hi : </div>
    <div style="display:inline; text-align: right;">
    <a href="../logout">Log Out</a>
    </div>
</div> -->
</body>
</html>