var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http,$interval,$window) {
	
	$scope.applied=[];
	$scope.myAds=[];
	$scope.loadPage= function(){
		console.log("load page")
		$http.get("Aggelies").then(function (response) {
				  console.log(response.data);
				  $scope.ads = response.data.ads;
				  $scope.user = response.data.user;
				  $scope.myAds = response.data.applAds;
				  for( j=0;j<$scope.ads.length;j++)
				  {
					  $scope.applied[j]=true;
					  for( i=0;i<$scope.ads[j].applications.length;i++)
					  {
						  if($scope.ads[j].applications[i].user.id == $scope.user.id )
						  {
							  $scope.applied[j] = false;
					      }
					  }
				  }
				  console.log($scope.applied);
			  });
	}
	
	$scope.addAdvertisment = function(){
		$scope.showAdvertisment = !$scope.showAdvertisment;
	}
	$scope.apply = function(id){
		var data = {
				advertisment_id: id
			};
		 $http.post("Aggelies",JSON.stringify(data)).then(function (response) {
				$scope.loadPage();
			  });
	}
	$scope.cancel = function(id){
		var data = {
				cancel_id: id
			};
		 $http.post("Aggelies",JSON.stringify(data)).then(function (response) {
				$scope.loadPage();
			  });
	}
	$scope.deleteAd = function(id,user){
		var data = {
				delete_id: id,
				chosen_user:user,
			};
		 $http.post("Aggelies",JSON.stringify(data)).then(function (response) {
				$scope.loadPage();
			  });		
	}
	
	
});