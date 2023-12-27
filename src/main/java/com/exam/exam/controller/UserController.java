package com.exam.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.exam.exception.UserNotFoundException;
import com.exam.exam.model.User;
import com.exam.exam.repository.UserReposistory;

@RestController

public class UserController {
	
	@Autowired
	private UserReposistory userReposistory;
	
	@PostMapping("/user")	//Create 
	User newuser (@RequestBody User newuser) {
		System.out.println("New User Created");
		return userReposistory.save(newuser);
	}
	
	@GetMapping("/users")	//readAll
	List<User> getAllUser(){
		System.out.println("All Users Are Fetched");
		return userReposistory.findAll();
	}
	
	
	
	@GetMapping("/users/{id}")	//ReadById
	User getUserById(@PathVariable Long id){
		System.out.println("User is Fetched with id :"+id);
		return userReposistory.findById(id)
				.orElseThrow(()-> new UserNotFoundException("User not exist with id: " + id));
	}
	
	
	
	@PutMapping("/user/{id}")	//Update
	User updateUser (@RequestBody User newUser,@PathVariable Long id)
	{
		return userReposistory.findById(id)
				.map(user -> {
					user.setName(newUser.getName());
					user.setEmail(newUser.getEmail());
					user.setPass(newUser.getPass());
					return userReposistory.save(user);
				}).orElseThrow(()-> new UserNotFoundException("User not exist with id: " + id));
	}
	
	
	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable Long id) {
		userReposistory.deleteById(id);
		return "Deleted with id: "+ id;
	}

	
}
