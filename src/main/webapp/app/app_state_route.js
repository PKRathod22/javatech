'use strict';
app.config(function($stateProvider, $urlRouterProvider) {
	console.log('inside config222');
    $urlRouterProvider.otherwise('/');
    
    $stateProvider
        .state('layout', {
            templateUrl: 'mainLayout.html'
        })
         .state('layout.home', {
            url: '/',
            controller :'dashboardCtrl',
            templateUrl : 'app/component/dashboard/dashboard.html'
        })
        .state('layout.contact', {
            url: '/contact',
            controller :'ContactCtrl',
            templateUrl : 'app/shared/Enquiry/contact.html'
        })
        .state('layout.contactList', {
            url: '/contactlist',
            controller :'ContactCtrl',
            templateUrl : 'app/shared/Enquiry/contact_list.html'
        })
        
        .state('about', {
            url: '/about',
            views: {
                '': { templateUrl: 'partial-about.html' },
                'columnOne@about': { template: 'Look I am a column!' },
                'columnTwo@about': { 
                    templateUrl: 'table-data.html',
                    controller: 'scotchController'
                }
            }
            
        });
        
});




