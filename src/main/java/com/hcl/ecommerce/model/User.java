package com.hcl.ecommerce.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{

	private static final long serialVersionUID = -3859241816334180782L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	private String firstName;
	private String lastName;
	@NotEmpty(message = "User Name Canot be Empty")
	@Size(min = 2,max = 10,message = "User Name Should be 2 to 10 characters")
	private String userName;
	private String city;
	private String mobile;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;

}
