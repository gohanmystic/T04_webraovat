(function(){
	'use strict';
	angular
		.module('webraovatApp')
		.controller('PostController', PostController);
	PostController.$inject = ['$state', '$http'];
	function PostController($state, $http){
		var vm = this;
		vm.posts = [];
		vm.alerts = [];
		vm.categories=[];
		vm.load = load;
		
		vm.load();
		
		function load() {
			vm.alerts = $state.params.alerts;
			
			$http({
				url : "PostController?action=getall",
				method: "POST",
				params: {
					searchKey : vm.searchKeyPost,
					filter : vm.filter
				}
			}).then(function(response) {
				vm.posts = response.data;

				if(vm.posts.length == 0) {
					var alert = { type: 'danger', msg: 'Không tìm thấy bài đăng nào!'};
					vm.alerts.push(alert);
				}
	        }, function(response) {
	        });
		}
		
		vm.search = function() {
			vm.load();
		}
		
		vm.closeAlert = function(index) {
		    vm.alerts.splice(index, 1);
		};
	}
	
})();