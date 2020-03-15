package com.trip.planning.tripplanningservice.tripplanningservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.trip.planning.tripplanningservice.tripplanningservice.bean.CountryBean;
import com.trip.planning.tripplanningservice.tripplanningservice.bean.CurrencyConversionBean;
import com.trip.planning.tripplanningservice.tripplanningservice.bean.NeighboursBean;
import com.trip.planning.tripplanningservice.tripplanningservice.service.CurrencyConversionService;
import com.trip.planning.tripplanningservice.tripplanningservice.service.ICountryInfoService;
import com.trip.planning.tripplanningservice.tripplanningservice.service.ICurrencyConversionService;
import com.trip.planning.tripplanningservice.tripplanningservice.service.ICurrencyExchangeService;


@RestController
@RequestMapping("/trip")
public class TripPlanningController {
    
	@Autowired
	ICurrencyConversionService currencyConversionService;
	
	@Autowired
	ICountryInfoService countryInfoService;
	

	@GetMapping("/start/{homeCountry}/budgetPerCountry/{budgetPerCountry}/totalBudget/{totalBudget}/currency/{currency}")
	public List<String> getTripInfo(@PathVariable String homeCountry, @PathVariable long budgetPerCountry,
			@PathVariable long totalBudget, @PathVariable String currency) {
		List<String> infoLines = new ArrayList<>();
		List<NeighboursBean> neighbous = getNeighbourCountries(homeCountry);
		long  visitTimes = calculateExactVisitTimes(totalBudget, budgetPerCountry, neighbous.size());
		long remainingAmont = calculateRemainingAmount(totalBudget, budgetPerCountry, neighbous.size(), visitTimes);
		String info = homeCountry + " has " + neighbous.size() + " neighbour countries (";
		for(NeighboursBean country : neighbous) {
			info = info + country.getName()+","; 
		}
		info = info + ")";
		info = info.replace(",)", ")");
		info = info + " and Angel can travel around them "+visitTimes+" times.";
		infoLines.add(info);
		info = "He will have "+remainingAmont +" "+currency +" leftover.";
		infoLines.add(info);
		CurrencyConversionBean conversionBean = new CurrencyConversionBean();
		String formatDouble = "";
		for(NeighboursBean neighbour : neighbous) {
		 conversionBean =  currencyConversionService.getCurrencyConversion(currency, neighbour.getCurrency(), new Long(visitTimes*budgetPerCountry).toString());
		 formatDouble = String.format("%.2f", conversionBean.getSpendingAmount());
		 info = "for "+ neighbour.getName()+" he will need to buy "+formatDouble+" "+neighbour.getCurrency();
		 infoLines.add(info);
		}
		return infoLines;
	}

	private long calculateExactVisitTimes(long totalBudget, long budgetPerCountry, long numberOfNeighbours) {
		long totalAmount = budgetPerCountry * numberOfNeighbours;
		long visitTimes = 0L;
		if(totalBudget != 0L && totalAmount != 0L) {
			visitTimes = (totalBudget / totalAmount);	
		} 
		return visitTimes;
	}

	private long calculateRemainingAmount(long totalBudget, long budgetPerCountry, long numberOfNeighbours,
			long visitTimes) {
		long remainingAmount = totalBudget - (numberOfNeighbours * visitTimes * budgetPerCountry);
		return remainingAmount;
	}

	private List<NeighboursBean> getNeighbourCountries(String mainLand) {
		List<NeighboursBean> neigbours = new ArrayList<>();
		NeighboursBean neighbour = new NeighboursBean();
		try {
			CountryBean countryInfo = countryInfoService.getCountryInfo(mainLand);
			if(countryInfo != null) {
			  for(String country : countryInfo.getBorders()) {
				  neighbour = countryInfoService.getNeighboursInfo(country);	  
				  neigbours.add(neighbour);
			  }
			}
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return neigbours;
	}
	

}
