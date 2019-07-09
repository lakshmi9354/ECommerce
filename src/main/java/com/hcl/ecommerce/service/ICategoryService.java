package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.dto.CategoryDetailsDto;
import com.hcl.ecommerce.dto.CategoryDto;

public interface ICategoryService {
	String addCategory(CategoryDto categoryDto);

	List<CategoryDetailsDto> productsByCategory();
}
