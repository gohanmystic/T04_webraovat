(function(){
	'use strict';
	angular
		.module('webraovatApp')
		.controller('UserDeleteController', UserDeleteController);
	
	UserDeleteController.$inject = ['userID', '$uibModalInstance', '$http', '$state'];
	function UserDeleteController(userID, $uibModalInstance, $http, $state){
		var vm = this;
		vm.alerts = [];
		vm.no = function() {
			$uibModalInstance.close();
		}
		
		vm.yes = function() {
			if(userID != null) {
				$http({
					url: "UserController?action=delete",
					method: "POST",
					params: {
						userID: userID,
					}
				}).then(function(response) {
					if(response.data) {
						var alert = { type: 'success', msg: 'Xóa user thành công!'};
						vm.alerts.push(alert);
						$state.go("user-list", {alerts : vm.alerts});
					}
					$uibModalInstance.close();
		        }, function(response) {
		            console.log(response);
		        });
			}
		}
	}
	
})();