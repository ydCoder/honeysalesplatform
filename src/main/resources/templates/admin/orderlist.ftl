
<html>
<head>
    <meta charset="utf-8">
    <title>订单列表</title>
    <link rel="icon" href="http://ydcoding.cn/sale/image/title.jpg" sizes="66x66">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">

    <link href="http://ydcoding.cn/sale/layui/layui/css/layui.css" rel="stylesheet">
    <link href="http://ydcoding.cn/sale/layui/layui/css/layui.css" rel="stylesheet">

    <script src="http://ydcoding.cn/sale/layui/layui/layui.js"></script>
    <script src="http://ydcoding.cn/sale/jquery-3.2.1.js"></script>
</head>
<body>

<div class="container-fluid">

    <div class="row clearfix">
        <blockquote class="layui-elem-quote" style="margin-left:20px;margin-top:10px;height:39px;width: 188px">
            <div id="test2">订单列表</div>
        </blockquote>
        <div class="col-md-12 column">
            <table class="layui-table" lay-size="sm">
 <thead>
                <tr>
                    <th>订单ID</th>
                    <th>姓名</th>
                    <th>联系电话</th>
                    <th>地址</th>
                    <th>金额</th>
                    <th>创建时间</th>
                    <th>支付状态</th>
                    <th>订单状态</th>

                     <th>详情</th>
                    <th>操作</th>

                </tr>
                </thead>
                <tbody>
                <#list   orderdtoPage.content as orderDto>
                     <#if   orderDto.getOrderStatusEnum().message=="新订单">
                      <tr>
                          <td>${orderDto.orderId}</td>
                          <td>${orderDto.buyerName}</td>
                          <td>${orderDto.buyerPhone}</td>
                          <td>${orderDto.buyerAddr}</td>
                          <td>${orderDto.orderTotal}</td>
                          <td>${orderDto.createTime}</td>
                          <td>${orderDto.getPayStatusEnum().message}</td>
                          <td><button  style="background:#32CD32"  class="layui-btn layui-btn-xs">${orderDto.getOrderStatusEnum().message}</button> </td>

                          <td><a href="/sell/admin/order/detail?orderId=${orderDto.orderId}"><button    class="layui-btn layui-btn-xs">详情</button></a></td>

                          <td><button     thisId="${orderDto.orderId}"    class="layui-btn layui-btn-danger layui-btn-xs cancel">取消</button></td>
                      </tr>
                 <#elseif orderDto.getOrderStatusEnum().message=="已发货">
                  <tr>
                      <td>${orderDto.orderId}</td>
                      <td>${orderDto.buyerName}</td>
                      <td>${orderDto.buyerPhone}</td>
                      <td>${orderDto.buyerAddr}</td>
                      <td>${orderDto.orderTotal}</td>
                      <td>${orderDto.createTime}</td>
                      <td>${orderDto.getPayStatusEnum().message}</td>
                     <td> <button  style="background:red;"  class="layui-btn layui-btn-danger layui-btn-xs">${orderDto.getOrderStatusEnum().message}</button></td>


                      <td><a href="/sell/admin/order/detail?orderId=${orderDto.orderId}"><button   class="layui-btn layui-btn-xs">详情</button></a></td>
                      <td><button    class="layui-btn layui-btn-primary layui-btn-xs">取消</button></td>
                  </tr>
                <#else >
                     <tr>
                     <td>${orderDto.orderId}</td>
                     <td>${orderDto.buyerName}</td>
                     <td>${orderDto.buyerPhone}</td>
                     <td>${orderDto.buyerAddr}</td>
                     <td>${orderDto.orderTotal}</td>
                     <td>${orderDto.createTime}</td>
                         <td>${orderDto.getPayStatusEnum().message}</td>
                     <td><button class="layui-btn layui-btn-primary layui-btn-xs">${orderDto.getOrderStatusEnum().message}</button></td>


                     <td><a href="/sell/admin/order/detail?orderId=${orderDto.orderId}"><button    class="layui-btn layui-btn-xs">详情</button></a></td>
                         <td><button    class="layui-btn layui-btn-primary layui-btn-xs">取消</button></td>
                 </tr>


                </#if>

                </#list>
                </tbody>
            </table>
        </div>
    </div>
<#--分页-->
    <div class="col-md-12 column">
        <ul    class="pagination pagination-sm  pull-right">
                <#if nowPage lte 1>

                    <li class="disabled"> <a href="#">上一页</a></li>
                <#else>
                  <li> <a style="color:darkgray"  href="/sell/admin/order/list?page=${nowPage-1}">上一页</a></li>
                </#if>
                <#list 1..orderdtoPage.getTotalPages() as index>
                    <#if nowPage==index>
                    <li  class="active"><a href="#"> ${index}</a></li>
                    <#else>
                <li><a    style="color:darkgray"  href="/sell/admin/order/list?page=${index}"> ${index}</a></li>
                    </#if>

                </#list>

                <#if nowPage gte orderdtoPage.getTotalPages()>
                     <li class="disabled"> <a href="#">下一页</a></li>
                <#else>
                <li> <a  style="color:darkgray"  href="/sell/admin/order/list?page=${nowPage+1}">下一页</a> </li>
                </#if>

        </ul>
    </div>
</div>

<script>

    // 取消时调用弹窗方法


    //单独模块需要单独引用layer
    layui.use(['table', 'layer'], function () {
        var table = layui.table;
        var layer = layui.layer;

    });
    $('.cancel').click(function () {


        var orderId = $(this).attr("thisId");
        layer.confirm("取消后不能恢复,确认要取消订单"+orderId+"吗", {title: "取消订单", offset: ['100px', '266px'], }, function (index) {
            layer.close(index);

             // alert(orderId);
            $.ajax({url: "/sell/admin/order/cancel",
                data: {
                    orderId: orderId
                },
                success: function (data) {
                    console.log("success!" + data);
                    // layer.msg("您已取消订单"+orderId);
                    layer.msg("您已取消订单"+orderId, {

                        time: 5000 //5秒关闭（如果不配置，默认是3秒）
                    }, function(){
                        //返回成功刷新页面
                        window.location.reload();
                    });

                }});
        });
    });

</script>

</body>
</html>