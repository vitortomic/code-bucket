
/**
 Directive for calling a function when rendering is finished (for example ng-repeat complete)
*/

//directive
var module = angular.module('testApp', [])
    .directive('onFinishRender', function ($timeout) {
    return {
        restrict: 'A',
        link: function (scope, element, attr) {
            if (scope.$last === true) {
                $timeout(function () {
                    scope.$emit(attr.onFinishRender);
                });
            }
        }
    }
});

//event handler
$scope.$on('ngRepeatFinished', function(ngRepeatFinishedEvent) {
    //you also get the actual event object
    //do stuff, execute functions -- whatever...
});

//template code
<div ng-repeat="item in items" on-finish-render="ngRepeatFinished">
    <div>{{item.name}}}<div>
</div>
