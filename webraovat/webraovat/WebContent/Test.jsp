<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="node_modules/angular/angular.min.js"></script>
</head>
<body>
	<h1>Test</h1>
	<div ng-app="myApp" ng-controller="myCtrl as vm">

		First Name: <input type="text" ng-model="vm.firstName"><br>
		Last Name: <input type="text" ng-model="vm.lastName"><br>
		<br>
		Full Name: {{vm.firstName + " " + vm.lastName}}
		
		<button ng-click="vm.sendPost()">Send</button>
		<input type="text" ng-model="message"/>
		
		<div ng-repeat="product in vm.listProduct">
			<h1>{{product.id}}</h1>
			<h1>{{product.name}}</h1>
			<h1>{{product.price}}</h1>	
		</div>
		
	</div>

	<script>
	var app = angular.module('myApp', []);
	app.$inject = ['$scope', '$http'];
	app.controller('myCtrl', myCtrl);
	
	function myCtrl ($scope, $http){
		var vm = this;
		console.log(vm);
		vm.firstName = "John";
		vm.lastName = "Doe";
		vm.listProduct = [];
		vm.sendPost = function() {
	        $http({
	            url : 'TestServlet?action=demo2',
	            method : "get",
	            headers: {
	            	Accept: "application/json; charset=utf-8",
	            	"Content-Type" : "application/json; charset=utf-8"
	            }
	        }).then(function(response) {
	        	vm.listProduct = response.data;
	            console.log(vm.listProduct);
	        }, function(response) {
	            //fail case
	            console.log(response);
	        });
	 
	    };
	}
	</script>
</body>
</html>