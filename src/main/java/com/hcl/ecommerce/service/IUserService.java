package com.hcl.ecommerce.service;

import com.hcl.ecommerce.dto.UpdateUserDto;
import com.hcl.ecommerce.dto.UserDto;

public interface IUserService {
	String createUser(UserDto userDto);
	String updateUserProfile(Long userId, UpdateUserDto updateUserDto);
	String validateLogin(String userName, String password);
}
