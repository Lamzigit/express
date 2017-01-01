var app = angular.module('userLogin',[]);
app.controller('userController',function ($scope,$http,$timeout) {
	$scope.checkUser = function () {
		$timeout(function () {
			$http({
				url : 'http://localhost:8080/express/user/checkUser',
				method : 'post',
				params : {
					phone : $scope.userPhone
				}
			}).success(function(data,header,config,status){
				$scope.userName = data;
			}).error(function(data,header,config,status){
				console.log("数据出错");
			});
		},2000);

	}
});