/*
*@Author Praveen
*Created on 01-01-2017
*/
app.controller('ContactCtrl',function($scope, $rootScope, $state,$log, Notification, 
		SendEnquiry, ContactView, appConstant, CommonValidationService, ContactService) {
	
	$scope.contact = {};
	$scope.limitArr = [10,15,20];
	$scope.page = 0;
	$scope.limit = 10;
	$scope.totalRecord= 0;
	
	
	$scope.contactValidate = function(){
		try{
			if($scope.contact == undefined || $scope.contact.name == null || $scope.contact.name ==""){
				$log.debug('contact name is mandatory');
				Notification.error('contact name is mandatory');
				return false;
			}
			if($scope.contact.email == undefined || $scope.contact.email == null || $scope.contact.email ==""){
				$log.debug('contact email is mandatory');
				Notification.error('contact email is mandatory');

				return false;
			}
			if($scope.contact.message == undefined || $scope.contact.message == null || $scope.contact.message ==""){
				$log.debug('contact message is mandatory');
				Notification.error('contact message is mandatory');
				return false;
			}
			
		}	
		catch(e){
			$log.error("Got an error!",e);
		      throw e;
		}
			return true
		}
	
	$scope.add = function(){
		$scope.contactData =[];
		$scope.contactData.push({});
		if(!CommonValidationService.checkRegExp(appConstant.REG_EXP_PHONE_NUMBER,$scope.contact)){
			return false;
		}
		
	}
	
	$scope.back = function(){
		$log.debug('back calling');
		$scope.contactView = false;

	}
	
	$scope.sendEnquiry = function(){
		//$rootScope.clientMessage ="contact Saving Failed.";	
		$scope.contactView = false;
		$log.debug("sendEnquiry  called..");
		
		if($scope.contactValidate()){
			SendEnquiry.save($scope.contact).$promise.then(function(data) {
				if(data.responseCode=="success"){
					Notification.success('contact saved successfully..');
					$state.go("layout.home");
				}
				else{
					Notification.error(data.responseDescription);	
				}
			}, function(error) {
				Notification.error(data.responseDescription);	
				$log.info("contact Saving Failed : " + error);
			    });	
			
		}else{
			$log.info("contact Saving Failed : ");	
		}
	}



	
	$scope.contactList = function(){
    	$scope.mainpreloder=true;
		$scope.contactView = true;
		$scope.contactData = [];
		$scope.ActivetatusCount = 0;
		ContactView.get().$promise.then(function(data) {
			$scope.contactData= data.responseObject;
			$scope.totalRecord =data.responseObject.length;
			for(i=0; i< data.responseObject.length ; i++){
				if(data.responseObject[i].status=='Active'){
					$scope.ActivetatusCount++;
				}
			}
			
		      console.log("contact view sucess: " ,data);
		}, function(error) {
			$rootScope.clientMessage ="contact Saving Failed.";	
		      console.log("contact Saving Failed : " + error);
		    });
		//$state.go("layout.contactList");
	
	
	}
	
	
	
});