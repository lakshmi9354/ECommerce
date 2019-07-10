package com.hcl.ecommerce.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto implements Serializable{
	
	private static final long serialVersionUID = 3917777511407919409L;
	private String firstName;
	private String lastName;
	private String email;
	private String city;
	private String mobile;
}
