<html>
<link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">

<link href="http://ydcoding.cn/sale/layui/layui/css/layui.css" rel="stylesheet">




<link href="http://ydcoding.cn/sale/layui/layui/css/layui.css" rel="stylesheet">

<script src="http://ydcoding.cn/sale/layui/layui/layui.js"></script>
<script src="http://ydcoding.cn/sale/jquery-3.2.1.js"></script>



<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->

<#--主要内容content-->
    <div id="page-content-wrapper">

        <div class="container-fluid">
            <div class="row clearfix">
                <blockquote class="layui-elem-quote" style="margin-left:20px;margin-top:10px;height:39px;width: 188px">
                    <div id="test2">类目列表</div>
                </blockquote>
    <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" id="addColor"   style="margin-left: 1.6%;width: 88px;i" onClick="add()">添加</button>
                <div class="col-md-12 column">

                    <table class="layui-table" lay-size="sm">
                        <thead>
                        <tr>

                            <th>类目</th>
                            <th>所属编号</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list categoryList as category>
                        <tr>

                            <td>${category.categoryName}</td>
                            <td>${category.categoryNo}</td>
                            <td>${category.createTime}</td>
                            <td>${category.updateTime}</td>
                            <td>

                            <button  style="" onClick="edit(${category.categoryId});" lay-event="edit"      thisId="${category.categoryId}"   class="layui-btn layui-btn-xs layui-btn-danger update">编辑</button></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>


<script>

    //单独模块不行引用
    layui.use(['table', 'layer'], function () {
        var table = layui.table;
        var layer = layui.layer;

    });
    $(".update").click(function () {

        var categoryId = $(this).attr("thisId");
        edit(categoryId);

    });

  function  edit(categoryId){
      layer.open({
          type: 2,
          title: '编辑类目',
          shadeClose: true,
          shade: 0.1,
          area: ['300px', '300px'],
          content: '/sell/admin/category/addEdit?categoryId='+categoryId//iframe的url
      });
  }

    function add() {
        layer.open({
            type: 2,
            title: '新增类目',
            shadeClose: true,
            shade: 0.1,
            area: ['300px', '300px'],
            content: '/sell/admin/category/addEdit'

        });
    }

</script>
</body>
</html>