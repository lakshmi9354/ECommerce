package com.hcl.ecommerce.dto;

import java.io.Serializable;
import java.util.List;

import com.hcl.ecommerce.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDetailsDto implements Serializable{
	
	private static final long serialVersionUID = -3240704841680514359L;
	
	private String categoryName;
	private List<Product> products;
}
