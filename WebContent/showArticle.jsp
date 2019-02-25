<?xml version="1.0" encoding="UTF-8" ?>
<%@ page import="java.util.List"%>
<%@ page import="com.ted.project.entities.User"%>
<%@ page import="com.ted.project.entities.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/home.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="resources/js/showArticle.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/shared.css">
<%
String url="";
String article_id="";
String user_id="";
String comm_id="";
if(request.getParameter("article_id")!=null)
{
	//int articleId = Integer.parseInt(); 
	//int userId = Integer.parseInt(request.getParameter("user_id")); 
	//int commId = Integer.parseInt(request.getParameter("comm_id"));
	article_id =request.getParameter("article_id");
	user_id =request.getParameter("user_id");
	if(request.getParameter("comm_id")!=null)
		comm_id =request.getParameter("comm_id");
	url="ShowArticle?articleId="+article_id+"&userId="+user_id+"&commId="+comm_id;
}

%>
<script type="text/javascript">
var url= "<%=url %>";
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
	    <li><a data-toggle="tab" href="net.jsp">Net</a></li>
	    <li><a data-toggle="tab" href="aggelies.jsp">Advertisments</a></li>
	    <li><a data-toggle="tab" href="conversation.jsp">Conversation</a></li>
    	<li><a data-toggle="tab" href="personalData.jsp">Personal data</a></li>
	   	<li><a data-toggle="tab" href="settings.jsp">Settings</a></li>
		<li class="active"><a data-toggle="tab" href="notifications.jsp">Notifications</a></li>
	  </ul>
	  <br>
		<div class="articles" ng-repeat="x in articles" >
			<div class="user_name" >  
				<h5 style="float: left">Published by <a href="personalData.jsp?friend={{x.user.id}}">{{x.user.name+" "+x.user.surname}}</a></h5>
				
			</div>
			<div class="user_name" >
				<label style="float:right">{{getTime(x.publishTime)}}</label> 
			</div>
			<div class="title">		 
				<label style="font-size: 19px;">{{x.title}}</label>    
			</div>
			<div class="textArea"><p>{{x.text}}</p></div>
			<div class="files" ng-show="showFiles[$index]">
				<div class="image">
				  	<img src="{{x.images}}" height="200" width="300" ng-show="showImage[$index]">
				</div>
				<div class="video">				
					<video width="320" height="240" controls ng-show="showVideo[$index]"> 
					  	<source src="{{x.videos}}" type="video/mp4">
		  				<source src="{{x.videos}}" type="video/ogg">
		  			</video>		
		  		</div>
				<div class="audio">
				  	<audio controls ng-show="showAudio[$index]">
				  		<source src="{{x.sounds}}" type="audio/ogg">
	  					<source src="{{x.sounds}}" type="audio/mpeg">
					</audio>
				</div>
			</div>
			<h4>{{x.articleId}}</h4>
			<div class="like_comment" >
				<button class="like_btn" ng-click="like(x.articleId,$index)" ng-show="liked[$index]">Like</button>
				<button class="dislike_btn" ng-click="dislike(x.articleId,$index)" ng-show="disliked[$index]">Dislike</button>
				<button class="like_btn"ng-click="comment($index)">Comment</button>
			</div>
			<div style="text-align: left;margin-left: 55px;">
				<label>{{likes.length}} Likes </label>
			</div>
	
			<div class="comments" >
				<div class="comment" ng-show="newComment[$index]">
					<form  ng-submit="submitComment(x.articleId,Comment,$index)">
						<a href="personalData.jsp?friend={{user.id}}" ><img height="30" width="30" style="border-radius: 50%;" src="{{user.image}}"></a> 
						<input type="text" class="input_comment" placeholder="Make a Comment" ng-model="Comment"></input>
					</form>
				</div>
				<div class="comment" ng-repeat="y in comments">
					<img height="30" ng-if="y.article.articleId==x.articleId" width="30" style="border-radius: 50%;background-color: white;" src="{{y.user.image}}"> 
					<p  ng-class="{'comment_p' : y.commentId !=active ,'comment_p_active': y.commentId == active}" ng-if="y.article.articleId==x.articleId">
						<a href="personalData.jsp?friend={{y.user.id}}" style="cursor: pointer;">{{y.user.name+" "+y.user.surname}}</a>{{": "+y.text}}
					</p>
					<label class="comment_time" ng-if="y.article.articleId==x.articleId" >{{getTime(y.publishTime)}}</label>
				</div>
			</div>
		</div>	
	  
</div>


</body>

</html>
