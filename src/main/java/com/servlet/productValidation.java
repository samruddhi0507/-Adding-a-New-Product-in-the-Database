package com.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Product;
import com.mysql.cj.Session;

import antlr.collections.List;

/**
 * Servlet implementation class productValidation
 */
@WebServlet("/productValidation")
public class productValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productValidation() {
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
		Map<String, String> errors = new HashMap<String, String>();
		String productId = request.getParameter("productId");
		
		if(request.getParameter("productName").length() == 0) {
			errors.put("nullProdName","Enter a product name");
		}
		try {
			Integer.parseInt(productId);
			Session session = HibernateUtility.getSession();
			List<Product> data = session.createQuery("from Product").list();
			for(Product ob: data) {
				if(String.valueOf(ob.getProductId()).compareTo(productId) == 0) {
					errors.put("dupeProductId", "Product ID already exists");
				}
			}
		}catch(NumberFormatException e) {
			errors.put("notInt", "Invalid product ID");
		}
		if(errors.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("productInsert");
			rd.forward(request,response);
		}else {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("productform.jsp").forward(request,response);
		}
	}
	}


