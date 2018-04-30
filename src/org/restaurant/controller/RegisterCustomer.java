package org.restaurant.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.restaurant.dao.RestaurantDao;
import org.restaurant.model.Customer;

/**
 * Servlet implementation class RegisterCustomer
 */
@WebServlet("/RegisterCustomer")
public class RegisterCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String pswd = request.getParameter("pswd");
		boolean userExist=false;
		
		//check if username already exist
		try {
			userExist = RestaurantDao.checkUsername(name);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(userExist){
			request.getRequestDispatcher("registerFail.jsp").forward(request, response);
		}
		else{
		Customer c = new Customer();
	    c.setName(name);
	    c.setPswd(pswd);
	    
	    try {
			c.setId(RestaurantDao.createCustomer(c));
		} 
	    catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    request.setAttribute("status", "registered");
		request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
