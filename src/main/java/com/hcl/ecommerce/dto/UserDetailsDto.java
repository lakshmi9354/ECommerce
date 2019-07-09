package com.hcl.ecommerce.dto;

import java.io.Serializable;

import com.hcl.ecommerce.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto implements Serializable{
	
	private static final long serialVersionUID = 1325132739868329561L;

	private String userName;
	
	private String email;
	
	private Role role;

}
