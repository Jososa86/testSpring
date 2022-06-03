package com.proyecto.zonegame.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.zonegame.model.User;
import com.proyecto.zonegame.service.UserService;

@RestController 
@RequestMapping("/api/user/")
@CrossOrigin("*")
public class UserREST {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
	private ResponseEntity<User> save (@RequestBody User user){
		User temporal = userService.create(user);
		
		try {
			return ResponseEntity.created(new URI("/api/user"+ temporal.getId() )).body(temporal);			
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping
	private ResponseEntity<List<User>> listarTodosLosUsuarios (){
		return ResponseEntity.ok(userService.getAllUser());
	}
	
	/*@GetMapping("/delete/{id}")
	private ResponseEntity<Void> delete (@RequestBody User user){
		userService.delete(user);
		return ResponseEntity.ok().build();
	}*/
	
	@GetMapping (value = "/All")
	public List<User> getAll(){ 
		return userService.getAllUser();
	}
	
	@Transactional
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long userId) {
		
		Optional<User> oUser = userService.search(userId);		
		if (!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		} 	
		
		userService.delete(userId);
		return ResponseEntity.ok().build();
		}
}
