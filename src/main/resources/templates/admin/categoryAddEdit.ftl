<html>
<link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">

<link href="http://ydcoding.cn/sale/layui/layui/css/layui.css" rel="stylesheet">
<script src="http://ydcoding.cn/sale/jquery-3.2.1.js"></script>

<link href="http://ydcoding.cn/sale/layui/layui/css/layui.css" rel="stylesheet">

<script src="http://ydcoding.cn/sale/layui/layui/layui.js"></script>

<body>
<div id="wrapper" class="toggled">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form class="layui-form"  method="post"  onsubmit="return false;">
                        <div class="form-group">
                            <label>类目名称</label>
                            <input name="categoryName"  lay-verify="required" type="text" class="form-control" value="${(category.categoryName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>类目编号</label>
                                <#if (category.categoryNo)??>
                            <input name="categoryNo"  id="categoryNo" lay-verify="required"  disabled="disabled"   type="number" class="form-control" value="${(category.categoryNo)!''}"/>

                                <#else >
                                 <input name="categoryNo"  id="categoryNo" lay-verify="required"    type="number" class="form-control" value="${(category.categoryNo)!''}"/>
                                </#if>


                                </div>
                        <input hidden type="text"    id="myId" thisId="${(category.categoryId)!''}"  name="categoryId" value="${(category.categoryId)!''}">
                        <div class="layui-form-item"  >
                            <div class="layui-input-block">
                                <button    class="layui-btn layui-btn-sm " lay-submit lay-filter="categorySubmit" id="send" type="Submit">提交</button>
                                <button    type="reset" class="layui-btn layui-btn-sm  layui-btn-danger">重置</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>

<script>

    $("#categoryNo").blur(function () {
        var categoryNo=$(this).val();
        $.ajax({
                    type: "post",
                    url: "/sell/admin/category/exist?categoryNo="+categoryNo,
                    dataType:'json',
                    success: function (data) {

                        console.log(data.categoryId);
                       if(data.categoryId!=0){
                           $("#send").attr("disabled","disabled");
                           layer.msg("该类目编号已存在，请您前往编辑");

                       }

                        $("#send").attr("disabled",false);


                    }

                }
        );

    });


    layui.use(['jquery', 'upload', 'form'], function () {
        var $ = layui.$
                , upload = layui.upload
                , form = layui.form;
        //监听提交
        form.on('submit(categorySubmit)', function (data) {

            $.post('/sell/admin/category/save', data.field, function (datas) {
                console.log(datas.categoryId);//{categoryId: 1, categoryName: "畅销", categoryNo: 9}

                if (datas.categoryId!=null) {
                    layer.msg("保存成功", {icon: 1, time: 500}, function (index) {
                        parent.location.reload();
                    });
                }
            }, "json");
            return false;

        });
    });
</script>

</body>
</html>