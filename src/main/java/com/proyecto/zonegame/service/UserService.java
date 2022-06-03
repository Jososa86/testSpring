package com.proyecto.zonegame.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.zonegame.model.User;
import com.proyecto.zonegame.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User create (User user) {
		return userRepository.save(user);
	}
	
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	
	public void delete(Long id){
		userRepository.deleteById(id);
	}
	
	public Optional<User> search (Long id) {
		return userRepository.findById(id);
		}
}