(function(window){
	function nekiService(){
	  var privateMetoda = function(){
		  
	  }
	  var service = {
			  publicMetoda : function(){
				  return privateMetoda();
			  }
	  }
	  return service;
	}
	window.nekiService = nekiService();
})(window);
