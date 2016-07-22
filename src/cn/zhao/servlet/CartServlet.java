package cn.zhao.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.zhao.Service.BookService;
import cn.zhao.entity.Book;

public class CartServlet extends HttpServlet {

	private BookService bookService = new BookService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag = req.getParameter("flag");
		if ("cart_add".equals(flag)) {
			System.out.println("进入添加购物车！");
			HttpSession session = req.getSession();
			Map<Book, Integer> cart = null;
			if ((Map<Book, Integer>) session.getAttribute("cart") == null) {
				// List<Book> cart1 = new ArrayList<Book>();
				System.out.println("进入第一次添加购物车！");
				cart = new HashMap<Book, Integer>();
				String[] bookIds = req.getParameterValues("bookId");
				System.out.println(bookIds.toString());
				for (int i = 0; i < bookIds.length; i++) {
					Book book = new Book();
					book = bookService
							.getBookById(Integer.parseInt(bookIds[i]));
					cart.put(book, 1);
					// cart1.add(book);
				}
			} else {
				System.out.println("进入第二次添加购物车！");
				cart = (Map<Book, Integer>) session.getAttribute("cart");
				String[] bookIds = req.getParameterValues("bookId");
				System.out.println(bookIds.toString());
				for (int i = 0; i < bookIds.length; i++) {
					System.out.println(bookIds[i]);
					Book book = new Book();
					book = bookService
							.getBookById(Integer.parseInt(bookIds[i]));

						if(!cart.containsKey(book))
							cart.put(book, 1);

				}
			}
			session.setAttribute("cart", cart);
			System.out.println(cart);
			resp.sendRedirect("book_getAll.do?flag=book_getAll&pageNum=1");
		} else if ("cart_show".equals(flag)) {
			System.out.println("进入购物车展示!");
			HttpSession session = req.getSession();
			Map<Book, Integer> cart = (Map<Book, Integer>) session
					.getAttribute("cart");
			double wholePrice = 0.00;
			if (cart != null) {
				for (Entry<Book, Integer> entry : cart.entrySet()) {
					wholePrice = wholePrice + entry.getKey().getPrice()
							* entry.getValue();
				}
			}
			req.setAttribute("wholePrice", wholePrice);
			req.getRequestDispatcher("shopping.jsp").forward(req, resp);
		} else if ("cart_update".equals(flag)) {
			System.out.println("进入购物车修改！");
			int id = Integer.parseInt(req.getParameter("id"));
			int count = Integer.parseInt(req.getParameter("count"));
			HttpSession session = req.getSession();
			Map<Book, Integer> cart = (Map<Book, Integer>) session
					.getAttribute("cart");
			for (Entry<Book, Integer> book : cart.entrySet()) {
				if (book.getKey().getId() == id) {
					book.setValue(count);
					break;
				}
			}
			double wholePrice = 0.00;
			if (cart != null) {
				for (Entry<Book, Integer> entry : cart.entrySet()) {
					wholePrice = wholePrice + entry.getKey().getPrice()
							* entry.getValue();
				}
			}
			req.setAttribute("wholePrice", wholePrice);
			session.setAttribute("cart", cart);
			req.getRequestDispatcher("shopping.jsp").forward(req, resp);
		}else if("cart_delete".equals(flag)){
			System.out.println("进入购物车删除");
			String id = req.getParameter("id");
			Book book = new Book();
			book = bookService.getBookById(Integer.parseInt(id));
			HttpSession session = req.getSession();
			Map<Book, Integer> cart = (Map<Book, Integer>) session
					.getAttribute("cart");
			System.out.println(cart.size());
			cart.remove(book);
			System.out.println(cart.size());
			session.setAttribute("cart", cart);
			resp.sendRedirect("cart_show.do?flag=cart_show");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
