(function(){
	'use strict';
	angular
		.module('webraovatApp')
		.controller('UserController', UserController);
	UserController.$inject = ['$http', '$state', '$scope'];
	function UserController($http, $state, $scope){
		var vm = this;
		vm.users = [];
		vm.alerts = [];
		vm.load = load;
		
		vm.load();
		
		function load() {
			if ($state.params.alerts) {
				vm.alerts = $state.params.alerts;
			}
			$http({
				url : "UserController?action=getall",
				method: "POST",
				params: {
					searchKey : vm.searchKey,
					filter : vm.filter
				}
			}).then(function(response) {
				vm.users = response.data;
				if(vm.users.length == 0) {
					var alert = { type: 'danger', msg: 'Không tìm thấy user nào!'};
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