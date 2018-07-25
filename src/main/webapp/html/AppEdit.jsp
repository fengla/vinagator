<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

            <%--这里填写查询条件的form--%>

            <div class="row wrapper border-bottom white-bg page-heading">
                <%--<div class="col-lg-2">--%>

                <%--</div>--%>

                <div class="col-lg-12">
                    <div class="search-form">
                        <%--todo： wx端需要json数据，但是管理端口需要返回页面，所以管理段需要在adminController中再写一个搜索方法--%>
                        <form action="/searchAppsAdmin" method="get">
                            <select id="type" name="searchType" class="col-lg-1" style="height:46px;background-color:#1ab394;border-radius:0px;color:white;font-size:17px">
                                <option value="keyword">关键词</option>
                                <%--<option value="title">标题</option>--%>
                                <%--这里可以扩展搜索关键词属性--%>
                            </select>
                            <div class="input-group">
                                <input type="text" placeholder="请键入搜索内容(暂未上线)" name="keyword" class="form-control input-lg">
                                <div class="input-group-btn">
                                    <button class="btn btn-lg btn-primary" type="submit" style="font-size:17px">
                                        搜索$
                                    </button>
                                </div>
                            </div>

                        </form>
                    </div>

                </div>

            </div>


        <div class="wrapper wrapper-content animated fadeIn">

            <div class="row">


                        <div class="ibox float-e-margins">

                            <div class="ibox-content" style="padding:0">
                                <table class="table table-striped" style="word-break:break-all; word-wrap:break-word; font-size: 15px">

                                    <thead>
                                    <tr>
                                        <th class="col-lg-1">图标</th>
                                        <th class="col-lg-0.5">appid</th>
                                        <th class="col-lg-1">名称</th>
                                        <th class="col-lg-1.5">开发者</th>
                                        <th class="col-lg-2">摘要</th>
                                        <%--<th class="col-lg-2">评分</th>--%>
                                        <th class="col-lg-1">分类</th>
                                        <th class="col-lg-2.5">备注</th>
                                        <th class="col-lg-2.5">操作</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach items="${resp.content}" var="app">
                                        <tr>
                                            <%--这里填写的属性名字就是服务端定义的属性名--%>
                                            <%--这里第一栏把app的图标也展示出来吧,设置好样式不要太大了--%>
                                            <td>
                                                <%--图片圆角怎么设置？ --%>
                                                <img alt="image" class="img-circle" src="http://localhost:8080${app.icon}" style="width:50px;height:50px"/>
                                            </td>
                                            <td>${app.appid}</td>
                                            <td>${app.appName}</td>
                                            <td>${app.dev}</td>
                                            <td>${app.summary}</td>
                                            <%--ct现在展示的是数字，后面需要修改成中文名，对于没有ct的展示空白，这样更容易识别出来--%>
                                            <%--分类做成下来菜单，而且可以直接在本页进行更新，ajax请求更新，更新成功或者失败以alert的形式反馈给用户--%>
                                            <%--下拉菜单用全量分类数据来展示，然后设置selected id为当前app的ct数据--%>
                                            <%--<td>${app.ct}</td>--%>
                                            <td>
                                                <%--这里可以包装一个控件用于设置这个下拉菜单的样式--%>
                                                <form:select id="ct-${app.appid}" path="cts" name="ct">
                                                    <form:option value="${app.ct}" label="${app.ctName}" />
                                                    <form:options items="${cts}" itemLabel="name" itemValue="id"/>
                                                </form:select>
                                            </td>
                                            <td>${app.remark}</td>
                                            <td>
                                                <%--根据app情况设置这个按钮，如果是已经审核通过,点击设置为不通过，如果不通过则点击变为通过。。。这个需求怎么做？--%>
                                                <%--删除后还是需要返回当前页面，所以需要带上curPage参数;但是对于令挖几个只是需要ajax请求的并不涉及到页面跳转，因此则不需要带上这个当前页面的参数--%>
                                                <c:if test="${app.valid eq true}">
                                                    <%--<a href="/auditApp?appid=${app.appid}&&valid=false" type="button" class="btn btn-primary btn-sm" onclick="auditApp(${app.appid}, false)">取消通过</a>--%>
                                                    <a type="button" class="btn btn-primary btn-sm" id="audit-${app.appid}" onclick="auditApp(${app.appid}, false)">取消通过</a>
                                                </c:if>
                                                <c:if test="${app.valid eq false}">
                                                    <%--<a href="/auditApp?appid=${app.appid}&&valid=true" type="button" class="btn btn-primary btn-sm" onclick="auditApp(${app.appid}, true)">审核通过</a>--%>
                                                    <a type="button" class="btn btn-primary btn-sm" id="audit-${app.appid}" onclick="auditApp(${app.appid}, true)">审核通过</a>
                                                </c:if>

                                                <a type="button" class="btn btn-info btn-sm" onclick="updateCt(${app.appid}, ${app.ct})">更新分类</a>
                                                <a href="/editApp?appid=${app.appid}&&curPage=${pageModel.curPage}" type="button" class="btn btn-warning btn-sm disabled">编辑</a>
                                                <%--先不开放删除功能，将这个按键设置为不可点击--%>
                                                <a type="button" class="btn btn-danger btn-sm" onclick="deleteApp(${app.appid})">删除</a>

                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>

                                </table>

                                <%--todo: 给下面这个控件取名字，然后根据当前页，以及总共页面数的关系用js来设置这个控件的内容--%>
                                <%--首页往前，尾页往后怎么处理--%>
                                <%--href可以改为onclick(page)..然后写个js function就好--%>
                                <%--<div class="text-center">--%>
                                    <%--<div class="btn-group">--%>
                                        <%--<a class="btn btn-white" href="/findDocs?curPage=0">第一页</a>--%>

                                        <%--<a class="btn btn-white" href="/findDocs?curPage=${pageModel.curPage-1}">上一页</a>--%>

                                        <%--<a class="btn btn-white" href="/findDocs?curPage=${pageModel.curPage-2}">${pageModel.curPage-2}</a>--%>
                                        <%--<a class="btn btn-white" href="/findDocs?curPage=${pageModel.curPage-1}">${pageModel.curPage-1}</a>--%>

                                        <%--<a class="btn btn-white active" href="/findDocs?curPage=${pageModel.curPage}">${pageModel.curPage}</a>--%>

                                        <%--<a class="btn btn-white" href="/findDocs?curPage=${pageModel.curPage+1}">${pageModel.curPage+1}</a>--%>
                                        <%--<a class="btn btn-white" href="/findDocs?curPage=${pageModel.curPage+2}">${pageModel.curPage+2}</a>--%>

                                        <%--<a class="btn btn-white" href="/findDocs?curPage=${pageModel.curPage+1}">下一页</a>--%>

                                        <%--<a class="btn btn-white" href="/findDocs?curPage=${pageModel.totalPage-1}">最后一页</a>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="text-center">
                                    <div class="btn-group" id="pageDiv">

                                    </div>
                                </div>


                                <div class="pull-right">
                                    第 ${resp.number+1} 页／共 ${resp.totalPages} 页
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
        // 注意区分逻辑页码与展示页码，展示页码从1开始，逻辑页码从0开始索引
        pageDiv = "<div class=\"text-center\">\n" +
            "                                    <div class=\"btn-group\">\n" +
            "                                        <a class=\"btn btn-white\" href=\"/editApp?curPage=0\">第一页</a>";

        if(${resp.number} > 0){
            pageDiv += "<a class=\"btn btn-white\" href=\"/editApp?curPage=${resp.number-1}\">上一页</a>";
        }
        if(${resp.number} > 1){
            pageDiv += "<a class=\"btn btn-white\" href=\"/editApp?curPage=${resp.number-2}\">${resp.number-1}</a>\n" +
                "                                        <a class=\"btn btn-white\" href=\"/editApp?curPage=${resp.number-1}\">${resp.number}</a>";
        }else if(${resp.number} > 0){
            pageDiv += "<a class=\"btn btn-white\" href=\"/editApp?curPage=${resp.number-1}\">${resp.number}</a>";
        }

        pageDiv += "<a class=\"btn btn-white active\" href=\"/editApp?curPage=${resp.number}\">${resp.number+1}</a>";

        if(${resp.number+1} < ${resp.totalPages-1}){
            pageDiv += "<a class=\"btn btn-white\" href=\"/editApp?curPage=${resp.number+1}\">${resp.number+2}</a>\n" +
                "                                        <a class=\"btn btn-white\" href=\"/editApp?curPage=${resp.number+2}\">${resp.number+3}</a>";
            pageDiv += "<a class=\"btn btn-white\" href=\"/editApp?curPage=${resp.number+1}\">下一页</a>";
        }else if(${resp.number+1} < ${resp.totalPages}){//当前页是倒数第二页
            pageDiv += "<a class=\"btn btn-white\" href=\"/editApp?curPage=${resp.number+1}\">${resp.number+2}</a>";
            pageDiv += "<a class=\"btn btn-white\" href=\"/editApp?curPage=${resp.number+1}\">下一页</a>";
        }

        pageDiv += "<a class=\"btn btn-white\" href=\"/editApp?curPage=${resp.totalPages-1}\">最后一页</a>";

        document.getElementById("pageDiv").innerHTML = pageDiv;
    </script>


    <%--审核，更新分类，删除--%>
    <script>
        // js如何修改控件中的内容
        // 我可以給控件加上id,这个id=audit+appid
        function auditApp(appid, valid) {
            //alert("js enter auditApp, appid:" + appid + " ||valid" + valid)
            // todo: 怎么带参数？这样子手动拼接data可以吗？
            var data = "appid="+appid+"&&valid="+valid
            $.ajax({
                url: 'http://localhost:8080/auditApp' ,
                type: 'GET',
                data: data,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (returndata) {
                    alert("修改成功");
                    if(valid == false)
                        document.getElementById("audit-"+appid).innerHTML="审核通过";
                    else
                        document.getElementById("audit-"+appid).innerHTML="取消通过";
                },
                error: function (returndata) {
                    alert("修改失败");
                }
            });
        }

        function updateCt(appid) {

            var tmpCt = document.getElementById("ct-"+appid).value;

            var data = "appid=" + appid + "&&ct=" + tmpCt
            $.ajax({
                url: 'http://localhost:8080/updateCt' ,
                type: 'GET',
                data: data,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (returndata) {
                    alert("修改成功，刷新页面可见");
                    //todo: 怎么做：修改成功需要把原先的控件属性及内容替换掉。。
                    // var obj = document.getElementById(id);
                    // obj.onclick="auditApp(appid, "+!valid+")"
                    // obj.value="asdasd"

                    //怎么获取到触发方法的控件，并修改其内容
                    //this.setValue("修改的")
                },
                error: function (returndata) {
                    alert("修改失败");
                }
            });
        }

        function deleteApp(appid) {
            var data = "appid=" + appid
            $.ajax({
                url: 'http://localhost:8080/deleteApp' ,
                type: 'GET',
                data: data,
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
