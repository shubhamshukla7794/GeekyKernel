myApp.controller("c_friendController", function($scope, $rootScope, $location, $http, $route)
{
	
	
	$scope.user={'loginname':'','password':'','username':'','emailid':'','mobile':'','role':'','address':''}
	$scope.friend = {'friendid':'','loginname':'','friendname':'','status':''};
	
	$scope.friends;
	$scope.requestlistsize;
	
	$scope.suggestedList;
	$scope.pendingRequests;
	
	function friendList()
	{
		
		$http.get('http://localhost:8087/GeekyKernelMiddleware/friend/list')
		.then(function(response)
				{
					console.log("i am in friend List function");
					
					$scope.friends = response.data;
					
				});
	};
	friendList();
	
	function pendingFriendRequests()
	{
		$http.get('http://localhost:8087/GeekyKernelMiddleware/friend/pendingRequest')
		.then(function(response)
				{
					console.log("pending request list function");
					$scope.pendingRequests = response.data;
					$scope.requestlistsize = response.data;
				},
				function(response)
				{
					$scope.requestlistsize = undefined;
				});
	}
pendingFriendRequests();

function SuggestedList()
{
	console.log('Suggested List Displaying');
	$http.get('http://localhost:8087/GeekyKernelMiddleware/friend/suggested')
	.then(function(response)
			{
				$scope.suggestedList = response.data;
			});
}
SuggestedList();

$scope.sendRequest = function(loginname)
{
	$http.get('http://localhost:8087/GeekyKernelMiddleware/friend/sendRequest/'+loginname)
	.then(function(response)
			{
				console.log("Friend Request Sent");
				/*$location.path("/friendrequests");*/
				$route.reload();
			});
}

$scope.acceptRequest = function(friendid)
{
	$http.post('http://localhost:8087/GeekyKernelMiddleware/friend/acceptRequest/'+friendid)
	.then(function(response)
			{
				console.log("Friend Request Accepted");
				$location.path("/friends");
			});
}

$scope.deleteRequest = function(friendid)
{
	$http.delete('http://localhost:8087/GeekyKernelMiddleware/friend/deleteRequest/'+friendid)
	.then(function(response)
			{
				console.log("Friend Request Deleted");
				/*$location.path("/friends");*/
				$route.reload();
			});
}

$scope.unfriend = function(friendid)
{
	$http.delete('http://localhost:8087/GeekyKernelMiddleware/friend/deleteRequest/'+friendid)
	.then(function(response)
			{
				console.log("Friend Removed Successfully");
				
				$route.reload();
			});
}

	
});