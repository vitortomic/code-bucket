$scope.setHistory = function(){
		history.pushState(null, null, $location.absUrl());
		window.addEventListener('popstate', backToGroupReview);
	}
	
	function backToGroupReview(){
		$scope.settingPrimaryContact = false;
	  $scope.$digest();
	}
	
	$scope.$on("$destroy", function(){
		window.removeEventListener('popstate', backToGroupReview);
	});
