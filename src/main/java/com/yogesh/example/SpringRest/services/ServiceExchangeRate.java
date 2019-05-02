package com.yogesh.example.SpringRest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceExchangeRate {

	@Autowired
	private ExchangeRateProperties exchangeRateProperties;
	
	public String getRate(String to){
		if(to.equalsIgnoreCase("INR"))
			return exchangeRateProperties.getInr();
		else if(to.equalsIgnoreCase("EURO"))
			return exchangeRateProperties.getEuro();
		else
			return exchangeRateProperties.getDefaultRate();
	
	}
}
