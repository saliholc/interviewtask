package com.trip.planning.tripplanningservice.tripplanningservice.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.trip.planning.tripplanningservice.tripplanningservice.bean.CurrencyConversionBean;

@Service
public class CurrencyConversionService implements ICurrencyConversionService {

	@Autowired
	ICurrencyExchangeService currencyExchangeService;

	public CurrencyConversionBean getCurrencyConversion(String from, String to, String amount) {
		try {
			return currencyExchangeService.getExchangeAmount(from, to, new Long(amount));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
