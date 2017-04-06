/**
 * Created by saravanan on 11/4/16.
 */
app.directive("paginationDr",function() {
    return {
        restrict: 'E',
        scope: {
            "total": "=",
            "page": "=",
            "size": "=",
            "pageChange": "&"
        },
        templateUrl: 'app/shared/pagination/pagination.html',
        controller:function($scope){
            //alert($scope.page);

            $scope.$watch('total',function(newValue,oldValue){
                if(newValue && newValue!=oldValue) {
                    if (newValue < 1) {
                        $scope.currentPage= 0;
                    } else if (newValue > 0 && $scope.size >= newValue) {
                        $scope.currentPage= 0;
                    } else if (newValue > 0 && $scope.size < newValue) {
                        $scope.currentPage= 0;
                    }
                }
            });
            $scope.$watch('size',function(newValue,oldValue){
                if(newValue && newValue!=oldValue) {
                    $scope.currentPage= 0;
                }
            });
            if($scope.total==null || $scope.total==undefined){
                $scope.total = 0;
            }
            //alert($scope.size);
            $scope.currentPage = $scope.page;
            $scope.itemsPerPage = $scope.size;
            $scope.pageCount;
            $scope.range = function() {
                //rangeSize is the number of pages (in numerical form) displayed in the pagination.
                var rangeTemp = ($scope.total/$scope.size >> 0);
                if ($scope.total % $scope.size > 0){
                    rangeTemp += 1;
                }

                var rangeSize = rangeTemp > 3 ? 3 : rangeTemp ; //This should be an odd number.
                var ret = [];
                var start;

                start = $scope.currentPage;
                //console.log("start",start);
                $scope.pageCount = rangeTemp;
                if ( start > rangeTemp-rangeSize ) {
                    start = rangeTemp-rangeSize;
                }else{
                    if((rangeTemp-rangeSize)<2){
                        if(rangeTemp-rangeSize==0){
                            start = 0;
                        }else{
                            start = (rangeTemp-rangeSize)-1;
                        }

                    }

                }

                for (var i=start; i<start+rangeSize; i++) {
                    ret.push(i);
                }
                return ret;
            };
            $scope.prevPage = function(){

                if($scope.currentPage > 0){
                    $scope.currentPage--;

                    $scope.pageChange({param:{page:$scope.currentPage,size:$scope.size,total:$scope.total}});
                }
            };
            $scope.firstPage = function(){

                if($scope.currentPage > 0){
                    $scope.currentPage=0;

                    $scope.pageChange({param:{page:$scope.currentPage,size:$scope.size,total:$scope.total}});
                }
            };
            $scope.prevPageDisabled = function(){
                return $scope.currentPage === 0 ? "disabled":" ";
            };
            $scope.pageCount = function(){
                return Math.ceil($scope.total/$scope.itemsPerPage)-1;
            };
            $scope.nextPage = function(){

                if($scope.currentPage < $scope.pageCount-1){
                    $scope.currentPage++;

                    $scope.pageChange({param:{page:$scope.currentPage,size:$scope.size,total:$scope.total}});
                }
            };
            $scope.lastPage = function(){

                if($scope.currentPage < $scope.pageCount-1){
                    $scope.currentPage = $scope.pageCount-1;

                    $scope.pageChange({param:{page:$scope.currentPage,size:$scope.size,total:$scope.total}});
                }
            };
            $scope.nextPageDisabled = function(){
                return $scope.currentPage === $scope.pageCount-1 ? "disabled":" ";
            };
            $scope.setPage = function(set){
                $scope.currentPage = set;
                $scope.pageChange({param:{page:$scope.currentPage,size:$scope.size,total:$scope.total}});
            };
        }
    }
});