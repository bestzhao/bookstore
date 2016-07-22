package cn.zhao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.zhao.entity.Book;
import cn.zhao.entity.Orders;
import cn.zhao.util.DBUtil;
import cn.zhao.util.PageUtil;

public class OrdersDao {

	private Connection conn;

	public boolean addOrder(Orders orders) {
		double totalPrice = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into orders(user_id,order_status,order_date,order_price,order_receiver) values(?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setInt(1, orders.getUserId());
			pst.setString(2, orders.getStatus());
			pst.setDate(3, new java.sql.Date(orders.getDate().getTime()));
			pst.setDouble(4, orders.getPrice());
			pst.setString(5, orders.getReceiver());
			pst.execute();
			ResultSet rs = pst.getGeneratedKeys(); // 获取结果
			int id = 0;
			if (rs.next()) {
				id = rs.getInt(1);// 取得ID
			}
			for (Entry<Book, Integer> book : orders.getMap().entrySet()) {
				String sql2 = "insert into orderitem(order_id,book_id,book_num) values(?,?,?)";
				pst = conn.prepareStatement(sql2);
				pst.setInt(1, id);
				pst.setInt(2, book.getKey().getId());
				pst.setInt(3, book.getValue());
				pst.execute();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	public List<Orders> findAll(int pageNum) {
		List<Orders> list = new ArrayList<Orders>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from orders limit ?,?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, (pageNum - 1) * PageUtil.pageSize);
			pst.setInt(2, PageUtil.pageSize);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Orders order = new Orders();
				order.setId(rs.getInt("order_id"));
				order.setDate(rs.getDate("order_date"));
				order.setPrice(rs.getDouble("order_price"));
				order.setReceiver(rs.getString("order_receiver"));
				order.setStatus(rs.getString("order_status"));
				order.setUserId(rs.getInt("user_id"));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public int getCount() {
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from orders";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	public Map<Book, Integer> getBooksByOrderId(int id) {
		// SELECT * FROM new LEFT JOIN comment ON new.news_id
		// =comment.comment_newsId LEFT JOIN user ON user.id =
		// comment.comment_userId where news_id = ?
		Map<Book, Integer> map = new HashMap<Book, Integer>();
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM orders LEFT JOIN orderitem ON orders.order_id=orderitem.order_id LEFT JOIN book ON orderitem.book_id= book.book_id where orders.order_id= ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("book_id"));
				b.setDes(rs.getString("book_des"));
				b.setName(rs.getString("book_name"));
				b.setPrice(rs.getDouble("book_price"));
				b.setImgUrl(rs.getString("book_img"));
				b.setSum(rs.getInt("book_sum"));
				int num = rs.getInt("book_num");
				map.put(b, num);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}
}
