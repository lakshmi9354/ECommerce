package com.hcl.ecommerce.dto;

import java.io.Serializable;

import com.hcl.ecommerce.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto implements Serializable{

	private static final long serialVersionUID = -3859241816334180782L;
	private String firstName;
	private String lastName;
	private String userName;
	private String city;
	private String mobile;
	private String email;
	private String password;
	private String confirmPasword;
	private Role role;

}
