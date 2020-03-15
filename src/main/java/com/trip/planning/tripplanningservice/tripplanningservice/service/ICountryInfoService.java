package com.trip.planning.tripplanningservice.tripplanningservice.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.trip.planning.tripplanningservice.tripplanningservice.bean.CountryBean;
import com.trip.planning.tripplanningservice.tripplanningservice.bean.NeighboursBean;

public interface ICountryInfoService {
	
	public CountryBean getCountryInfo(String name) throws UnirestException;
	
	public NeighboursBean getNeighboursInfo(String shortName) throws UnirestException;

}
