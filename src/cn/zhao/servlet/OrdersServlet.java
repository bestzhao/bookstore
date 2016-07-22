package cn.zhao.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.zhao.Service.OrdersService;
import cn.zhao.Service.UserService;
import cn.zhao.entity.Book;
import cn.zhao.entity.Orders;
import cn.zhao.util.PageUtil;

public class OrdersServlet extends HttpServlet {
	private OrdersService ordersService = new OrdersService();
	private UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag = (String) req.getParameter("flag");
		if ("order_add".equals(flag)) {
			System.out.println("进入添加订单！");
			HttpSession session = req.getSession();
			double wholePrice = Double.parseDouble(((String) req
					.getParameter("wholePrice")));
			Map<Book, Integer> cart = (Map<Book, Integer>) session
					.getAttribute("cart");
			Orders orders = new Orders();
			orders.setUserId(userService.getUserByName(
					(String) session.getAttribute("loginName")).getId());
			Date date = new Date();
			orders.setDate(date);
			orders.setReceiver((String) session.getAttribute("loginName"));
			orders.setMap(cart);
			orders.setStatus("已完成");
			orders.setPrice(wholePrice);
			ordersService.addOrders(orders);
			resp.sendRedirect("shopping-success.html");
		}else if("order_show".equals(flag)){
			System.out.println("进入订单列表！");
			String pageNum = req.getParameter("pageNum");
			int total = ordersService.getAllCount();
			int totalNum = PageUtil.getTotalNum(total);
			List<Orders> list = new ArrayList<Orders>();
			list = ordersService.getAllOrders(Integer.parseInt(pageNum));
			for(int i = 0; i < list.size();i++){
				list.get(i).setMap(ordersService.getBooksByOrderId(list.get(i).getId()));
			}
			System.out.println(list.toString());
			req.setAttribute("list", list);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("totalNum", totalNum);
			req.getRequestDispatcher("orderlist.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
