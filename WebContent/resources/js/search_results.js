var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http,$interval) {
	$scope.loadPage= function(){
		console.log(url);
		$http.get(url).then(function (response) {
				  console.log(response.data);
				  $scope.usersByName = response.data.usersByName;
				  $scope.usersBySurname = response.data.usersBySurname;
				  $scope.usersByNameSurname = response.data.usersByNameSurname;
				  $scope.user = response.data.user;
				  $scope.friends = response.data.friends;
				  $scope.friend_requests = response.data.friend_requests;
				  
				  $scope.users = [];
				  $scope.cancel = [];
				  $scope.users.push.apply($scope.users,$scope.usersByName);
				  $scope.users.push.apply($scope.users,$scope.usersBySurname);
				  $scope.users.push.apply($scope.users,$scope.usersByNameSurname);
				  $scope.connected=[];
				  
				  
				  for(i=0;i<$scope.users.length;i++)
				  {
					  $scope.connected[i] = false;
					  $scope.cancel[i] = false;
					  for(j=0;j<$scope.friends.length;j++)
				      {
						  if($scope.friends[j].id == $scope.users[i].id)
					      {
							  
							  $scope.connected[i]=true;
							  break;
					      }
				      }
					  for(j=0;j<$scope.friend_requests.length;j++)
					  {
						  if($scope.friend_requests[j].id == $scope.users[i].id)
						  {
							  $scope.cancel[i] = true;
						  }
						  
					  }
				  }
				  console.log($scope.connected);
				  console.log($scope.users);
			  });
	}
	$scope.addUser = function(id){
		console.log(id);
		var data = {
				friend_id: id
			};
		console.log(data);
		  $http.post("SearchResults",JSON.stringify(data)).then(function (response) {
			  console.log(response.data);
			  $scope.conn_request = true;
			  $scope.loadPage();
		  });		
	}
	$scope.cancelRequest = function(id){
		console.log(id);
		var data = {
				cancel_id: id
			};
		console.log(data);
		  $http.post("SearchResults",JSON.stringify(data)).then(function (response) {
			  console.log(response.data);
			  $scope.loadPage();
		  });	
	}
});


