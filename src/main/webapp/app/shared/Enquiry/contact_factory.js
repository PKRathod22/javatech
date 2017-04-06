(function() {
		
		app.factory("SendEnquiry", function($resource) {
			return $resource("/api/pk/contact/create", {}, {
				save : {
					method : 'POST'
				}
			});
		});
	
		app.factory("ContactView", function($resource) {
			return $resource("/api/pk/contact/get/getall", {}, {
				get : {
					method : 'GET',
					params : {
						id : ''
					},
					isArray : false
				}
			});
		});
		
})();
