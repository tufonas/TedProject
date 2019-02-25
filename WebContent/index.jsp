<?xml version="1.0" encoding="UTF-8" ?>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Welcome Page</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="resources/js/index.js"></script>
<link rel="stylesheet" type="text/css" href="resources\css\index.css">
</head>

<div class="images">
  <img src="resources\images\6.jpg"  >
</div>
<body ng-app="myApp" ng-controller="myCtrl">
	<div class="logo" id="logo">
	<p>Business World</p>
	</div>
	<div class="login" id=""> 
	  <form action="Login" method="post"> 
	      <input class="login" type="text" placeholder="Email" name="email">
	      <input class="login" type="password" placeholder="Password" name="password">
	      <button class="login" type="submit">Submit</button>
	  </form>
	</div>
	<%String message =(String)request.getAttribute("message");
	if(message != null){%>
	<div class="error">
		<h4><%out.print(message); %></h4>
	</div>
	<%} %>
	<div class="paragraph">
	  <p>  Welcome to our site!</p>
	  <p style="font-size: 20px">This site is responding to those who search for a part-time/full-time job.
	  It also helps companies to find suitable partners for the positions which needs to be covered.If u are a free businessman or a person who need a real job this is your site.Sign up below!!! </p>
	</div>
	<div class="signup">
	  <form action="signup.jsp">
	      <button class="signup" type="submit">Sign Up</button>
	  </form>
	</div>
</body>

</html>