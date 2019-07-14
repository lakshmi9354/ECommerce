package com.hcl.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dto.UpdateUserDto;
import com.hcl.ecommerce.dto.UserDetailsDto;
import com.hcl.ecommerce.dto.UserDto;
import com.hcl.ecommerce.exception.EmailValidationException;
import com.hcl.ecommerce.exception.MobileNumberValidationException;
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
			if (userDto.getConfirmPasword().equals(userDto.getPassword()))
				if (emailValidation(userDto.getEmail()))
					if (mobileNumberValidation(userDto.getMobile())) {
						user = new User();
						userDto.setPassword(passwordEncoder.encodePassword(userDto.getPassword()));
						BeanUtils.copyProperties(userDto, user);
						userRepository.save(user);
						return "User Created Successfully";
					} else
						throw new MobileNumberValidationException("Mobile Number must and should numbers between 0-9");
				else
					throw new EmailValidationException("Email Id must and should ex@gmail.com");
			else
				throw new PasswordMismatchException("password and conformpassword should be same");
		}
	}

	public String updateUserProfile(Long userId, UpdateUserDto updateUserDto) {
		LOGGER.debug("UserServiceImpl:updateUserProfile {} ", updateUserDto);

		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			if (emailValidation(updateUserDto.getEmail())) {
				BeanUtils.copyProperties(updateUserDto, user.get());
				userRepository.save(user.get());
				return "Profile updated successfuly";
			} else
				throw new EmailValidationException("Email Id must and should ex@gmail.com");
		} else
			throw new UserNotFoundException("User Not Found");
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

	public List<UserDetailsDto> users(String role) {
		LOGGER.debug("UserServiceImpl:users {} ", role);
		List<User> users = userRepository.findByRole(role);
		List<UserDetailsDto> userDetailsDtos = new ArrayList<>();
		for (User user : users) {
			UserDetailsDto userDetailsDto = new UserDetailsDto();
			BeanUtils.copyProperties(user, userDetailsDto);
			userDetailsDtos.add(userDetailsDto);
		}
		return userDetailsDtos;
	}

	static boolean emailValidation(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	static boolean mobileNumberValidation(String s) {
		Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
		Matcher m = p.matcher(s);
		return (m.find() && m.group().equals(s));
	}
}
