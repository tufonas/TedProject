<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="resources/css/signup.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Signup</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="resources/js/signup.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/shared.css">
<script>
	// Changing manually angular controller so that file upload label is updated
	function updateFileUploadLabel(angObj,name){
		angular.element(angObj).scope().changeInputName(name);
		angular.element(angObj).scope().$digest();
	}
</script>
</head>
<br>  
<body ng-app="myApp" ng-controller="myCtrl">
	<div class="back_button_area">
		<button class="back_button" ng-click="backToIndex()"><span style="font-size: 16px;"> &#8592;</span>Back</button>
	</div>
	<div class="container_eggrafhs" style="text-align: center;background-color: none;">
		<form action="Signup" method="post" enctype = "multipart/form-data">

		 	<h3>Sign Up</h3>  
		 	<br>
			<%String message_complete =(String)request.getAttribute("message_complete");
				String message_fail =(String)request.getAttribute("message_fail");
			%>
			<div class="error">
			<%if(message_fail!=null){ %>
				<h4><%out.print(message_fail); %></h4><%}else if(message_complete!=null){ %>
				<h4 style="color: #00cc00;"><%out.print(message_complete); %></h4><%} %>
			</div>
			<br>
		 	<div class="container_eggrafhs_row">
		 		<label>*Name:</label><br>
		 		<div style="display: inline-block;width: 100%;">
		 			<input ng-class="{'input_field_true' : string==true && def==false ,'input_field_false' : string==false && def==false}" class="input_field" required="required" type="text" ng-init="def=true" ng-model="name" ng-keyup="checkPatternName()" ng-change="checkPatternName()" name="name">
		 			<img src="resources/images/1.png" height="30" ng-show="string==false && def==false" >
		 			<img id="onoma_true" src="resources/images/11.png" height="30" ng-show="string==true && def==false" >
		 		</div>
				<ul ng-show="string==false && def==false">
					<li>Name must have only letters!</li>
					<li>Name must not contain numbers or symbols (12,.,/,\)!</li>
				</ul>									
		 	</div>
			
			<div class="container_eggrafhs_row">
		 			<label>*Surname:</label><br>
		 			<div style="display: inline-block;width: 100%;">
		 			<input ng-class="{'input_field_true' : sur==true && def1==false ,'input_field_false' : sur==false && def1==false}" class="input_field" name="surname" required="required" type="text" ng-init="def1=true" ng-model="surname" ng-keyup="checkPatternSurname()" ng-change="checkPatternSurname()">
		 			<img src="resources/images/1.png" height="30" ng-show="sur==false && def1==false" >
		 			<img id="onoma_true" src="resources/images/11.png" height="30" ng-show="sur==true && def1==false" >
		 		</div>
				<ul ng-show="sur==false && def1==false">
					<li>Surname must have only letters!</li>
					<li>Surname must not contain numbers or symbols (12,.,/,\)!</li>							
				</ul>								
								 			
		 	</div>
	
		 	<div class="container_eggrafhs_row">
					<label>*E-mail:</label> <br>
					<div style="display: inline-block;width: 100%;">
			 			<input ng-class="{'input_field_true' : email==true && def2==false ,'input_field_false' : email==false && def2==false}" class="input_field" required="required" type="text" ng-init="def2=true" ng-model="e_mail" ng-keyup="checkPatternEmail()" name="e_mail" ng-change="checkPatternEmail()">
			 			<img src="resources/images/1.png" height="30" ng-show="email==false && def2==false" >
			 			<img id="onoma_true" src="resources/images/11.png" height="30" ng-show="email==true && def2==false" >
			 		</div>
					<ul ng-show="email==false && def2==false">
						<li>Email must be in this format : [ xxxx@yyyy.zzz ] !</li>
					</ul>								
		 	</div>
	
		 	<div class="container_eggrafhs_row">
					<label >*Password:</label> <br>
					<div style="display: inline-block;width: 100%;">
			 			<input name="password"
			 			ng-class="{'input_field_easy' : (pass0==true || pass1==true) && def3==false,'input_field_normal' : pass2==true && def3==false,'input_field_good' : pass3==true && def3==false,'input_field_very_good':pass4==true && def3==false,'input_field_hard':pass5==true && def3==false}" 
			 			class="input_field" ng-change="checkPatternPassword();checkPass();" ng-keyup="checkPatternPassword();checkPass();" type="password" required="required" ng-model="pass">
			 			</input>
						<img src="resources/images/11.png" height="30" ng-show="def3==false" >			 			
		 			</div>
					<ul >
						<li style="color:#ff0000" ng-if="(pass0==true || pass1==true) && def3==false" id="very_easy">Very Weak Password</li>
						<li style="color:rgb(192, 64, 0)" ng-if="pass2==true  && def3==false" id="easy">Weak Password</li>
						<li style="color:rgb(128, 128, 0)" ng-if="pass3==true  && def3==false" id="medium">Good Password</li>
						<li style="color: rgb(64, 192, 0)" ng-if="pass4==true  && def3==false" id="medium_hard">Very good Password</li>
						<li style="color: #4ce600 " ng-if="pass5==true  && def3==false" id="hard">Strong Password</li>
					</ul>								
		 	</div>
		 	
	
		 	<div class="container_eggrafhs_row">
		 		<label>*Confirm Password:</label> <br>
		 			<div style="display: inline-block;width: 100%;">
		 				<input name="password_check" class="input_field" ng-class="{'input_field_true' : check==true ,'input_field_false': check==false}" type="password" ng-model="check_pass" ng-keyup="checkPass()" ng-change="checkPass()" required="required" >
		 				<img src="resources/images/1.png" height="30" ng-show="check==false" >
		 				<img id="onoma_true" src="resources/images/11.png" height="30" ng-show="check==true" >
		 			</div>
		 			<ul ng-show="check==false">
						<li>Confirmation password doesn't match with the password above! </li>
					</ul>								
		 	</div>
		 
		 	<div class="container_eggrafhs_row">
					<label>*Telephone number:</label> <br>	
					<div style="display: inline-block;width: 100%;">
			 			<input name="telephone" ng-class="{'input_field_true' : telephone==true && def4==false ,'input_field_false': telephone==false && def4==false}" class="input_field" type="text" required="required" ng-model="tel" ng-keyup="checkPatternTelephone()" ng-change="checkPatternTelephone()">					
						<img src="resources/images/1.png" height="30"  ng-show="telephone==false && def4==false">	
						<img src="resources/images/11.png" height="30" ng-show="telephone==true && def4==false">	
					</div>
					<ul ng-show="telephone==false && def4==false">
						<li>Telephone must have 10 digits (2109999999 or 6977777777)!</li>
					</ul>														

		 	</div>
		 	<div class="container_eggrafhs_row">
		 		
		 		<label >Profession:</label><br>
				<div style="display: inline-block;width: 100%;">
		 			<input placeholder="Not required field!" name="profession" ng-class="{'input_field_true' : profession==true && def5==false ,'input_field_false': profession==false && def5==false}" class="input_field" type="text"  ng-model="prof" ng-keyup="checkPatternProfession()" ng-change="checkPatternProfession()">					
					<img src="resources/images/1.png" height="30"  ng-show="profession==false && def5==false">	
					<img src="resources/images/11.png" height="30" ng-show="profession==true && def5==false">	
				</div>
					<ul  ng-show="profession==false && def5==false">
						<li>Profession must have only letters!</li>
						<li>Profession must not contain numbers or symbols (12,.,/,\)!</li>
					</ul>									
		 	</div>
		 	<div class="container_eggrafhs_row">
		 		<label >Employment Institution:</label><br>
				<div style="display: inline-block;width: 100%;">
		 			<input placeholder="Not required field!" ="institution" ng-class="{'input_field_true' : institution==true && def6==false ,'input_field_false': institution==false && def6==false}" class="input_field" type="text"  ng-model="inst" ng-keyup="checkPatternInstitution()" ng-change="checkPatternInstitution()">					
					<img src="resources/images/1.png" height="30"  ng-show="institution==false && def6==false">	
					<img src="resources/images/11.png" height="30" ng-show="institution==true && def6==false">	
				</div>
					<ul  ng-show="institution==false && def6==false">
						<li>Employment Institution must have only letters!</li>
						<li>Employment Institution must not contain numbers or symbols (12,.,/,\)!</li>
					</ul>									
		 	</div>
		 	<div class="container_eggrafhs_row">
					<br><label >*Profile Photo:</label>   <br>
					<input type="file" name="photo" id="file-1" onchange="updateFileUploadLabel(this,this.value)"  ng-model="inputname" class="inputfile inputfile-1" required="required" />
					<label for="file-1">
						<svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17">
							<path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"/>
						</svg> 
						<span ng-hide="inpt1">Choose a file&hellip;</span>
						<span ng-show="inpt1">{{inputname}}</span>
					</label>
		 	</div>
		 	<br>
		 	<div style="float: left;margin-left: 5px;">
		 		<label>Required Fields *</label>
		 	</div>
		 	<br>
		 	<br>
		 	<a style="margin-right: 10px;" href="signup.jsp"><h4>Clear Fields</a>
			<input type="submit" class="submit" value="Submit" ng-show="signup_complete">
			<input type="submit" class="submit" value="Submit" disabled ng-hide="signup_complete">
		</form>
			
	</div><br>
</body>
</html>