package com.trip.planning.tripplanningservice.tripplanningservice.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.trip.planning.tripplanningservice.tripplanningservice.bean.CurrencyConversionBean;


public interface ICurrencyExchangeService {
	
	public CurrencyConversionBean getExchangeAmount(String from, String to, long amount) throws JsonParseException, IOException;

}
