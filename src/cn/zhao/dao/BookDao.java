package cn.zhao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.zhao.entity.Book;
import cn.zhao.util.DBUtil;
import cn.zhao.util.PageUtil;

public class BookDao {
	private Connection conn;
//	   book_id               int not null auto_increment,
//	   book_name             varchar(32) not null,
//	   book_des              varchar(140),
//	   book_price            float not null,
//	   book_sum              int not null,
//	   book_img              varchar(20) not null,
	public boolean addBook(Book book){
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into book(book_name,book_des,book_price,book_sum,book_img) values(?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, book.getName());
			pst.setString(2, book.getDes());
			pst.setDouble(3, book.getPrice());
			pst.setInt(4, book.getSum());
			pst.setString(5, book.getImgUrl());
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public List<Book> findAll(int pageNum) {
		List<Book> list = new ArrayList<Book>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from book limit ?,?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, (pageNum-1)*PageUtil.pageSize);
			pst.setInt(2, PageUtil.pageSize);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("book_id"));
				book.setName(rs.getString("book_name"));
				book.setDes(rs.getString("book_des"));
				book.setPrice(rs.getDouble("book_price"));
				book.setSum(rs.getInt("book_sum"));
				book.setImgUrl(rs.getString("book_img"));
				list.add(book);
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
	
	public int getCount(){
		int count=0;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from book";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	public Book findById(int id) {
		// TODO Auto-generated method stub
		Book book = new Book();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from book where book_id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				book.setId(id);
				book.setName(rs.getString("book_name"));
				book.setDes(rs.getString("book_des"));
				book.setPrice(rs.getDouble("book_price"));
				book.setSum(rs.getInt("book_sum"));
				book.setImgUrl(rs.getString("book_img"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return book;
	}
}
