<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript">
		function updateCart(id,count){
			if(isNaN(count)){
				alert("请输入正确格式的数字！");
				return;
			}
			location.href="/BookStore/cart_update.do?flag=cart_update&id="+id+"&count="+count;
		}
</script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<div id="navbar">
		<div class="userMenu">
			<ul>
				<li><a href="book_getAll.do?flag=book_getAll&pageNum=1">User首页</a></li>
				<li><a href="order_show.do?flag=order_show&pageNum=1">我的订单</a></li>
				<li class="current"><a href="cart_show.do?flag=cart_show">购物车</a></li>
				<li><a href="user_unlogin.action?flag=user_unlogin">注销</a></li>
			</ul>
		</div>
		<form method="get" name="search" action="">
			搜索：<input class="input-text" type="text" name="keywords" /><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>
<div id="content" class="wrap">
	<div class="list bookList">
		<form method="post" name="shoping" action="order_add.do">
		<input type="hidden" name = "flag" value="order_add"/>
			<table>
				<tr class="title">
					<th class="view">图片预览</th>
					<th>书名</th>
					<th class="nums">数量</th>
					<th class="price">价格</th>
					<th class="delete">删除</th>
				</tr>
				<c:forEach items="${sessionScope.cart }" var="book" varStatus="status">
				<c:choose>
					<c:when test="${status.index %2 == 0}">
						<tr class="odd">
					</c:when>
					<c:otherwise>
						<tr>
					</c:otherwise>
				</c:choose>
					<td class="thumb"><img src="upload/${book.key.imgUrl }" /></td>
					<td class="title">${book.key.name }</td>
					<td><input class="input-text" type="text" name="nums" value="${book.value }" onblur="updateCart('${book.key.id}',this.value)"/></td>
					<td>￥<span>${book.key.price }</span></td>
					<td><a href="cart_delete.do?flag=cart_delete&id=${book.key.id}">删除</a></td>
				</tr>
				</c:forEach>
			</table>
			<div class="button">
				<h4>总价：￥<span>${wholePrice }</span>元</h4>
				<input type="hidden" name = "wholePrice" value="${wholePrice }" />
				<input class="input-chart" type="submit" name="submit" value="" />
			</div>
		</form>
	</div>
</div>
<%@ include file="util/bottom.jsp"%>
</body>
</html>
    