<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>登录 - meetu</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div>

                <img src="img/logo.jpeg" class="img-circle" style="width:180px;height:180px" alt="profile">

            </div>
            <h3>Nice 2 meetu !</h3>
            <p>欢迎来到meetu公众平台，有趣的活动，有料的文章，有品的伙伴，都在这里等你呢～
                <!--Continually expanded and constantly improved Inspinia Admin Them (IN+)-->
            </p>
            <form:form method="post" class="m-t" role="form" action="/doLogin">
                <div class="form-group">
                    <form:input type="email" class="form-control" placeholder="用户名" required="" path="email"/>
                </div>
                <div class="form-group">
                    <form:input type="password" class="form-control" placeholder="密码" required="" path="pwd"/>
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b" style="background-color:#44b549">登录</button>

                <a href="login.html#"><small>忘记密码?</small></a>
                <p class="text-muted text-center"><small>还没有meetu账号吗?</small></p>
                <a class="btn btn-sm btn-white btn-block" href="register.html">微信登录</a>
                <a class="btn btn-sm btn-white btn-block" href="register.html">创建账号</a>
                <a class="btn btn-sm btn-white btn-block" href="register.html">访客模式浏览？？？是否支持这个？？？</a>
            </form:form>
            <p class="m-t"> <small>CopyRight &copy meetu.cn ; 2018</small> </p>
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
