(function(){
	'use strict';
	angular
		.module('webraovatApp')
		.controller('CategoryController', CategoryController);
	CategoryController.$inject = ['$http', '$scope', '$state'];
	function CategoryController($http, $scope, $state){
		var vm = this;
		console.log($state);
		vm.categories = [];
		vm.alerts = [];
		vm.load = load;
		
		vm.load();
		
		function load() {
			if ($state.params.alerts) {
				vm.alerts = $state.params.alerts;
			}
			$http({
				url : "CategoryController?action=getall",
				method: "POST",
				params: {
					searchKey : vm.searchKey,
				}
			}).then(function(response) {
				vm.categories = response.data;
				if(vm.categories.length == 0) {
					var alert = { type: 'danger', msg: 'Không tìm thấy danh mục nào!'};
					vm.alerts.push(alert);
				}
	        }, function(response) {
	            console.log(response);
	        });
		}
		
		vm.showUser = function(id) {
			$state.go("user-create", {id: id});
		}
		
		vm.closeAlert = function(index) {
		    vm.alerts.splice(index, 1);
		};
		
		vm.search = function() {
			vm.load();
		}
	}
	
})();