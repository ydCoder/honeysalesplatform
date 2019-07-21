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

        <div class="container">
            <div class="row clearfix">

                <blockquote class="layui-elem-quote" style="margin-left:20px;margin-top:10px;height:39px;width: 188px">
                    <div id="test2">订单详情</div>
                </blockquote>
            <#--订单详情表数据-->
                <div class="col-md-12 column">

                    <table class="layui-table" lay-size="sm">
                        <thead>
                        <tr>
                            <th>订单ID</th>
                            <th>名称</th>
                            <th>商品图片</th>
                            <th>价格</th>
                            <th>数量</th>
                            <th>金额</th>
                            <th>总金额</th>
                        </tr>
                        </thead>
                        <tbody>



                     <#list orderDto.orderDetails as orderDetail>

                     <#if orderDetail_index==0>
                       <tr>

                           <td rowspan="${count}">${orderDto.orderId}</td>
                            <td>${orderDetail.productName}</td>
                            <td><img height="86" width="86" src="${orderDetail.imgAddr}" alt=""></td>
                            <td>${orderDetail.productPrice}</td>
                            <td>${orderDetail.productAmount}</td>
                            <td>${orderDetail.productAmount * orderDetail.productPrice}</td>
                         <td rowspan="${count}">${orderDto.orderTotal}</td>
                       </tr>
                    <#else >
                    <tr>
                      <td>${orderDetail.productName}</td>
                            <td><img height="86" width="86" src="${orderDetail.imgAddr}" alt=""></td>
                            <td>${orderDetail.productPrice}</td>
                            <td>${orderDetail.productAmount}</td>
                            <td>${orderDetail.productAmount * orderDetail.productPrice}</td>
                    </tr>

                     </#if>



                     </#list>


                        </tbody>
                    </table>
                </div>

            <#--操作-->
                <div class="col-md-12 column">
                <#if orderDto.getOrderStatusEnum().message == "新订单">
                    <a  type="button" class="layui-btn layui-btn-danger layui-btn-xs btn1">去发货</a>

                </#if>
                </div>
            </div>
        </div>

     <script>
         $(document).on('click', '.btn1', function () {
             $.ajax({
                         type: "get",
                         url: "/sell/admin/order/finish?orderId="+"${orderDto.orderId}",
                         dataType:'json',
                         success: function (data) {
                             console.log('success');
                             $('.btn1').text("已发货");

                         }

                     }
             );
         });
     </script>



</body>
</html>