package com.hcl.ecommerce.service;

import com.hcl.ecommerce.dto.CategoryDetailsDto;
import com.hcl.ecommerce.dto.CategoryDto;

public interface ICategoryService {
	String addCategory(CategoryDto categoryDto);

	CategoryDetailsDto productsByCategory(String categoryName);
}
