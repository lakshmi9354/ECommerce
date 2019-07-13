package com.hcl.ecommerce.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dto.CategoryDetailsDto;
import com.hcl.ecommerce.dto.CategoryDto;
import com.hcl.ecommerce.exception.CategoryNameNotSameException;
import com.hcl.ecommerce.model.Category;
import com.hcl.ecommerce.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);
	@Autowired
	CategoryRepository categoryRepository;

	public String addCategory(CategoryDto categoryDto){
		
		LOGGER.debug("CategoryServiceImpl:addCategory {}{}{} ",categoryDto);
		
		Category category = categoryRepository.findByCategoryName(categoryDto.getCategoryName());
		if(category!=null) {
			throw new CategoryNameNotSameException(category.getCategoryName()+ "Already Exists");
		}
		else {
			Date date = new Date();
			category = new Category();
			category.setCategoryName(categoryDto.getCategoryName());
			category.setLastUpdate(date);
			categoryRepository.save(category);
			return "Category created Successfully.";
		}

	}

	public CategoryDetailsDto productsByCategory(String categoryName) {
		LOGGER.debug("CategoryServiceImpl:productsByCategory ");
		CategoryDetailsDto categoryDetailsDto = null;
		Category category = categoryRepository.findByCategoryName(categoryName);
		if(category!=null) {
					categoryDetailsDto = new CategoryDetailsDto();
					BeanUtils.copyProperties(category, categoryDetailsDto);
		}
		else {
			throw new CategoryNameNotSameException("Category Name Not Availabe.");
		}
		return categoryDetailsDto;
	}
}
