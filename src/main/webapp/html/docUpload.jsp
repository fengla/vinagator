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
                            <a href="index.html">文章上传</a>
                        </li>

                        <li class="active">
                            <strong>单篇上传</strong>
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
                        <h5>文章编辑区域</h5>
                    </div>
                    <div class="ibox-content" style="padding-bottom:80px">
                        <form:form method="post" action="${pageContext.request.contextPath}/doDocUpload" class="form-horizontal" enctype="multipart/form-data">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">文章类型</label>
                                <div class="col-sm-10">
                                    <%--<form:select path="typeList" items="${typeList}" class="col-lg-1"/>--%>
                                        <form:select path="type" class="col-lg-1">
                                            <form:options items="${typeList}"/>
                                        </form:select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group"><label class="col-sm-2 control-label">docID</label>
                                <div class="col-sm-10"><input type="text" class="form-control" name="docId"></div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group"><label class="col-sm-2 control-label">标题</label>
                                <div class="col-sm-10"><input type="text" class="form-control" name="title"></div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group"><label class="col-sm-2 control-label">摘要</label>
                                <div class="col-sm-10"><input type="text" class="form-control" name="summary"></div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group"><label class="col-sm-2 control-label">过期时间</label>
                                <div class="col-sm-10"><input type="text" class="form-control" name="expire"> <span class="help-block m-b-none">格式:2018/05/07; 无时效性填写"长期有效"</span></div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group"><label class="col-sm-2 control-label">channel</label>
                                <div class="col-sm-10"><input type="text" class="form-control" name="channel"></div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group"><label class="col-sm-2 control-label">特定机型-level1</label>
                                <div class="col-sm-10"><input type="text" class="form-control" name="level1"> <span class="help-block m-b-none">没有数据留空或者填写"无"</span></div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group"><label class="col-sm-2 control-label">机型类型-level2</label>
                                <div class="col-sm-10"><input type="text" class="form-control" name="level2"> <span class="help-block m-b-none">没有数据留空或者填写"无"</span></div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group"><label class="col-sm-2 control-label">APP_ID-level3</label>
                                <div class="col-sm-10"><input type="text" class="form-control" name="level3"> <span class="help-block m-b-none">没有数据留空或者填写"无"</span></div>
                            </div>

                            <button class="btn btn-primary" data-toggle="tooltip" data-placement="top" type="submit" style="float:right"> 提交上传</button>

                        </form:form>
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

</body>

</html>
