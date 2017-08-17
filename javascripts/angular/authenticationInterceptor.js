app.config(function Config($httpProvider){
  $httpProvider.interceptors.push('authHttpResponseInterceptor');
	//$httpProvider.interceptors.push('retryInterceptor');
})

app.factory('authHttpResponseInterceptor',['$q','$injector',function($q,$injector) {

    var getRedirectLink = function(){
    	var userDetailsString = localStorage.getItem("careUpdate.userDetails");
    	if(userDetailsString){
    		var userDetails = JSON.parse(userDetailsString);
    		switch(userDetails.role){
    			case "Sender": return "/login/login-staff";
    			break;
    			case "Patient": return "/login/login-patient";
    			break;
    			default: return "/login";
    			break;
    		}
    	}
    	else return "/"
    }
	
 
    var refreshToken = function(){
    	var $http = $injector.get('$http');
    	return $http.post("/api/v1/token/refresh", {
    		"userToken": localStorage.getItem("careUpdate.userToken"),
    		"refreshToken": localStorage.getItem("refreshToken")
    	}).then(function success(response){
    		return response.data.payload.data;
    	}, function error(error){
    		console.log(error);
    	})
    }
    
	return {
        response: function(response){
            return response || $q.when(response);
        },
        responseError: function(rejection) {
        	if ((rejection.status === 401 || rejection.status === 403) && !rejection.config.retry){
	    		return refreshToken().then(function success(tokens){
	    			rejection.config.retry = 1;
            		localStorage.setItem("careUpdate.userToken",tokens.userToken);
            		localStorage.setItem("refreshToken", tokens.refreshToken);
            		rejection.config.headers.Authorization = "Bearer " + localStorage.getItem("careUpdate.userToken");
            		return $injector.get('$http')(rejection.config);
            	});
	    	}
	    	else{
	    		window.location = getRedirectLink();
	    		return $q.reject(rejection);
	    	}
        }
    };
}]);

/*authHttpResponseInterceptor is used instead
app.factory('retryInterceptor', function($q, $injector) {
	var refreshToken = function(){
    	var $http = $injector.get('$http');
    	return $http.post("/api/v1/token/refresh", {
    		"userToken": localStorage.getItem("careUpdate.userToken"),
    		"refreshToken": localStorage.getItem("refreshToken")
    	}).then(function success(response){
    		return response.data.payload.data;
    	}, function error(error){
    		console.log(error);
    	})
    }
	  return {
	    responseError: function(rejection) {
	    	if (rejection.status === 401 || rejection.status === 403){
	    		return refreshToken().then(function success(tokens){
            		localStorage.setItem("careUpdate.userToken",tokens.userToken);
            		localStorage.setItem("refreshToken", tokens.refreshToken);
            		rejection.config.headers.Authorization = "Bearer " + localStorage.getItem("careUpdate.userToken");
            		return $injector.get('$http')(rejection.config);
            	});
	    	}
	    	else{
	    		return $q.reject(rejection);
	    	}
	    }
	  };
})*/
