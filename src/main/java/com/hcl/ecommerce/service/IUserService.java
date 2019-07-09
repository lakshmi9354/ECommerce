package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.dto.UpdateUserDto;
import com.hcl.ecommerce.dto.UserDetailsDto;
import com.hcl.ecommerce.dto.UserDto;

public interface IUserService {
	String createUser(UserDto userDto);
	String updateUserProfile(Long userId, UpdateUserDto updateUserDto);
	String validateLogin(String userName, String password);
	List<UserDetailsDto> users(String role);
}
