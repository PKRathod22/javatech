/**
 * Created by hmspl on 21/7/16.
 */
app.directive('mainefsSpinner',function(){
    return{
        restrict:'E',
        scope:{
            spinnerStatus:"="
        },
        template:'<div class="abs loader-container mainefs-overlay" ng-show="spinnerStatus">' +
        '<div class="sprite_hmblue img-loader">'+
            '</div>'+
        '</div>'
    }
});