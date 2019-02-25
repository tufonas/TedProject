var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http,$interval,$window) {
	$scope.liked=[];
	$scope.disliked=[];
	$scope.like_number=[];
	$scope.showImage=[];
	$scope.inpt = false;
	$scope.showVideo=[];
	$scope.showAudio=[];
	$scope.showFiles=[];
	$scope.loadPage= function(){
		console.log("load page")
		console.log($scope.inpt);
		$http.get("Home").then(function (response) {
				  console.log(response.data);
				  if(response.data == 'false')
				  {
					  $window.location.href = 'index.jsp';
				  }
				  $scope.friends = response.data.friends;
				  $scope.articles = response.data.articles;
				  $scope.likes = response.data.likes;
				  $scope.user = response.data.user;
				  $scope.comments = response.data.comments;
				  if($scope.user.experience!=null)
				  {
					  if($scope.user.experience.skills_visibility=="Public")
					  {
						  $scope.public_skills=true;
						  $scope.private_skills=false;
					  }
					  else if($scope.user.experience.skills_visibility=="Private")
					  {
						  $scope.private_skills=true;
						  $scope.public_skills=false;
					  }
					  else
					  {
						  $scope.private_skills=false;
						  $scope.public_skills=false;  
					  }
					  if($scope.user.experience.education_visibility=="Public")
					  {
						  $scope.public_education=true;
						  $scope.private_education=false;
					  }
					  else if($scope.user.experience.education_visibility=="Private")
					  {
						  $scope.private_education=true;
						  $scope.public_education=false;
					  }	
					  else
					  {
						  $scope.public_education=false;
						  $scope.private_education=false; 
					  }					  
				  }
				  for(j=0;j<$scope.articles.length;j++)
				  {	
					  $scope.liked[j]=true;
					  $scope.disliked[j]=false;
					  $scope.showImage[j] = false;
					  $scope.showVideo[j] = false;
					  $scope.showAudio[j] = false;
					  $scope.showFiles[j]=false;
					  
					  $scope.like_number[j]=0;
					  
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
					  for(i=0;i<$scope.likes.length;i++)
					  {
							if($scope.articles[j].articleId == $scope.likes[i].article.articleId)
							{
								$scope.like_number[j]=$scope.like_number[j]+1;		
							}
					  }
					  for(i=0;i<$scope.likes.length;i++)
					  {
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
	$scope.goToProfile = function()
	{
		var url = "personalData.jsp";
		$window.location.href = url;
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
		//console.log("GET TIME()");
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
			//console.log("month="+month+" day="+day);
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
	$scope.addArticle = function(){
		$scope.showArticle = !$scope.showArticle;
	}
	$scope.changeInputName1 = function(name){
		console.log("Called with arg ",name);
		if(name===undefined)
			return;
		name = name.replace(/.*[\/\\]/, '');
		$scope.inputname1 = name;
		$scope.inpt1 = true;
	}
	$scope.changeInputName2 = function(name){
		console.log("Called with arg ",name);
		if(name===undefined)
			return;
		name = name.replace(/.*[\/\\]/, '');
		$scope.inputname2 = name;
		$scope.inpt2 = true;
	}
	$scope.changeInputName3 = function(name){
		console.log("Called with arg ",name);
		if(name===undefined)
			return;
		name = name.replace(/.*[\/\\]/, '');
		$scope.inputname3 = name;
		$scope.inpt3 = true;
	}	
	$scope.like = function(id,$index){
		var data = {
				like: id
			};

		  $http.post("Home",JSON.stringify(data)).then(function (response) {
			  console.log(response);
			  $scope.loadPage();
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

	
});