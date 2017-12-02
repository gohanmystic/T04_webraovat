(function(){
	'use strict';
	angular
		.module('webraovatApp')
		.controller('PostCreateUpdateController', PostCreateUpdateController);
	PostCreateUpdateController.$inject = ['$http', '$scope', '$state']
	function PostCreateUpdateController($http, $scope, $state){
		var vm = this;
		vm.post = {};
		vm.alerts = [];
		vm.load = load;
		vm.load();
		
		function load() {
			loadData();
			$http({
				url : "CategoryController?action=getall",
				method: "POST",
				params: {
					searchKey : vm.searchKey,
				}
			}).then(function(response) {
				vm.categories = response.data;
	        }, function(response) {
	        });
			
			$http({
				url : "UserController?action=getall",
				method: "POST",
				params: {
					searchKey : vm.searchKey,
				}
			}).then(function(response) {
				vm.users = response.data;
	        }, function(response) {
	        });
			
			
		}
		
		vm.save = function () {
			$scope.$broadcast('show-errors-check-validity');
			if ($scope.postCreateUpdate.$invalid) {
				var invalidControl = angular.element('input.ng-invalid').first() || angular.element('select.ng-invalid').first() ||angular.element('option.ng-invalid').first();
				if (invalidControl != null) {
                    invalidControl.focus();
                }
			} else {
				$http({
		            url : 'PostController?action=create',
		            method : "POST",
		            headers: {
		            	Accept: "application/json; charset=utf-8",
		            	"Content-Type" : "application/json; charset=utf-8"
		            },
					data: JSON.stringify(vm.post)
		        }).then(function(response) {
		        	if(response.data) {
		        		var alert = { type: 'success', msg: 'Thêm bài đăng thành công!'};
						vm.alerts.push(alert);
						$state.go("post-list", {alerts : vm.alerts});
		        	}

		        }, function(response) {
		            console.log(response);
		        });
			}
		}
		
		function loadData() {
			vm.post.UserID = "1";
			vm.post.PostTypeID = "1";
			vm.post.CreationDate = new Date();
		}
		
		vm.closeAlert = function(index) {
		    vm.alerts.splice(index, 1);
		};
	}
	
})();