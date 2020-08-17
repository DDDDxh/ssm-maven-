<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
	<meta charset="utf-8">
	<title>Layui</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
	<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
	<script src="${pageContext.request.contextPath}layui/layui.js"></script>
	<script type="text/html" id="bar">
		<button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="detail">查看</button>
		<button id="edit" class="layui-btn layui-btn-sm" lay-event="update">编辑</button>
	</script>
</head>
<body>
<table id="demo" lay-filter="test"></table>

<script>
	layui.use('table', function(){
		var table = layui.table;
		//第一个实例
		table.render({
			elem: '#demo'
			,url: '${pageContext.request.contextPath}/find' //数据接口
			,page: true //开启分页
			,cols: [[ //表头
				{field: 'id', title: '商品ID', width:80, sort: true, fixed: 'left'}
				,{field: 'name', title: '商品名称', width:80}
				,{field: 'price', title: '市场价格', width:80, sort: true}
				,{field: 'detail', title: '商店价格', width:80}
				,{field: 'createtime', title: '日期', width: 177,templet:'<div>{{ layui.util.toDateString(d.createtime, "yyyy-MM-dd HH:mm:ss") }}</div>'}
				,{field: '',title: '操作',toolbar: "#bar"}
			]]
			,limits: [5,10,15]
		});
		table.on('tool(test)',function (obj) {
			var data = obj.data;
			var layEvent = obj.event;
			if(layEvent === 'update'){
				alert(data.id);

			}

		})
	});

</script>
</body>
</html>