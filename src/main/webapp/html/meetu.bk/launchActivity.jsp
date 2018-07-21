<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>发布活动 - meetu</title>

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
                                    <div class="ibox-title" style="border:0;background-color:#44b549;color:white">
                                        <img src="img/logo.jpeg" class="img-circle" style="width:48px;height:48px" alt="profile">
                                        <!-- todo: 把字体调大一点 -->
                                         <strong>发布活动信息 </strong><br>
                                         <small style="color:white">tips: 可以发送链接到电脑，电脑端填写更方便呢</small>

                                    </div>

                                    <div class="ibox-content">


                                        <%--以下为待提交的表单信息--%>
                                        <form method="post" action="${pageContext.request.contextPath}/doLaunchActivity" class="form-horizontal" enctype="multipart/form-data">
                                            <div class="form-group"><label class="col-sm-2 control-label">活动名称</label>

                                                <div class="col-sm-10"><input type="text" class="form-control" name="name"></div>
                                            </div>
                                            <div class="hr-line-dashed"></div>
                                            <div class="form-group"><label class="col-sm-2 control-label">活动时间</label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="date"> <span class="help-block m-b-none">可以填写活动开始时间或者活动持续时间段</span>
                                                </div>
                                            </div>
                                            <div class="hr-line-dashed"></div>
                                            <div class="form-group"><label class="col-sm-2 control-label">活动地点</label>
                                                <div class="col-sm-10"><input type="text" class="form-control" name="location"> <span class="help-block m-b-none">确保小伙伴们可以顺利抵达目的地，请填写详细位置信息哦</span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">费用</label>

                                                <div class="col-sm-10">
                                                    <div>
                                                        <label style="font-weight:normal">
                                                            <input type="radio" value="0" id="optionsRadios1" name="charge" style="margin-top:15px;margin-right:8px">免费
                                                        </label>
                                                    </div>
                                                    <div class="form-inline">
                                                        <label style="font-weight:normal">
                                                            <input type="radio" value="1" id="optionsRadios2" name="charge" style="margin-top:15px;margin-right:8px">收费
                                                        </label>
                                                        <input type="text" name="chargeDetail" placeholder="" class="form-control" style="border:0px;border-bottom:#000000 1px solid;width:110px;margin-left:10px"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">发起人</label>

                                                <div class="col-sm-10">
                                                    <div><label style="font-weight:normal"> <input type="radio" name="sponsor" value="0" id="optionsRadios1"  style="margin-top:15px;margin-right:8px">我是发起人</label></div>
                                                    <div><label style="font-weight:normal"> <input type="radio" name="sponsor" value="1" id="optionsRadios2" style="margin-top:15px;margin-right:8px">我是票务代理</label></div>
                                                    <div><label style="font-weight:normal"> <input type="radio" name="sponsor" value="2" id="optionsRadios3" style="margin-top:15px;margin-right:8px">我是场地提供方</label></div>
                                                    <div><label style="font-weight:normal"> <input type="radio" name="sponsor" value="3" id="optionsRadios4" style="margin-top:15px;margin-right:8px">以上都不是</label></div>
                                                </div>
                                            </div>

                                            <div class="hr-line-dashed"></div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">活动海报</label>
                                                <input type="file" name="logoFile"/>
                                            </div>
                                            <div class="hr-line-dashed"></div>
                                            <div class="form-group"><label class="col-sm-2 control-label">活动详情</label>

                                                <div class="col-sm-10">
                                                    <textarea name="details" class="note-editor note-editable form-control"></textarea>
                                                </div>
                                            </div>
                                            <div class="hr-line-dashed"></div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">活动须知</label>

                                                <div class="col-sm-10">
                                                    <textarea name="tips" class="note-editor note-editable form-control"></textarea>
                                                </div>
                                            </div>
                                            <div class="hr-line-dashed"></div>
                                            <h3>更多活动相关图片...(让用户可以上传更多图片)</h3>
                                            <button class="btn btn-sm btn-primary pull-right" data-toggle="tooltip" data-placement="top" type="submit" style="background-color:#44b549;margin-top:10px;margin-bottom:100px"> 发布活动</button>

                                        </form>
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
activityDetail_meetu.html
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

    <%--先试试不用ajax，直接只用springMVC表单提交，看能不能完成我的需求？？？--%>
    <%--直接把以上的表单改成springMVC的表单，同时在controller那里依然按照ajax那种形式的方法来写文件接收。。看能不能接收到文件？？？--%>
    <script>
        function doLaunchActivity() {
            alert("enter doLaunchActivity()")
            var formData = new FormData($( "#launchActivityForm" )[0]);
            $.ajax({
                url: 'http://localhost:8080/doLaunchActivity' ,
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (returndata) {
                    alert(returndata);
                },
                error: function (returndata) {
                    alert(returndata);
                }
            });
        }
    </script>
</body>
</html>
