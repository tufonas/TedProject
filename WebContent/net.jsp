<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page import="com.ted.project.entities.*"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="UTF-8"%>
  
<head>
<link rel="stylesheet" type="text/css" href="resources/css/net.css">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/shared.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Net</title>
<script src="resources/js/net.js"></script>
</head>  
<body ng-app="myApp" ng-controller="myCtrl" ng-init="loadPage()">
<div class="logout" >
		<form class="form" method="post" action="Logout">
			<input class="logout_button" type="submit" value="Logout"/>
		</form>
</div>

	<div class="navbar">  
        <ul class="nav nav-tabs">
		    <li><a data-toggle="tab" href="home.jsp">Home</a></li>
		    <li class="active"><a data-toggle="tab" href="net.jsp">Net</a></li>
		    <li><a data-toggle="tab" href="aggelies.jsp">Advertisments</a></li>
		    <li><a data-toggle="tab" href="conversation.jsp">Conversation</a></li>
	    	<li><a data-toggle="tab" href="personalData.jsp">Personal data</a></li>
		   	<li><a data-toggle="tab" href="settings.jsp">Settings</a></li>
			<li><a data-toggle="tab" href="notifications.jsp">Notifications</a></li>
        </ul>
 
        <form class="form1" method="get" action="search_results.jsp">
	  		<input type="text" class="textbox" placeholder="Find other users!" name="search">
		  	<input title="Search" type="submit" class="button" value="Search"> 
		</form>
	</div>
<br>
<div class="middle">
	<h2 ng-show="friends.length==0" style="text-align: center;">No Friends!</h2>
	<div class="grid-container">
		  <div class="grid-item" ng-repeat="x in friends">
			  <a style="text-decoration: none;" href="personalData.jsp?friend={{x.id}}"><img src="{{x.image}}" height="200" width="200" style="border:1px solid black;background-color: white;"> </a><br>
			  <label>Name: </label><a href="personalData.jsp?friend={{x.id}}" >{{" "+x.name + " "+x.surname}}</a>
			  <br><label ng-if="x.profession!=null">Profession: {{x.profession}}</label>
			  <label ng-hide="x.profession!=null">Profession:-</label>
			  <br><label ng-if="x.foreas!=null">Employment Institution: {{x.foreas+" "}}</label>
			  <label ng-hide="x.foreas!=null">Employment Institution:<br>     -</label>
			  <button class="button" ng-click="deleteUserFromFriends(x.id)">Remove from your connections</button>
		  </div> 
	</div>
<div class="footer1">
	<p style="font-size: 16px;color :black"><a href="home.jsp" style="color: black;text-decoration: underline;">Business World</a></p>
	<p>Designed by Ilias Vergos and Kostas Pleuris </p>
	<p><a href="" style="color: black;text-decoration: underline;">Communication </a> - <a href="" style="color: black;text-decoration: underline;"> Terms & Service</a></p>
</div>
</div>


</body>
</html>