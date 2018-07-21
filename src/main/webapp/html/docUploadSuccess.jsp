<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>push内容工具 | 新用户内容池</title>

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
                    <h2>文章维护</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="index.html">机型文章</a>
                        </li>

                        <li class="active">
                            <strong>单篇文章上传</strong>
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
                        <h5>上传结果</h5>
                    </div>
                    <div class="ibox-content">
                        <b> ${docId} </b> - 上传成功！
                        <%--<form id="my-awesome-dropzone" class="dropzone" action="/machineFileUploadPage" enctype="multipart/form-data">--%>
                            <%--<div class="dropzone-previews"></div>--%>
                            <%--<button type="submit" class="btn btn-primary pull-right">提交上传!</button>--%>
                        <%--</form>--%>
                    </div>
                    <div>
                        <div class="m text-right">请上传约定格式的CSV文件 </div>
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

    <!-- DROPZONE -->
    <script src="../js/dropzone.js"></script>


    <script>
        $(document).ready(function(){

            Dropzone.options.myAwesomeDropzone = {

                autoProcessQueue: false,
                uploadMultiple: true,
                parallelUploads: 100,
                maxFiles: 100,

                // Dropzone settings
                init: function() {
                    var myDropzone = this;

                    this.element.querySelector("button[type=submit]").addEventListener("click", function(e) {
                        e.preventDefault();
                        e.stopPropagation();
                        myDropzone.processQueue();
                    });
                    this.on("sendingmultiple", function() {
                    });
                    this.on("successmultiple", function(files, response) {
                    });
                    this.on("errormultiple", function(files, response) {
                    });
                }

            }

       });
    </script>

</body>

</html>
