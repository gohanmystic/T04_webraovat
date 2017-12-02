(function(){
	'use strict';
	angular
		.module('webraovatApp')
		.controller('PostConfirmController', PostConfirmController);
	
	PostConfirmController.$inject = ['postID', '$uibModalInstance', '$http', '$state'];
	function PostConfirmController(postID, $uibModalInstance, $http, $state){
		var vm = this;
		vm.alerts = [];
		vm.no = function() {
			$uibModalInstance.close();
		}
		
		vm.yes = function() {
			if(postID != null) {
				$http({
					url: "PostController?action=updateStatus",
					method: "POST",
					params: {
						postID: postID,
						status: 1,
					}
				}).then(function(response) {
					if(response.data) {
						var alert = { type: 'success', msg: 'Duyệt bài đăng thành công!'};
						vm.alerts.push(alert);
						$state.go("post-list", {alerts : vm.alerts});
					}
					$uibModalInstance.close();
		        }, function(response) {
		        });
			}
		}
	}
	
})();