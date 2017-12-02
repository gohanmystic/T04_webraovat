(function(){
	'use strict';
	angular
		.module('webraovatApp')
		.controller('CategoryDetailController', CategoryDetailController);
	CategoryDetailController.$inject = ['$scope', 'cateID', '$http', '$state'];
	function CategoryDetailController($scope, cateID, $http, $state){
		console.log(cateID);
		var vm = this;
		vm.load = load;
		vm.load();
		
		function load() {
			
		}
	
	}
	
})();