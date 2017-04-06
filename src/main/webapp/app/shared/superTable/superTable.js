/**
 * Created by saravanan on 5/4/16.
 */
app.directive("superTable",function($timeout,ngProgressFactory,$window,$rootScope){
    return {
        restrict: 'E',
        scope:{
            "data":"=",
            "head":"=",
            "sort":"=",
            "page":"=",
            "size":"=",
            "searchEvent":"&",
            "dropEvent":"&",
            "clickEvent":"&",
            "sortEvent":"&",
            "actionEvent":"&",
            "linkEvent":"&"
        },
        templateUrl :'app/shared/superTable/superTable.html',
        controller:function($scope,$rootScope){
        	
        	 $scope.datepickeropts = {
                     clearLabel: 'Clear',
                     locale: {
                         applyClass: 'btn-green',
                         applyLabel: "Apply",
                         fromLabel: "From",
                         //format: $rootScope.userProfile.selectedUserLocation.dateFormat,
                         format: $rootScope.userProfile.selectedUserLocation.dateFormat ? $rootScope.userProfile.selectedUserLocation.dateFormat : "DD-MM-YYYY",
                         toLabel: "To",
                         //cancelLabel: 'Clear',
                         //customRangeLabel: 'Custom range'
                     },
                     ranges: {
                         'Today': [moment(), moment()],
                         'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                         'Last 30 Days': [moment().subtract(29, 'days'), moment()]
                     },
                     eventHandlers: {
                         'apply.daterangepicker': function (ev, picker) {
                             $timeout(function () {
                                 $scope.changeFunction();
                             }, 2);
                             //$scope.changeSearch();
                         },
                         'cancel.daterangepicker': function (ev, picker) {
                             $timeout(function () {
                                 $scope.changeFunction();
                             }, 2);

                         }
                     }
                 };
        	
            $timeout(function() {

                $scope.searchData = {};
                $scope.sortData = {};
                console.log("page", $scope.page);
                console.log("limit", $scope.limit);
                $scope.contained_progressbar = ngProgressFactory.createInstance();
                $scope.contained_progressbar.setParent(document.getElementById('table-body'));
                $scope.contained_progressbar.setAbsolute();
                $scope.contained_progressbar.start();
                $scope.waitLoad = false;
                $scope.$watch('data', function (newvalue, oldvalue) {
                    $timeout(function () {
                        $scope.contained_progressbar.complete();
                        $scope.waitLoad = false;
                    }, 1000);
                    if (newvalue != oldvalue) {
                        $scope.selectedRow = -1;
                    }
                });


               

                $scope.getStyle = function (index, row) {
                    if (index == row) {
                        return {
                            "border-color": "#66afe9",
                            "background": "rgb(199,228,252)",
                            "background-color": "rgb(199,228,252)",
                            "outline": "thin dotted #777",
                            "outline": "0px auto -webkit-focus-ring-color",
                            "outline-offset": "0px",
                            "-webkit-box-shadow": "none",
                            "box-shadow": "none"
                        }
                    }
                }

                $scope.tdClass = function (data) {
                    if (data.wrap_cell) {
                        return "wrap_cell"
                    }
                }

                $scope.changeFunction = function () {
                    $scope.selectedRow = -1;
                    $scope.searchEvent({"param": $scope.searchData});
                }
                $scope.rowSelect = function (data, index) {
                    $scope.selectedRow = index;
                    $scope.clickEvent({"param": data, "index": index});
                }
                $scope.rowClickLink = function ($event, data, index) {
                    $event.stopPropagation();
                    $scope.selectedRow = index;
                    $scope.linkEvent({"param": data, "index": index});
                }

                $scope.config = {
                    autoHideScrollbar: true,
                    theme: 'light',
                    setHeight: 200,
                    scrollInertia: 0
                }

                $scope.changeOrder = function (data) {
                    if (data.sort) {
                        if ($scope.sort.sortKey == data.model) {
                            $scope.selectedRow = -1;
                            $scope.sort.sortKey = data.model;
                            $scope.sort.sortOrder = $scope.sort.sortOrder == 'asc' ? 'desc' : 'asc';
                            $scope.sortEvent({param: $scope.sort});
                        } else {
                            $scope.selectedRow = -1;
                            $scope.sort.sortKey = data.model;
                            $scope.sort.sortOrder = 'asc';
                            $scope.sortEvent({param: $scope.sort});
                        }
                    }

                }


                $scope.findProp = function (obj, prop, defval) {
                    if ((prop.model != 'status' && prop.model != 'autoMailStatus') && prop.type != 'img') {
                        if (typeof defval == 'undefined') defval = null;
                        prop = prop.split('.');
                        for (var i = 0; i < prop.length; i++) {
                            //console.log("value",obj[prop[i]],typeof obj[prop[i]],prop[i]);
                            try {
                                if (obj[prop[i]] == null && typeof obj[prop[i]] == 'undefined')
                                    return defval;
                                obj = obj[prop[i]];
                            } catch (e) {
                                return null;
                            }

                        }
                        return obj;
                    }
                };
                $scope.setDate = function (obj, model) {
                    return $rootScope.dateToString($scope.findProp(obj, model));
                }
                $scope.setTitle = function (type, obj, model) {
                    if (type != 'img') {
                        if (type == 'date-range') {
                            return $rootScope.dateToString($scope.findProp(obj, model));
                        } else if (type == 'date-time-range') {
                            return $rootScope.dateAndTimeToString($scope.findProp(obj, model));
                        } else {
                            return $scope.findProp(obj, model);
                        }
                    }
                }

                $scope.setValue = function (type, obj, model) {
                    if ((model != 'status' && model != 'autoMailStatus') && type != 'img' && type != 'action' && type != 'link') {
                        if (type == 'date-range') {
                            return $rootScope.dateToString($scope.findProp(obj, model));
                        } else if (type == 'date-time-range') {
                            return $rootScope.dateAndTimeToString($scope.findProp(obj, model));
                        } else if (type == 'currency') {
                            if ($scope.findProp(obj, model)) {
                                return $scope.findProp(obj, model).toFixed($rootScope.appMasterData['round.decimal']);
                            }
                        } else {
                            return $scope.findProp(obj, model);
                        }
                    }

                }
                $scope.setLink = function (type, obj, model) {
                    if (type == 'link') {
                        return $scope.findProp(obj, model);
                    }
                }
                $scope.actionClick = function (param) {
                    if ($scope.actionEvent) {
                        $scope.actionEvent(param);
                    }

                }

            },1);
        }
    }
});