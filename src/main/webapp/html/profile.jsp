<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>个人中心 - meetu</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- Toastr style -->
    <link href="css/plugins/toastr/toastr.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

</head>

<body style="background-color:white">
    <div id="wrapper">


        <div class="row">
            <div class="col-lg-12">
                                <div class="ibox float-e-margins">
            <div class="col-lg-12" style="padding-left:0;padding-right:0;color:white">
              <div class="contact-box center-version" style="margin:0">

                  <a style="background-color:#44b549">

                      <img alt="image" class="img-circle" src="img/a2.jpg">


                      <h3 class="m-b-xs"><strong>${user.nickName}</strong></h3>


                      <address class="m-t-md">
                          <strong>签名</strong><br>
                          这里是签名
                      </address>

                  </a>
                  <div class="contact-box-footer" style="color:#676a6c">
                      <div class="m-t-xs btn-group">
                          <a class="btn btn-xs btn-white"><i class="fa fa-envelope"></i> 留言</a>
                          <a class="btn btn-xs btn-white"><i class="fa fa-user-plus"></i> 关注</a>
                      </div>
                  </div>

              </div>
          </div>

                                    <div class="ibox-content">

                                            <div class="feed-activity-list" >

                                                <div class="feed-element" >
                                                    <div><i class="fa fa-building"></i> 学校:</div><p>${user.school}<span class="label label-info pull-right" style="padding:0">已认证</span></p>
                                                </div>

                                                <div class="feed-element">
                                                    <div><i class="fa fa-graduation-cap"></i> 专业:</div>${user.major}
                                                </div>

                                                <div class="feed-element">
                                                    <!--改成利用生日计算年龄，js在前台计算还是在后台计算?-->
                                                    <div><i class="fa fa-birthday-cake"></i> 生日:</div><div class="date">${user.birthday}</div>
                                                </div>

                                                <div class="feed-element">
                                                    <div><i class="fa fa-phone"></i> 电话:</div>${user.tel}
                                                </div>

                                                <div class="feed-element">
                                                    <div><i class="fa fa-weixin"></i> 微信:</div>${user.wechat}
                                                </div>



                                            </div>


                                    </div>
                                </div>






                <%--引入公用footer--%>
                <jsp:include page="footer.jsp"></jsp:include>
            </div>
        </div>





    </div>

    <!-- Mainly scripts -->
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Flot -->
    <script src="js/plugins/flot/jquery.flot.js"></script>
    <script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="js/plugins/flot/jquery.flot.spline.js"></script>
    <script src="js/plugins/flot/jquery.flot.resize.js"></script>
    <script src="js/plugins/flot/jquery.flot.pie.js"></script>

    <!-- Peity -->
    <script src="js/plugins/peity/jquery.peity.min.js"></script>
    <script src="js/demo/peity-demo.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="js/inspinia.js"></script>
    <script src="js/plugins/pace/pace.min.js"></script>

    <!-- jQuery UI -->
    <script src="js/plugins/jquery-ui/jquery-ui.min.js"></script>

    <!-- GITTER -->
    <script src="js/plugins/gritter/jquery.gritter.min.js"></script>

    <!-- Sparkline -->
    <script src="js/plugins/sparkline/jquery.sparkline.min.js"></script>

    <!-- Sparkline demo data  -->
    <script src="js/demo/sparkline-demo.js"></script>

    <!-- ChartJS-->
    <script src="js/plugins/chartJs/Chart.min.js"></script>

    <!-- Toastr -->
    <script src="js/plugins/toastr/toastr.min.js"></script>


    <script>

    </script>
    <script>
        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
            (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
                m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

        ga('create', 'UA-4625583-2', 'webapplayers.com');
        ga('send', 'pageview');

    </script>
</body>
</html>
