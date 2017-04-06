(function() {
	app.factory("RegExpAll", function($resource) {
		return $resource("/api/v1/regularexpression/get/all", {}, {
			get : {
				method : 'GET',
				params : {},
				isArray : false
			}
		});
	});
	
})();
