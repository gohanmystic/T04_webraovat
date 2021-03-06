(function(){
	'use strict';
	angular
		.module('webraovatApp')
		.controller('UserDetailController', UserDetailController);
	
	UserDetailController.$inject = ['userID', '$http', '$uibModal', '$scope', '$state'];
	function UserDetailController(userID, $http, $uibModal, $scope, $state){
		var vm = this;
		console.log($state);
		vm.user = {};
		vm.alerts = [];
		vm.load = load;
		vm.load();
		
		function load(){
			if($state.params.alerts) {
				vm.alerts = $state.params.alerts;
			}
			$http({
				url: "UserController?action=detail",
				method: "POST",
				params: {
					userID: userID
				}
			}).then(function(response) {
				vm.user = response.data;
				formatData();
	        }, function(response) {
	            console.log(response);
	        });
		}
		
		function formatData(){
			if(vm.user.birthday) {
				vm.user.birthday = new Date(vm.user.birthday);
			}
			vm.user.level = vm.user.level.toString();
			vm.user.gender = vm.user.gender.toString(); 
		}
		
		vm.delete = function(userID) {
			vm.modalInstantDeleteUser = $uibModal.open({
                templateUrl: 'app/admin/user/user-delete-dialog.html',
                controller: 'UserDeleteController',
                controllerAs: 'vm',
                size: 'md',
                resolve: {
                    userID: function() {
                        return userID;
                    }
                }
            });
		}
		vm.block = function(userID) {
			vm.modalInstantDeleteUser = $uibModal.open({
                templateUrl: 'app/admin/user/user-block-dialog.html',
                controller: 'UserBlockController',
                controllerAs: 'vm',
                size: 'md',
                resolve: {
                    userID: function() {
                        return userID;
                    }
                }
            });
			vm.modalInstantDeleteUser.result.then(function(){
				var alert = { type: 'success', msg: 'Khóa user thành công!'};
				vm.alerts.push(alert);
			})
		} 
		
		
		vm.closeAlert = function(index) {
		    vm.alerts.splice(index, 1);
		};
	}
	
})();