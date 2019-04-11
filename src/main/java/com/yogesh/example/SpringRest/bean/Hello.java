package com.yogesh.example.SpringRest.bean;

public class Hello {

	private String name;
	private String deg;
	
	public Hello(String name,String deg){
		this.name=name;
		this.deg=deg;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeg() {
		return deg;
	}
	public void setDeg(String deg) {
		this.deg = deg;
	}
	
	
}
