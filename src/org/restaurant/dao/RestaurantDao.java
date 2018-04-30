package org.restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

import org.restaurant.model.Customer;
import org.restaurant.model.Reservation;
import org.restaurant.model.Restaurant;

public class RestaurantDao {
	
	static Connection conn = null;
	public static void getConnection()
	{
		try
		{
			//System.out.println("1");
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("2");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantreservation","root","root");	
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}	
		
	}
	public static int getTablesOnDay(String day) throws SQLException {
		// TODO Auto-generated method stub
		getConnection();
		String query = "Select available_tables from day_availability where day_of_week=?;";
		PreparedStatement  pst;
		pst=conn.prepareStatement(query);
		pst.setString(1, day);
		ResultSet rs=pst.executeQuery();
		rs.next();
		int available_tables = rs.getInt("available_tables");
		pst.close();
		conn.close();
		return available_tables;
	}
	
	public static int createCustomer(Customer c) throws SQLException {
		// TODO Auto-generated method stub
		getConnection();
		String query = "Insert into customer(cust_name,pswd) values(?,?);";
		PreparedStatement  pst;
		pst=conn.prepareStatement(query);
		pst.setString(1, c.getName());
		pst.setString(2, c.getPswd());
		pst.execute();
		ResultSet rs = pst.getGeneratedKeys();
		int generatedKey = 0;
		if (rs.next()) {
		    generatedKey = rs.getInt(1);
		}
		pst.close();
		conn.close();
		return generatedKey;
	}
	
	public static void createReservation(Reservation resv) throws SQLException {
		// TODO Auto-generated method stub
		getConnection();
		String query = "Insert into reservation(restaurant_id,customer_id,tables_reqd,reservation_day,reservation_time) values(?,?,?,?,?);";
		PreparedStatement  pst;
		pst=conn.prepareStatement(query);
		pst.setInt(1, resv.getRestaurant().getId());
		pst.setInt(2, resv.getCustomer().getId());
		pst.setInt(3, resv.getTables_reqd());
		pst.setString(4, resv.getReservation_day());
		pst.setTime(5, java.sql.Time.valueOf(resv.getTime()));
		pst.execute();
		pst.close();
		conn.close();
		
	}
	public static void updateAvailTables(String day, int updated_table_count) throws SQLException {
		// TODO Auto-generated method stub
		getConnection();
		String query = "Update day_availability set available_tables=? where day_of_week=?;";
		PreparedStatement  pst;
		pst=conn.prepareStatement(query);
		pst.setInt(1, updated_table_count);
		pst.setString(2, day);
		pst.executeUpdate();
		pst.close();
		conn.close();
		
	}
	
	public static ArrayList<Reservation> getReservations() throws SQLException {
		// TODO Auto-generated method stub
		getConnection();
		ArrayList<Reservation> resvList = new ArrayList<Reservation>();
		String query = "Select * from reservation;";
		PreparedStatement  pst;
		pst=conn.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			int reservation_id = rs.getInt("reservation_id");
			int restaurant_id = rs.getInt("restaurant_id");
			int customer_id = rs.getInt("customer_id");
			int tables_reqd = rs.getInt("tables_reqd");
			String reservation_day = rs.getString("reservation_day");
			LocalTime reservation_time = rs.getTime("reservation_time").toLocalTime();
			//LocalTime reservation_time = LocalTime.parse((CharSequence) rs.getTime("reservation_time"));
			
			Restaurant r = new Restaurant();
			Customer c = getCustomerFromId(customer_id);
			
			Reservation resv = new Reservation();
			resv.setRestaurant(r);
			resv.setCustomer(c);
			resv.setTables_reqd(tables_reqd);
			resv.setReservation_day(reservation_day);
			resv.setTime(reservation_time);
			
			resvList.add(resv);
		}
		pst.close();
		conn.close();
		return resvList;
		
	}
	
	
	private static Customer getCustomerFromId(int customer_id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "Select * from customer where cust_id=?;";
		PreparedStatement  pst;
		pst=conn.prepareStatement(query);
		pst.setInt(1, customer_id);
		ResultSet rs=pst.executeQuery();
		rs.next();
		int cust_id = rs.getInt("cust_id");
		String cust_name = rs.getString("cust_name");
		String pswd = rs.getString("pswd");
		Customer c = new Customer();
		c.setId(cust_id);
		c.setName(cust_name);
		c.setPswd(pswd);
		return c;
	}
	
	public static boolean authenticateCustomer(String name, String pswd) throws SQLException {
		// TODO Auto-generated method stub
		getConnection();
		String query = "Select pswd from customer where cust_name=?;";
		PreparedStatement  pst;
		pst=conn.prepareStatement(query);
		pst.setString(1, name);
		ResultSet rs=pst.executeQuery();
		rs.next();
		String password = rs.getString("pswd");
		pst.close();
		conn.close();
		if(password.equals(pswd)){
			return true;
		}
		else{
			return false;
		}
		
	}
	public static Customer getCustomerByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		getConnection();
		String query = "Select * from customer where cust_name=?;";
		PreparedStatement  pst;
		pst=conn.prepareStatement(query);
		pst.setString(1, name);
		ResultSet rs=pst.executeQuery();
		rs.next();
		int cust_id = rs.getInt("cust_id");
		String cust_name = rs.getString("cust_name");
		String pswd = rs.getString("pswd");
		Customer c = new Customer();
		c.setId(cust_id);
		c.setName(cust_name);
		c.setPswd(pswd);
		pst.close();
		conn.close();
		return c;
	}
	public static boolean checkUsername(String name) throws SQLException {
		// TODO Auto-generated method stub
		getConnection();
		boolean userExist=false;
		String query = "Select cust_name from customer;";
		PreparedStatement  pst;
		pst=conn.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			if(rs.getString("cust_name").equals(name)){
				userExist=true;
			}
		}
		return userExist;
	}
	

}
