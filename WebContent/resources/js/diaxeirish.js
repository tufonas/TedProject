var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http,$interval,$window) {
	$scope.selected = false;
	$scope.loadPage= function(){
		$http.get("Diaxeirish").then(function (response) {
				  console.log(response.data);
				  $scope.users = response.data;
			  });
	}
	$scope.selectAll = function(){
		$scope.selected = !$scope.selected;
	}
	$scope.deleteUser = function(id){
		if(window.confirm("Are you sure, you want to delete this user?"))
		{
			var data = {
					user_id: id
				};

			  $http.post("Diaxeirish",JSON.stringify(data)).then(function (response) {
				  console.log(response);
				  $scope.loadPage();
			  });
		}
	}
	
});