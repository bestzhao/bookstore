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
<%@ include file="util/header.jsp"%>
<div id="content" class="wrap">
	<div class="list bookList">
		<form method="post" name="shoping" action="cart_add.do">
		<input type="hidden" name="flag" value="cart_add"/>
			<table>
				<tr class="title">
					<th class="checker"></th>
					<th>书名</th>
					<th class="price">价格</th>
					<th class="store">库存</th>
					<th class="view">图片预览</th>
				</tr>
				<c:forEach items="${list }" var="book" varStatus="status">
				<c:choose>
					<c:when test="${status.index %2 == 0}">
						<tr class="odd">
					</c:when>
					<c:otherwise>
						<tr>
					</c:otherwise>
				</c:choose>
					<td><input type="checkbox" name="bookId" value="${book.id }" /></td>
					<td class="title">${book.name }</td>
					<td>￥${book.price}</td>
					<td>${book.sum }</td>
					<td class="thumb"><img src="upload/${book.imgUrl }" /></td>
				</tr>
				</c:forEach>
			</table>
			<div class="page-spliter">
		<!-- 	<p align="right"> 当前页数:[${pageNum }/${totalNum }]&nbsp; --> 
				<a href="book_getAll.do?flag=book_getAll&pageNum=1">首页</a>
    	<c:if test="${pageNum>1 }">
   	 	<a href="book_getAll.do?flag=book_getAll&pageNum=${pageNum-1 }">上一页</a>
   	 	</c:if>
   	 	<c:forEach begin="1" end="${totalNum  }" var="i">
   	 	<a href="book_getAll.do?flag=book_getAll&pageNum=${i }">
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
    	<a href="book_getAll.do?flag=book_getAll&pageNum=${pageNum+1 }">下一页</a>
    	</c:if>
    	<a href="book_getAll.do?flag=book_getAll&pageNum=${totalNum }">末页</a> </p>
			</div>
			<div class="button"><input class="input-btn" type="submit" name="submit" value="" /></div>
		</form>
	</div>
</div>
<%@ include file="util/bottom.jsp"%>
</body>
</html>
