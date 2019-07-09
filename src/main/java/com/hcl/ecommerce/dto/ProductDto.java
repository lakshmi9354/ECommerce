package com.hcl.ecommerce.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable{
	
	private static final long serialVersionUID = -5706443415334970530L;
	
	private String productCode;
	private String productName;
	private double productPrice;
	private Long productQuantity;
	private String categoryName;
}
