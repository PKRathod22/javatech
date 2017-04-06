app.controller('mainCtrl',function($scope, $state, $rootScope, $timeout, RegExpAll){
console.log('called main..');

		
	$rootScope.clientMessage = null;
	$rootScope.enum = {};
	$rootScope.nls = {};
	$rootScope.appRegExp = {};
	
	 RegExpAll.get(function(data) {
         if (data) {
             $rootScope.appRegExp = data.responseObject;
             console.log("appRegExp Loaded Successfully : ")
         } else {
        	 console.log("appRegExp Loaded failed")
         }
    }, function(error) {
   	 console.log("appRegExp Loaded failed : " + error)
    });
	
	$rootScope.clear = function() {
		console.log("Clear Method is called....!");
		$rootScope.clientMessage = null;
	}
		
		
	$rootScope.navigateToNextField = function(id) {
		$timeout(function(){
			if(id!=undefined && id != null) {
				var docElement = document.getElementById(id);
				if(docElement != undefined && docElement != null){
					docElement.focus();
				}
			}
		})
     }



});
