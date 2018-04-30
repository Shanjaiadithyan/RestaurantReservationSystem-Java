package org.restaurant.model;

import java.time.LocalTime;

public class Reservation {
	
	Restaurant r;
	Customer c;
	private int tables_reqd;
	private String reservation_day;
	private LocalTime time;
	public Restaurant getRestaurant() {
		return r;
	}
	public void setRestaurant(Restaurant r) {
		this.r = r;
	}
	public Customer getCustomer() {
		return c;
	}
	public void setCustomer(Customer c) {
		this.c = c;
	}
	public int getTables_reqd() {
		return tables_reqd;
	}
	public void setTables_reqd(int tables_reqd) {
		this.tables_reqd = tables_reqd;
	}
	public String getReservation_day() {
		return reservation_day;
	}
	public void setReservation_day(String reservation_day) {
		this.reservation_day = reservation_day;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}

	
}
