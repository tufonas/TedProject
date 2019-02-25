var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http,$interval,$window) {
	$scope.decline_choice = []
	$scope.accept_choice = []
	$scope.msg = [];
	$scope.loadPage= function(){
		  $http.get("Notification").then(function (response) {
			  console.log(response); 
		      $scope.friend_requests = response.data.friend_requests;
		      for(i =0 ;i<$scope.friend_requests.length;i++)
		      {
		    	  $scope.msg[i] = true;
		    	  $scope.accept_choice[i] = true;
		    	  $scope.decline_choice[i] = true;
		      }

		      $scope.likes = response.data.likes;
		      $scope.comments = response.data.comments;
		      $scope.user = response.data.user;
	    	  console.log("1 "+$scope.comm_readed);
		      for(i =0 ;i<$scope.comments.length;i++)
		      {
		    	  if($scope.comments[i].user.id!=$scope.user.id)
		    	  {
		    		  $scope.comm_indx = i;
		    		  break;
		    	  }
		      }
		      for(i =0 ;i<$scope.likes.length;i++)
		      {
		    	  if($scope.likes[i].user.id!=$scope.user.id)
		    	  {
		    		  $scope.like_indx =  i;
		    		  break;
		    	  }
		      }
		  });
	}
	$scope.accept = function(id,index){
		
		for(i =0 ;i<$scope.friend_requests.length;i++)
		{
			if(index == i)
			{
				$scope.msg[i]=false;
				 $scope.accept_choice [i]= false;
			}
		}
		var data = {
				choice: 'accept',
				friend: id,
			};

		  $http.post("Notification",JSON.stringify(data)).then(function (response) {
			  console.log(response); 
			 
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
		
		console.log("GET TIME()");
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
			console.log("month="+month+" day="+day);

			day--;
			if(day==-1)
				day=6;
			if(month==0)month="Jan";else if(month==1)month="Feb";else if(month==2)month="Mar";else if(month==3)month="Apr";else if(month==4)month="May";else if(month==5)month="June";
			else if(month==6)month="July";else if(month==7)month="Aug";else if(month==8)month="Sept";else if(month==9)month="Octob";else if(month==10)month="Nov";else if(month==11)month="Dec";
			if(day==0)day="Mon";else if(day==1)day="Tue";else if(day==2)day="Wed";else if(day==3)day="Thu";else if(day==4)day="Fri";else if(day==5)day="Sat";else if(day==6)day="Sun";
			if(minutes<10)minutes = "0"+ minutes;
			var string = day+" "+date.getDate()+" "+month+" "+ date.getHours().toString()+":"+minutes.toString();
			return string;
		}
	}
	$scope.reject = function(id,index){
		var data = {
				choice: 'reject',
				friend: id,
			};
		for(i =0 ;i<$scope.friend_requests.length;i++)
		{
			if(index == i)
			{
				 $scope.decline_choice[i]= false;
			}
		}
		  $http.post("Notification",JSON.stringify(data)).then(function (response) {
			  console.log(response); 
			  $scope.loadPage();
		  });
	}
	$scope.showProfile = function(id){
		var url = "profile.jsp?friend="+id;
		$window.location.href = url;
	}
	$scope.goToArticle = function(art_id,user_id,comm_id){

		var url = "showArticle.jsp?article_id="+art_id+"&user_id="+user_id+"&comm_id="+comm_id;
		$window.location.href = url;
	}
	$scope.goToArticle1 = function(art_id,user_id){
		
		var url = "showArticle.jsp?article_id="+art_id+"&user_id="+user_id;
		$window.location.href = url;
	}
});