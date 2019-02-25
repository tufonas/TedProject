var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http,$interval,$window) {
	$scope.liked=[];
	$scope.disliked=[];
	$scope.showFiles=[];
	$scope.showImage=[];
	$scope.showVideo=[];
	$scope.showAudio=[];
	$scope.loadPage= function(){
		console.log("load page");
		$http.get(url).then(function (response) {
				  console.log(response.data);
				  $scope.articles = response.data.articles;
				  $scope.likes = response.data.likes;
				  $scope.user = response.data.user;
				  $scope.comments = response.data.comments;
				  $scope.active = response.data.active_comm_id;
				  for(j=0;j<$scope.articles.length;j++)
				  {	
					  
					  $scope.showFiles[j]=false;
					  $scope.liked[j]=true;
					  $scope.showImage[j] = false;
					  $scope.showVideo[j] = false;
					  $scope.showAudio[j] = false;
					  $scope.disliked[j]=false;
					  for(i=0;i<$scope.likes.length;i++)
					  {
						  
						  if($scope.articles[j].images!=null)
						  {
							  
							  $scope.showImage[j] = true;
							  $scope.showFiles[j]=true;
						  }
						  if($scope.articles[j].videos!=null)
						  {
							  
							  $scope.showVideo[j] = true;
							  $scope.showFiles[j]=true;
						  }
						  if($scope.articles[j].sounds!=null)
						  {
							
							  $scope.showAudio[j] = true;
							  $scope.showFiles[j]=true;
						  }
					      if($scope.user.id == $scope.likes[i].user.id && $scope.likes[i].article.articleId == $scope.articles[j].articleId)
						  {
							  $scope.liked[j] = false;
							  $scope.disliked[j] = true;
							  break;
						  }
						  else
						  {
							  $scope.liked[j] = true;
							  $scope.disliked[j] = false;
						  }	
				      }
				  }

			  });
	}
	$scope.like = function(id,$index){
		var data = {
				like: id
			};

		  $http.post("Home",JSON.stringify(data)).then(function (response) {
			  console.log(response);
			  $scope.loadPage();
		      //$scope.users = response.data.user;
		     // $scope.messages = response.data.messages;
		  });
		 
	}
	
	$scope.dislike = function(id,$index){
		var data = {
				dislike: id
			};
		  $http.post("Home",JSON.stringify(data)).then(function (response) {
			  console.log(response);
			  $scope.loadPage();
		  });
		
	}
	$scope.newComment=[];
	$scope.comment = function($index){
		$scope.newComment[$index]=!$scope.newComment[$index];
	}
	$scope.submitComment = function(id,Comment,$index){
		$scope.newComment[$index]=false;
		console.log(Comment);
		var data = {
				comment: Comment,
				article_id: id
			};
		console.log(data);
		 $http.post("Home",JSON.stringify(data)).then(function (response) {
			$scope.loadPage();
		  });
		
	}
	$scope.getTime = function(time){
		var curr_date = new Date();
		var curr_day = curr_date.getDate();
		var curr_month = curr_date.getMonth();
		var curr_hours = curr_date.getHours(); 
		var curr_min = curr_date.getMinutes();
		var curr_sec = curr_date.getSeconds();
		
		
		var date = new Date(time);
		var month_day = date.getDate();
		var month = date.getMonth();
		var day = date.getDay();
		var minutes = date.getMinutes();
		var hours = date.getHours();
		var sec = date.getSeconds();
		
		if(curr_day - month_day == 0 && curr_month-month == 0 )
		{
			var time;
			if(curr_hours - hours == 0 && curr_min - minutes ==0){
				time =curr_sec-sec;
				return time.toString()+" seconds ago";
			}
			else if(curr_hours - hours == 0 && curr_min - minutes !=0){
				time=(curr_min-minutes);
				return time.toString()+" minutes ago";
			}
			else if(curr_hours - hours != 0 ){
				time = (curr_hours-hours);
				return time.toString()+" hours ago";
			}
		}
		else
		{
			day--;
			if(day==-1)
				day=6;
			if(month==0)month="Jan";else if(month==1)month="Feb";else if(month==2)month="Mar";else if(month==3)month="Apr";else if(month==4)month="May";else if(month==5)month="June";
			else if(month==6)month="July";else if(month==7)month="Aug";else if(month==8)month="Sept";else if(month==9)month="Octob";else if(month==10)month="Nov";else if(month==11)month="Dec";
			if(day==0)day="Mon";else if(day==1)day="Tue";else if(day==2)day="Wed";else if(day==3)day="Thu";else if(day==4)day="Fri";else if(day==5)day="Sat";else if(day==6)day="Sun";
			if(minutes<10)minutes = "0"+ minutes;
			var string =date.getDate()+" "+month+" "+ date.getHours().toString()+":"+minutes.toString();
			return string;
		}
	}

	
});