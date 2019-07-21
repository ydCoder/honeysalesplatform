
<html>
<head>
    <meta charset="utf-8">
    <title>订单列表</title>
    <link rel="icon" href="http://ydcoding.cn/sale/image/title.jpg" sizes="66x66">
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
        <div class="row clearfix">
            <blockquote class="layui-elem-quote" style="margin-left:20px;margin-top:10px;height:39px;width: 266px">
                <a ><span class="glyphicon glyphicon-save" aria-hidden="true"></span> <font color="#FF5722">&nbsp;&nbsp;<strong>点击下载已发货订单数据</strong></font></a>
            </blockquote>
        <div class="col-md-12 column">
            <table  style="margin-left: 20px" class="layui-table" lay-size="sm">
                <thead>
                <tr>
                    <th >订单ID</th>
                    <th>姓名</th>
                    <th>联系电话</th>
                    <th>地址</th>
                    <th>金额</th>
                    <th>创建时间</th>

                    <th>订单状态</th>




                </tr>
                </thead>
                <tbody>
                <#list  orderList as orderDto>
                     <#if  orderDto.getOrderStatusEnum().message=="已发货">

                     <tr>
                         <td>${orderDto.orderId}</td>
                         <td>${orderDto.buyerName}</td>
                         <td>${orderDto.buyerPhone}</td>
                         <td>${orderDto.buyerAddr}</td>
                         <td>${orderDto.orderTotal}</td>
                         <td>${orderDto.createTime}</td>

                         <td>${orderDto.getOrderStatusEnum().message}</td>




                     </tr>


                  </#if>










                </#list>
                </tbody>
            </table>
        </div>
    </div>

</div>



















<script>

    // 使用outerHTML属性获取整个table元素的HTML代码（包括<table>标签），然后包装成一个完整的HTML文档，设置charset为urf-8以防止中文乱码
    var html = "<html><head><meta charset='utf-8' /></head><body>" +
            document.getElementsByTagName("table")[0].outerHTML +
            "</body></html>";
    // 实例化一个Blob对象，其构造函数的第一个参数是包含文件内容的数组，第二个参数是包含文件类型属性的对象
    var blob = new Blob([html], {type: "application/vnd.ms-excel"});
    var a = document.getElementsByTagName("a")[0];
    // 利用URL.createObjectURL()方法为a元素生成blob URL
    a.href = URL.createObjectURL(blob);
    // 设置文件名
    a.download = "已发货数据统计表.xls";





</script>







</body>
</html>