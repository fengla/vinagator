<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>

<head>


    <meta name="viewport" content="width=device-width, initial-scale=1.0">



    <%--<link href="../css/bootstrap.min.css" rel="stylesheet">--%>

    <%--<link href="../font-awesome/css/font-awesome.css" rel="stylesheet">--%>
    <%--<link href="../css/dropzone.css" rel="stylesheet">--%>
    <%--<link href="../css/style.css" rel="stylesheet">--%>



    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>D1小程序助手 | App管理</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- Toastr style -->
    <link href="css/plugins/toastr/toastr.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

    <%--富文本编辑器测试--%>
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link href="css/summernote/summernote.css" rel="stylesheet">
    <script src="js/plugins/summernote/summernote.js"></script>
    <%--富文本编辑器测试--%>

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
                            <a href="index.html">手动上传</a>
                        </li>

                        <li class="active">
                            <strong>编辑信息</strong>
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
                        <form method="post" action="${pageContext.request.contextPath}/doPubApp" class="form-horizontal" enctype="multipart/form-data">
                            <div class="form-group">
                                <label class="col-sm-1 control-label">小程序名称</label>
                                <div class="col-sm-11">
                                    <input type="text" class="form-control" name="name">
                                </div>
                            </div>
                            <%--<div class="hr-line-dashed"></div>--%>
                            <%--<div class="form-group">--%>
                            <%--<label class="col-sm-1 control-label">费用</label>--%>

                            <%--<div class="col-sm-11">--%>
                            <%--<div>--%>
                            <%--<label style="font-weight:normal">--%>
                            <%--<input type="radio" value="0" id="optionsRadios1" name="charge" style="margin-top:15px;margin-right:8px">免费--%>
                            <%--</label>--%>
                            <%--</div>--%>
                            <%--<div class="form-inline">--%>
                            <%--<label style="font-weight:normal">--%>
                            <%--<input type="radio" value="1" id="optionsRadios2" name="charge" style="margin-top:15px;margin-right:8px">收费--%>
                            <%--</label>--%>
                            <%--<input type="text" name="chargeDetail" placeholder="" class="form-control" style="border:0px;border-bottom:#000000 1px solid;width:110px;margin-left:10px"/>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                            <%--<label class="col-sm-1 control-label">发起人</label>--%>

                            <%--<div class="col-sm-11">--%>
                            <%--<div><label style="font-weight:normal"> <input type="radio" name="sponsor" value="0" id="optionsRadios1"  style="margin-top:15px;margin-right:8px">我是发起人</label></div>--%>
                            <%--<div><label style="font-weight:normal"> <input type="radio" name="sponsor" value="1" id="optionsRadios2" style="margin-top:15px;margin-right:8px">我是票务代理</label></div>--%>
                            <%--<div><label style="font-weight:normal"> <input type="radio" name="sponsor" value="2" id="optionsRadios3" style="margin-top:15px;margin-right:8px">我是场地提供方</label></div>--%>
                            <%--<div><label style="font-weight:normal"> <input type="radio" name="sponsor" value="3" id="optionsRadios4" style="margin-top:15px;margin-right:8px">以上都不是</label></div>--%>
                            <%--</div>--%>
                            <%--</div>--%>

                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">图  标</label>
                                <input type="file" name="iconFile"/>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">二维码图片</label>
                                <input type="file" name="qrCodeFile"/>
                            </div>
                            <div class="hr-line-dashed"></div>

                            <%--js可以通过这个id="preview"来识别控件，追加更多图片内容--%>
                            <div id="previewOut">
                                <div class="form-group" id="preview0">
                                    <label class="col-sm-1 control-label">预览图1</label>
                                    <input type="file" name="preview0"/>
                                </div>
                                <div class="form-group" id="preview1" style="display:none">
                                    <label class="col-sm-1 control-label">预览图2</label>
                                    <input type="file" name="preview1"/>
                                </div>
                                <div class="form-group" id="preview2" style="display:none">
                                    <label class="col-sm-1 control-label">预览图3</label>
                                    <input type="file" name="preview2"/>
                                </div>
                                <div class="form-group" id="preview3" style="display:none">
                                    <label class="col-sm-1 control-label">预览图4</label>
                                    <input type="file" name="preview3"/>
                                </div>
                                <div class="form-group" id="preview4" style="display:none">
                                    <label class="col-sm-1 control-label">预览图5</label>
                                    <input type="file" name="preview4"/>
                                </div>
                            </div>
                            <%--预览图可以设置成js加载控件，如果用户已然添加了，这里才需要继续展示更多的预览图控件--%>

                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">分类</label>
                                <form:select id="ct" path="cts" name="ct">
                                    <form:option value="NONE" label="...请选择..." />
                                    <form:options items="${cts}" itemLabel="name" itemValue="id"/>
                                </form:select>
                                <%--如果ajax请求有返回数据再給这个div赋值--%>
                                <select id="sct" style="display:none" name="sct">
                                    <option value="0">...请选择...</option>
                                </select>
                                <%--<select name="cts"> </select>--%>
                            </div>

                            <%--version 1 textArea start--%>
                            <%--<div class="hr-line-dashed"></div>--%>
                            <%--<div class="form-group"><label class="col-sm-1 control-label">介绍</label>--%>

                            <%--<div class="col-sm-11">--%>
                            <%--<textarea name="details" class="note-editor note-editable form-control"></textarea>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            <%--version 1 textArea end--%>

                            <%--富文本编辑器测试--%>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group"><label class="col-sm-1 control-label">介绍</label>
                                <div class="col-sm-11">
                                    <textarea id="summernote" name="details"></textarea>
                                </div>
                            </div>
                            <%-- todo:前端是完成了，但是数据怎么传到服务端呢？可以按照正常提交表单的形式来提交吗？那么图片文件是怎么传输的呢？--%>
                            <%--富文本编辑器测试--%>

                            <%--<div class="hr-line-dashed"></div>--%>
                            <%--<div class="form-group">--%>
                            <%--<label class="col-sm-1 control-label">活动须知</label>--%>

                            <%--<div class="col-sm-11">--%>
                            <%--<textarea name="tips" class="note-editor note-editable form-control"></textarea>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="hr-line-dashed"></div>--%>
                            <%--<h3>更多活动相关图片...(让用户可以上传更多图片)</h3>--%>
                            <div class="col-sm-12">
                                <button class="btn btn-sm btn-primary pull-right" data-toggle="tooltip" data-placement="top" type="submit" style="margin-top:10px;margin-bottom:100px"> 发布小程序</button>
                            </div>


                        </form>
                    </div>


                </div>
            </div>
            </div>

        </div>


            <jsp:include page="footer.jsp"></jsp:include>

        </div>
        </div>



    <!-- Mainly scripts -->

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


    <%--富文本编辑器测试--%>
    <script>
        $(document).ready(function() {
            $('#summernote').summernote({
                height: 300,
            });
        });
    </script>
    <%--富文本编辑器测试--%>


    <script>
        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
            (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

        ga('create', 'UA-4625583-2', 'webapplayers.com');
        ga('send', 'pageview');

    </script>

    <script type="text/javascript" charset="UTF-8">
        //alert(${cts})
        //alert(${test})
        $(function(){
            //触发的下拉框chang事件
            <%--$("#tclx").change(function(){--%>
            <%--var tclx = $("#tclx").val();--%>
            <%--$.ajax({--%>
            <%--type:"POST",--%>
            <%--url :"${ctx}/tdictypecon/listDictype",--%>
            <%--data:{--%>
            <%--id:tclx--%>
            <%--},--%>
            <%--dataType:"json",--%>
            <%--success:function(data){--%>
            <%--$("#tid").empty();--%>
            <%--$("#tid").append("<option value=''>----请选择----</option>");--%>
            <%--$.each(data,function(index,item){--%>
            <%--console.info("item:"+item.id);--%>
            <%--//填充内容--%>
            <%--$("#tid").append( "<option value='"+item.id+"'>"+item.text+"</option>");--%>
            <%--});--%>
            <%--}--%>
            <%--});--%>
            <%--});--%>


            $("#ct").change(function(){
                var ctid = $("#ct").val();
                $.ajax({
                    type:"GET",
                    url :"${ctx}/listScts",
                    data:{
                        ctid:ctid
                    },
                    dataType:"json",
                    success:function(text){
                        //alert(text.toString())
                        //console.log(text)

                        if(isEmpty(text)){//返回不为空才需要展现二级分类
                            document.getElementById("sct").style.display = ""
                            //console.log(text)
                            var sct = $("#sct");
                            var str = '';
                            //var data = eval("("+text+")");
                            //console.log(data)
                            for(var o in text) {
                                console.log(o)
                                str += '<option value="'+o+'">'+text[o]+'</option>';
                            }
                            sct.append(str);


                            // $("#zdz").empty();
                            // $("#zdz").append("<option value=''>----请选择----</option>");
                            // console.info(data);
                            // $.each(data,function(index,item){
                            //     $("#zdz").append( "<option value='"+item.id+"'>"+item.text+"</option>");
                            // });
                        }
                    }
                });
            });



        });


    </script>


    <%--上传预览图--%>
    <script type="text/javascript" charset="UTF-8">

        $(function(){
            $("#preview0").change(function(){//获取标签值
                //修改标签style
                var uiPreview1 =document.getElementById("preview1");
                uiPreview1.style.display="";
            });

            $("#preview1").change(function(){//获取标签值
                //修改标签style
                var uiPreview2 =document.getElementById("preview2");
                uiPreview2.style.display="";
            });

            $("#preview2").change(function(){//获取标签值
                //修改标签style
                var uiPreview3 =document.getElementById("preview3");
                uiPreview3.style.display="";
            });

            $("#preview3").change(function(){//获取标签值
                //修改标签style
                var uiPreview4 =document.getElementById("preview4");
                uiPreview4.style.display="";
            });


        });
    </script>

</body>

</html>
