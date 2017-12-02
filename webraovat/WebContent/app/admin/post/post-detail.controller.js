(function(){
	'use strict';
	angular
		.module('webraovatApp')
		.controller('PostDetailController', PostDetailController);
	PostDetailController.$inject = ['$state', '$http', 'postID', '$uibModal'];
	function PostDetailController($state, $http, postID, $uibModal){
		var vm = this;
		vm.post = {};
		vm.alerts = [];
		vm.load = load;
		
		vm.load();
		
		function load() {
			vm.alerts = $state.params.alerts;
			
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
		
		function formatData(){
			if(vm.post.CreationDate) {
				vm.post.CreationDate = new Date(vm.post.CreationDate);
			}
			vm.post.UserID = vm.post.UserID.toString();
			vm.post.PostTypeID = vm.post.PostTypeID.toString();
			vm.post.CategoryID = vm.post.CategoryID.toString();
		}
		
		vm.delete = function() {
			vm.modalInstantDeletePost = $uibModal.open({
                templateUrl: 'app/admin/post/post-delete-dialog.html',
                controller: 'PostDeleteController',
                controllerAs: 'vm',
                size: 'md',
                resolve: {
                    postID: function() {
                        return postID;
                    }
                }
            });
		}
		
		vm.confirm = function () {
			vm.modalInstantConfirmPost = $uibModal.open({
                templateUrl: 'app/admin/post/post-confirm-dialog.html',
                controller: 'PostConfirmController',
                controllerAs: 'vm',
                size: 'md',
                resolve: {
                    postID: function() {
                        return postID;
                    }
                }
            });
		}

		vm.closeAlert = function(index) {
		    vm.alerts.splice(index, 1);
		};
	}
	
})();