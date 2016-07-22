<%@ page language="java"  pageEncoding="UTF-8"%>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<div id="navbar">
		<div class="userMenu">
			<ul>
				<li class="current"><a href="book_getAll.do?flag=book_getAll&pageNum=1">User首页</a></li>
				<li><a href="order_show.do?flag=order_show&pageNum=1">我的订单</a></li>
				<li><a href="cart_show.do?flag=cart_show">购物车</a></li>
				<li><a href="user_unlogin.action?flag=user_unlogin">注销</a></li>
			</ul>
		</div>
		<form method="get" name="search" action="">
			搜索：<input class="input-text" type="text" name="keywords" /><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>