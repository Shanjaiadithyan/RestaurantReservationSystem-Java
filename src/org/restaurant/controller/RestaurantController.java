package org.restaurant.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalTime;

import javax.servlet.*;
import javax.servlet.http.*;

import org.restaurant.dao.RestaurantDao;
import org.restaurant.model.Customer;
import org.restaurant.model.Reservation;
import org.restaurant.model.Restaurant;

/**
 * Servlet implementation class RestaurantController
 */
//@WebServlet("/RestaurantController")
public class RestaurantController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RestaurantController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		
		int table_capacity=8;
		int reqd_tables=0;
		boolean reservation_status=false;
		int available_tables = 0;
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    int seats = Integer.parseInt(request.getParameter("seats"));
	    String day = request.getParameter("day");
	    LocalTime resv_time=LocalTime.parse(request.getParameter("time"));

	    
	    
	    //Calculate required tables
	    if(seats<=table_capacity && seats!=0){
	    	reqd_tables=1;
	    }
	    else{
	    	reqd_tables = (int) Math.ceil((float)seats/(float)table_capacity);
	    }
	    
	    
	    //check availability on that particular day
	   try {
			available_tables = RestaurantDao.getTablesOnDay(day);
		} 
	    catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    
	    if(available_tables>=reqd_tables)
	    	reservation_status=true;
	    else
	    	reservation_status=false;
	    
	    
	    
	    
	    //if reservation can be done, store data in reservation in db, decrement avail table from db on that day
	    if(reservation_status){
	    	
	    	Customer c = new Customer();
			try {
				c = RestaurantDao.getCustomerByName(name);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	
		    Restaurant r = new Restaurant();
		    Reservation resv = new Reservation();
		    resv.setRestaurant(r);
		    resv.setCustomer(c);
		    resv.setTables_reqd(reqd_tables);
		    resv.setReservation_day(day);
		    resv.setTime(resv_time);
		    
			try {
				RestaurantDao.createReservation(resv);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				RestaurantDao.updateAvailTables(day,available_tables-reqd_tables);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("status", reservation_status);
			request.getRequestDispatcher("status.jsp").forward(request, response);
	    }
	    else{
	    	request.setAttribute("status", reservation_status);
			request.getRequestDispatcher("status.jsp").forward(request, response);
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
