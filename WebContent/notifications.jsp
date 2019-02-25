<?xml version="1.0" encoding="ISO-8859-1" ?>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Notifications</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/conversation.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/notifications.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/shared.css">
<script src="resources/js/notifications.js"></script>
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
		   	<li><a data-toggle="tab" href="settings.jsp">Settings</a></li>
			<li class="active"><a data-toggle="tab" href="notifications.jsp">Notifications</a></li>
		  </ul>
</div>
<div class="middle">
	<h3>Friends Requests</h3>
	<div class="content" >
		<h4 ng-show="friend_requests.length==0">You have no Friends requests!</h4>
		<div class="friend_request" ng-repeat="x in friend_requests">
				<p ng-if="accept_choice[$index]">User <a href="personalData.jsp?friend={{x.id}}">{{x.name+" "+x.surname}}</a> has sent you a friend request!</p>		
				<div >
					<input class="accept" type="button" ng-click="accept(x.id,$index)" value="Accept" ng-if="accept_choice[$index]" ></input>
					<input class="decline" type="button" ng-click="reject(x.id,$index)" value="Decline" ng-if="accept_choice[$index]"></input>
					<p ng-if="!msg[$index]">User {{x.name+" "+x.surname}} is now your friend!</p>
				</div>
		</div>
	</div>
	<h3 style="margin-top: 80px;">Article Notifications</h3>
	<div class="bottom">
		<h4 ng-show="likes.length==0 && comments.length==0">You have no Article Notifications!</h4>
		<h4 ng-show="likes.length!=0">Likes</h4>
		<div ng-class="{'notification' : $index!=like_indx ,'notification_active' : $index==like_indx }" ng-repeat="y in likes" ng-click="goToArticle1(y.article.articleId,y.user.id)" ng-if="y.user.id!=user.id">
			<label style="cursor: pointer;">User {{y.user.name+" "+y.user.surname}} has liked your article!</label>  
			<label style="float:right;cursor: pointer;">{{getTime(y.likedTime)}}</label> 
		</div>
		<h4 ng-show="comments.length!=0">Comments</h4> 
		<div ng-class="{'notification' : $index!=comm_indx ,'notification_active' : $index==comm_indx }" ng-repeat="y in comments" ng-click="goToArticle(y.article.articleId,y.user.id,y.commentId)" ng-if="y.user.id!=user.id">
			<label style="cursor: pointer;">User {{y.user.name+" "+y.user.surname}} has commented your article!</label>
			<label style="float:right;cursor: pointer;">{{getTime(y.publishTime)}}</label>
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