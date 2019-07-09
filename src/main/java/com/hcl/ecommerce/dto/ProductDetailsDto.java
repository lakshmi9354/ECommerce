package com.hcl.ecommerce.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDto implements Serializable{
	private static final long serialVersionUID = 6799022399336021181L;
	
	private String productCode;
	private String productName;
	private double productPrice;
	private Long productQuantity;
	private Date lastUpdate;
}
