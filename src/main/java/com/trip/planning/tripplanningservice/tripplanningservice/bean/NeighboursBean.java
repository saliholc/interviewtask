package com.trip.planning.tripplanningservice.tripplanningservice.bean;

public class NeighboursBean {
	private String name;
	private String shortName;
	private String currency;
	
	public NeighboursBean() {
	}

	public NeighboursBean(String name, String shortName, String currency) {
		this.name = name;
		this.shortName = shortName;
		this.currency = currency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
 
	
	 
	

}
