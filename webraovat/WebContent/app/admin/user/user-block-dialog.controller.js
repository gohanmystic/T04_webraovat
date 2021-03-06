(function(){
	'use strict';
	angular
		.module('webraovatApp')
		.controller('UserBlockController', UserBlockController);
	
	UserBlockController.$inject = ['userID', '$uibModalInstance', '$http', '$state'];
	function UserBlockController(userID, $uibModalInstance, $http, $state){
		var vm = this;
		
		vm.no = function() {
			$uibModalInstance.close(false);
		}
		
		vm.yes = function() {
			if(userID != null) {
				$http({
					url: "UserController?action=updateStatus",
					method: "POST",
					params: {
						userID: userID,
						status: 1,
					}
				}).then(function(response) {
					if(response.data)
						$state.go("user-detail", {userID: userID});
					$uibModalInstance.close(true);
		        }, function(response) {
		            console.log(response);
		        });
			}
		}
	}
	
})();