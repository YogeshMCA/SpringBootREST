package com.yogesh.example.SpringRest.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.yogesh.example.SpringRest.bean.Users;

@Component
public class UserService {
	
	@Autowired
	UsersRepository usersRepository;
	
	@Resource(name="redisTemplate")
	private RedisTemplate<String, Users> redisTemplate;
	@Resource(name="redisTemplate")
	private HashOperations<String,Object,Users> hashOperation;
			
	private static int count = 3;
	private static List<Users> userLst = new ArrayList<Users>();
	static{
		userLst.add(new Users(1,"Yogesh","Erode",new Date()));
		userLst.add(new Users(2,"Ram","Salem",new Date()));
		userLst.add(new Users(3,"Kumar","Ooty",new Date()));
	}
	
	public String expireRedis(){
		if(this.redisTemplate.expire("TABLE_USER", 1, TimeUnit.SECONDS))
			return "Cleared";
		else
			return "Not Cleared";
	}
	public List<Users> getAllUsers(){
		System.out.println(redisTemplate.hasKey("TABLE_USER"));
		if(!redisTemplate.hasKey("TABLE_USER")){
			List<Users> lst = usersRepository.findAll();
			lst.forEach(user->hashOperation.put("TABLE_USER","USERS_"+user.getId(),user));
		}
		return hashOperation.values("TABLE_USER");
		
		//return userLst;
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
