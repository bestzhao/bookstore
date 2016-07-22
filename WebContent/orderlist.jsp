<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<div id="navbar">
		<div class="userMenu">
			<ul>
				<li><a href="book_getAll.do?flag=book_getAll&pageNum=1">User首页</a></li>
				<li class="current"><a href="order_show.do?flag=order_show&pageNum=1">我的订单</a></li>
				<li><a href="cart_show.do?flag=cart_show">购物车</a></li>
				<li><a href="user_unlogin.action?flag=user_unlogin">注销</a></li>
			</ul>
		</div>
		<form method="get" name="search" action="">
			搜索：<input class="input-text" type="text" name="keywords" /><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>
<div id="content" class="wrap">
	<div class="list orderList">
			<table>
				<tr class="title">
					<th class="orderId">订单编号</th>
					<th>订单商品</th>
					<th class="userName">收货人</th>
					<th class="price">订单金额</th>
					<th class="createTime">下单时间</th>
					<th class="status">订单状态</th>
				</tr>
				<c:forEach items="${requestScope.list }" var="order" varStatus="status">
				<c:choose>
					<c:when test="${status.index %2 == 0}">
						<tr class="odd">
					</c:when>
					<c:otherwise>
						<tr>
					</c:otherwise>
				</c:choose>
					<td>${order.id +1000 }</td>
					<td class="thumb">
						<c:set var="isDone" value="1" ></c:set>
						<c:forEach items="${order.map }" var="book" varStatus="status2">
						<c:if test="${status2.index==3 }">
						<c:set var="isDone" value="0" ></c:set>
						．．．
						</c:if>
						<c:if test="${isDone!=0 }">
							<img src="upload/${book.key.imgUrl }" />
						</c:if>
						<c:if test="${status2.last }">
							<c:set var = "isDone" value = "1"></c:set>
						</c:if>
						</c:forEach>
					</td>
					<td>${order.receiver }</td>
					<td>￥${order.price }</td>
					<td>${order.date }</td>
					<td>${order.status}</td>
				</tr>
				</c:forEach>
			</table>
			<div class="page-spliter">
		<a href="order_show.do?flag=order_show&pageNum=1">首页</a>
    	<c:if test="${pageNum>1 }">
   	 	<a href="order_show.do?flag=order_show&pageNum=${pageNum-1 }">上一页</a>
   	 	</c:if>
   	 	<c:forEach begin="1" end="${totalNum  }" var="i">
   	 	<a href="order_show.do?flag=order_show&pageNum=${i }">
   	 	<c:choose>
					<c:when test="${pageNum == i}">
						<font color="red">
					</c:when>
					<c:otherwise>
						<font>
					</c:otherwise>
				</c:choose>
   	 	
   	 	${i }</font>
   	 	</a>
   	 	</c:forEach>
    	<c:if test="${pageNum<totalNum }">
    	<a href="order_show.do?flag=order_show&pageNum=${pageNum+1 }">下一页</a>
    	</c:if>
    	<a href="order_show.do?flag=order_show&pageNum=${totalNum }">末页</a> </p>
			</div>
			<div class="button"><input class="input-gray" type="submit" name="submit" value="查看一个月前的订单" /><input class="input-gray" type="submit" name="submit" value="查看一个月前的订单" /></div>
	</div>
</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有</div>
</body>
</html>
    