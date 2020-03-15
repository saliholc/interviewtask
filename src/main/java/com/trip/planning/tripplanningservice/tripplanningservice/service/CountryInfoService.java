package com.trip.planning.tripplanningservice.tripplanningservice.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.trip.planning.tripplanningservice.tripplanningservice.bean.CountryBean;
import com.trip.planning.tripplanningservice.tripplanningservice.bean.NeighboursBean;

@Service
@ConfigurationProperties("app")
public class CountryInfoService implements ICountryInfoService{
	
	@Autowired
	private Environment env;
	
	@Override
	public CountryBean getCountryInfo(String name) throws UnirestException {
		String xkey = env.getProperty("x.rapidapi.key");
		String xhost = env.getProperty("x.rapidapi.host");
		String xurl = env.getProperty("x.rapid.url");
		
		HttpResponse<JsonNode> response = Unirest.get(xurl+"name/"+name)
				.header("x-rapidapi-host", xhost)
				.header("x-rapidapi-key", xkey)
				.asJson();
		JsonNode jsonNode = response.getBody();
		JSONObject jsonObject = jsonNode.getArray().getJSONObject(0);
		CountryBean country = new CountryBean();
		String neighbour = null;
		List<String> neighbours = new ArrayList<>(); 
		int responseStatus = response.getStatus();
		if (responseStatus == 200) {
		   country.setName(jsonObject.optString("name"));
		   country.setCurrency(jsonObject.optJSONArray("currencies").get(0).toString());
		   country.setShortName(jsonObject.optString("alpha3Code"));
		   for(int i=0; i<= jsonObject.optJSONArray("borders").length()-1;i++) {
			  neighbour = jsonObject.optJSONArray("borders").get(i).toString();  
		      neighbours.add(neighbour);
		   }
		   country.setBorders(neighbours);
		}
		return country;
	}
	@Override
	public NeighboursBean getNeighboursInfo(String shortName) throws UnirestException {
		String xkey = env.getProperty("x.rapidapi.key");
		String xhost = env.getProperty("x.rapidapi.host");
		String xurl = env.getProperty("x.rapid.url");
		
		HttpResponse<JsonNode> response = Unirest.get(xurl+"alpha/"+shortName)
				.header("x-rapidapi-host", xhost)
				.header("x-rapidapi-key", xkey)
				.asJson();
		NeighboursBean neighbour = new NeighboursBean();
		JsonNode jsonNode = response.getBody();
		JSONObject jsonObject = jsonNode.getArray().getJSONObject(0);
		int responseStatus = response.getStatus();
		if (responseStatus == 200) {
			neighbour.setName(jsonObject.optString("name"));
			neighbour.setShortName(jsonObject.optString("alpha3Code"));
			neighbour.setCurrency(jsonObject.optJSONArray("currencies").get(0).toString());
		}
		return neighbour;
	}

}
