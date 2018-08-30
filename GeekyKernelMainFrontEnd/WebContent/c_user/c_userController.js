myApp.controller("c_userController", function($scope, $http, $rootScope, $location,$cookieStore,$route)
{
	$scope.user={'loginname':'','password':'','username':'','emailid':'','mobile':'','role':'','address':''};
	
	$scope.CurrentUser;
	
	$scope.login = function()
	{
		console.log('Im in check login');		
		console.log($scope.user);
		$http.post('http://localhost:8087/GeekyKernelMiddleware/checkLogin', $scope.user)
		.then(function(response)
				{
					console.log("login function");
					$scope.user = response.data;
					$rootScope.CurrentUser = $scope.user;
					$cookieStore.put('userDetail',response.data);
					console.log("login Successful: ");
					
					$location.path("/loggedin");
				});
	}
	
	$scope.register = function()
	{
		console.log("register page");
		
		$http.post('http://localhost:8087/GeekyKernelMiddleware/registerUser', $scope.user)
		.then(function(response)
				{
					console.log("registered successfully");
					alert("Registered Successfully")
					$location.path("/login");
				},
				function(response)
				{
					alert("Please Enter Valid Data")
				});
}
	
	
	
	
	$scope.logout = function()
	{
		console.log("Logging Out");
		alert("Logged Out Successfully")
		$location.path("/login");
		$rootScope.CurrentUser = undefined;
		$cookieStore.remove('userDetail');
		$window.location.reload();
}
	
	

});