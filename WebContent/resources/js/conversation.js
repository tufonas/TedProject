

var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http,$interval) {
	//$interval(function(){ $scope.loadPage(); }, 5000);
	$scope.loadPage= function(){
		$scope.friendid=friend_Id;
		console.log("asdasd");
		if(url!="")
		{
			  $http.get(url).then(function (response) {
				  console.log(response);
			      $scope.users = response.data.user;
			      $scope.messages = response.data.messages;
			  });
		}
		else
		{
			
			  $http.get("Conversation").then(function (response) {
				  console.log(response); 
			      $scope.users = response.data.user;
			      $scope.friendid=response.data.user[0].id;
			      $scope.messages = response.data.messages;
			      
			  });
		}

	}
	$scope.selectedIndex = 0;
	$scope.userClick=function(id,$index){
		console.log(id);
		$scope.friendid=id;
		$scope.chosenuser=true;
		$scope.selectedIndex = $index;
		url="Conversation?friend="+id;
		var urlfriend="Conversation?refresh="+id;
		 $http.get(urlfriend).then(function (response) {
			  console.log(response);
		      $scope.messages = response.data.messages;
		  });
	}
	$scope.takeUser = function(user,$index){
		if($scope.selectedIndex == $index)
		{
			$scope.user = user;
		}
	}	
	$scope.submit=function(){ 
		console.log($scope.msg+$scope.friendid);
		var data = {
			    msg: $scope.msg,
			    friend: $scope.friendid
			};
		 $http.post("Conversation",JSON.stringify(data)).then(function (response) {
			  console.log(response);
			  $scope.msg="";
			  console.log(response.data);
		      $scope.messages = response.data.messages;
		  });
	}
	$scope.chooseUser=function(user){
		if(user.user.id == $scope.friendid){
			return false;
		}
		else {
			return true;
		}
			
	}

});


