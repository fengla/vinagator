<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>D1小程序助手 | App管理</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="../css/dropzone.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">

</head>

<body>

    <div id="wrapper">

        <jsp:include page="nav.jsp"></jsp:include>

        <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">

        </div>
            <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2>APP上传</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="index.html">自动上传</a>
                        </li>

                        <li class="active">
                            <strong>爬虫获取</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>


        <div class="wrapper wrapper-content animated fadeIn">
            <div class="row">
                <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>APP编辑区域</h5>
                    </div>
                    <div class="ibox-content" style="padding-bottom:80px">
                        <div  class="form-horizontal">

                            <div class="form-group">
                                <label class="col-sm-2 control-label">dmx(整页获取)</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="srcUrl" name="srcUrl">
                                </div>
                            </div>

                            <button class="btn btn-primary" data-toggle="tooltip" data-placement="top" onclick="crawlList()" style="float:right"> 提交</button>

                            <div class="hr-line-dashed" style="margin-top: 100px"></div>
                            <%--相应信息如何作为json格式化展示？搜索bootstrap展示json数据--%>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">响应信息:</label>
                                <br>
                                <%--以上这样就可以在新一行展示内容了吗？这样的写法科学吗？--%>

                                <text id="crawlerResp" class="col-sm-10"></text>

                            </div>
                        </>
                    </div>


                </div>
            </div>
            </div>

        </div>


            <jsp:include page="footer.jsp"></jsp:include>

        </div>
        </div>



    <!-- Mainly scripts -->
    <script src="../js/jquery-2.1.1.js"></script>
    <script src="../js/bootstrap.min.js"></script>

    <script src="../js/plugins/metisMenu/jquery.metisMenu.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="../js/inspinia.js"></script>
    <%--<script src="js/plugins/pace/pace.min.js"></script>--%>

    <!-- DROPZONE -->
    <script src="../js/dropzone.js"></script>

    <script>

        function crawlList() {

            var srcUrl = document.getElementById("srcUrl").value;
            alert("源地址:" + srcUrl)

            var data = "url=" + srcUrl
            console.log("srcUrl" + data)
            $.ajax({
                url: '${pageContext.request.contextPath}/crawlerList' ,
                type: 'GET',
                data: data,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (resp) {
                    console.log("修改成功,resp:"+resp.toString())
                    document.getElementById("crawlerResp").innerHTML = "<pre style='white-space: pre-wrap;'>"+JSON.stringify(resp, null, 2)+"</pre>"
                },
                error: function (resp) {
                    alert("修改失败");
                }
            });
        }
    </script>

</body>

</html>
