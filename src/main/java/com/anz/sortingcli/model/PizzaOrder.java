package com.anz.sortingcli.model;

public class PizzaOrder {
	
	private String OrderName;
	private String time;

	public PizzaOrder() {
		super();
	}
	
	public PizzaOrder(String orderName, String time) {
		super();
		OrderName = orderName;
		this.time = time;
	}

	public String getOrderName() {
		return OrderName;
	}

	public void setOrderName(String orderName) {
		OrderName = orderName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "PizzaOrder [OrderName=" + OrderName + ", time=" + time + "]";
	}
}
