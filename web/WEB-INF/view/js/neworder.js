/**
 * Created by linzhijie on 2017/1/1.
 */

var orderApp = angular.module("orderApp",['ngRoute']);
orderApp.controller("orderController",function ($scope,$http) {
    //实时计算价格
    $scope.countPrice = function () {
        if($scope.weight>10&&$scope.weight<50)
            $scope.price = 10 + ($scope.weight-2)*1.3;//1kg起步,底价10元，超出部分按1.3倍叠加
        else if($scope.weight>50)
            $scope.price = "货物太重了";
        else
            $scope.price = 10;
    }
    //异步提交表单
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
        }).success(function (data) {
         $scope.hasAdd=data.hasAdd;
         $scope.transnum = data.transnum;
     })
        console.log($scope.phone);
    }
    //获取用户历史订单列表
    $scope.getOrderList = function () {
        $http({
            url: 'http://localhost:8080/express/order/list',
            methode: post,
            params: {
                phone: $scope.phone
            }
        }).success(data,function () {
            console.log(data);
        });
    }
    //获取发货地址
    $scope.tempaddress = "";
    $scope.getSaddress = function (address) {
        $scope.tempaddress =$scope.tempaddress+address;
        if($scope.saddress.scity != undefined)
        //var map = new BMap.Map("vv"); // 创建地图实例
        map.centerAndZoom($scope.tempaddress,15);
        console.log($scope.tempaddress);
    }
});
