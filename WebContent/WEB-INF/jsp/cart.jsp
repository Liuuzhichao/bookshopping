<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>购物车</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/public.css">
</head>

<body>
	<div class="topbar">
		<div class="topbar_content">
			<div class="topbar_content_left">欢迎光临Estore图书商城</div>
			<div class="topbar_content_right">
				<ul>
					<c:if test="${empty user }">
						<li><a href="${pageContext.request.contextPath }/showLogin"
							style="color: rgb(241, 187, 10)">亲，请登录</a></li>
						<li><a href="${pageContext.request.contextPath }/showReg"
							style="color: rgb(241, 187, 10)">免费注册</a></li>
					</c:if>
					<c:if test="${!empty user }">
						<li><a href="#" style="color: rgb(241, 187, 10)">${user.nickname }</a>
						</li>
						<li><a href="${pageContext.request.contextPath }/logout"
							style="color: rgb(241, 187, 10)">退出登录</a></li>
					</c:if>
					<li><a href="${pageContext.request.contextPath }/showIndex">首页</a>
					</li>
					<li>|</li>
					<li><a href="${pageContext.request.contextPath }/showCart">购物车</a>
					</li>
					<li>|</li>
					<li><a
						href="${pageContext.request.contextPath }/showOrders">我的订单</a>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<div class="cart_content">
		<div class="header">
			<div class="header_png"></div>
			<div class="estore" align="center">
				<h1 align="center"
					style="font-size: 28px; color: #87520E; margin: 40px 0 10px 0;">Estore图书商城</h1>
				<img src="images/TB1yeWeIFXXXXX5XFXXuAZJYXXX-210-210.png_50x50.jpg"
					style="border-radius: 50%"> <br /> <font size="2">Hi!你好</font>
				<br /> <br />
				<button>注册</button>
				<button>登录</button>
			</div>
		</div>

		<div align="center" id="cart">
			<table style="width: 100%;" class="caption">
				<tr align="center">
					<td style="width: 25%;">商品名称</td>
					<td style="width: 25%;">商品单价</td>
					<td style="width: 22%;">购买的数量</td>
					<td style="width: 20%;">可购买数量</td>
					<td>订单操作</td>
				</tr>

			</table>

			<c:if test="${empty cart }">
				购物车无信息
			</c:if>
			<c:if test="${!empty cart }">
				<table border="1" class="maintable">
					<!-- 
					jsp中有九大内置对象:
					  4大域对象:
					  pageContext:配置域:只在当前页面有效
					  request:在同一个请求中有效(包含请求转发)
					  session:在同一个会话中有效(一个会话中可以有多个session,但是只能同时存在一个)
					  application:在当前的web应用中有效  
					  	类型:ServletContnext:代表当前的web应用,在整个web应用中只有一个ServletContnext对象
					  
					  5个配置对象:
					  page:可以获取其他的8个对象
					  config
					  out
					  response
					  exception
				
				 -->
					<%
						//Java程序片的标记,里面编写Java代码
							//jsp是一个servlet,第一次访问jsp时,web服务器会将jsp转化为Java文件,将Java文件转换为字节码文件再运行
					%>
					<!-- 设置一个变量,变量名字为money,赋值为0 -->
					<c:set var="money" value="0"></c:set>
					<c:forEach items="${cart }" var="pro">
						<tr align="center">
							<td style="width: 25%;">${pro.key.name }</td>
							<td style="width: 25%;">${pro.key.price }</td>
							<td style="width: 22%;"><input type="button" value="-"
								onclick="changeCount('${pro.key.id}','${pro.value-1 }','${pro.key.pnum }')"
								class="btn"> <input type='text' value="${pro.value }"
								style="text-align: center; color: #87520E; width: 120px; height: 25px;"
								onkeydown="numbText(event);"
								onchange="changeCount('${pro.key.id}',this.value,'${pro.key.pnum }')">

								<input type="button" value='+'
								onclick="changeCount('${pro.key.id}','${pro.value+1 }','${pro.key.pnum }')"
								class="btn"></td>


							<td style="width: 20%;">${pro.key.pnum }</td>
							<td><a
								onclick="changeCount('${pro.key.id}',0,'${pro.key.pnum }')">删除</a>

							</td>
						</tr>

						<!-- 如果已经创建了变量,可以直接进行赋值,没创建会自动创建-->
						<c:set var="money" value="${pro.key.price * pro.value + money}"></c:set>
					</c:forEach>
					<tr>
						<td colspan="5" align="right">
							<button onclick="gotoOrder();"
								style="cursor: pointer; background: #87520E; border-radius: 5px; line-height: 40px; border: none; width: 160px; color: white; font-size: 18px;">进入结算</button>
						</td>
					</tr>
					<tr>
						<td colspan="5" align="right">总价:${money }</td>
					</tr>
				</table>
			</c:if>

		</div>
	</div>

	<div class="footer1">
		<p align="center">总部地址:北京市海淀区小南庄怡秀园甲一号亿德大厦10层 电话：010-61943240</p>
		<p align="center">Copyright © 2005-2020 北京翡翠教育科技有限公司，All Rights
			Reserved 京ICP备12036804号-23</p>
	</div>

</body>
<script type="text/javascript">
	//id:要修改的商品id  count:修改的数量   max:商品的最大数量
	function changeCount(id, count, max) {
		if (count < 0) {
			count = 1;
		}
		if (count > max) {
			alert("最多购买" + max + "件.");
			count = max;
		}
		if (count == 0) {
			var flag = confirm("确定删除商品吗?");
			if (!flag) {
				return;
			}
		}
		window.location.href = "${pageContext.request.contextPath}/updateCart?id="
				+ id + "&count=" + count;
	}

	function gotoOrder() {
		window.location.href = "${pageContext.request.contextPath}/showCreatOrder";
	}
</script>

</html>