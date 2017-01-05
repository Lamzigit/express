<%--
  Created by IntelliJ IDEA.
  User: linzhijie
  Date: 2017/1/4
  Time: 22:24
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
            <h1 class="page-header">Dashboard</h1>

            <h2 class="sub-header">管理员列表</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>管理员姓名</th>
                        <th>等级</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${!empty managerList}">
                        <c:forEach items="${managerList}" var="manager">
                            <tr>
                                <td>${manager.id}</td>
                                <td>${manager.name}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${manager.type == 1}">
                                            超级管理员
                                        </c:when>
                                        <c:when test="${manager.type == 0}">
                                            管理员
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td><button>修改权限</button><button>删除</button></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
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

