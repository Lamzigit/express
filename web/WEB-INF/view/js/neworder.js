/**
 * Created by linzhijie on 2017/1/1.
 */
var appAdd = angular.module("orderAdd",[]);
appAdd.controller("addController",function ($scope,$http) {
    $scope.countPrice = function () {
        if($scope.weight>10&&$scope.weight<50)
            $scope.price = 10 + ($scope.weight-2)*1.3;//1kg起步,底价10元，超出部分按1.3倍叠加
        else if($scope.weight>50)
            $scope.price = "货物太重了";
        else
            $scope.price = 10;
    }

    $scope.postOrder = function () {
     $http({
            url : 'http://localhost:8080/express/order/add',
            method : "post",
            params : {
                phone : $scope.phone,
                saddress : $scope.saddress,
                raddress : $scope.raddress,
                weight : $scope.weight,
                price : $scope.price,
                state : $scope.state,
            }

        })
        /*console.log(
            "phone " + $scope.phone + "\n" +
            "saddress " + $scope.saddress + "\n" +
            "raddress " + $scope.raddress + "\n" +
            "weight " + $scope.weight + "\n" +
            "price " + $scope.price + "\n" +
            "uid " + $scope.uid + "\n" +
            "state " + $scope.state);*/

    }
});

var appList = angular.module("orderList",[]);
appList.controller("listController",function ($scope,$http) {
    $scope.getOrderList= function () {
        $http({
            url : 'http://localhost:8080/express/order/list',
            methode : post,
            params : {
                phone : $scope.phone
            }
        });
    }
});