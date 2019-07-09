package com.hcl.ecommerce.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dto.UpdateUserDto;
import com.hcl.ecommerce.dto.UserDto;
import com.hcl.ecommerce.exception.PasswordMismatchException;
import com.hcl.ecommerce.exception.UserNameNotSameException;
import com.hcl.ecommerce.exception.UserNotFoundException;
import com.hcl.ecommerce.model.User;
import com.hcl.ecommerce.repository.UserRepository;
import com.hcl.ecommerce.util.PasswordEncoder;

@Service
public class UserServiceImpl implements IUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public String createUser(UserDto userDto) {
		LOGGER.debug("UserServiceImpl:createUser {}{}{} ", userDto);
		User user = userRepository.findByUserName(userDto.getUserName());
		if (user != null) {
			throw new UserNameNotSameException(user.getUserName() + "User Name Already Exists");
		} else {
			if (userDto.getConfirmPasword().equals(userDto.getPassword())) {
				user = new User();
				user.setUserName(userDto.getUserName());
				user.setEmail(userDto.getEmail());
				user.setPassword(passwordEncoder.encodePassword(userDto.getPassword()));
				user.setRole(userDto.getRole());
				userRepository.save(user);
				return "User Created Successfully";
			} else {
				throw new PasswordMismatchException("password and conformpassword should be same");
			}

		}

	}

	public String updateUserProfile(Long userId, UpdateUserDto updateUserDto) {
		LOGGER.debug("UserServiceImpl:updateUserProfile {} ", updateUserDto);

		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			user.get().setEmail(updateUserDto.getEmail());
			userRepository.save(user.get());
			return "Profile updated successfuly";
		} else {
			throw new UserNotFoundException("User Not Found");
		}
	}

	public String validateLogin(String userName, String password) {
		LOGGER.debug("UserServiceImpl:validateLogin {} ", userName);
		Optional<User> user = userRepository.findByUserNameAndPassword(userName,
				passwordEncoder.encodePassword(password));
		if (user.isPresent()) {
			return "Logged in successfully.";
		} else {
			throw new UserNotFoundException("Invlid username or password");
		}
	}

}
