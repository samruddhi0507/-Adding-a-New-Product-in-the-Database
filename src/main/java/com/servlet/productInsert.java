package com.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transaction;

import com.entity.Product;
import com.mysql.cj.Session;

/**
 * Servlet implementation class productInsert
 */
@WebServlet("/productInsert")
public class productInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
Map<String, String> dbMessages = new HashMap<String, String>();
		
		Session session = HibernateUtility.getSession();
		
		Transaction tx = session.beginTransaction();
		
		Product p1 = new Product(Integer.parseInt(request.getParameter("productId")), request.getParameter("productName"));
		session.save(p1);
		tx.commit();
		
		dbMessages.put("success", "Product inserted");
	
		request.setAttribute("dbMessages", dbMessages);
		request.getRequestDispatcher("productform.jsp").forward(request,response);
	}

	}


