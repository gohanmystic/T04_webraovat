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
		});
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
        })
        .state('post-list', {
        	parent: 'admin',
        	url: '/post-list',
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