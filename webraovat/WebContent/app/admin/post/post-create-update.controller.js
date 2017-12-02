(function(){
	'use strict';
	angular
		.module('webraovatApp')
		.controller('PostCreateUpdateController', PostCreateUpdateController);
	PostCreateUpdateController.$inject = ['$http', '$scope', '$state', 'postID']
	function PostCreateUpdateController($http, $scope, $state, postID){
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
			
			if (postID != null) {
				$http({
					url : "PostController?action=detail",
					method: "POST",
					params: {
						postID: postID
					}
				}).then(function(response) {
					vm.post = response.data;
					formatData();
		        }, function(response) {
		        });
			}
		}
		
		vm.save = function () {
			$scope.$broadcast('show-errors-check-validity');
			if ($scope.postCreateUpdate.$invalid) {
				var invalidControl = angular.element('input.ng-invalid').first() || angular.element('select.ng-invalid').first() ||angular.element('option.ng-invalid').first();
				if (invalidControl != null) {
                    invalidControl.focus();
                }
			} else {
				if(postID == null) {
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
				} else {
					$http({
			            url : 'PostController?action=update',
			            method : "POST",
			            headers: {
			            	Accept: "application/json; charset=utf-8",
			            	"Content-Type" : "application/json; charset=utf-8"
			            },
						data: JSON.stringify(vm.post)
			        }).then(function(response) {
			        	if(response.data) {
			        		var alert = { type: 'success', msg: 'Cập nhật bài đăng thành công!'};
							vm.alerts.push(alert);
							$state.go("post-detail", {postID : postID, alerts : vm.alerts});
			        	}

			        }, function(response) {
			        });
				}
				
			}
		}
		
		function loadData() {
			vm.post.UserID = "1";
			vm.post.PostTypeID = "1";
			vm.post.CreationDate = new Date();
		}
		
		function formatData(){
			if(vm.post.CreationDate) {
				vm.post.CreationDate = new Date(vm.post.CreationDate);
			}
			vm.post.UserID = vm.post.UserID.toString();
			vm.post.PostTypeID = vm.post.PostTypeID.toString();
			vm.post.CategoryID = vm.post.CategoryID.toString();
		}
		
		vm.closeAlert = function(index) {
		    vm.alerts.splice(index, 1);
		};
	}
	
})();