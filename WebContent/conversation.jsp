<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.ted.project.entities.Message"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="com.ted.project.entities.User"%>
     <%@ page import="java.util.*"%>
      <%@ page import="com.ted.project.entities.Conversations"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Messages</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/conversation.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/conversation.css"> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/shared.css">
<%
String url="";
String id="";
if(request.getParameter("friend")!=null)
{
	int friendId = Integer.parseInt(request.getParameter("friend")); 
	url="Conversation?friend="+request.getParameter("friend");
	id=request.getParameter("friend");
	System.out.print(url);
}

%>
<script type="text/javascript">
var url="<%=url%>";
var friend_Id="<%=id%>";  
</script>
</head>

<body ng-app="myApp" ng-controller="myCtrl" ng-init="loadPage()" ng-model="friendid">
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
		    <li class="active"><a data-toggle="tab" href="conversation.jsp">Conversation</a></li>
	    	<li><a data-toggle="tab" href="personalData.jsp">Personal data</a></li>
		   	<li><a data-toggle="tab" href="settings.jsp">Settings</a></li>
			<li><a data-toggle="tab" href="notifications.jsp">Notifications</a></li>
		  </ul>
		  <h4>Messages</h4>
</div>
<div class="info">
	<div class="left" >
		<h3>Users</h3>
		<label ng-show="users.length==0">No Friends!</label>
		<div class="user" ng-repeat="x in users" ng-click="userClick(x.id,$index);takeUser(x,$index)" ng-class="{'chosenUser' :$index == selectedIndex}" ng-init="takeUser(x,$index)" >
			{{x.name+" "+x.surname}}   
		</div>
	</div>
	<div class="middle" > 
	 		<div class=name>
				<label><a style="color: black;" href="personalData.jsp?friend={{user.id}}">{{user.name+" "+user.surname}}</a></label>
			</div>
			<div class="msg">
				<div class="messages" id="messages1">
					<div style="background-color:none;">
						<div ng-repeat="x in messages"  ng-class="{'leftmsg': chooseUser(x),'rightmsg':!chooseUser(x)}">
							<a href="personalData.jsp?friend={{x.user.id}}"><img height="30" ng-if="y.article.articleId==x.articleId" width="30" style="border-radius: 50%;background-color: white;" src="{{x.user.image}}"></a> 
							{{x.message}}
						</div>
					</div>
				</div>
			</div>
			<form  ng-submit="submit()">
				<input type="text" class="message" autocomplete="off" placeholder="Type message here..." name="msg" ng-model="msg"/>
			</form>
	
	</div>
</div>
</body>
</html>