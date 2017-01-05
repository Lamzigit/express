<%--
  Created by IntelliJ IDEA.
  User: linzhijie
  Date: 2017/1/4
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dashboard.css" rel="stylesheet">
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/angular.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/angular-route.min.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=kFWiWgzUfAUN9rmebdSCiVT30IYRDmi6"></script>
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Dashboard</a></li>
                <li><a href="#">Settings</a></li>
                <li><a href="#">Profile</a></li>
                <li><a href="#">Help</a></li>
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
            </form>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active">订单管理</li>
                <li><a href="http://localhost:8080/express/manager/order/undeal">未处理订单</a></li>
                <li><a href="http://localhost:8080/express/manager/order/add">后台添加订单</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li class="active">路线规划</li>
                <li><a href="http://localhost:8080/express/manager/route/embrace">揽件路线</a></li>
                <li><a href="http://localhost:8080/express/manager/route/dispatch">派件路线</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li class="active">管理员管理</li>
                <li><a href="http://localhost:8080/express/manager/staff/add">添加管理员</a></li>
                <li><a href="http://localhost:8080/express/manager/staff/list">全部管理员</a></li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"   ng-app="orderApp">
            <h1 class="page-header">控制台</h1>

            <h2 class="sub-header">订单列表</h2>
            <div class="row" id="mapContainer">
                <div class="col-md-12">
                    <div id="vv" style="height: 400px"></div>
                </div>
            </div>
            <div class="table-responsive"  ng-init="hasAdd=false"  ng-controller="orderController">
                <!--新订单填写-->
                <form class="form-horizontal" role="form"  ng-hide="hasAdd">
                    <!--寄件人
                    <div class="form-group">
                        <label for="user" class="col-sm-2 control-label">寄件人</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="user" placeholder="用户" name="username" ng-model="user">
                        </div>
                    </div>-->
                    <!--寄件人电话-->
                    <div class="form-group">
                        <label for="phone" class="col-sm-2 control-label">寄件电话</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="phone" placeholder="电话" name="phone" ng-model="phone">
                        </div>
                    </div>
                    <!--发货地址-->
                    <div class="form-group">
                        <label for="sprovince" class="col-sm-2 control-label">发货地址</label>
                        <!--省-->
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="sprovince" placeholder="省" name="saddress" ng-model="saddress.province" ng-change="slocateProvince()">
                        </div>
                        <!--市-->
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="scity" placeholder="市" name="saddress" ng-model="saddress.city"  ng-change="slocateCity()">
                        </div>
                        <!--区-->
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="sblock" placeholder="区" name="saddress" ng-model="saddress.block"  ng-change="slocateBlock()">
                        </div>
                        <!--门牌号-->
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="snumber" placeholder="门牌号" name="saddress" ng-model="saddress.number"  ng-change="slocateNumber()">
                        </div>
                    </div>
                    <!--收货地址-->
                    <div class="form-group">
                        <label for="rprovince" class="col-sm-2 control-label">收货地址</label>
                        <!--省-->
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="rprovince" placeholder="省" name="raddress" ng-model="raddress.province" ng-change="rlocateProvince()">
                        </div>
                        <!--市-->
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="rcity" placeholder="市" name="raddress" ng-model="raddress.city" ng-change="rlocateCity()">
                        </div>
                        <!--区-->
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="rblock" placeholder="区" name="raddress" ng-model="raddress.block" ng-change="rlocateBlock()">
                        </div>
                        <!--门牌号-->
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="rnumber" placeholder="门牌号" name="raddress" ng-model="raddress.number" ng-change="rlocateNumber()">
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

                    <!--寄件人
                    <div class="form-group">
                        <label class="col-sm-2 control-label">寄件人</label>
                        <div class="col-sm-10">
                            <label class="col-sm-2 control-label">${user.username}</label>
                        </div>
                    </div>-->
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
                        <div class="col-sm-10 col-md-6">
                            <label class="col-sm-2 control-label">{{saddress.province+saddress.city+saddress.block+saddress.number}}</label>
                        </div>
                    </div>
                    <!--收货地址-->
                    <div class="form-group">
                        <label class="col-sm-2 control-label">收货地址</label>
                        <div class="col-sm-10 col-md-6">
                            <label class="col-sm-2  control-label">{{raddress.province+raddress.city+raddress.block+raddress.number}}</label>
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
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript">
    var map = new BMap.Map("vv"); // 创建地图实例
    var point = new BMap.Point(116.404, 39.915); // 创建点坐标
    map.centerAndZoom(point, 15); // 初始化地图，设置中心点坐标和地图级别
</script>
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/docs.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/neworder.js"></script>
</body>
</html>



