
<html>
<head>
    <meta charset="utf-8">
    <title>订单详情</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">

    <link href="http://ydcoding.cn/sale/layui/layui/css/layui.css" rel="stylesheet">

    <link href="http://ydcoding.cn/sale/layui/layui/css/layui.css" rel="stylesheet">

    <script src="http://ydcoding.cn/sale/layui/layui/layui.js"></script>
    <script src="http://ydcoding.cn/sale/jquery-3.2.1.js"></script>

</head>
<body>

<div class="container-fluid">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <blockquote class="layui-elem-quote" style="margin-left:6px;margin-top:10px;height:39px;width: 188px">
                <div id="test2">商品列表</div>
            </blockquote>
            <a href="/sell/admin/product/addEdit">   <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" id="addProduct"   style="margin-left: 0.5%;width: 88px;i" >添加</button></a>
            <table class="layui-table" lay-size="sm"">
                <thead>
                <tr>
                    <th>商品id</th>
                    <th>名称</th>
                    <th>图片</th>
                    <th>单价</th>
                    <th>库存</th>
                    <th>描述</th>
                    <th>类目</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody>

                        <#list productInfoPage.content as productInfo>
                        <tr>
                            <td>${productInfo.productId}</td>
                            <td>${productInfo.productName}</td>
                            <td><img height="100" width="100" src="${productInfo.imgAddr}" alt=""></td>
                            <td>${productInfo.productPrice}</td>
                            <td>${productInfo.productStock}</td>
                            <td>${productInfo.productDesc}</td>
                            <td>${productInfo.categoryNo}</td>
                            <td>${productInfo.createTime}</td>
                            <td>${productInfo.updateTime}</td>
                            <td><a href="/sell/admin/product/addEdit?productId=${productInfo.productId}"><button    class="layui-btn layui-btn-xs">更新</button></a></td>
                            <td>
                               <#if productInfo.getProductStatusEnum().message == "在售">
                                   <button     thisId="${productInfo.productId}"    class="layui-btn layui-btn-danger layui-btn-xs btn1">下架</button>
                                <#else>
                                    <button     thisId="${productInfo.productId}"    class="layui-btn layui-btn-danger layui-btn-xs btn1">上架</button>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                </tbody>
            </table>
        </div>

    <#--分页-->
        <div class="col-md-12 column">
            <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li   class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a   style="color:darkgray"  href="/sell/admin/product/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                    <#list 1..productInfoPage.getTotalPages() as index>
                        <#if currentPage == index>
                            <li class="active"><a href="#">${index}</a></li>
                        <#else>
                            <li><a   style="color:darkgray" href="/sell/admin/product/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte productInfoPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a   style="color:darkgray" href="/sell/admin/product/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
            </ul>
        </div>
    </div>
</div>

        <script>
           //上下架ajax操作
            $(document).on('click', '.btn1', function () {
                var productId = $(this).attr("thisId");
                $.ajax({
                            type: "get",
                            url: "/sell/admin/product/upordown?productId="+productId,
                            dataType:'json',
                            success: function (data) {
                                console.log('success');
                             console.log(data);
                                //返回成功刷新页面
                                window.location.reload();

                            }

                        }
                );
            });

        </script>

</body>
</html>