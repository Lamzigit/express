/**
 * Created by linzhijie on 2017/1/1.
 */

var orderApp = angular.module("orderApp",['ngRoute']);
orderApp.controller("orderController",function ($scope,$http) {
    //根据地址对象获取对应的经纬度坐标点
    /*function getAddressPoint(addressObject) {
     //返回的坐标点
     var pointt;
     // 创建地址解析器实例
     var myGeo = new BMap.Geocoder();
     // 将地址解析结果显示在地图上,并调整地图视野
     myGeo.getPoint(getAddressText(addressObject), function(point){
     return point;
     }, addressObject.city);

     }*/
    //根据地址对象获取地址字符串
    function getAddressText(addressObject) {
        return addressObject.province + addressObject.city + addressObject.block + addressObject.number;
    }



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
        // 创建地址解析器实例
        var myGeo = new BMap.Geocoder();
        // 将地址解析结果显示在地图上,并调整地图视野
        myGeo.getPoint(getAddressText($scope.raddress),function (point) {
            $scope.raddressPoint = point
            myGeo.getPoint(getAddressText($scope.saddress), function(point){
                $scope.saddressPoint = point;
                $http({
                    url : 'http://localhost:8080/express/order/add',
                    method : "post",
                    params : {
                        phone : $scope.phone,
                        saddress : {
                            point:$scope.saddressPoint,
                            text:getAddressText($scope.saddress)
                        },
                        raddress : {
                            point:$scope.raddressPoint,
                            text:getAddressText($scope.raddress)
                        },
                        weight : $scope.weight,
                        price : $scope.price,
                        state : $scope.state,
                    }
                }).success(function (data) {
                    $scope.hasAdd=data.hasAdd;
                    $scope.transnum = data.transnum;
                }).error(function (data) {
                    console.log(data);
                });
            }, $scope.saddress.city);
        },$scope.raddress.city);
        console.log($scope.phone);
        console.log(getAddressText($scope.saddress));
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
    //发货地址
    $scope.slocateProvince = function () {
        map.centerAndZoom($scope.saddress.province,15);
    }
    $scope.slocateCity = function () {
        if($scope.saddress.block != undefined)
            map.centerAndZoom($scope.saddress.city+$scope.saddress.block,15);
        else
            map.centerAndZoom($scope.saddress.city,15)
    }
    $scope.slocateBlock = function () {
        if($scope.saddress.city != undefined)
            map.centerAndZoom($scope.saddress.city+$scope.saddress.block,15);
    }
    $scope.slocateNumber = function () {
        /*function G(id) {
         return document.getElementById(id);
         }
         var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
         {
         "input" : "snumber",
         "location" : map
         });

         ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
         var str = "";
         var _value = e.fromitem.value;
         var value = "";
         if (e.fromitem.index > -1) {
         value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
         }
         str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

         value = "";
         if (e.toitem.index > -1) {
         _value = e.toitem.value;
         value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
         }
         str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
         G("searchResultPanel").innerHTML = str;
         });

         var myValue;
         ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
         var _value = e.item.value;
         myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
         G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;

         setPlace();
         });

         function setPlace(){
         map.clearOverlays();    //清除地图上所有覆盖物
         function myFun(){
         var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
         map.centerAndZoom(pp, 18);
         map.addOverlay(new BMap.Marker(pp));    //添加标注
         }
         var local = new BMap.LocalSearch(map, { //智能搜索
         onSearchComplete: myFun
         });
         local.search(myValue);
         }*/
        var local = new BMap.LocalSearch(map, {
            renderOptions:{map: map},
            onInfoHtmlSet:function (LocalResultPoi) {
                var marker = LocalResultPoi.marker;
                marker.addEventListener("click",function (){
                    $scope.saddress.number = marker.getTitle();  //获取marker的位置
                });
            }
        });
        local.searchNearby($scope.saddress.number,$scope.saddress.block,1000);
        //让所有点在视野范围内
        //map.setViewport(pointArray);
    }
    //收货货地址
    $scope.rlocateProvince = function () {
        map.centerAndZoom($scope.raddress.province,15);
    }
    $scope.rlocateCity = function () {
        if($scope.raddress.block != undefined)
            map.centerAndZoom($scope.raddress.city+$scope.raddress.block,15);
        else
            map.centerAndZoom($scope.raddress.city,15)
    }
    $scope.rlocateBlock = function () {
        if($scope.raddress.city != undefined)
            map.centerAndZoom($scope.raddress.city+$scope.raddress.block,15);
    }
    $scope.rlocateNumber = function () {
        /*function G(id) {
         return document.getElementById(id);
         }
         var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
         {
         "input" : "snumber",
         "location" : map
         });

         ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
         var str = "";
         var _value = e.fromitem.value;
         var value = "";
         if (e.fromitem.index > -1) {
         value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
         }
         str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

         value = "";
         if (e.toitem.index > -1) {
         _value = e.toitem.value;
         value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
         }
         str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
         G("searchResultPanel").innerHTML = str;
         });

         var myValue;
         ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
         var _value = e.item.value;
         myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
         G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;

         setPlace();
         });

         function setPlace(){
         map.clearOverlays();    //清除地图上所有覆盖物
         function myFun(){
         var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
         map.centerAndZoom(pp, 18);
         map.addOverlay(new BMap.Marker(pp));    //添加标注
         }
         var local = new BMap.LocalSearch(map, { //智能搜索
         onSearchComplete: myFun
         });
         local.search(myValue);
         }*/
        var local = new BMap.LocalSearch(map, {
            renderOptions:{map: map},
            onInfoHtmlSet:function (LocalResultPoi) {
                var marker = LocalResultPoi.marker;
                marker.addEventListener("click",function (){
                    $scope.raddress.number = marker.getTitle();  //获取marker的位置
                });
            }
        });
        local.searchNearby($scope.raddress.number,$scope.raddress.block,1000);
        //让所有点在视野范围内
        //map.setViewport(pointArray);
    }
});
