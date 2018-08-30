myApp.controller("c_chatController", function($scope, $rootScope, chatService)
{
		console.log("Hello I am in chat");
			
		$scope.messages=[];
		$scope.message="";
		$scope.max=140;
				
		$scope.addMessage=function()
		{
			chatService.send($rootScope.CurrentUser.username+" ---> "+$scope.message);
			$scope.message="";
		};
		
		chatService.receive().then(null,null,function(message)
		{
			$scope.messages.push(message);
		});
});