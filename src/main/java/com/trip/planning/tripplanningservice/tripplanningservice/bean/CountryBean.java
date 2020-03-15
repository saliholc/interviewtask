package com.trip.planning.tripplanningservice.tripplanningservice.bean;

import java.util.List;

public class CountryBean {
	private String name;
	private String shortName;
	private String currency;
	private List<String> borders;
	
	public CountryBean() {
	}

	public CountryBean(String name, String shortName, String currency, List<String> borders) {
		this.name = name;
		this.shortName = shortName;
		this.currency = currency;
		this.borders = borders;
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

	public List<String> getBorders() {
		return borders;
	}

	public void setBorders(List<String> borders) {
		this.borders = borders;
	}
	

}
