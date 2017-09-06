.directive(	
'buttonsRadioMultiselect',
function() {
return {
restrict : 'E',
scope : {
	modeli : '=',
	optionsi : '=',
	result : '=',
	exclusive : '='
},
controller : function($scope) {
	$scope.addOrRemoveOption = function(opti) {

		/*
		 * Exclusive option is an option that when selected, all other options are unselected.
		 * When any other option is selected, the exclusive option is unselected if it is
		 * already selected.
		 */
		function removeExclusive(exclusiveOption){
			//if exclusiveOption is already selected remove it
			var indexOfAll = $scope.modeli.indexOf(
					$scope.optionsi[$scope.optionsi.map(function(o){
						return o.text;
					}).indexOf(exclusiveOption)].choice
			);
			if(indexOfAll > -1){
				$scope.modeli.splice(indexOfAll,1);
			}
			//if exclusiveOption is being selected, remove all other selections
			if(opti.text === exclusiveOption){
				if($scope.modeli.includes(opti.choice)){
							$scope.modeli = [];
							$scope.result = $scope.modeli.join();
							return;
						}
						$scope.modeli = [];
						$scope.modeli.push(opti.choice);
						$scope.result = $scope.modeli.join();
						return;
					}
				}

				removeExclusive("All");
				if($scope.exclusive && $scope.exclusive.length>0){
					var exclusiveOptions = $scope.exclusive.split(",");
					for(var i = 0;i<exclusiveOptions.length;i++){
						var optionName = exclusiveOptions[i];
						removeExclusive(optionName);
					}
				}

				//on second click on already selected option, unselect it
				if($scope.modeli.includes(opti.choice)){
					if($scope.modeli.length == 1){
						return;
					}
					$scope.modeli.splice($scope.modeli.indexOf(opti.choice),1);
					$scope.result = $scope.modeli.join();
					return;
				}
				$scope.modeli.push(opti.choice);
				//combine all array elements into comma separated string
				$scope.result = $scope.modeli.join();
			};
		},
		template : "<button type='button' class='btn greenbut borderbut' "
				+ "ng-class='{active: modeli.includes(opti.choice)}'"
				+ "ng-repeat='opti in optionsi'"
				+ "ng-click='addOrRemoveOption(opti)'>{{opti.text}}"
				+ "</button>"
	};
})
/*<buttons-radio-multiselect class="btn-group" data-toggle="buttons-radio-multiselect"
					modeli='offersCtrl.selectedStatuses' optionsi='offersCtrl.statuses'
					 result='offersCtrl.statusSelected' exclusive='offersCtrl.exclusiveOptions'>
</buttons-radio-multiselect>*/
