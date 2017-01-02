<%--
  Created by IntelliJ IDEA.
  User: linzhijie
  Date: 2016/12/29
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>快递</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=kFWiWgzUfAUN9rmebdSCiVT30IYRDmi6"></script>
    <script type="text/javascript" src="js/angular.min.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row" id="mapContainer">
        <div class="col-md-12">
            <div id="vv" style="height: 400px"></div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-2"  ng-app="userLogin">
            <form role="form" name="userForm" ng-controller="userController" action="http://localhost:8080/express/user/addUser">
                <div class="form-group">
                    <label for="phone">电话</label>
                    <input type="text" class="form-control" id="phone" name="phone" ng-model="userPhone" ng-change="checkUser()" required/>
                    <span ng-show="userForm.userName.$error.required">留个电话吧</span>
                </div>
                <div class="form-group">
                    <label for="username">寄件人</label>
                    <input type="text" class="form-control" id="username" name="username" ng-model="userName" required/>
                    <span ng-show="userForm.userName.$error.required">新用户必须填写寄件人</span>
                </div>
                <button type="submit" class="btn btn-info">进入</button>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>

<script type="text/javascript">
    var map = new BMap.Map("vv"); // 创建地图实例
    var point = new BMap.Point(116.404, 39.915); // 创建点坐标
    map.centerAndZoom(point, 15); // 初始化地图，设置中心点坐标和地图级别
</script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>
