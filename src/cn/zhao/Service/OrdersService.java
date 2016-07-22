package cn.zhao.Service;

import java.util.List;
import java.util.Map;

import cn.zhao.dao.OrdersDao;
import cn.zhao.entity.Book;
import cn.zhao.entity.Orders;

public class OrdersService {
	
	private OrdersDao ordersDao = new OrdersDao();
	
	public boolean addOrders(Orders orders){
		return ordersDao.addOrder(orders);
	}
	
	public List<Orders> getAllOrders(int pageNum){
		return ordersDao.findAll(pageNum);
	}

	public int getAllCount() {
		// TODO Auto-generated method stub
		return ordersDao.getCount();
	}
	public Map<Book,Integer> getBooksByOrderId(int id){
		return ordersDao.getBooksByOrderId(id);
	}
}
