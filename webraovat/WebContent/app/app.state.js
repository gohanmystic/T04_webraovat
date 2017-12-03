(function() {
    'use strict';

    angular
        .module('webraovatApp')
        .config(stateAbsConfig)
        .config(stateConfig);
    
    stateAbsConfig.$inject = [ '$stateProvider' ];
    stateConfig.$inject = ['$stateProvider'];

	function stateAbsConfig($stateProvider) {
		$stateProvider.state('admin', {
			url : '/admin',
		})
	}
	
    function stateConfig($stateProvider) {
    	
        $stateProvider
        .state('account', {
        	parent: 'admin',
        	url: '/account',
        	views: {
                'content@': {
                    templateUrl: 'app/admin/account/account.html',
                    controller: 'AccountController',
                    controllerAs: 'vm'
                }
            },
        })
        .state('category-list', {
        	parent: 'admin',
        	url: '/category-list',
        	params: {alerts: null},
        	views: {
                'content@': {
                    templateUrl: 'app/admin/category/categories.html',
                    controller: 'CategoryController',
                    controllerAs: 'vm'
                }
            },
        })
        .state('category-create', {
        	parent: 'admin',
        	url: '/category-create',
        	views: {
                'content@': {
                    templateUrl: 'app/admin/category/category-create-update.html',
                    controller: 'CategoryCreateUpdateController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            	cateID: function() {
            		return null;
            	}
            }
        })
        .state('category-update', {
        	parent: 'admin',
        	url: '/category-update/{cateID}',
        	views: {
                'content@': {
                    templateUrl: 'app/admin/category/category-create-update.html',
                    controller: 'CategoryCreateUpdateController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            	cateID: ['$stateParams', function($stateParams) {
                    return $stateParams.cateID;
                }]
            }
        })
        .state('category-detail', {
        	parent: 'admin',
        	url: '/category-detail/{cateID}',
        	params: {alerts : null},
        	views: {
                'content@': {
                    templateUrl: 'app/admin/category/category-detail.html',
                    controller: 'CategoryDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            	cateID: ['$stateParams', function($stateParams) {
                    return $stateParams.cateID;
                }]
            }
        })
        .state('post-list', {
        	parent: 'admin',
        	url: '/post-list',
        	params: {alerts : null},
        	views: {
                'content@': {
                    templateUrl: 'app/admin/post/posts.html',
                    controller: 'PostController',
                    controllerAs: 'vm'
                }
            },
        })
        .state('post-create', {
        	parent: 'admin',
        	url: '/post-create',
        	views: {
                'content@': {
                    templateUrl: 'app/admin/post/post-create-update.html',
                    controller: 'PostCreateUpdateController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            	postID: function() {
            		return null;
            	}
            }
        })
        .state('post-update', {
        	parent: 'admin',
        	url: '/post-update/{postID}',
        	views: {
                'content@': {
                    templateUrl: 'app/admin/post/post-create-update.html',
                    controller: 'PostCreateUpdateController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            	postID: ['$stateParams', function($stateParams) {
                    return $stateParams.postID;
                }]
            }
            
        })
        .state('post-detail', {
        	parent: 'admin',
        	url: '/post-detail/{postID}',
        	params: {alerts: null},
        	views: {
                'content@': {
                    templateUrl: 'app/admin/post/post-detail.html',
                    controller: 'PostDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            	postID: ['$stateParams', function($stateParams) {
                    return $stateParams.postID;
                }]
            }
            
        })
        .state('report-list', {
        	parent: 'admin',
        	url: '/report-list',
        	views: {
                'content@': {
                    templateUrl: 'app/admin/report/reports.html',
                    controller: 'ReportController',
                    controllerAs: 'vm'
                }
            },
        })
        .state('report-create', {
        	url: '/report-create',
        	views: {
                'content@': {
                    templateUrl: 'app/admin/report/report-create-update.html',
                    controller: 'ReportCreateUpdateController',
                    controllerAs: 'vm'
                }
            },
        })
        .state('user-list', {
        	parent: 'admin',
        	url: '/user-list',
        	params : {alerts : null},
        	views: {
                'content@': {
                    templateUrl: 'app/admin/user/users.html',
                    controller: 'UserController',
                    controllerAs: 'vm'
                }
            },
        })
        .state('user-create', {
        	parent: 'admin',
        	url: '/user-create',
        	views: {
                'content@': {
                    templateUrl: 'app/admin/user/user-create-update.html',
                    controller: 'UserCreateUpdateController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                userID: function() {
                	return null;
                }
            }
        })
        .state('user-detail', {
        	parent: 'admin',
        	url: '/user-detail/{userID}',
        	params : {alerts: null},
        	views: {
                'content@': {
                    templateUrl: 'app/admin/user/user-detail.html',
                    controller: 'UserDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                userID: ['$stateParams', function($stateParams) {
                    return $stateParams.userID;
                }]
            }
        })
        .state('user-update', {
        	parent: 'admin',
        	url: '/user-update/{userID}',
        	views: {
                'content@': {
                    templateUrl: 'app/admin/user/user-create-update.html',
                    controller: 'UserCreateUpdateController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                userID: ['$stateParams', function($stateParams) {
                    return $stateParams.userID;
                }]
            }
        })
    }
    
    
})();
