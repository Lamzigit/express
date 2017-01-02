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
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/neworder.js"></script>
</head>
<body>

    <div class="container">
        <h1>乌龟快递</h1>
        <div class="row">
            <!--新订单表单-->
            <div class="col-md-6 col-md-offset-1" ng-app="orderAdd">
                <form class="form-horizontal" role="form" ng-controller="addController">
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
                        <label for="saddress" class="col-sm-2 control-label">发货地址</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="saddress" placeholder="发货地址" name="saddress" ng-model="saddress">
                        </div>
                    </div>
                    <!--收货地址-->
                    <div class="form-group">
                        <label for="raddress" class="col-sm-2 control-label">收货地址</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="raddress" placeholder="收货地址" name="raddress" ng-model="raddress">
                        </div>
                    </div>
                    <!--货物重量-->
                    <!--监测货物重量的值，实时计算价格-->
                    <div class="form-group">
                        <label for="weight" class="col-sm-2 control-label">货物重量</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="weight" placeholder="货物重量" name="weight" ng-model="weight" ng-change="countPrice()">
                        </div>
                    </div>
                    <!--价格-->
                    <!--监测货物重量的值，实时计算价格-->
                    <div class="form-group">
                        <label class="col-sm-2 control-label">价格</label>
                        <div class="col-sm-10">
                            <label  class="col-sm-2 control-label" ng-model="price">{{price+" 元"}}</label>
                        </div>
                    </div>
                    <!--货物状态-->
                    <div class="form-group">
                        <label for="phone" class="col-sm-2 control-label">寄件电话</label>
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
            </div>
            <!--历史订单表单-->
            <div class="col-md-4" ng-app="orderList">
                <div class="row" ng-controller="listController">
                    <div class="col-md-4 col-md-offset-4">
                        <button class="btn btn-info">查看历史订单</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
</body>
</html>
