(function(){
	'use strict';
	angular
		.module('webraovatApp')
		.controller('CategoryDetailController', CategoryDetailController);
	CategoryDetailController.$inject = ['$scope', 'cateID', '$http', '$state', '$uibModal'];
	function CategoryDetailController($scope, cateID, $http, $state, $uibModal){
		var vm = this;
		vm.alerts = [];
		vm.load = load;
		vm.load();
		
		function load() {
			vm.alerts = $state.params.alerts;
			$http({
				url : "CategoryController?action=detail",
				method: "POST",
				params: {
					CategoryID : cateID
				}
			}).then(function(response) {
				vm.cate = response.data;
	        }, function(response) {
	            console.log(response);
	        });
		}
		
		vm.delete = function () {
			vm.modalInstantDeleteCate = $uibModal.open({
                templateUrl: 'app/admin/category/category-delete-dialog.html',
                controller: 'CategoryDeleteController',
                controllerAs: 'vm',
                size: 'md',
                resolve: {
                    cateID: function() {
                        return cateID;
                    }
                }
            });
		}
	
	}
	
})();