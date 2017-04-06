
app.directive("clientError", function($timeout,$rootScope) {
	return {
		templateUrl : 'app/shared/clienterror/error.html',
		replace: true,
		restrict : 'E',
		/*scope:{
	            "message":"="
	        },*/
		link:function(scope,elem,attr){
			scope.$watch('message',function(newValue,oldValue){
				if(newValue!=oldValue){
					//$timeout(function(){
					//	$rootScope.respCode=null;
					//},3000);
				}

			})

		},
		controller: function($scope) {
			/*$scope.clear = function() {
				console.log("Clear Method is called....!");
				$scope.message = null;
			}*/
		}

	};
});



