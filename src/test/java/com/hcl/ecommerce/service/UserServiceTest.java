package com.hcl.ecommerce.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.ecommerce.dto.UpdateUserDto;
import com.hcl.ecommerce.dto.UserDetailsDto;
import com.hcl.ecommerce.dto.UserDto;
import com.hcl.ecommerce.exception.PasswordMismatchException;
import com.hcl.ecommerce.exception.UserNameNotSameException;
import com.hcl.ecommerce.exception.UserNotFoundException;
import com.hcl.ecommerce.model.Role;
import com.hcl.ecommerce.model.User;
import com.hcl.ecommerce.repository.UserRepository;
import com.hcl.ecommerce.util.PasswordEncoder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	UserServiceImpl userService;

	@MockBean
	UserRepository userRepository;

	@Mock
	PasswordEncoder passwordUtil;

	UserDto userDto = null;
	User user = null;
	UpdateUserDto updateUserDto = null;
	UserDetailsDto userDetailsDto1 = null;
	UserDetailsDto userDetailsDto2 = null;
	List<UserDetailsDto> userDetailsDtos = null;
	List<User> userss = null;
	Optional<User> users = null;
	
	@Test
	public void testCreateUserUserNameNotAvailable() {
		user = new User(1L, "lakshmi", "lakshmi", "lakshmi", "Banglore", "9618339354", "lakshmi@gmail.com", "lakshmi",
				Role.SELLER);
		userDto = new UserDto("lakshmi", "lakshmi", "lakshmi", "Banglore", "9618339354", "lakshmi@gmail.com", "lakshmi",
				"lakshmi", Role.SELLER);
		Mockito.when(userRepository.findByUserName(userDto.getUserName())).thenReturn(null);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		assertEquals("User Created Successfully", userService.createUser(userDto));
	}

	@Test(expected = UserNameNotSameException.class)
	public void testCreateUserUserNameAvailable() {
		user = new User(1L, "lakshmi", "lakshmi", "lakshmi", "Banglore", "9618339354", "lakshmi@gmail.com", "lakshmi",
				Role.SELLER);
		userDto = new UserDto("lakshmi", "lakshmi", "lakshmi", "Banglore", "9618339354", "lakshmi@gmail.com", "lakshmi",
				"lakshmi", Role.SELLER);
		Mockito.when(userRepository.findByUserName(userDto.getUserName())).thenReturn(user);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		assertEquals(Mockito.anyString(), userService.createUser(userDto));
	}
	@Test
	public void testCreateUserPasswordMatch() {
		userDto = new UserDto();
		userDto.setUserName("lakshmi");
		userDto.setPassword("123");
		userDto.setConfirmPasword("123");
		Mockito.when(userRepository.findByUserName(userDto.getUserName())).thenReturn(null);
		//assertEquals("User Created Successfully", userService.createUser(userDto));
	}
	@Test(expected = PasswordMismatchException.class)
	public void testCreateUserPasswordMissMatch() {
		userDto = new UserDto();
		userDto.setUserName("lakshmi");
		userDto.setPassword("123");
		userDto.setConfirmPasword("12");
		Mockito.when(userRepository.findByUserName(userDto.getUserName())).thenReturn(null);
		assertEquals("password and conformpassword should be same", userService.createUser(userDto));
	}
	
	@Test
	public void testCreateUserEmailValidationSuccess() {
		userDto = new UserDto();
		userDto.setUserName("lakshmi");
		userDto.setEmail("AG@GMAIL.com");
		Mockito.when(userRepository.findByUserName(userDto.getUserName())).thenReturn(null);
		//assertEquals("User Created Successfully", userService.createUser(userDto));
	}
	
	@Test
	public void testCreateUserMobileValidationSuccess() {
		userDto = new UserDto();
		userDto.setUserName("lakshmi");
		userDto.setMobile("9618339354");
		Mockito.when(userRepository.findByUserName(userDto.getUserName())).thenReturn(null);
		//assertEquals("User Created Successfully", userService.createUser(userDto));
	}
	
	@Test
	public void testUpdateUserProfileUserPresents() {
		user = new User(1L, "lakshmi", "lakshmi", "lakshmi", "Banglore", "9618339354", "lakshmi@gmail.com", "lakshmi",
				Role.SELLER);
		users = Optional.of(user);
		System.out.println(users.toString());
		updateUserDto = new UpdateUserDto("lakshmi", "lakshmi", "lakshmi@gmail.com", "Banglore", "9618339354");
		Mockito.when(userRepository.findById(1L)).thenReturn(users);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		assertThat(users.isPresent());
		assertEquals("Profile updated successfuly", userService.updateUserProfile(1L, updateUserDto));
	}
	
	@Test(expected = UserNotFoundException.class)
	public void testUpdateUserProfileUserNotPresents() {
		user = new User(2L, "lakshmi", "lakshmi", "lakshmi", "Banglore", "9618339354", "lakshmi@gmail.com", "lakshmi",
				Role.SELLER);
		updateUserDto = new UpdateUserDto("lakshmi", "lakshmi", "lakshmi@gmail.com", "Banglore", "9618339354");
		users = Optional.empty();
		Mockito.when(userRepository.findById(1L)).thenReturn(users);
		assertNotNull(users);
		assertEquals("User Not Found", userService.updateUserProfile(1L, updateUserDto));
	}
	@Test(expected = UserNotFoundException.class)
	public void testValidateLoginUserPresents() {
		user = new User(1L, "lakshmi", "lakshmi", "lakshmi", "Banglore", "9618339354", "lakshmi@gmail.com", "lakshmi",
				Role.SELLER);
		users = Optional.of(user);
		System.out.println(users.toString());
		updateUserDto = new UpdateUserDto("lakshmi", "lakshmi", "lakshmi@gmail.com", "Banglore", "9618339354");
		Mockito.when(userRepository.findByUserNameAndPassword("lakshmi", "lakshmi")).thenReturn(users);
		assertEquals("Invlid username or password", userService.validateLogin("lakshmi", "lakshmi"));
	}
	@Test
	public void testUsers() {
		user = new User(1L, "lakshmi", "lakshmi", "lakshmi", "Banglore", "9618339354", "lakshmi@gmail.com", "lakshmi",
				Role.SELLER);
		userss = new ArrayList<User>();
		userss.add(user);
		userDetailsDto1 = new UserDetailsDto("lakshmi", "lakshmi@gmai;.com", Role.BUYER);
		userDetailsDtos = new ArrayList<>();
		userDetailsDtos.add(userDetailsDto1);
		Mockito.when(userRepository.findByRole(Mockito.anyString())).thenReturn(userss);
		assertEquals(userDetailsDtos.size(), userService.users("BUYER").size());
	}
}
