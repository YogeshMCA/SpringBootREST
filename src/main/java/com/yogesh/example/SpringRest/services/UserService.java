package com.yogesh.example.SpringRest.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.h2.engine.User;
import org.springframework.stereotype.Component;
import com.yogesh.example.SpringRest.bean.Users;

@Component
public class UserService {
	
	private static int count = 3;
	private static List<Users> userLst = new ArrayList<Users>();
	static{
		userLst.add(new Users(1,"Yogesh","Erode",new Date()));
		userLst.add(new Users(2,"Ram","Salem",new Date()));
		userLst.add(new Users(3,"Kumar","Ooty",new Date()));
	}
	
	public List<Users> getAllUsers(){
		return userLst;
	}
	
	public Users findUser(Integer id){
		for(Users user:userLst){
			if(user.getId()==id){
				return user;
			}
		}
		return null;
	}
	
	public Users deleteUser(Integer id){
		Users user = this.findUser(id);
		if(user!=null)
			userLst.remove(user);
		return user;
	}
	
	public Users addUser(Users user){
		if(user.getId()==null){
			user.setId(++count);
			userLst.add(user);
		}
		return user;
	}

}
