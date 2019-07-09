package com.hcl.ecommerce.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.dto.UpdateUserDto;
import com.hcl.ecommerce.dto.UserDetailsDto;
import com.hcl.ecommerce.dto.UserDto;
import com.hcl.ecommerce.service.IUserService;

@RestController
@RequestMapping("/api")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	IUserService userService;

	@PostMapping("/addUser")
	public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
		LOGGER.debug("UserController:createUser {}{}{} ", userDto);
		String response = userService.createUser(userDto);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	@PutMapping("/updateUser")
	public ResponseEntity<String> updateUserProfile(Long userId,UpdateUserDto updateUserDto){
		LOGGER.debug("UserController:updateUserProfile {} ", updateUserDto);
		String response = userService.updateUserProfile(userId, updateUserDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PutMapping("/login")
	public ResponseEntity<String> validateLogin(String userName, String password) {
		LOGGER.debug("UserController:validateLogin {} ", userName);
		String response = userService.validateLogin(userName, password);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("/users/{role}")
	public ResponseEntity<List<UserDetailsDto>> users(@PathVariable("role") String role){
		LOGGER.debug("UserController:users {} ", role);
		List<UserDetailsDto> userDetailsDto = userService.users(role);
		return new ResponseEntity<>(userDetailsDto,HttpStatus.ACCEPTED);
	}
}
