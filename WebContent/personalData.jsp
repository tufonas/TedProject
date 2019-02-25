<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ted.project.entities.*"%>
<%@ page import="java.util.*"%>
<head>
<%
	String url="";
	String id="";
	if(request.getParameter("friend")!=null && request.getParameter("admin")!=null)
	{
		int friendId = Integer.parseInt(request.getParameter("friend")); 
		url="PersonalData?friend="+request.getParameter("friend")+"&admin=true";
		id=request.getParameter("friend");
	}
	else if(request.getParameter("friend")!=null)
	{
		int friendId = Integer.parseInt(request.getParameter("friend")); 
		url="PersonalData?friend="+request.getParameter("friend");
		id=request.getParameter("friend");
		System.out.print(url);
	}
 %>
<link rel="stylesheet" type="text/css" href="resources/css/personalData.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Personal Data</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/shared.css">
<script src="resources/js/personalData.js"></script>
<script type="text/javascript">
var url="<%=url%>";
var friend_Id="<%=id%>";  
</script>
<script>
	// Changing manually angular controller so that file upload label is updated
	function updateFileUploadLabel(angObj,name){
		angular.element(angObj).scope().changeInputName(name);
		angular.element(angObj).scope().$digest();
	}
</script>
</head>

<body ng-app="myApp" ng-controller="myCtrl" ng-init="loadPage()" >
<div class="logout">
	<form method="post" action="Logout">  
		<input class="logout_button" type="submit" value="Logout"/>
	</form>
</div>
<div class="navbar" ng-hide="admin"> 
		<ul class="nav nav-tabs">
		    <li><a data-toggle="tab" href="home.jsp">Home</a></li>
		    <li ng-if="logged_user.id ==user.id"><a data-toggle="tab" href="net.jsp" >Net</a></li>
		    <li class="active" ng-if="logged_user.id !=user.id"><a data-toggle="tab" href="net.jsp">Net</a></li>
		    <li><a data-toggle="tab" href="aggelies.jsp">Advertisments</a></li>
		    <li><a data-toggle="tab" href="conversation.jsp">Conversation</a></li>
	    	<li class="active" ng-if="logged_user.id ==user.id"><a data-toggle="tab" href="personalData.jsp">Personal data</a></li>
	    	<li ng-if="logged_user.id !=user.id"><a data-toggle="tab" href="personalData.jsp">Personal data</a></li>
		   	<li><a data-toggle="tab" href="settings.jsp">Settings</a></li>
			<li><a data-toggle="tab" href="notifications.jsp">Notifications</a></li>
		  </ul>
		  <div class="breadcrumps" ng-if="logged_user.id !=user.id">
		  		<a href="net.jsp">Net </a> / {{" "+user.name+" "+user.surname}}
		  </div>
