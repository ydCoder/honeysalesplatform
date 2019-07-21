<html>

<head>
    <meta charset="utf-8">
    <title>新增|编辑商品</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">

    <link href="http://ydcoding.cn/sale/layui/layui/css/layui.css" rel="stylesheet">

    <script src="http://ydcoding.cn/sale/layui/layui/layui.js"></script>
    <script src="http://ydcoding.cn/sale/jquery-3.2.1.js"></script>


</head>
<body>
<div class="container-fluid">
    <div class="row clearfix">
        <blockquote class="layui-elem-quote" style="margin-left:20px;margin-top:10px;height:39px;width: 188px">
            <div id="test2">新增|编辑商品</div>
        </blockquote>
        <div class="col-md-6 column">
            <form role="form" method="post"      class="layui-form"   >

                <div class="layui-form-item">

                    <label class="layui-form-label">名称</label>
                    <div class="layui-input-block">
                        <input style="width: 200px;"    lay-verify="required"  class="layui-input" name="productName"  type="text"  value="${(product.productName)!''}"/>


                    </div>
                </div>


                <div class="layui-form-item">

                    <label class="layui-form-label">价格</label>
                    <div class="layui-input-block">
                        <input  style="width: 200px;"   lay-verify="required" class="layui-input"   type="number" name="productPrice"  type="text"  value="${(product.productPrice)!''}"/>


                    </div>
                </div>
                <input class="layui-input"   type="hidden" type="number" name="status"    value="${(product.status)!''}"/>

                <div class="layui-form-item">

                    <label class="layui-form-label">库存</label>
                    <div class="layui-input-block">
                        <input  style="width: 200px;"  lay-verify="required"  name="productStock"  type="number" class="layui-input" name="productPrice"  value="${(product.productStock)!''}"/>


                    </div>
                </div>




                <div class="layui-form-item">

                    <label class="layui-form-label">描述</label>
                    <div class="layui-input-block">
                        <input   style="width: 200px;"   name="productDesc"    class="layui-input" name="productName"  type="text"  value="${(product.productDesc)!''}"/>


                    </div>
                </div>


                <div class="layui-form-item">

                    <label class="layui-form-label">图片</label>
                    <div class="layui-input-block">
                             <#if (product.imgAddr)??>
                         <img width="100" height="100"   src="${(product.imgAddr)!''}"/>

                             </#if>
                        <input   name="imgAddr"   id="imgAddr"  class="layui-input"  type="hidden"  value="${(product.imgAddr)!''}"/>


                    </div>

                    <div class="layui-upload">
                        <button type="button" class="layui-btn layui-btn-xs" id="test1">上传图片</button>
                        <div class="layui-upload-list">
                            <img class="layui-upload-img" id="demo1">
                            <p id="demoText"></p>
                        </div>
                    </div>



                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">类别</label>

                    <div class="layui-input-block">
                        <select id="mySelect"  style="width:100px;"    lay-filter="mySelect" name="category">
                        <#list categoryList as category>
                                          <option  value="${category.categoryNo}"
                                            <#if (product.categoryNo)?? && product.categoryNo == category.categoryNo>
                                                selected<#else >

                                            </#if>
                                          >${category.categoryName}
                                          </option>

                                      </#list>

                        </select>

                            <div class="layui-form-item">
                        <input id="categoryNo"   class="layui-input"  name="categoryNo" type="hidden" >

                    </div>



                    </div>
                </div>


                <input  type="hidden"  id="productId" thisId="${(product.productId)!''}" name="productId" value="${(product.productId)!''}">
                <button style="margin-left: 66%" class="layui-btn layui-btn-sm"  lay-submit=""   lay-filter="demo2" >提交</button>
            </form>
        </div>
    </div>
</div>

<script>

    $(document).ready(function(){
        // 初始化内容
        $("#categoryNo").val($("select option:selected").val());

    });
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
                ,layer = layui.layer
                ,layedit = layui.layedit
                ,laydate = layui.laydate;

        form.on('select(mySelect)', function(data){
            // alert($("select option:selected").val());
            $("#categoryNo").val($("select option:selected").val());
        });
        //监听指定开关


        //监听提交
        form.on('submit(demo2)', function(data){
            // layer.alert(JSON.stringify(data.field), {
            //     title: '最终的提交信息'
            // });

            $.post('/sell/admin/product/save', data.field, function (datas) {
                console.log(JSON.stringify(data.field));

                layer.msg('操作成功！',{time:2000},function() {
                    window.location.href="/sell/admin/product/list";
//回调
                })

            }, "text");

            return false;
        });



    });
</script>

<script>
    layui.use('upload', function(){
        var $ = layui.jquery
                ,upload = layui.upload;
        var productId = $("#productId").attr("thisId");

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#test1'
            ,url: '/sell/admin/product/img/upload?productId='+productId
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                //上传成功，将图片地址回传
                console.log(res.data.src);
                $("#imgAddr").val(res.data.src);

                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                //上传成功
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        })})

</script>
</body>
</html>