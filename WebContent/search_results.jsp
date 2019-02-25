<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.ted.project.entities.*"%>
<%@ page import="java.util.*"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="resources/css/search_results.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<title>Home</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/shared.css">
<script src="resources/js/search_results.js"></script>
<%
String url="";
if(request.getParameter("search")!=null)
{
	url="SearchResults?search="+request.getParameter("search");
	System.out.print(url);
}

%>
<script type="text/javascript">
var url="<%=url%>"; 
</script>
</head>
<body ng-app="myApp" ng-controller="myCtrl" ng-init="loadPage()" >
	<div class="logout">
		<form method="post" action="Logout">  
			<input class="logout_button" type="submit" value="Logout"/>
		</form>
	</div>
	<div class="navbar"> 
		<ul class="nav nav-tabs"> 
		    <li ><a data-toggle="tab" href="home.jsp">Home</a></li>
		    <li class="active"><a data-toggle="tab" href="net.jsp">Net</a></li>
		    <li><a data-toggle="tab" href="aggelies.jsp">Advertisments</a></li>
		    <li><a data-toggle="tab" href="conversation.jsp">Conversation</a></li>
	    	<li><a data-toggle="tab" href="personalData.jsp">Personal data</a></li>
		   	<li><a data-toggle="tab" href="settings.jsp">Settings</a></li>
			<li><a data-toggle="tab" href="notifications.jsp">Notifications</a></li>
		  </ul> 
		<div class="breadcrumps">
			 <a href="net.jsp">Net </a> / Search Results
		</div>		  
	</div>
	<div class="middle">
	<h3 ng-if="users.length==0" style="text-align: center">No results Found!</h3>
		<div class="user1" ng-repeat="x in users">
			     <div class="image">
			     	<a href="personalData.jsp?friend={{x.id}}"><img height="120" width="120" style="border:1px solid black;background-color: white;" src="{{x.image}}"></a>
			     </div>
				 <div class="data">
				 	<a href="personalData.jsp?friend={{x.id}}">{{x.name+" "+x.surname}}</a>
		       		<br><label>Profession:  {{x.profession}}</label>
				</div>		
				<div class="buttons">
					<input type="button" class="connected" value="Connected" disabled ng-show="connected[$index]">
					<input type="button" class="adduserbutton" value="Send Connection Request" ng-hide="connected[$index] || cancel[$index]" ng-click="addUser(x.id)">
						 
					<input type="button" class="adduserbutton" value="Cancel Request"  ng-show="cancel[$index]" ng-click="cancelRequest(x.id)">							      		
				</div>
		</div>
	</div>

</body>
</html>