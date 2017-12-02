(function(){
	'use strict';
	angular
		.module('webraovatApp')
		.controller('CategoryCreateUpdateController', CategoryCreateUpdateController);
	
	function CategoryCreateUpdateController(){
		var vm = this;
		vm.load = load;
		vm.load();
		
		function load() {
			
		}
		
		vm.save = function() {
			$scope.$broadcast('show-errors-check-validity');
			if ($scope.createUpdateForm.$invalid) {
				var invalidControl = angular.element('input.ng-invalid').first() || angular.element('select.ng-invalid').first() ||angular.element('option.ng-invalid').first();
				if (invalidControl != null) {
                    invalidControl.focus();
                }
			} else {
				console.log("OK");
			}
		}
	}
	
})();