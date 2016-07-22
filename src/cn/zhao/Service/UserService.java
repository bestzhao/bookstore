package cn.zhao.Service;

import cn.zhao.dao.UserDao;
import cn.zhao.entity.User;

public class UserService {
	
	private UserDao userDao = new UserDao();
	
	public boolean register(User user){
		return userDao.addUser(user);
	}
	
	public User getUserByName(String name){
		return userDao.findUserByName(name);
	}

	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		User user = userDao.findUserByName(username);
		if(user==null)
			return false;
		if(!user.getPassword().equals(password))
			return false;
		return true;
	}
}
