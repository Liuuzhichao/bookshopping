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
            <div class="topbar_content_left">
                欢迎光临Estore图书商城
            </div>
            <div class="topbar_content_right">
                <ul>
                    <li>
                        <a href="#" style="color:rgb(241, 187, 10)">亲，请登录</a>
                    </li>
                    <li>
                        <a href="#" style="color: rgb(241, 187, 10)">免费注册</a>
                    </li>
                    <li>
                        <a href="#">首页</a>
                    </li>
                    <li>|</li>
                    <li>
                        <a href="#">购物车</a>
                    </li>
                    <li>|</li>
                    <li>
                        <a href="#">我的订单</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div class="cart_content">
        <div class="header">
            <div class="header_png"></div>
            <div class="estore" align="center">
                <h1 align="center" style="font-size: 28px;color: #87520E;margin: 40px 0 10px 0;">Estore图书商城</h1>
                <img src="images/TB1yeWeIFXXXXX5XFXXuAZJYXXX-210-210.png_50x50.jpg" style="border-radius:50%">
                <br/>
                <font size="2">Hi!你好</font>
                <br/>
                <br/>
                <button>注册</button>
                <button>登录</button>
            </div>
        </div>

        <div align="center" id="cart">
            <table style="width:100%;" class="caption">
                <tr align="center">
                    <td style="width:25%;">商品名称</td>
                    <td style="width:25%;">商品单价</td>
                    <td style="width:22%;">购买的数量</td>
                    <td style="width:20%;">可购买数量</td>
                    <td>订单操作</td>
                </tr>

            </table>

			<c:if test="${empty cart }">
				购物车无信息
			</c:if>
			
			<c:if test="${!empty cart }">
				<table border="1" class="maintable">
				<c:forEach items="${cart }" var="pro">
					<tr align="center">
                    <td style="width:25%;">${pro.key.name }</td>
                    <td style="width:25%;">${pro.key.price }</td>
                    <td style="width:22%;">
                        <input type="button" value="-" onclick="changeCount('482b5255-741d-4466-8596-26b68db91dbb','3','95')" class="btn">

                        <input type='text' value="${pro.value }" style="text-align:center;color:#87520E;width:120px;height:25px;" onkeydown="numbText(event);"
                            onblur="changeCount('482b5255-741d-4466-8596-26b68db91dbb',this.value,'95')">

                        <input type="button" value='+' onclick="changeCount('482b5255-741d-4466-8596-26b68db91dbb','5','95')" class="btn">
                    </td>


                    <td style="width:20%;">${pro.key.pnum }</td>
                    <td>
                        <a href="/bb/cart?method=remove&id=482b5255-741d-4466-8596-26b68db91dbb" onclick="delConfirm(event)">删除</a>

                    </td>
                </tr>

                <tr>
                    <td colspan="5" align="right">总价:￥720.0元</td>
                </tr>
                <tr>
                    <td colspan="5" align="right">
                        <button onclick="gotoorder();" style="cursor:pointer;background:#87520E;border-radius:5px;line-height:40px;border:none;width:160px;color:white;font-size:18px;">进入结算</button>
                    </td>
                </tr>
				</c:forEach>
                
            </table>
			</c:if>

            

        </div>





        </div>

    <div class="footer1">
        <p align="center">
            总部地址:北京市海淀区小南庄怡秀园甲一号亿德大厦10层 电话：010-61943240
        </p>
        <p align="center"> Copyright © 2005-2020 北京翡翠教育科技有限公司，All Rights Reserved 京ICP备12036804号-23</p>
    </div>


</body>

</html>