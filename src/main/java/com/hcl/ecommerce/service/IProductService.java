package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.dto.ProductDetailsDto;
import com.hcl.ecommerce.dto.ProductDto;

public interface IProductService {
	String addProduct(ProductDto productDto);
	List<ProductDetailsDto> products();
	ProductDetailsDto product(String productName);
}
