package com.raj.mongodb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.raj.mongodb.MongoRepository.UserRepository;
import com.raj.mongodb.document.Users;

import io.swagger.models.Model;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/v1")
public class Home {

	@Autowired
	private UserRepository userRepository;

	@PostMapping(value = "/save")
	public void save(@RequestBody Users user) {
		userRepository.save(user);
	}
	
	@RequestMapping(value="/update/{id}",method = RequestMethod.POST)
	public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Users user) {

	    Optional<Users> useOptional = userRepository.findById(Integer.parseInt(id));
	    if (!useOptional.isPresent())
	      return ResponseEntity.notFound().build();
	    user.setId(Integer.parseInt(id));
	    userRepository.save(user);
	    return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "/delete/{id}")
	public void  delete(@PathVariable("id") String id) {
		userRepository.deleteById(Integer.parseInt(id));
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Iterable list(Model model) {
		Iterable productList = userRepository.findAll();
		return productList;
	}
	
	
}
