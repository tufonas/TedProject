<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="com.ted.project.entities.*"%>
<%@ page import="java.util.*"%>
<head>
<%
	String id = request.getParameter("friend");
	System.out.print(id);
 	String url = "Profile?friend="+id;
 	
 %>
<link rel="stylesheet" type="text/css" href="resources/css/profile.css">
<link rel="stylesheet" type="text/css" href="resources/css/shared.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Profile</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/conversation.js"></script>

<script type="text/javascript">
var url = "<%=url%>";
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http,$interval) {
	$scope.loadPage= function(){
		$http.get(url).then(function (response) {
				  console.log(response);
			      $scope.user = response.data;
			  });
	}
});
</script>
</head>
<body ng-app="myApp" ng-controller="myCtrl" ng-init="loadPage()">

	
<form method="post" action="SearchResults">
  <input type="text" class="textbox" placeholder="Search" name="search">
  <input title="Search" type="submit" class="button"> 
</form>
<br><br><br><br><br>

<fieldset class="stoixeia" >
	<label>Name: </label> {{user.name}}<br>
	<label>Surname: </label>{{ user.surname}}<br>
	<label>Email: </label> {{user.email}}<br>
	<label>Profession: </label> {{user.profession}}<br>
	<label>Telephone: </label> {{user.telephone}}<br>
	<label>Foreas: </label> {{user.foreas}}<br>
	<form method="get" action="conversation.jsp">
		<input type="hidden" value="{{user.id}}" name="friend"></input>
		<button type="submit">Send Message</button>
	</form>

</fieldset>
</body>
</html>