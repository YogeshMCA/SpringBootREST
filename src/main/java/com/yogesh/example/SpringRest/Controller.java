package com.yogesh.example.SpringRest;
import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.yogesh.example.SpringRest.bean.Hello;
import com.yogesh.example.SpringRest.bean.Users;
import com.yogesh.example.SpringRest.exceptions.UserNotFoundException;
import com.yogesh.example.SpringRest.services.UserService;
import com.yogesh.example.SpringRest.services.UsersRepository;

@RestController
public class Controller {

	@Autowired
	UserService service;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	SimpleFilterProvider simpleFilterProvider;
	
	@Autowired
	MessageSource messageSources; //Object name should be same as bean name (here method name messageSources )
	
	@GetMapping(path="/hello")
	public Hello getMessage(){
		return new Hello("Yogesh","MCA");
	}
	
	@GetMapping(path="/user-detail")
	public List<Users> retuenAllUsers(){
		return service.getAllUsers();
	}

	@GetMapping(path="/jpa/user-detail")
	public List<Users> getAllUsers(){
		return usersRepository.findAll();
	}

	@GetMapping(path="/user-detail/{id}")
	public Users returnUser(@PathVariable int id){
		Users user=service.findUser(id);
		if(user==null)
			throw new UserNotFoundException("id="+id +" Not Found"); 
		return user;
	}
	
	/*@GetMapping(path="/user-detail/{id}")
	public MappingJacksonValue returnUser(@PathVariable int id){
		Users user=service.findUser(id);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id");
		simpleFilterProvider.addFilter("UserBean", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(user);
		mapping.setFilters(simpleFilterProvider);
		if(user==null)
			throw new UserNotFoundException("id="+id +" Not Found"); 
		return mapping;
	}*/
	
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
	/*
	//HATEOAS Concept
	@GetMapping(path="/user-detail/{id}")
	public Resource<Users> hateoasConcept(@PathVariable int id){
		Users user=service.findUser(id);
		
		if(user==null)
			throw new UserNotFoundException("id="+id +" Not Found");
		Resource<Users> resources = new Resource<Users>(user); 			// Instead of Resource<Users>, extend the user class with ResourceSupport class(has add method), then add the link as user.add(link) and return the user object
		
		ControllerLinkBuilder link = linkTo(methodOn(this.getClass()).retuenAllUsers()).slash(user.getId());
		resources.add(link.withRel("All-Users-List"));
		return resources;
	}
	*/
	@GetMapping(path="/internalization")
	public String internalizationMessage(@RequestHeader(name="Accept-Language",required=false) Locale locale) {
		return messageSources.getMessage("message.error.name", null, locale); //Based on the locale it will get the values from messages-<locale>.properties
		//return "Welcome";
	}
}