</div>

	<div style="margin-left:auto;margin-right:auto;width:700px;">
		<button class="accept" ng-click="backToUsers()" ng-show="admin"><span style="font-size: 16px;">&#8592;</span> Back to Users</button>
	</div>	 
	<div class="profile_field">
	<h2>Personal Data</h2> 

		<div style="width:30%;margin-left:auto;margin-right:auto;text-align: left;"> 
			<img style="border: 1px solid black;" src="{{user.image}}" height="200" width="200"></img>
		</div>
		<div class="column">
			<label>Name: </label> 
			<input class="profile" type="text" value="{{user.name}}"  disabled></input>
		</div>	
		<div class="column">
			<label>Surname: </label> 
			<input class="profile" type="text" value="{{user.surname}}"  disabled></input>
		</div>
		<div class="btns" ng-if="logged_user.id ==user.id" ng-hide="admin || hide_buttons">
			<button class="add_buttons" ng-click="showExperience()" >Add Experience info</button>
			<button class="add_buttons" ng-click="showEducation()" ng-show="showAddEducationButton">Add Education info</button>
			<button class="add_buttons" ng-click="showSkills()" ng-show="showAddSkillButton">Add Skills info</button>
		</div>
		<div class="btns" ng-if="is_friend || admin">
				<button class="add_buttons" ng-click="showFriends()">Friends</button>
		</div>
	<div class="field" id ="field" ng-show="experience">

		<h3>Professional Experience</h3>
		<form action="PersonalData" method="post">
		
			<div >
				<div class="column1">
					<label >Profession</label>
					<input class="exp" type="text" placeholder="Profession" ng-model="profession" name="profession"></input>
					<input type="radio" name="choice" value="Public" checked> </input>
					<label >Public</label>
					<input type="radio" name="choice" value="Private"> </input>
					<label >Private</label>
				</div>
				<div class="column1">
					<label >Employment Institution</label>
					<input class="exp" type="text" placeholder="Tomeas" ng-model="tomeas" name="tomeas"></input>
					<input type="radio" name="choice1" value="Public" checked> </input>
					<label >Public</label>
					<input type="radio" name="choice1" value="Private"> </input>
					<label >Private</label>
				</div>
				
				<div class="column1">
					<label >Business Name</label>
					<input class="exp" type="text" placeholder="Business Name" ng-model="business_name" name="business_name"></input>
					<input type="radio" name="choice2" value="Public" checked> </input>
					<label >Public</label>
					<input type="radio" name="choice2" value="Private"> </input>	
					<label >Private</label>									
				</div>	
				
				<div class="row1">
					<label >Duties</label>
					<textarea class="area1" rows="10" cols="70" placeholder="Type your duties here!" ng-model="duties" name="duties"></textarea>
					
					<input type="radio" name="choice3" value="Public" checked> </input>
					<label >Public</label>
					<input type="radio" name="choice3" value="Private"> </input>					
					<label >Private</label>			
				</div>
				<div class="row1">				
					<label >Achievements</label>
					<textarea class="area1" rows="10" cols="70" placeholder="Type your achievements here!" ng-model="achievements" name="achievements"></textarea>
					<input type="radio" name="choice4" value="Public" checked> </input>
					<label >Public</label>
					<input type="radio" name="choice4" value="Private"> </input>	
					<label >Private</label>				
				</div>
			</div>
			<button type="button" class="back_button" ng-click="backToProfile()" >Go back</button>	
			<input class="button" type="submit" value="Save" ></input>
		</form>
	</div> 
	
	<div class="field" ng-show="education">
		<h3>Education</h3>
		<form action="PersonalData" method="post">
			<textarea class="area" rows="10" cols="70" name="education" placeholder="Type your education here!"></textarea><br>
			<input type="radio" name="choice5" value="Public" checked> </input>
			<label >Public</label>
			<input type="radio" name="choice5" value="Private"> </input>	
			<label >Private</label><br>	
			<button type="button" class="back_button" ng-click="backToProfile()" >Go back</button>							
			<input class="button" type="submit" value="Save" ></input>
		</form>
	</div>
	<div class="field" ng-show="skills">
		<h3>Skills</h3> 
		<form action="PersonalData" method="post">
			<textarea class="area" rows="10" cols="70" name="skills" placeholder="Type your skills here!"></textarea><br>
			<input type="radio" name="choice6" value="Public" checked> </input>
			<label >Public</label>
			<input type="radio" name="choice6" value="Private"> </input>	
			<label >Private</label>	<br>				
			<button type="button" class="back_button" ng-click="backToProfile()" >Go back</button>			
			<input class="button" type="submit" value="Save" ></input>
		</form>
	</div>		
	<div ng-show="showprofession">	
		<h2>Profession</h2>
		<select ng-model="selected"  ng-change="selectedItemChanged()" ng-options="y.position for y in user.jobs | filter:hide_profession_from_dropdown">
			<option value="" disabled selected>Choose one of your previous jobs</option>
		</select>	
	</div>		
		<form method="post" action="PersonalData" >  
			<div ng-show="showData">
				<h2 >Professional Experience</h2>
				<input type="hidden" value="{{selected.professionId}}" name="profid"></input>
				<div class="column1">
					<label style="text-align:left;">Profession</label>
					<input class="exp" type="text" disabled value="{{selected.position}}" ng-if="(logged_user.id !=user.id  && is_friend) || (logged_user.id !=user.id && !is_friend && selected.position_visibility=='Public') || admin"></input>
					<input class="exp" type="text" value="{{selected.position}}"   name="edit_profession" ng-if="logged_user.id ==user.id"></input>
					<div ng-if="logged_user.id ==user.id">
						<input type="radio" name="choice7" value="Public" ng-checked="selected.position_visibility=='Public'"> </input>
						<label >Public</label>
						<input type="radio" name="choice7" value="Private" ng-checked="selected.position_visibility=='Private'"> </input>	
						<label >Private</label>
					</div>
				</div>
				<div class="column1">
					<label style="text-align:left;">Employment Institution</label>
					<input ng-class="{'exp':selected.tomeas!='','exp_new':selected.tomeas==''}" type="text" disabled value="{{selected.tomeas}}" placeholder="-" ng-if="(logged_user.id !=user.id && is_friend) || (logged_user.id !=user.id && !is_friend && selected.tomeas_visibility=='Public') || admin"></input>
					<input class="exp_new" type="text" disabled value="Private data" placeholder="Private Data" ng-if="!(logged_user.id ==user.id) && !(logged_user.id !=user.id && is_friend) && !(logged_user.id !=user.id && !is_friend && selected.tomeas_visibility=='Public') && !(admin)"></input>
					<input class="exp" type="text" value="{{selected.tomeas}}" placeholder="Tomeas"  name="edit_tomeas" ng-if="logged_user.id ==user.id"></input>
					<div ng-if="logged_user.id ==user.id">
						<input type="radio" name="choice8" value="Public" ng-checked="selected.tomeas_visibility=='Public'"> </input>
						<label >Public</label>
						<input type="radio" name="choice8" value="Private" ng-checked="selected.tomeas_visibility=='Private'"> </input>	
						<label >Private</label>
					</div>
				</div>
				
				<div class="column1">
					<label style="text-align:left;">Business Name</label>
					<input ng-class="{'exp':selected.business_name!='','exp_new':selected.business_name==''}" type="text" disabled value="{{selected.business_name}}" placeholder="-" ng-if="(logged_user.id !=user.id && is_friend) || (logged_user.id !=user.id && !is_friend && selected.business_name_visibility=='Public') || admin"></input>
					<input class="exp_new" type="text" disabled value="Private data" placeholder="Private Data" ng-if="!(logged_user.id ==user.id) && !(logged_user.id !=user.id && is_friend) && !(logged_user.id !=user.id && !is_friend && selected.business_name_visibility=='Public') && !(admin)"></input>
					<input class="exp" type="text" value="{{selected.business_name}}" placeholder="Business Name"   name="edit_business_name" ng-if="logged_user.id ==user.id"></input>
					<div ng-if="logged_user.id ==user.id">
						<input type="radio" name="choice9" value="Public" ng-checked="selected.business_name_visibility=='Public'"> </input>
						<label >Public</label>
						<input type="radio" name="choice9" value="Private" ng-checked="selected.business_name_visibility=='Private'"> </input>	
						<label >Private</label>
					</div>
				</div>	
				
				<div class="row1" >
					<label style="text-align:left;">Duties</label>
					<textarea class="area1" placeholder="-" disabled rows="10" cols="70"  ng-if="(logged_user.id !=user.id && is_friend) || (logged_user.id !=user.id && !is_friend && selected.duties_visibility=='Public') || admin">{{selected.duties}}</textarea>
					<textarea class="area1" placeholder="Private Data" disabled rows="10" cols="70"  ng-if="!(logged_user.id ==user.id) && !(logged_user.id !=user.id && is_friend) && !(logged_user.id !=user.id && !is_friend && selected.duties_visibility=='Public') && !(admin)">Private data</textarea>
					<textarea class="area1" rows="10" cols="70"  name="edit_duties" ng-if="logged_user.id ==user.id">{{selected.duties}}</textarea>
					<div ng-if="logged_user.id ==user.id">
						<input type="radio" name="choice10" value="Public" ng-checked="selected.duties_visibility=='Public'"> </input>
						<label >Public</label>
						<input type="radio" name="choice10" value="Private" ng-checked="selected.duties_visibility=='Private'"> </input>	
						<label >Private</label>
					</div>
	
				</div>
				<div class="row1">				
					<label style="text-align:left;">Achievements</label>
					<textarea class="area1" placeholder="-" disabled rows="10" cols="70"   ng-if="(logged_user.id !=user.id && is_friend) || (logged_user.id !=user.id && !is_friend && selected.achievements_visibility=='Public') || admin">{{selected.achievements}}</textarea>
					<textarea class="area1" placeholder="Private Data" disabled rows="10" cols="70"   ng-if="!(logged_user.id ==user.id) && !(logged_user.id !=user.id && is_friend) && !(logged_user.id !=user.id && !is_friend && selected.achievements_visibility=='Public') && !(admin)">Private data</textarea>
					<textarea class="area1" rows="10" cols="70"  name="edit_achievements" ng-if="logged_user.id ==user.id">{{selected.achievements}}</textarea>
					<div ng-if="logged_user.id ==user.id">
						<input type="radio" name="choice11" value="Public" ng-checked="selected.achievements_visibility=='Public'"> </input>
						<label >Public</label>
						<input type="radio" name="choice11" value="Private" ng-checked="selected.achievements_visibility=='Private'"> </input>	
						<label >Private</label>
					</div>
				</div>		
			</div>			
			<div ng-show="showeducation">	
				<h2 >Education</h2>
				<textarea class="area" rows="10" cols="70" name="edit_education" ng-if="logged_user.id ==user.id">{{user.experience.education}}</textarea><br>
				<div class="textArea" ng-if="(logged_user.id !=user.id && is_friend) || (logged_user.id !=user.id && !is_friend && user.experience.education_visibility=='Public') || admin"><p>{{user.experience.education}}</p></div>
				<div class="textArea" ng-if="!(logged_user.id ==user.id) && !(logged_user.id !=user.id && is_friend) && !(logged_user.id !=user.id && !is_friend && user.experience.education_visibility=='Public') && !(admin)"><p>Private data</p></div>
				<div ng-if="logged_user.id ==user.id">
					<input type="radio" name="edit_choice" value="Public" ng-checked="public_education"> </input>
					<label >Public</label>
					<input type="radio" name="edit_choice" value="Private" ng-checked="private_education"> </input>
					<label >Private</label>			
				</div>
			</div>	
			<div ng-show="showskills">	
				<h2 >Skills</h2>	
				<textarea class="area" rows="10" cols="70" name="edit_skills" ng-if="logged_user.id ==user.id">{{user.experience.skills}}</textarea><br>
				<div class="textArea" ng-if="(logged_user.id !=user.id && is_friend) || (logged_user.id !=user.id && !is_friend && user.experience.skills_visibility=='Public') || admin"><p>{{user.experience.skills}}</p></div>
				<div class="textArea" ng-if="!(logged_user.id ==user.id) && !(logged_user.id !=user.id && is_friend) && !(logged_user.id !=user.id && !is_friend && user.experience.skills_visibility=='Public') && !(admin)"><p>Private data</p></div>
				<div ng-if="logged_user.id ==user.id">
					<input type="radio" name="edit_choice1" value="Public" ng-checked="public_skills"> </input>
					<label >Public</label>
					<input type="radio" name="edit_choice1" value="Private" ng-checked="private_skills"> </input>
					<label >Private</label>
				</div>
			</div>	
			<input type="hidden" value="{{user.experience.experienceId}}" name="expid"></input>
			<div class="btns"  ng-if="logged_user.id ==user.id && showSave && ( logged_user.experience!=null || showData )"  >
				<button type="submit" class="button">Save Edited Changes</button>
			</div>
		</form>
		<form method="get" action="conversation.jsp"  ng-if="logged_user.id !=user.id" ng-hide="admin || friends.length==0">
			<input type="hidden" value="{{user.id}}" name="friend"></input>
			<button class="button" type="submit">Send Message</button>
		</form>
		
	</div>
	<div ng-show="showfriends">
		<div class="friends_title">
			<h4>Friends</h4>
		</div>
		<div class="friends">
			<div class="friend"  ng-repeat="x in friends">
				<a href="personalData.jsp?friend={{x.id}}" ><img height="60" width="60"  src="{{x.image}}"></a> 
				<label style="margin: 15px;"><a href="personalData.jsp?friend={{x.id}}" style="color: black" >{{x.name+" "+x.surname}}</a></label>
			</div>
			<label  ng-if="friends.length==0">No Friends!</label>
		</div>
	</div>
	<div class="footer1">
		<p style="font-size: 16px;color :black"><a href="home.jsp" style="color: black;text-decoration: underline;">Business World</a></p>
		<p>Designed by Ilias Vergos and Kostas Pleuris </p>
		<p><a href="" style="color: black;text-decoration: underline;">Communication </a> - <a href="" style="color: black;text-decoration: underline;"> Terms & Service</a></p>		 
	</div>	

</body>
</html>