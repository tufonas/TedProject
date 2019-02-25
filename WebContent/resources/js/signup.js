var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http,$interval,$window) {
	$scope.validate_page= function(){
		
		if($scope.string && $scope.sur && $scope.email && $scope.check && $scope.telephone && $scope.inpt1)
		{
			$scope.signup_complete = true;

		}else
			$scope.signup_complete = false;
	}
	$scope.checkPatternName = function(){
		var strings=/^[a-zA-Zα-ωΑ-Ω]+$/;
		if($scope.name==undefined)
		{
			$scope.def=true;
		}
		else
		{

			$scope.def=false;
			if($scope.name.match(strings)){
				$scope.string = true;

			}else $scope.string = false;
		}
		$scope.validate_page();
	}
	$scope.checkPatternSurname = function(){
		var strings=/^[a-zA-Zα-ωΑ-Ω]+$/;
		if($scope.surname==undefined)
		{
			$scope.def1=true;
		}
		else
		{
			$scope.def1=false;
			if($scope.surname.match(strings)){
				$scope.sur = true;
			}else $scope.sur = false;
		}
		$scope.validate_page();
	}
	$scope.checkPatternProfession = function(){
		var strings=/^[a-zA-Zα-ωΑ-Ω]+$/;
		if($scope.prof==undefined)
		{
			$scope.def5=true;
		}
		else
		{
			$scope.def5=false;
			if($scope.prof.match(strings)){
				$scope.profession = true;
			}else 
			{
				$scope.profession = false;
			}
		}
		$scope.validate_page();
	}
	$scope.changeInputName = function(name){
		console.log("Called with arg ",name);
		if(name===undefined)
			return;
		name = name.replace(/.*[\/\\]/, '');
		$scope.inputname = name;
		$scope.inpt1 = true;
		$scope.validate_page();
	}
	$scope.checkPatternInstitution = function(){
		var strings=/^[a-zA-Zα-ωΑ-Ω]+$/;
		if($scope.inst==undefined)
		{
			$scope.def6=true;
		}
		else
		{
			$scope.def6=false;
			if($scope.inst.match(strings)){
				$scope.institution = true;
			}else $scope.institution = false;
		}
		$scope.validate_page();
	}	
	
	$scope.checkPatternEmail = function(){
		var e_mails=/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
		if($scope.e_mail==undefined)
		{
			$scope.def2=true;
		}
		else
		{
			$scope.def2=false;
			if($scope.e_mail.match(e_mails)){
				$scope.email = true;
			}else $scope.email = false;
		}
		$scope.validate_page();
	}
	$scope.checkPatternPassword = function(){
		var very_easy_password=/^[0-9]{1,7}$/;
		var very_easy_password1=/^[a-zA-Zα-ωΑ-Ω]{1,7}$/;
		var easy_password=/^[0-9]{8,200}$/;
		var medium_password=/^[a-zA-Zα-ωΑ-Ω0-9]{1,6}$/;
		var medium_hard_password=/^[a-z0-9]{7,20}$/;
		var hard=/^[" ",.!@#$%^&*()|{}*/]$/;
		
		if($scope.pass==undefined)
		{
			$scope.def3=true;
		}
		else
		{
			console.log("asda");
			$scope.def3=false;
			if($scope.pass.match(very_easy_password))
			{
				$scope.pass0 = true;
				$scope.pass1 = true;
				$scope.pass2 = false;
				$scope.pass3 = false;
				$scope.pass4 = false;
				$scope.pass5 = false;
			}
			else if($scope.pass.match(very_easy_password1))
			{
				$scope.pass0 = true;
				$scope.pass1 = true;
				$scope.pass2 = false;
				$scope.pass3 = false;
				$scope.pass4 = false;
				$scope.pass5 = false;
			}
			else if($scope.pass.match(easy_password))
			{
				$scope.pass0 = false;
				$scope.pass1 = false;
				$scope.pass5 = false;
				$scope.pass3 = false;
				$scope.pass4 = false;
				$scope.pass2 = true;
			}
			else if($scope.pass.match(medium_password))
			{
				$scope.pass3 = true;
				$scope.pass0 = false;
				$scope.pass1 = false;
				$scope.pass2 = false;
				$scope.pass4 = false;
				$scope.pass5 = false;
			}
			else if($scope.pass.match(medium_hard_password))
			{
				$scope.pass4 = true;
				$scope.pass0 = false;
				$scope.pass1 = false;
				$scope.pass2 = false;
				$scope.pass3 = false;
				$scope.pass5 = false;
			}
			else if($scope.pass.match(hard))
			{
				$scope.pass5 = true;
				$scope.pass0 = false;
				$scope.pass1 = false;
				$scope.pass2 = false;
				$scope.pass3 = false;
				$scope.pass4 = false;
			}
		}
		

	}
	$scope.checkPass = function(){
		if($scope.check_pass!=undefined)
		{
			if($scope.pass == $scope.check_pass)
			{
				$scope.check =true;
			}
			else $scope.check =false;
	   }
	}
	$scope.backToIndex = function(){
		var url = "index.jsp";
		$window.location.href = url;
	}
	$scope.checkPatternTelephone = function(){
		var thlefwno=/^[0-9]{10}(?:List)?$/;
		if($scope.tel==undefined)
		{
			$scope.def4=true;
		}
		else
		{
			console.log("asd");
			$scope.def4=false;
			if($scope.tel.match(thlefwno)){
				$scope.telephone = true;
			}else $scope.telephone = false;
		}
		$scope.validate_page();
	}
});