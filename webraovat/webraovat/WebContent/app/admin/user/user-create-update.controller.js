(function(){
	'use strict';
	angular
		.module('webraovatApp')
		.controller('UserCreateUpdateController', UserCreateUpdateController);
	
	UserCreateUpdateController.$inject = ['$scope', '$http', '$filter', '$state', '$stateParams', 'userID'];
	function UserCreateUpdateController($scope, $http, $filter, $state, $stateParams, userID){
		var vm = this;
		vm.user = {};
		vm.alerts = [];
		vm.load = load;
		
		vm.load();
		function load() {
			loadUserForUpdate();
			formatDataWhenLoad();
		}
		vm.save = function() {
			$scope.$broadcast('show-errors-check-validity');
			if ($scope.createUpdateForm.$invalid) {
				var invalidControl = angular.element('input.ng-invalid').first() || angular.element('select.ng-invalid').first() ||angular.element('option.ng-invalid').first();
				if (invalidControl != null) {
                    invalidControl.focus();
                }
			} else {
				formatDataBeforeSave();
				if (userID === null) {
					$http({
			            url : 'UserController?action=create',
			            method : "POST",
			            headers: {
			            	Accept: "application/json; charset=utf-8",
			            	"Content-Type" : "application/json; charset=utf-8"
			            },
						data: JSON.stringify(vm.user)
			        }).then(function(response) {
			        	if(response.data) {
			        		var alert = { type: 'success', msg: 'Thêm user thành công!'};
							vm.alerts.push(alert);
							$state.go("user-list", {alerts : vm.alerts});
			        	}

			        }, function(response) {
			            console.log(response);
			        });
				} else {
					$http({
			            url : 'UserController?action=update',
			            method : "POST",
			            headers: {
			            	Accept: "application/json; charset=utf-8",
			            	"Content-Type" : "application/json; charset=utf-8"
			            },
						data: JSON.stringify(vm.user)
			        }).then(function(response) {
			        	if(response.data) {
			        		var alert = { type: 'success', msg: 'Cập nhật user thành công!'};
							vm.alerts.push(alert);
			        		$state.go("user-detail", {userID : userID, alerts : vm.alerts});
			        	}
			        		
			        	
			        }, function(response) {
			            console.log(response);
			        });
				}
				
			}
		}
		
		function formatDataWhenLoad() {
			if(vm.user.birthday) {
				vm.user.birthday = new Date(vm.user.birthday);
			}
		}
		
		function formatDataBeforeSave() {
			vm.user.birthday = $filter('date')(vm.user.birthday, 'yyyy-MM-dd');
			console.log(vm.user.birthday);
		}
		
		function loadUserForUpdate() {
			if(userID != null) {
				$http({
					url: "UserController?action=detail",
					method: "POST",
					params: {
						userID: userID,
					}
				}).then(function(response) {
		        	vm.user = response.data;
		        	formatData();
		        }, function(response) {
		            console.log(response);
		        });
			}
		}
		
		function formatData(){
			if(vm.user.birthday) {
				vm.user.birthday = new Date(vm.user.birthday);
			}
			vm.user.level = vm.user.level.toString();
			vm.user.gender = vm.user.gender.toString(); 
		}
		
		vm.closeAlert = function(index) {
		    vm.alerts.splice(index, 1);
		};
	}
	
})();