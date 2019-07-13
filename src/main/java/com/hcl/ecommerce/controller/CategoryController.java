package com.hcl.ecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.dto.CategoryDetailsDto;
import com.hcl.ecommerce.dto.CategoryDto;
import com.hcl.ecommerce.service.ICategoryService;
@RestController
@RequestMapping("/api")
public class CategoryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	ICategoryService categoryService;
	
	@PostMapping("/addCategory")
	public ResponseEntity<String> addCategory(CategoryDto categoryDto) {
		LOGGER.debug("CategoryController:addCategory {}{}{} ",categoryDto);
		String response = categoryService.addCategory(categoryDto);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@GetMapping("/productsCategory/{categoryName}")
	public ResponseEntity<CategoryDetailsDto> productsByCategory(@PathVariable("categoryName") String categoryName){
		LOGGER.debug("CategoryController:productsByCategory {}",categoryName);
		CategoryDetailsDto category  = categoryService.productsByCategory(categoryName);
		return new ResponseEntity<>(category,HttpStatus.OK);
	}
}
