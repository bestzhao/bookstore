package cn.zhao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.zhao.entity.User;
import cn.zhao.util.DBUtil;

public class UserDao {
	
	private Connection conn;
	public boolean addUser(User user){
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into user(user_name,user_password,user_email) values(?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUserName());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getEmail());
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
	public User findUserByName(String name){
		User user = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from user where user_name = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				user = new User();
				user.setId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("user_password"));
				user.setEmail(rs.getString("user_email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}
	
}
