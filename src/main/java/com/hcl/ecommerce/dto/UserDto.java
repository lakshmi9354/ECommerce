package com.hcl.ecommerce.dto;

import java.io.Serializable;

import com.hcl.ecommerce.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable{

	private static final long serialVersionUID = -3859241816334180782L;
		
	private String userName;
	
	private String email;
	
	private String password;
	
	private String confirmPasword;
	
	private Role role;

}
