<%--
  Created by IntelliJ IDEA.
  User: linzhijie
  Date: 2017/1/4
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dashboard.css" rel="stylesheet">
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
                <li><a href="http://localhost:8080/express/manager/order/address">修改订单地址</a></li>
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
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">控制台</h1>

            <h2 class="sub-header">添加管理员</h2>
            <div class="table-responsive">
                <form class="form-horizontal" role="form" action="http://localhost:8080/express/manager/staff/add" method="post">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="name" placeholder="name" name="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="password" placeholder="Password" name="password">
                        </div>
                    </div>
                    <select class="form-control" name="type">
                        <option value="0">管理员</option>
                        <option value="1">超级管理员</option>
                    </select>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">添加</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/docs.min.js"></script>
</body>
</html>


