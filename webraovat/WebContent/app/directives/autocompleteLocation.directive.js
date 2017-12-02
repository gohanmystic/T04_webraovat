(function() {
    'use strict';

    angular
    .module('webraovatApp')
    .directive('googleplace', directiveFunction);
    directiveFunction.$inject = ['$rootScope'];

    function directiveFunction($rootScope) {
        return {
            require: '?ngModel',
            scope: {
                ngModel: '=',
                details: '=?'
            },
            link: function(scope, element, attrs, model) {
                var options = {
                    componentRestrictions: {country: "vn"}
                };

                if(scope.gPlace === undefined) {
                    scope.gPlace = new google.maps.places.Autocomplete(element[0], options);
                }

                google.maps.event.addListener(scope.gPlace, 'place_changed', function() {
                    var result = scope.gPlace.getPlace();

                    if(result !== undefined) {
                        scope.$apply(function() {
                            scope.details = result;
                            model.$setViewValue(element.val());
                            $rootScope.$broadcast('place_changed', scope.details);
                        });
                    }
                });
            }
        };
    }
})();
