package com.yogesh.example.SpringRest.bean;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class Users {
	
	private Integer id;
	@Size(min=2,message="Minimum 2 characters is required for name")
	private String name;
	@Size(min=2,message="Minimum 2 characters is required address")
	private String address;
	@Past
	private Date dob;
	
	protected Users(){
		
	}
	public Users(Integer id,String name,String address,Date dob){
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.dob = dob;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	

}
