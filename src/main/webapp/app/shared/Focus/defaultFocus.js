/**
 * Created by Praveen on 19/9/16
 */

app.directive('defaultFocus', function($timeout) {
  return {
    scope: { trigger: '=defaultFocus' },
    link: function(scope, element) {
      scope.$watch('trigger', function(value) {
        if(value === true) { 
          console.log('trigger',value);
          $timeout(function() {
            element[0].focus();
            scope.trigger = false;
          });
        }
      });
    }
  };
});