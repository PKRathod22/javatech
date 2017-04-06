app.service('CommonValidationService', ['$log','$rootScope', 'appConstant', function($log, $rootScope, appConstant, ValidateUtil){

	this.checkPiecesRange = function(value) {
		if (value < 0 || value >= 99999999999) {
			return false;
		}
		return true;
	}

	this.checkPiecesFormat = function(value) {
		return this.checkFormat(value, appConstant.piecesValExpr);
	}
	this.checkWeightFormat = function(value) {
		return this.checkFormat(value, appConstant.weightValExpr);
	}

	this.checkMultipleMail = function(value) {
		var emailArr = value.split(";");
		if(emailArr != null && emailArr.length >0 ){
			for(var i = 0; i < emailArr.length; i++) {
				if(!this.checkFormat(emailArr[i], $rootScope.appRegExp[appConstant.REG_EXP_MULTIPLE_EMAIL])) {
					return false; 
				}
			}
 		}
		return true;
	}

	this.checkFormat = function(object, regExp) {
		var regexp = new RegExp(regExp);
		if (!regexp.test(object)) {
			return false;
		}
		return true;
	}
	
	
	this.checkRegExp = function(key, object) {
		return this.checkFormat(object,$rootScope.appRegExp[key]);
	}
	
	this.checkObjectIsEmpty = function(obj){
		if(obj == undefined || obj == null || obj == "") {
			return true;
		} else {
			var keyNames = Object.keys(obj);
			if(keyNames.length > 0) {
				for (var i=0;i<keyNames.length;i++) {
					if(keyNames[i] != "error" && keyNames[i] != "errorList" && obj[keyNames[i]] != undefined && obj[keyNames[i]] != null && obj[keyNames[i]] != "") {
						return false;
					}
				}
			}
		}
		return true;
	}
}]);
