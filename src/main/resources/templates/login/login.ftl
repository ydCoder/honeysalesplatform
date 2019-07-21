<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <title>向宇蜜园后台管理系统登录</title>
    <link href="http://ydcoding.cn/sale/layui/layui/css/layui.css" rel="stylesheet">


    <link rel="icon" href="http://ydcoding.cn/sale/image/title.jpg" sizes="32x32">

    <link href="http://ydcoding.cn/sale/layui/layui/css/layui.css" rel="stylesheet">

    <script src="http://ydcoding.cn/sale/layui/layui/layui.js"></script>
    <script src="http://ydcoding.cn/sale/jquery-3.2.1.js"></script>

    <link rel="stylesheet" type="text/css" href="http://ydcoding.cn/sale/login/login/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="http://ydcoding.cn/sale/login/login/css/demo.css" />
    <!--必要样式-->
    <link rel="stylesheet" type="text/css" href="http://ydcoding.cn/sale/login/login/css/component.css" />
    <link rel="stylesheet" type="text/css" href="http://ydcoding.cn/sale/login/layui/css/layui.css" />
    <script type="text/javascript" src="http://ydcoding.cn/sale/login/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="http://ydcoding.cn/sale/login/layui/layui.js"></script>
</head>
<body>
<div class="container demo-1">
    <div class="content">
        <div id="large-header" class="large-header">
            <canvas id="demo-canvas"></canvas>
            <div class="logo_box">
                <h1 style="margin-bottom:66px;text-align: center"><strong>向宇蜂园后台管理系统</strong></h1>
                <form action="#" name="f" method="post">
                    <div class="input_outer">
                        <span class="u_user"></span>
                        <input id="email1"  name="username" class="text" style="color:  !important" type="text" placeholder="请输入账户邮箱">
                    </div>

                    <a  href="#"  id="error" > </a>
                    <div class="input_outer">
                        <span class="us_uer"></span>
                        <input  id="password"     name="password" class="text" style="color:  !important; position:absolute; z-index:100;" value="" type="password" placeholder="请输入密码">




                    </div>

                    <a  href="#"  id="error2" > </a>
                    <div class="input_outer">
                        <span class="cs_uer"></span>
                   <input type="text"   id="code"  placeholder="请输入验证码"    style="color:  !important; position:absolute; z-index:100;"     class="text" name="vrifyCode">

                    </div>
                        <img     src="/sell/admin/defaultKaptcha" onclick="this.src='/sell/admin/defaultKaptcha?d='+new Date()*1">

                    <br/><br/><br/>
                    <div      href="#"  id="error3"   style='display:none;color:red;text-align: center;margin-bottom:16px';>验证码输入有误 </div>
                    <div class="mb2"><a id = "sub" lay-filter="sub" class="layui-btn layui-btn-fluid"   style="color: #FFFFFF;background:rgb(0,182,250)">登录</a></div>

                </form>
            </div>
        </div>
    </div>
</div><!-- /container -->
<script src="http://ydcoding.cn/sale/login/login/js/TweenLite.min.js"></script>
<script src="http://ydcoding.cn/sale/login/login/js/EasePack.min.js"></script>
<script src="http://ydcoding.cn/sale/login/login/js/rAF.js"></script>
<script src="http://ydcoding.cn/sale/login/login/js/demo-1.js"></script>
</body>
<script>
// 前端验证

    $(document).ready(function(){

    })


$("#email1").blur(function(){
    if($("#email1").val()=="")
    {
        $("#error").html("<p style='color:red;text-align: center;margin-bottom:16px;'>账户信息不能为空！</p>").show().delay(2000).hide(300);
            return false;
    }

    var email=$("#email1").val();
    if(!email.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/))
    {
        $("#error").html("<p style='color:red;text-align: center;margin-bottom:16px'>邮箱格式不正确！请重新输入！</p>").show().delay(2000).hide(300);


        $("#email1").focus();
    }

});







$("#password").blur(function(){
    if($("#password").val()=="")
    {
        $("#error2").html("<p style='color:red;text-align: center;margin-bottom:16px;'>密码不能为空！</p>").show().delay(2000).hide(300);
        return false;
    }



});




//前端向后端发送验证码校验请求
$("#code").blur(function () {
    var code = $(this).val();
    $.ajax({
                type: "post",
                url: "/sell/admin/login/code?code="+code,
                dataType:'json',
                success: function (data) {
                 if(data.result==="error"){
                        $("#error3").show().delay(2000).hide(300);
                    }
                }
        });
    });





    function refreshCaptcha() {
        $("#kaptcha").attr("src","/images/kaptcha.jpg?t=" + Math.random());
    }














    //加载弹出层组件
    layui.use('layer',function(){

        var layer = layui.layer;

        //登录的点击事件
        $("#sub").on("click",function(){
            login();
        })

        $("body").keydown(function(){
            if(event.keyCode == "13"){
                login();
            }
        })

        $("#sub").click(function () {
            login();

        });




        //登录函数
        function login(){
            var adminName= $(" input[ name='username' ] ").val();
            var password = $(" input[ name='password' ] ").val();
            $.ajax({
                url:"/sell/admin/login",
                data:{"adminEmail":adminName,"password":password},
                type:"post",
                dataType:"json",
                success:function(data){
                    if(data.result=="success"){
                        window.location = "/sell/admin/main";
                    }else{
                        layer.msg(data.result);
                    }
                }
            })
        }
    })
</script>
</html>