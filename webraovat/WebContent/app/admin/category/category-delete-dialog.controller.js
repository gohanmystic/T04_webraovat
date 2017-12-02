(function(){
	'use strict';
	angular
		.module('webraovatApp')
		.controller('CategoryDeleteController', CategoryDeleteController);
	
	CategoryDeleteController.$inject = ['cateID', '$uibModalInstance', '$http', '$state'];
	function CategoryDeleteController(cateID, $uibModalInstance, $http, $state){
		var vm = this;
		vm.alerts = [];
		vm.no = function() {
			$uibModalInstance.close();
		}
		
		vm.yes = function() {
			if(cateID != null) {
				$http({
					url: "CategoryController?action=delete",
					method: "POST",
					params: {
						CategoryID: cateID,
					}
				}).then(function(response) {
					if(response.data) {
						var alert = { type: 'success', msg: 'Xóa danh mục thành công!'};
						vm.alerts.push(alert);
						$state.go("category-list", {alerts : vm.alerts});
					}
					$uibModalInstance.close();
		        }, function(response) {
		            console.log(response);
		        });
			}
		}
	}
	
})();