# Welcome to GeekyKernel!

**GeekyKernel** is a collaborative type project created during the course tenure in NIIT. The project focuses on creating a collaboration tool that can be used by multiple people where they can share their ideas using Blogs and Forum. They can also send and accept or reject friendship request and can also group chat with their friends.

## Built With
- Java - Creating Backend and Middleware

- HTML, CSS - Creating FrontEnd

- AngularJS - Framework to enhances HTML working

- Spring Framework - The web framework used

- Maven - Dependency Management

- Oracle 11g Database - For Database

- Eclipse Oxygen.3 IDE - IDE Environment to create the project

- Junit Test Case - For Testing BackEnd

- Postman Tool - For Testing MiddleWare


##  Recent Problem Encountered and Solution I Found

In the project the validation were not present. Then I made following changes in my code for validations to work perfectly in the Register Page. 

## Registration

### BackEnd

 **Changes in Model Class** 
I added
   *  javax.validation.constraints.Pattern    
   *  javax.validation.constraints.Size    
   * org.hibernate.validator.constraints.Email    
   * org.hibernate.validator.constraints.NotEmpty

*The update UserDetail Model Class*


        public class UserDetail 
        {   	
        	@Id
        	@Pattern(regexp = "^[a-zA-Z0-9_-]{3,20}$")
        	private String loginname;
        	
        	@NotEmpty(message = "Please enter your password.")
            @Size(min = 6, max = 15, message = "Your password must between 6 and 15 characters")
        	private String password;
        	
        	@Pattern(regexp = "^[a-zA-Z ]{3,50}$")
        	private String Username;
        	
        	@NotEmpty @Email
        	private String emailId;
        	
        	@Pattern(regexp = "^(\\+\\d{1,2}[- ]?)?\\d{10}$")
        	private String mobile;
        	
        	private String roles;
        	
        	@Pattern(regexp = "^[a-zA-Z0-9-,() ]{3,250}$")
        	private String address; 
        	
        	//Getter Setter Methods
        }
    
   

### FrontEnd

   **Changes in Controller**
    		
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
    			alert("Please Enter Valid Credentials as per the instructions!!!")
    		});
    	}
   
- **Output Images**
  * The new Register Page
![New Register Page](https://github.com/shubhamshukla7794/GeekyKernel/blob/master/ScreenShots/New%20Register%20Page.JPG)
  * The Alert Message
  ![Alert msg of Register](https://github.com/shubhamshukla7794/GeekyKernel/blob/master/ScreenShots/Alert%20msg%20of%20Register.JPG)

## Login
Now an alert is shown if wrong credentials are provided.

### Middleware
**Changes in Middleware**

		
	@PostMapping("/checkLogin")
	public ResponseEntity<UserDetail> checkLogin(@RequestBody UserDetail userDetail, HttpSession session)
	{
		UserDetail tempUserDetail = userDAO.checkUser(userDetail);
		try 
		{
			if (tempUserDetail != null)
			{
				session.setAttribute("userDetail", tempUserDetail);
				session.setAttribute("loginname", tempUserDetail.getLoginname());
				return new ResponseEntity<UserDetail>(tempUserDetail, HttpStatus.OK);

			} 
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			return new ResponseEntity<UserDetail>(tempUserDetail, HttpStatus.NOT_FOUND);				
		}
	    return new ResponseEntity<UserDetail>(tempUserDetail, HttpStatus.OK);
	}


### FrontEnd
**Changes in Controller**
	
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
		},
		function(response)
		{
			alert("Please Enter Valid Credentials!!!")
		});
	}

**Output Image**
![Alert msg of Login](https://github.com/shubhamshukla7794/GeekyKernel/blob/master/ScreenShots/Alert%20msg%20of%20Login.JPG)


##  Author

Project created by ;

**SHUBHAM KUMAR SHUKLA**