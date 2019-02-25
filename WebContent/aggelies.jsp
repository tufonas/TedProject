<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="resources/css/aggelies.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Advertisments</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="resources/js/advertisments.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/shared.css">
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
		    <li class="active"><a data-toggle="tab" href="aggelies.jsp">Advertisments</a></li>
		    <li><a data-toggle="tab" href="conversation.jsp">Conversation</a></li>
	    	<li><a data-toggle="tab" href="personalData.jsp">Personal data</a></li>
		   	<li><a data-toggle="tab" href="settings.jsp">Settings</a></li>
			<li><a data-toggle="tab" href="notifications.jsp">Notifications</a></li>
		  </ul>
		<button class="logout_button" ng-click="addAdvertisment()">Add new Advertisment </button>
	</div>
	<div class="info">
		<div class="leftcontainer"> 
			<h2 >Applications</h2> 
			<h4 style="border-top: 2px solid black;margin-top: 10px;" ng-show="myAds.length==0">You have no appications!</h4> 
			<div class="applications" ng-repeat="x in myAds"> 
				<div style="border-top: 2px solid black;margin-top:10px;width: 100%;"></div>
				<label style="font-size: 16px;">{{x.title}}</label>
				<div style="background-color: #adad85;border-radius: 4px;border:1px solid black;margin-bottom: 5px;" ng-repeat="y in x.applications" ng-if="y.pending=='TRUE'">
						<label style="float:left;font-size: 12px;">User <a style="color:blue" href="personalData.jsp?friend={{y.user.id}}">{{y.user.name+" "+y.user.surname}}</a> has applied to your advertisment with title "{{x.title}}"</label>
						<button class="accept" ng-click="deleteAd(x.advertismentId,y.user.id)">Accept</button>
						<button class="decline" ng-click="deleteAd(x.advertismentId,y.user.id)">Decline</button>
						<div style="margin-top:10px;width: 100%;"></div>
				</div>
			</div>
			<div class="footer">
				<p style="font-size: 16px;color :black"><a href="home.jsp" style="color: black;text-decoration: underline;">Business World</a></p>
				<p>Designed by </p>
				<p>Ilias Vergos and Kostas Pleuris</p>
				<p><a href="" style="color: black;text-decoration: underline;">Communication</a></p><a href="" style="color: black;text-decoration: underline;"> Terms & Service</a>			 
			</div>
		</div>
		<div class="middle">
				<form action="Aggelies" method="post">	  	
					<div class="boarder" ng-show="showAdvertisment">
						<p style="margin-top:20px;">Title</p>
						<input class="input" type="text" name="title" placeholder="Type the title of your advertisment here!" autocomplete="off"></input>
						<br></br>
						<p>Text</p>
						<textarea class="area"  placeholder="Type your text here!" name="text"></textarea>
						<br></br>
						<p>Necessary Skills</p>
						<textarea class="area"  placeholder="Type the necessary skills for the adverisment here!" name="skills"></textarea>
						<br></br>
						<input type="submit" class="button" ></input>
					</div>
				</form>		
				<div class="aggelies" ng-repeat="x in ads" >
					<div class="user_name" >
						<h5 style="text-align: left">Published by <a href="personalData.jsp?friend={{x.user.id}}">{{x.user.name+" "+x.user.surname}}</a></h5>  
					</div>
					<div class="title">		 
						<label style="font-size: 19px;">{{x.title}}</label>    
					</div>
					<div class="textArea" >{{x.text}}</div>
					<br>
					<div class="title">		 
						<label style="font-size: 19px;">Necessary Skills</label>    
					</div>
					<div class="textArea" ng-if="x.necessarySkills!=''">{{x.necessarySkills}}</div> 
					<div class="textArea" ng-if="x.necessarySkills==''">No skills needed for this job!</div><br>  
					<button class="button" ng-click="apply(x.advertismentId)" ng-show="applied[$index]">Apply For The Job</button> 
					<!--  <button class="button" disabled ng-hide="applied[$index]">You have applied for the job</button> -->
					<button class="button" ng-click="cancel(x.advertismentId)" ng-hide="applied[$index]">Cancel Apply</button> 
				</div>
			<br>
			<br>
		</div>	
	</div>
</body>
</html>