package com.yogesh.example.SpringRest.bean;

import java.util.Date;

<<<<<<< HEAD
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
=======
>>>>>>> 74eb4a21ab0cda14a53a92735e1f83490a43175e
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
//@JsonIgnoreProperties({"address","id"})//Class level - mention all fields at one place
@Entity
public class Users {
	@Id
	@GeneratedValue
	private Integer id;
	@Size(min=2,message="message.error.name")
	private String name;
	
	@Size(min=2,message="message.error.address")
	private String address;
	//@JsonIgnore //This will ignore this field in the response
=======
public class Users {
	
	private Integer id;
	@Size(min=2,message="message.error.name")
	private String name;
	@Size(min=2,message="message.error.address")
	private String address;
>>>>>>> 74eb4a21ab0cda14a53a92735e1f83490a43175e
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
