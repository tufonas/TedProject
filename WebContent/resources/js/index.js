var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http,$interval,$window) {
	console.log("asdada");
	$scope.loadPage= function(){
		$http.get("Settings").then(function (response) {
				  console.log(response.data);
				  $scope.email = response.data.email;
			  });
	}
	$scope.checkEmailPassword = function(email,password){
		var data = {
				email: email,
				password: password
			};
		console.log(email+password);
		  $http.post("Login",JSON.stringify(data)).then(function (response) {
			  console.log(response.data);
			  if(response.data == "error") $scope.error = true;
			  else
			  {
				  $scope.error = false;
			  }
				  
		  });
	}
});