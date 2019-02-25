<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="com.ted.project.entities.*"%>
<%@ page import="java.util.*"%>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/settings.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Settings</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/shared.css">
<script src="resources/js/settings.js"></script>

</head>
<body ng-app="myApp" ng-controller="myCtrl" ng-init="loadPage()">
<div class="logout">
	<form method="post" action="Logout">  
		<input class="logout_button" type="submit" value="Logout"/>
	</form>
</div>
<div class="navbar"> 
	<ul class="nav nav-tabs">
	    <li><a data-toggle="tab" href="home.jsp">Home</a></li>
	    <li><a data-toggle="tab" href="net.jsp">Net</a></li>
	    <li><a data-toggle="tab" href="aggelies.jsp">Advertisments</a></li>
	    <li><a data-toggle="tab" href="conversation.jsp">Conversation</a></li>
    	<li><a data-toggle="tab" href="personalData.jsp">Personal data</a></li>
	   	<li  class="active"><a data-toggle="tab" href="settings.jsp">Settings</a></li>
		<li><a data-toggle="tab" href="notifications.jsp">Notifications</a></li>
	  </ul>
</div>
<div class="container1">
	<form method="post" action="Settings">
		<label>Current Email</label>
		<input class="input" type="text" value="{{email}}" disabled> </input>
		<label ng-show="new_email">New Email</label>
		<input type="text" class="input"  ng-show="new_email" name="new_email"></input>
		<button type="submit" class="button" ng-show="new_email">Confirm Changes</button>
		<div class="line">
			<button type="button" class="button" ng-click="changeEmail()" ng-hide="change_email || hideEmail">Change Email</button>
		</div>
		<div class=line>
			<button type="button" class="button" ng-click="changePassword()" ng-hide="change_pass || hidePassword">Change Password</button>	
		</div>
	</form>	
	<form method="post" action="Settings">
		<label ng-show="old_password">Old Password</label>
		<input type="password" class="input" name="old_password" ng-show="old_password" ></input>
		<label  ng-show="new_password">New Password</label>
		<input type="password" class="input" name="new_password" ng-show="new_password"></input>
		<label ng-show="confirm_password">Confirm New Password</label>
		<input type="password" class="input" name="confirm_password" ng-show="confirm_password"></input>
		<button type="submit" class="button" ng-show="confirm_password">Confirm changes</button>
	</form>		
	<%
	String message_fail =(String)request.getAttribute("message_fail");
	String message_complete =(String)request.getAttribute("message_complete");
	if(message_fail!=null)
	{
	%>
		<label style="color: #cc0000"><% out.print(message_fail);%></label>	
	<%
	}else if(message_complete!=null)
	{
		%>	<label style="color:  #009900">Update was successful!</label><%	
	}
	%>
</div>

<div class="footer1">
	<p style="font-size: 16px;color :black"><a href="home.jsp" style="color: black;text-decoration: underline;">Business World</a></p>
	<p>Designed by Ilias Vergos and Kostas Pleuris </p>
	<p><a href="" style="color: black;text-decoration: underline;">Communication </a> - <a href="" style="color: black;text-decoration: underline;"> Terms & Service</a></p>
</div>	

</body>
</html>