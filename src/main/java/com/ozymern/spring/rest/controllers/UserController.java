package com.ozymern.spring.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ozymern.spring.rest.entity.User;
import com.ozymern.spring.rest.services.UserService;

@RestController
@RequestMapping(UserController.URI_BASE)
public class UserController {

	public static final String URI_BASE = "/api/v1";

	@Autowired
	@Qualifier("userServiceImp")
	private UserService userService;

	@GetMapping("/users")
	public List<User> listAllUser() {

		return userService.listAllUser();

	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {

		User user = userService.getUserById(id);

		return (user != null) ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {

		return userService.addUser(user);

	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {

		if (userService.updateUser(user, id) != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Integer id) {

		User userDelete = userService.getUserById(id);

		if (userDelete != null) {

			userService.deleteUser(userService.getUserById(id));
			return new ResponseEntity<>(userDelete, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
