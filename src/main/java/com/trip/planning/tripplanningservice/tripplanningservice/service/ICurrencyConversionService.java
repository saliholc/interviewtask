package com.trip.planning.tripplanningservice.tripplanningservice.service;

import com.trip.planning.tripplanningservice.tripplanningservice.bean.CurrencyConversionBean;

public interface ICurrencyConversionService {
	
	public CurrencyConversionBean getCurrencyConversion(String from,String to, String amount); 

}
