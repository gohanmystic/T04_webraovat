(function(){
	'use strict';
	angular
		.module('webraovatApp')
		.controller('CategoryDetailController', CategoryDetailController);
	CategoryDetailController.$inject = ['$scope', 'cateID', '$http', '$state'];
	function CategoryDetailController($scope, cateID, $http, $state){
		var vm = this;
		vm.load = load;
		vm.load();
		
		function load() {
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
	
	}
	
})();