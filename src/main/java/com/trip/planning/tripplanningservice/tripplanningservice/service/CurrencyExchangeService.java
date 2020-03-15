package com.trip.planning.tripplanningservice.tripplanningservice.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.trip.planning.tripplanningservice.tripplanningservice.bean.CurrencyConversionBean;

@Service
@ConfigurationProperties("app")
public class CurrencyExchangeService implements ICurrencyExchangeService{
	
	@Autowired
	private Environment env;


	 @Override
	  public CurrencyConversionBean getExchangeAmount(String from, String to, long amount) throws JsonParseException, IOException {
		  CurrencyConversionBean rate = new CurrencyConversionBean();
		  String fxUrl = env.getProperty("fixer.url");
		  String fxKey = env.getProperty("fixer.key");
		  String url =  fxUrl+fxKey+"&symbols="+to+"&format=1";
   	      String response = new RestTemplate().getForObject(url, String.class);
		  
		  
		   JsonFactory factory = new JsonFactory();
		   JsonParser parser = factory.createParser(response);
		   
		   while(!parser.isClosed()) {
			   JsonToken jsonToken = parser.nextToken();
			   
			   if(JsonToken.FIELD_NAME.equals(jsonToken)) {
				  String fieldName = parser.getCurrentName();
				  jsonToken = parser.nextToken();
				  if(to.equals(fieldName)) {
					  rate.setSpendingAmount(parser.getValueAsDouble() * amount);
				  }
				  
			   }
		   }
		   return rate;
	  }
	

}
