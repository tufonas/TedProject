var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http,$interval,$window) {
	$scope.is_friend = false ;
	$scope.loadPage= function(){
		console.log(url);
		if(url=="")
		{
			
			url = "PersonalData";
		}
		console.log(url);
		$http.get(url).then(function (response) {
				  console.log(response.data);
			      $scope.user = response.data.profile_user;
			      $scope.friends = response.data.friends;
			      $scope.logged_user = response.data.logged_user;
			      if(response.data.type_of_user=="admin"){
			    	  $scope.admin =true;
			      }else $scope.admin=false;
			      for(i=0;i<$scope.friends.length;i++)
			      {
			    	  if($scope.friends[i].id==$scope.logged_user.id)
			    	  {
			    		  $scope.is_friend = true ;
			    		  break ;
			    	  }
			    	  else
			    	  {
			    		  $scope.is_friend = false ;
			    	  }
			      }
			      console.log($scope.is_friend);
			      $scope.showData=false;
			      $scope.showSave =true;

			      $scope.hide_buttons =false;
			      $scope.showAddSkillButton = true;
			      $scope.showAddEducationButton = true;
			      if($scope.user.jobs.length == 0)
			      {
				      $scope.showprofession = false;
			      }else
				      $scope.showprofession = true;
			      if($scope.user.experience!=null)
			      {
				    	if($scope.user.experience.education!=null && $scope.user.experience.education!="")
				    	{
				    		$scope.showeducation = true;
				    		$scope.showAddEducationButton = false;
				    		if($scope.user.experience.education_visibility == "Public")
				    		{
				    				$scope.public_education=true;
				    				$scope.private_education=false;
				    		}
				    		else
				    		{
				    				$scope.public_education=false;
				    				$scope.private_education=true;
				    		}				    		
				    	}
				    	else
				    	{
				    		$scope.showeducation = false;
				    		$scope.showAddEducationButton = true;
				    	}
				    	if($scope.user.experience.skills!=null && $scope.user.experience.skills!="")
				    	{
				    		$scope.showskills = true;
				    		$scope.showAddSkillButton = false;
				    		if($scope.user.experience.skills_visibility == "Public")
				    		{
				    				$scope.public_skills=true;
				    				$scope.private_skills=false;
				    		}
				    		else
				    		{
				    				$scope.public_skills=false;
				    				$scope.private_skills=true;
				    		}
				    	}
				    	else
				    	{
				    		$scope.showAddSkillButton = true;
				    		$scope.showskills = false;
				    	}
				
			       }
			  });
	}
	$scope.showFriends = function(){
		$scope.showfriends = !$scope.showfriends;
		$window.scrollBy(0, 5000);
	}
	$scope.backToUsers = function(){
		 $window.location.href = 'diaxeirish.jsp';
	}
	$scope.backToProfile = function(){
		 $window.location.href = 'personalData.jsp';
	}
	$scope.addJob = function(){
		var data = {
			    profession: $scope.profession,
			    tomeas: $scope.tomeas,
			    business_name: $scope.business_name,
			    duties: $scope.duties,
			    achievements: $scope.achievements,
			};
		 $http.post("PersonalData",JSON.stringify(data)).then(function (response) {

			  $scope.tomeas="";
		      $scope.messages = response.data.messages;
		  });

	}
	$scope.changeInputName = function(name){
		console.log("Called with arg ",name);
		if(name===undefined)
			return;
		name = name.replace(/.*[\/\\]/, '');
		$scope.inputname = name;
		$scope.inpt1 = true;
	}
	$scope.addEducation = function(){
		var x = document.getElementById("container");
		var y = x.cloneNode(true);
		document.getElementById("field").append(y);

	}
	$scope.showUpload =function(){
		$scope.upload = !$scope.upload;
	}
	$scope.addSkills = function(){
		var x = document.getElementById("container");
		var y = x.cloneNode(true);
		document.getElementById("field").append(y);

	}
	$scope.showExperience = function(){
		$scope.experience = !$scope.experience;
		$scope.skills = false;
		$scope.education = false;
		console.log("experience");
		console.log($scope.showData);
		console.log($scope.showeducation);
		console.log($scope.showskills);
		console.log($scope.showprofession);
		console.log($scope.showSave);
		$scope.showData = false;
		$scope.showeducation =false;
		$scope.showskills = false;
		$scope.showprofession =false;	
		$scope.showSave =false;
		$scope.hide_buttons = true;
	}
	$scope.showEducation = function(){
		$scope.education = !$scope.education;
		$scope.experience =false;
		$scope.skills = false;
		console.log("education");
		console.log($scope.showData);
		console.log($scope.showeducation);
		console.log($scope.showskills);
		console.log($scope.showprofession);
		console.log($scope.showSave);
		$scope.showData = false;
		$scope.showeducation = false;
		$scope.showskills = false;
		$scope.showprofession =false;	
		$scope.showSave = false;
		$scope.hide_buttons = true;
	}
	$scope.showSkills = function(){
		$scope.skills = !$scope.skills;
		$scope.experience = false;
		$scope.education = false;
		console.log("skillss");
		console.log($scope.showData);
		console.log($scope.showeducation);
		console.log($scope.showskills);
		console.log($scope.showprofession);
		console.log($scope.showSave);
		$scope.showData = false;
		$scope.showeducation =false;
		$scope.showskills = false;
		$scope.showprofession =false;
		$scope.showSave =false;
		$scope.hide_buttons = true;
	}
	$scope.selectedItemChanged = function()
	{
		$scope.showData = true;
	}
	$scope.update = function()
	{
		var data = {
				edit_profession: $scope.edit_profession,
				edit_tomeas: $scope.edit_tomeas,
				edit_business_name: $scope.edit_business_name,
				edit_duties: $scope.edit_duties,
				edit_achievements: $scope.edit_achievements,
				edit_education : $scope.edit_education,
				edit_skills: $scope.edit_skills
			};
		 $http.post("PersonalData",JSON.stringify(data)).then(function (response) {

		  });
	}
	$scope.hide_profession_from_dropdown = function(value, index, array) {
		console.log($scope.is_friend);
		if($scope.logged_user.id!=$scope.user.id && !$scope.admin && !$scope.is_friend)
		{
			if(value.position_visibility=='Public')
			{
				return true;
			}
			else if(value.position_visibility=='Private')
			{
				return false;
			}			
		}
		else
		{
			return true;
		}
		

	};
	
	
});