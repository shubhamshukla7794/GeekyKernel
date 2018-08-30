myApp.controller("c_forumController", function($location, $http, $rootScope, $scope)
{
		$scope.forum = {'forumId':'','forumName':'','forumContent':'','createDate':'','status':''}
		$scope.allForumData;
		
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
});