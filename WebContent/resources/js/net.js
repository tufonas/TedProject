var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http,$interval) {
	$scope.loadPage= function(){
		$http.get("Net").then(function (response) {
				  console.log(response.data);
				  $scope.friends = response.data;
			  });
	}
	$scope.changeEmail = function(){
		$scope.new_email = !$scope.new_email;
		$scope.change_email = !$scope.change_email;
		$scope.hidePassword = !$scope.hidePaswword;
	}
	$scope.deleteUserFromFriends = function(id){
		var data = {
				removed_id: id
			};

		  $http.post("Net",JSON.stringify(data)).then(function (response) {
			  $scope.loadPage();
		  });
	}
});