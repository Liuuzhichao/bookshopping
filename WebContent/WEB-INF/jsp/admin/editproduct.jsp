<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>商品发布</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link rel="shortcut icon" href="favicon.ico">
	<link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
	<link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
	<link href="css/plugins/iCheck/custom.css" rel="stylesheet">
	<link href="css/animate.min.css" rel="stylesheet">
	<link href="css/style.min.css?v=4.0.0" rel="stylesheet">
	<script src="js/jquery.min.js?v=2.1.4"></script>

	<script src="js/content.min.js?v=1.0.0"></script>
	<script src="js/plugins/iCheck/icheck.min.js"></script>
	<script src="js/plugins/validate/jquery.validate.min.js"></script>
	<script src="js/plugins/validate/messages_zh.min.js"></script>
	<script src="js/plugins/date/WdatePicker.js"></script>
	<script src="js/plugins/layer/layer.js"></script>
	<script type="text/javascript" src="plugins/ckeditor/ckeditor.js"></script>
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							商品管理
							<small>商品发布</small>
						</h5>
						<div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<!-- action中可以只保留updateProduct?id=${product.id } 在请求后面挂参数是get请求-->
						<!-- 对图片进行处理的两种方法:1.在请求后面挂图片URL参数;2.写动态SQL语句 -->
						<form method="post" class="form-horizontal" id="editGoodsForm" 
						action="${pageContext.request.contextPath }/updateProduct?id=${product.id }&imgurl=${product.imgurl }" onsubmit="return checkCategory()" 
						enctype="multipart/form-data"><!-- 不对文件进行编码,表单包含上传文件时必须使用该值 -->
							<!-- 商品名称输入框 -->
							<div class="form-group">
								<!-- 隐藏域:用户看不到,传递参数的另一种方法:将商品id传递过去,用于进行按照id进行删除 -->
								<%-- <input type="hidden" name="id" value="${product.id }"> --%>
								<label class="col-sm-2 control-label">商品名称</label>
								<div class="col-sm-4">
									<input id="name" name="name" type="text" value="${product.name }" class="form-control" required="required">
									<br />
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- 商品类型下拉框 -->
							<div class="form-group">
								<label class="col-sm-2 control-label">商品类型</label>
								<div class="col-sm-4">
									<select class="form-control m-b" name="category" id="category">
										<option value="null">--请选择类别--</option>
										<option value="novel">小说</option>
										<option value="cartoon">童书</option>
										<option value="study">学习考试</option>
										<option value="literature">文学</option>
										<option value="music">音乐</option>
										<option value="art">艺术</option>
										<option value="science">科技</option>
									</select>
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<!-- 商品价格输入框 -->
							<div class="form-group">
								<label class="col-sm-2 control-label">商品价格</label>
								<div class="col-sm-4">
									<input name="price" type="text" value="${product.price }" class="form-control" required="required">
								</div>
							</div>
							<div class="hr-line-dashed"></div>


							<!-- 商品数量输入框 -->
							<div class="form-group">
								<label class="col-sm-2 control-label">商品数量</label>
								<div class="col-sm-4">
									<input name="pnum" type="text" value="${product.pnum }" class="form-control" required="required">
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<!-- 商品出版时间输入框 -->
							<div class="form-group">
								<label class="col-sm-2 control-label">出版时间</label>
								<div class="col-sm-4">
									<input name="cbtime" type="date" value="${product.cbtime }" class="form-control" required="required">
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<!-- 商品图片输入框 -->
							<div class="form-group">
								<label class="col-sm-2 control-label">商品图片</label>
								<div class="col-sm-4">
									<img src="${product.imgurl }" height="30px">
									<input name="imgpic" type="file" value="" class="form-control" 
									onchange="checkImg(this)"><!-- 内容发生变化触发onchange() -->
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- 商品详情输入框 -->
							<div class="form-group">
								<label class="col-sm-2 control-label">商品详情</label>
								<div class="col-sm-4">
									<textarea name="description" id="description" class="form-control">${product.description }</textarea>
								</div>
							</div>
							<!-- 商品操作 -->
							<div class="form-group">
								<label class="col-sm-2 control-label">商品操作</label>
								<div class="col-sm-4">
									<c:if test="${product.state==0 }">
										<input type="radio" name="state" value="1"/>上架
										<input type="radio" name="state" value="0" checked="checked"/>下架
									</c:if>
									<c:if test="${product.state==1 }">
										<input type="radio" name="state" value="1" checked="checked" />上架
										<input type="radio" name="state" value="0" />下架
									</c:if>
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-2">
									<button class="btn btn-primary" type="submit">保存</button>
									<button class="btn btn-white" type="reset">重置</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">

	//检查文件的格式是否是图片
	function checkImg(obj){
		//获取到上传文件的路径
		var filename = obj.value;
		//只能上传 .jpg .png .gif
		var index = filename.lastIndexOf(".");
		var name = filename.substring(index);
		name = name.toLocalelowerCase();
		if(name!=".jpg" && name!=".png" && name!="gif"){
			alert("只能上传.jpg .png .gif格式的文件");
			obj.value="";
		}
	}
	
	//检查类型是否为空
	function checkCategory(){
		var option = document.getElementById("category").value;
		if(option=="null"){
			alert("商品类型不能为空");
			return false;
		}
	}
	
	//在编辑页面获取之前的类型的值
	$(function (){
		$("#category").val("${product.category}");
	});
	
</script>

</html>