myApp.controller("c_forumController", function($location, $http, $rootScope, $scope, $route)
{
	$scope.forum = {'forumId':'','forumName':'','forumContent':'','createDate':'','status':''}
	$scope.forumComment = {'forumid':'','loginname':'','discussionDate':'','discussiontxt':'','commemtid':''}
	$scope.allForumData;
	$scope.forumcommentid;
		
	$scope.addForum = function()
	{
		console.log("adding forum");
		$http.post('http://localhost:8087/GeekyKernelMiddleware/forum/add', $scope.forum)	
		.then(function(response)
				{
					alert('added successfully')
					$location.path("/showForum");				
				});
	}
		
	function listForum()
	{
		$http.get('http://localhost:8087/GeekyKernelMiddleware/forum/list')
		.then(function(response)
				{
					console.log("listing forum");
					$scope.allForumData = response.data;
				});
	}
	listForum();

	$scope.deleteForum = function(forumId)
	{
		console.log("deleting forum");
		$http.delete('http://localhost:8087/GeekyKernelMiddleware/forum/delete/'+forumId)
		.then(function(response)
				{
						
					alert("Forum Deleted Successfully")
					$location.path("/showForum");
				});
	}
		
		
		
	$scope.approveForum = function(forumid)
	{
		console.log("approving forum");
		$http.put('http://localhost:8087/GeekyKernelMiddleware/forum/approve/'+forumid)
		.then(function(response)
				{
					console.log("forum approved");
					$location.path("/showForum");
				});
	}
		
	$scope.rejectForum = function(forumid)
	{
		console.log("approving forum");
		$http.put('http://localhost:8087/GeekyKernelMiddleware/forum/reject/'+forumid)
		.then(function(response)
				{
					console.log("forum rejected");
					$location.path("/showForum");
				});
	}
		
		
		
	$scope.commentedforum = function(forumId)
	{
		$http.get('http://localhost:8087/GeekyKernelMiddleware/forum/get/'+forumId)
		.then(function(response)
				{
					$rootScope.forumcommentid = forumId;
					$location.path("/forumcomment");
				});
			
	}
		
	$scope.commentforum = function()
	{
		$http.post('http://localhost:8087/GeekyKernelMiddleware/forum/comment', $scope.forumComment)
		.then(function(response)
				{
					$route.reload();
					alert("Thank u for commenting");
					console.log("commented succesfully");
				});
	}
		
		
	function forumcommentlist()
	{
		$http.post('http://localhost:8087/GeekyKernelMiddleware/forum/listComments/'+$rootScope.forumcommentid)
		.then(function(response)
				{
					console.log("loading comments "+$rootScope.forumcommentid);
					$rootScope.forumcommentedData = response.data;
				});
	}
	forumcommentlist();
	listForum();
});










