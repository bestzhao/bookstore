package cn.zhao.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zhao.Service.BookService;
import cn.zhao.entity.Book;
import cn.zhao.util.PageUtil;

public class BookServlet extends HttpServlet{
	
	private BookService  bookService = new BookService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag = req.getParameter("flag");
		if("book_getAll".equals(flag)){
			System.out.println("进入书列表！");
			String pageNum = req.getParameter("pageNum");
			int total = bookService.getAllCount();
			int totalNum = PageUtil.getTotalNum(total);
			List<Book> list = new ArrayList<Book>();
			list = bookService.getAllBook(Integer.parseInt(pageNum));
			req.setAttribute("list", list);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("totalNum", totalNum);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
}
