<%--
  Created by IntelliJ IDEA.
  User: linzhijie
  Date: 2017/1/1
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>快递</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/angular.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/angular-route.min.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=kFWiWgzUfAUN9rmebdSCiVT30IYRDmi6"></script>

</head>
<body>
    <div class="container-fluid">
        <div class="row" id="mapContainer">
            <div class="col-md-12">
                <div id="vv" style="height: 400px"></div>
            </div>
        </div>
    </div>
    <div class="container"  ng-app="orderApp" ng-init="hasAdd=false"  ng-controller="orderController">
        <div class="row" ng-switch="hasAdd">
            <!--新订单表单-->
            <div class="col-md-6 col-md-offset-1">
                <!--新订单填写-->
                <form class="form-horizontal" role="form"  ng-hide="hasAdd">
                    <!--寄件人-->
                    <div class="form-group">
                        <label for="user" class="col-sm-2 control-label">寄件人</label>
                        <div class="col-sm-10">
                            <label id="user" class="col-sm-2 control-label" ng-model="username">${user.username}</label>
                        </div>
                    </div>
                    <!--寄件人电话-->
                    <div class="form-group">
                        <label for="phone" class="col-sm-2 control-label">寄件电话</label>
                        <div class="col-sm-10">
                            <label id="phone" class="col-sm-2 control-label" ng-model="phone">{{phone=${user.phone}}}</label>
                        </div>
                    </div>
                    <!--发货地址-->
                    <div class="form-group">
                        <label for="sprovince" class="col-sm-2 control-label">发货地址</label>
                        <!--省-->
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="sprovince" placeholder="省" name="saddress" ng-model="saddress.sprovince" ng-change="getSaddress(saddress.sprovince)">
                        </div>
                        <!--市-->
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="scity" placeholder="市" name="saddress" ng-model="saddress.scity"  ng-change="getSaddress(saddress.scity)">
                        </div>
                        <!--区-->
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="sblock" placeholder="区" name="saddress" ng-model="saddress.sblock"  ng-change="getSaddress(saddress.sblock)">
                        </div>
                        <!--门牌号-->
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="snumber" placeholder="门牌号" name="saddress" ng-model="saddress.snumber"  ng-change="getSaddress(saddress.snumber)">
                        </div>
                    </div>
                    <!--收货地址-->
                    <div class="form-group">
                        <label for="rprovince" class="col-sm-2 control-label">收货地址</label>
                        <!--省-->
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="rprovince" placeholder="省" name="saddress" ng-model="saddress">
                        </div>
                        <!--市-->
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="rcity" placeholder="市" name="saddress" ng-model="saddress">
                        </div>
                        <!--区-->
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="rblock" placeholder="区" name="saddress" ng-model="saddress">
                        </div>
                        <!--门牌号-->
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="rnumber" placeholder="门牌号" name="saddress" ng-model="saddress">
                        </div>
                    </div>

                    <div class="form-group">
                        <!--货物重量-->
                        <!--监测货物重量的值，实时计算价格-->
                        <label for="weight" class="col-sm-2 control-label">货物重量</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="weight" placeholder="货物重量" name="weight" ng-model="weight" ng-change="countPrice()">
                        </div>
                        <!--价格-->
                        <!--监测货物重量的值，实时计算价格-->
                        <label class="col-sm-2 control-label">价格</label>
                        <div class="col-sm-4">
                            <label  class="col-sm-2 control-label" ng-model="price">{{price+" 元"}}</label>
                        </div>
                    </div>
                    <!--货物状态-->
                    <div class="form-group" ng-hide="true">
                        <label for="phone" class="col-sm-2 control-label">货物状态</label>
                        <div class="col-sm-10">
                            <label id="state" class="col-sm-2 control-label" ng-model="state">{{state="waiting"}}</label>
                        </div>
                    </div>
                    <!--提交订单-->
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-info" ng-click="postOrder()">提交订单</button>
                        </div>
                    </div>
                </form>
                <!--新订单信息-->
                <div class="form-horizontal" role="form"  ng-show="hasAdd">

                    <!--寄件人-->
                    <div class="form-group">
                        <label class="col-sm-2 control-label">寄件人</label>
                        <div class="col-sm-10">
                            <label class="col-sm-2 control-label">${user.username}</label>
                        </div>
                    </div>
                    <!--寄件人电话-->
                    <div class="form-group">
                        <label class="col-sm-2 control-label">寄件电话</label>
                        <div class="col-sm-10">
                            <label class="col-sm-2 control-label">{{phone}}</label>
                        </div>
                    </div>
                    <!--单号-->
                    <div class="form-group">
                        <label class="col-sm-2 control-label">订单号</label>
                        <div class="col-sm-10">
                            <label class="col-sm-2 control-label">{{transnum}}</label>
                        </div>
                    </div>
                    <!--发货地址-->
                    <div class="form-group">
                        <label class="col-sm-2 control-label">发货地址</label>
                        <div class="col-sm-10">
                            <label class="col-sm-2 control-label">{{saddress}}</label>
                        </div>
                    </div>
                    <!--收货地址-->
                    <div class="form-group">
                        <label class="col-sm-2 control-label">收货地址</label>
                        <div class="col-sm-10">
                            <label class="col-sm-2 control-label">{{raddress}}</label>
                        </div>
                    </div>
                    <!--货物重量-->
                    <!--监测货物重量的值，实时计算价格-->
                    <div class="form-group">
                        <label class="col-sm-2 control-label">货物重量</label>
                        <div class="col-sm-10">
                            <label class="col-sm-2 control-label">{{weight}}</label>
                        </div>
                    </div>
                    <!--价格-->
                    <!--监测货物重量的值，实时计算价格-->
                    <div class="form-group">
                        <label class="col-sm-2 control-label">价格</label>
                        <div class="col-sm-10">
                            <label  class="col-sm-2 control-label" ng-model="price">{{price}}</label>
                        </div>
                    </div>
                    <!--货物状态-->
                    <div class="form-group">
                        <label class="col-sm-2 control-label">货物状态</label>
                        <div class="col-sm-10">
                            <label class="col-sm-2 control-label" ng-model="state">{{state}}</label>
                        </div>
                    </div>
                </div>
            </div>
            <!--历史订单表单-->
            <div class="col-md-4" >
                <div class="row">
                    <div class="col-md-4 col-md-offset-4">
                        <button class="btn btn-info">查看历史订单</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        var map = new BMap.Map("vv"); // 创建地图实例
        var point = new BMap.Point(116.404, 39.915); // 创建点坐标
        map.centerAndZoom(point, 15); // 初始化地图，设置中心点坐标和地图级别
    </script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/neworder.js"></script>
</body>
</html>
