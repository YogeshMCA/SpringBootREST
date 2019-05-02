package com.yogesh.example.SpringRest.services;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix="exchange-service")
public class ExchangeRateProperties {
	private String inr;
	private String euro;
	private String defaultRate;
	
	public String getInr() {
		return inr;
	}
	public void setInr(String inr) {
		this.inr = inr;
	}
	public String getEuro() {
		return euro;
	}
	public void setEuro(String euro) {
		this.euro = euro;
	}
	public String getDefaultRate() {
		return defaultRate;
	}
	public void setDefaultRate(String defaultRate) {
		this.defaultRate = defaultRate;
	}
	
	

}
