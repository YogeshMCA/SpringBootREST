package com.yogesh.example.SpringRest;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yogesh.example.SpringRest.bean.Hello;
import com.yogesh.example.SpringRest.bean.Users;
import com.yogesh.example.SpringRest.exceptions.UserNotFoundException;
import com.yogesh.example.SpringRest.services.UserService;

@RestController
public class Controller {

	@Autowired
	UserService service;
	
	@GetMapping(path="/hello")
	public Hello getMessage(){
		return new Hello("Yogesh","MCA");
	}
	
	@GetMapping(path="/user-detail")
	public List<Users> retuenAllUsers(){
		return service.getAllUsers();
	}
	
	@GetMapping(path="/user-detail/{id}")
	public Users returnUser(@PathVariable int id){
		Users user=service.findUser(id);
		if(user==null)
			throw new UserNotFoundException("id="+id +" Not Found"); 
		return user;
	}
	
	@DeleteMapping(path="/user-detail/{id}")
	public ResponseEntity<Object> removeUser(@PathVariable int id){
		Users user=service.deleteUser(id);
		if(user==null)
			throw new UserNotFoundException("id="+id +" Not Found");
		return ResponseEntity.noContent().build();
		
	}
	
	@PostMapping(path="/user-detail")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody Users user){					//@RequestBody - get the request body and map it to user object
		Users addedUser = service.addUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()					//get the requested URI
						.path("/{id}").buildAndExpand(addedUser.getId()).toUri();		//append the id with value to the url
		return ResponseEntity.created(location).build();								// send the URI to response body header
	}
}
