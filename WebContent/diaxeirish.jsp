<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Administration</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="resources/js/diaxeirish.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/diaxeirish.css">
<link rel="stylesheet" type="text/css" href="resources/css/shared.css">
</head>

     
<body ng-app="myApp" ng-controller="myCtrl" ng-init="loadPage()" >
<div class="logout">
	<form method="post" action="Logout">  
		<input class="logout_button" type="submit" value="Logout"/>
	</form>
</div>
 <form action="Diaxeirish" method="post">

   <div class="middle">
       <button type="submit" class="xmlbutton" >Create XML for the chosen users</button>
        <div class="selectall"> 
     		<label style="margin-right: 8px;">Choose all</label><input name='selectall' type="checkbox" class="selectall"  ng-click="selectAll()" >  
     	</div>

	     <div class="user1" ng-repeat="x in users">
		     <div class="image">
		     	<a style="text-decoration: none;color:black;" href="personalData.jsp?friend={{x.id}}&admin=true"><img height="120" width="120" style="border:1px solid black;background-color: white;" src="{{x.image}}"></a>
		     </div>
			 <div class="data">
	       		<a style="text-decoration: none;color:blue;" href="personalData.jsp?friend={{x.id}}&admin=true">{{x.name+" "+x.surname}}</a>
	       		<br><label>Email:</label>{{x.email}}
	       		<br><label>Profession:</label>{{x.profession}}
	       		<br><label>Type of User:</label>{{x.typeOfUser}}
	       		<br><label>Telephone:</label>{{x.telephone}}
			</div>	
			<input class="user" type="checkbox" name="user" id="user" value="{{x.email}}" ng-checked="selected">  
			<br><br><br><br>	
			<input type="button" class="delete_button" ng-click="deleteUser(x.id)" value="Delete User"></input>		 
		</div>	      		

	   
   </div>
   </form>
</body>

</html>
