<%--
  Created by IntelliJ IDEA.
  User: linzhijie
  Date: 2017/1/4
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <title>管理员登录</title>
</head>
<body>
    <div class="container">

        <div class="col-md-offset-4 col-md-4">
            <form class="form-signin" role="form" action="http://localhost:8080/express/manager/login" method="post">
                <h2 class="form-signin-heading">管理员登录</h2>
                <input type="text" class="form-control" placeholder="姓名" name="name" required autofocus>
                <input type="password" class="form-control" placeholder="密码" name="password" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
            </form>
        </div>

    </div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->
</body>
</html>
