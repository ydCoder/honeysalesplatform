<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>后台管理系统Admin 1.0</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="stylesheet" href="http://ydcoding.cn/sale/admin/static/css/font.css">
    <link rel="stylesheet" href="http://ydcoding.cn/sale/admin/static/css/weadmin.css">
    <script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
    <script src="http://ydcoding.cn/sale/jquery-3.2.1.js"></script>

</head>
<audio id="music">
    <source src="http://ydcoding.cn/sale/music.mp3" type="audio/mpeg" />
</audio>
<audio id="cancel">
    <source src="http://ydcoding.cn/sale/cancel.mp3" type="audio/mpeg" />
</audio>


<script>

    /**
     * WebSocket客户端
     *
     * 使用说明：
     * 1、WebSocket客户端通过回调函数来接收服务端消息。例如：webSocket.onmessage
     * 2、WebSocket客户端通过send方法来发送消息给服务端。例如：webSocket.send();
     */

    $(document).ready(function(){
        getWebSocket();
    });

    /**
     * WebSocket客户端 PS：URL开头表示WebSocket协议 中间是域名端口 结尾是服务端映射地址
     */

    function getWebSocket() {
        var websocket = null;
        if ('WebSocket' in window) {
            // websocket = new WebSocket('ws://pyd888.natapp1.cc/sell/webSocketServer');
            websocket = new WebSocket('ws://pyd888.natapp1.cc/sell/webSocketServer');
        } else {
            alert('该浏览器不支持websocket!');
        }

        websocket.onopen = function (event) {
            console.log('建立连接');
        }

        websocket.onclose = function (event) {
            console.log('连接关闭');
        }

        websocket.onmessage = function (event) {
            console.log('收到消息:' + event.data)

            var message=0;
            count=parseInt(message);
            count++;

            $('#message').text(count);
            if(event.data=='新订单'){
                document.getElementById('music').play();
            } else {
                document.getElementById('cancel').play();
            }

        }

        websocket.onerror = function () {

        }

        window.onbeforeunload = function () {
            websocket.close();
        }
    }
</script>

<body>
<!-- 顶部开始 -->
<div class="container">
    <div class="logo">
        <a href=""><strong>向宇蜜园后台管理系统</strong></a>
    </div>
    <div class="left_open">
    </div>
    <ul class="layui-nav left fast-add" lay-filter="">

        <a href="javascript:;"></a>
        </li>
    </ul>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">
                欢迎您,
<#if Session["adminName"]?exists>

    ${Session["adminName"]}
</#if>
        </a>

        <li class="layui-nav-item">
            <a href="">消息<span style="background: #5FB878"   id="message" class="layui-badge">0</span></a>
        </li>

        <li class="layui-nav-item to-index">

        <a href="/sell/admin/logout">注销</a>
        </li>
    </ul>


</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->

<div class="left-nav">
    <div id="side-nav">
        <ul id="nav"  >
            <li>
                <a href="javascript:;">
                    <cite><strong>订单管理</strong></cite>
                </a>
            </li>

            <li>
                <a href="javascript:toOrderList();">
                    <span class="layui-badge-dot layui-bg-orange"></span>
                    <cite>订单列表</cite>

                </a>

            </li>


            <li>
                <a href="javascript:;">

                    <cite><strong>商品管理</strong></cite>

                </a>

            </li>



            <li>
                <a href="javascript:toProductList();">
                    <span class="layui-badge-dot"></span>

                    <cite>商品列表</cite>

                </a>

            </li>
            <li>

                <ul class="sub-menu">
                    <li>
                        <a _href="./pages/order/list.html">

                            <cite>订单列表</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">

                    <cite><strong>类目管理</strong></cite>

                </a>

            </li>
            <li>
                <a href="javascript: toCategoryList() ;">
                    <span class="layui-badge-dot layui-bg-green"></span>
                    <cite>类目列表</cite>

                </a>

            </li>
            <li>
                <a href="javascript:;">
                    <cite><strong>发货订单</strong></cite>

                </a>
                <a href="javascript: toOrderAll() ;">
                    <span class="layui-badge-dot layui-bg-cyan"></span>
                    <cite>数据导出</cite>

                </a>


            </li>
        </ul>
    </div>


</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="wenav_tab" id="WeTabTip" lay-allowclose="true">
        <ul class="layui-tab-title" id="tabName">
            <li>当前位置</li>
        </ul>
        <div class="layui-tab-content">
            <div  id="content"   class="layui-tab-item layui-show">
                <iframe src='/sell/admin/order/list' frameborder="0" scrolling="yes" class="weIframe"></iframe>
            </div>
        </div>
    </div>
</div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->
<div class="footer">
    <div class="copyright" ><h4 style="color:gray"><strong>Copyright ©2019 向宇蜜园 v1.0 All Rights Reserved</strong></h4></div>
</div>


<!-- 底部结束 -->

<script type="text/javascript">


    function toOrderList() {
        document.getElementById("content").innerHTML = "<object type=\"text/html\" data=\"/sell/admin/order/list\" width=\"100%\" height=\"100%\"></object>";
    }

    function toProductList() {
        document.getElementById("content").innerHTML = "<object type=\"text/html\" data=\"/sell/admin/product/list\" width=\"100%\" height=\"100%\"></object>";
    }

    function toCategoryList() {
        document.getElementById("content").innerHTML = "<object type=\"text/html\" data=\"/sell/admin/category/list\" width=\"100%\" height=\"100%\"></object>";
    }

    function toOrderAll() {
        document.getElementById("content").innerHTML = "<object type=\"text/html\" data=\"/sell/admin/order/orderall\" width=\"100%\" height=\"100%\"></object>";
    }


</script>


</body>



</html>