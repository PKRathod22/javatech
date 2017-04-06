app.controller('headerCtrl',function($scope, $state, $http){

	console.log('called header');

	$scope.defaultDashboard = function(){
		console.log('defaultDashboard');
		 $state.go('layout.home');
	}
	$scope.contact = function(){
		console.log('calling getdashboard');
	    $state.go('layout.contact');
		//$http.get("app/component/shopping/shopping.html");
		
	 }


 });
