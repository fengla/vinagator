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
                                        <%--todo: js根据css值修改物料样式--%>
                                        <th class="col-lg-0.5">物料</th>
                                        <th class="col-lg-0.5">id</th>
                                        <th class="col-lg-1">名称</th>
                                        <th class="col-lg-3">css</th>
                                        <th class="col-lg-1">时间</th>
                                            <%--弄成下拉菜单true=有效，false=无效--%>
                                        <th class="col-lg-1">审核</th>
                                        <th class="col-lg-2">备注</th>
                                        <th class="col-lg-2">操作</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach items="${resp.content}" var="ct">
                                        <tr>
                                            <%--这里填写的属性名字就是服务端定义的属性名--%>
                                            <td>
                                                <a class="${ct.css}" style="color:#feb70f;font-size:30px"></a>
                                            </td>
                                            <td>
                                                <input type="text" value="${ct.id}" placeholder="" name="id" class="form-control input-sm">
                                            </td>

                                            <td>
                                                <input type="text" value="${ct.name}" placeholder="" name="name" class="form-control input-sm">
                                            </td>

                                            <td>
                                                <input type="text" value="${ct.css}" placeholder="" name="css" class="form-control input-sm">
                                            </td>
                                                <%--todo: updateDate就是系统设置的根据修改时间来设置，而不是用户自己编辑,需要做的是格式化展示--%>
                                            <td>${ct.updateDate}</td>
                                            <td>${ct.valid}</td>
                                                <%--todo: valid改成下拉菜单来做--%>
                                            <td>
                                                <textarea type="text" placeholder="" name="remark" class="form-control">${ct.remark}</textarea>
                                            </td>
                                            <td>
                                                <%--根据app情况设置这个按钮，如果是已经审核通过,点击设置为不通过，如果不通过则点击变为通过。。。这个需求怎么做？--%>
                                                <%--删除后还是需要返回当前页面，所以需要带上curPage参数;但是对于令挖几个只是需要ajax请求的并不涉及到页面跳转，因此则不需要带上这个当前页面的参数--%>

                                                <a href="/updateCt?id=${ct.id}&&curPage=${resp.number}" type="button" class="btn btn-warning btn-sm disabled">更新</a>
                                                <%--先不开放删除功能，将这个按键设置为不可点击--%>
                                                <a type="button" class="btn btn-danger btn-sm" onclick="deleteCt(${ct.id})">删除</a>

                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>

                                </table>

                                <%--这是用来展示上一页，下一页的，不是冗余代码，不要删除了--%>
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
            "                                        <a class=\"btn btn-white\" href=\"/editCt?curPage=0\">第一页</a>";

        if(${resp.number} > 0){
            pageDiv += "<a class=\"btn btn-white\" href=\"/editCt?curPage=${resp.number-1}\">上一页</a>";
        }
        if(${resp.number} > 1){
            pageDiv += "<a class=\"btn btn-white\" href=\"/editCt?curPage=${resp.number-2}\">${resp.number-1}</a>\n" +
                "                                        <a class=\"btn btn-white\" href=\"/editCt?curPage=${resp.number-1}\">${resp.number}</a>";
        }else if(${resp.number} > 0){
            pageDiv += "<a class=\"btn btn-white\" href=\"/editCt?curPage=${resp.number-1}\">${resp.number}</a>";
        }

        pageDiv += "<a class=\"btn btn-white active\" href=\"/editCt?curPage=${resp.number}\">${resp.number+1}</a>";

        if(${resp.number+1} < ${resp.totalPages-1}){
            pageDiv += "<a class=\"btn btn-white\" href=\"/editCt?curPage=${resp.number+1}\">${resp.number+2}</a>\n" +
                "                                        <a class=\"btn btn-white\" href=\"/editCt?curPage=${resp.number+2}\">${resp.number+3}</a>";
            pageDiv += "<a class=\"btn btn-white\" href=\"/editCt?curPage=${resp.number+1}\">下一页</a>";
        }else if(${resp.number+1} < ${resp.totalPages}){//当前页是倒数第二页
            pageDiv += "<a class=\"btn btn-white\" href=\"/editCt?curPage=${resp.number+1}\">${resp.number+2}</a>";
            pageDiv += "<a class=\"btn btn-white\" href=\"/editCt?curPage=${resp.number+1}\">下一页</a>";
        }

        pageDiv += "<a class=\"btn btn-white\" href=\"/editCt?curPage=${resp.totalPages-1}\">最后一页</a>";

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
                url: '${pageContext.request.contextPath}/auditApp' ,
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
                url: '${pageContext.request.contextPath}/updateCt' ,
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
                url: '${pageContext.request.contextPath}/deleteApp' ,
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
