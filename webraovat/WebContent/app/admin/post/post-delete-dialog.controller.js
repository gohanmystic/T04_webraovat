(function(){
	'use strict';
	angular
		.module('webraovatApp')
		.controller('PostDeleteController', PostDeleteController);
	
	PostDeleteController.$inject = ['postID', '$uibModalInstance', '$http', '$state'];
	function PostDeleteController(postID, $uibModalInstance, $http, $state){
		var vm = this;
		vm.alerts = [];
		vm.no = function() {
			$uibModalInstance.close();
		}
		
		vm.yes = function() {
			if(postID != null) {
				$http({
					url: "PostController?action=delete",
					method: "POST",
					params: {
						postID: postID,
					}
				}).then(function(response) {
					if(response.data) {
						var alert = { type: 'success', msg: 'Xóa bài đăng thành công!'};
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