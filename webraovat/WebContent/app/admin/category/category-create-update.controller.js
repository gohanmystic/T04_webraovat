(function(){
	'use strict';
	angular
		.module('webraovatApp')
		.controller('CategoryCreateUpdateController', CategoryCreateUpdateController);
	CategoryCreateUpdateController.$inject = ['$scope', 'cateID', '$state', '$http'];
	function CategoryCreateUpdateController($scope, cateID, $state, $http){
		var vm = this;
		vm.cate = {};
		vm.alerts = [];
		vm.load = load;
		vm.load();
		
		function load() {
			
		}
		
		vm.save = function() {
			$scope.$broadcast('show-errors-check-validity');
			if ($scope.CateCreateUpdateForm.$invalid) {
				var invalidControl = angular.element('input.ng-invalid').first() || angular.element('select.ng-invalid').first() ||angular.element('option.ng-invalid').first();
				if (invalidControl != null) {
                    invalidControl.focus();
                }
			} else {
				if (cateID == null) {
					$http({
			            url : 'CategoryController?action=create',
			            method : "POST",
			            headers: {
			            	Accept: "application/json; charset=utf-8",
			            	"Content-Type" : "application/json; charset=utf-8"
			            },
						data: JSON.stringify(vm.cate)
			        }).then(function(response) {
			        	if(response.data) {
			        		var alert = { type: 'success', msg: 'Thêm Danh mục thành công!'};
							vm.alerts.push(alert);
							console.log(vm.alerts);
							$state.go("category-list", {alerts : vm.alerts});
			        	}

			        }, function(response) {
			            console.log(response);
			        });
				} else {
					$http({
			            url : 'CategoryController?action=update',
			            method : "POST",
			            headers: {
			            	Accept: "application/json; charset=utf-8",
			            	"Content-Type" : "application/json; charset=utf-8"
			            },
						data: JSON.stringify(vm.user)
			        }).then(function(response) {
			        	if(response.data) {
//			        		var alert = { type: 'success', msg: 'Cập nhật Danh mục thành công!'};
//							vm.alerts.push(alert);
//			        		$state.go("user-detail", {userID : userID, alerts : vm.alerts});
			        		console.log(response.data);
			        	}
			        		
			        	
			        }, function(response) {
			            console.log(response);
			        });
				}
			}
		}
	}
	
})();