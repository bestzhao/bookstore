package cn.zhao.Service;

import java.util.List;

import cn.zhao.dao.BookDao;
import cn.zhao.entity.Book;

public class BookService {
	private BookDao bookDao = new BookDao();
	
	public List<Book> getAllBook(int pageNum){
		return bookDao.findAll(pageNum);
	}

	public int getAllCount() {
		// TODO Auto-generated method stub
		return bookDao.getCount();
	}

	public Book getBookById(int id) {
		// TODO Auto-generated method stub
		return bookDao.findById(id);
	}
}
