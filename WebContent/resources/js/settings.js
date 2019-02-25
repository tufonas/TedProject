var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http,$interval) {
	$scope.loadPage= function(){
		$http.get("Settings").then(function (response) {
				  console.log(response.data);
				  $scope.email = response.data.email;
			  });
	}
	$scope.changeEmail = function(){
		$scope.new_email = !$scope.new_email;
		$scope.change_email = !$scope.change_email;
		$scope.hidePassword = !$scope.hidePaswword;
	}
	$scope.changePassword = function(){
		$scope.old_password = !$scope.old_password;
		$scope.new_password = !$scope.new_password;
		$scope.confirm_password = !$scope.confirm_password;
		$scope.change_pass = !$scope.change_pass;
		$scope.hideEmail = $scope.hideEmail;
	}
});