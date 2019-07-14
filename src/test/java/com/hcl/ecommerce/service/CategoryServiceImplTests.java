package com.hcl.ecommerce.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.ecommerce.dto.CategoryDetailsDto;
import com.hcl.ecommerce.dto.CategoryDto;
import com.hcl.ecommerce.exception.CategoryNameNotSameException;
import com.hcl.ecommerce.model.Category;
import com.hcl.ecommerce.model.Product;
import com.hcl.ecommerce.repository.CategoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTests {

	@Autowired
	CategoryServiceImpl categoryService;

	@MockBean
	CategoryRepository categoryRepository;
	
	Category category = null;
	Product product = null;
	CategoryDto categoryDto =null;
	CategoryDetailsDto categoryDetailsDto = null;
	List<Category> categories = null;
	List<CategoryDetailsDto> categoryDetailsDtos = null;
	List<Product> products = null;
	
	@Test
	public void addCategorySuccess() {
		category = new Category();
		category.setCategoryName("Mobiles");
		categoryDto = new CategoryDto("Mobiles");
		Mockito.when(categoryRepository.findByCategoryName("Mobile")).thenReturn(category);
		Mockito.when(categoryRepository.save(category)).thenReturn(category);
		assertEquals("Category created Successfully.", categoryService.addCategory(categoryDto));
	}
	
	@Test(expected = CategoryNameNotSameException.class)
	public void addCategoryFailure() {
		category = new Category();
		category.setCategoryName("Mobiles");
		categoryDto = new CategoryDto("Mobiles1");
		Mockito.when(categoryService.addCategory(categoryDto)).thenReturn(Mockito.anyString());
		Mockito.when(categoryRepository.findByCategoryName(categoryDto.getCategoryName())).thenReturn(category);
		assertNotNull(category);
		assertEquals(Mockito.anyString(), categoryService.addCategory(categoryDto));
		}
	
	@Test
	public void testProductsByCategorySuccess() {
		product = new Product();
		product.setProductCode("IP001");
		product.setProductName("Iphone 5S");
		products = new ArrayList<>();
		products.add(product);
		category = new Category(1L, "Mobiles", new Date(), products);
		categoryDetailsDto = new CategoryDetailsDto("Mobiles", products);
		Mockito.when(categoryRepository.findByCategoryName("Mobiles")).thenReturn(category);
		assertNotNull(category);
		assertEquals(categoryDetailsDto.toString(), categoryService.productsByCategory("Mobiles").toString());
	}
	@Test(expected = CategoryNameNotSameException.class)
	public void testProductsByCategoryFailure() {
		Mockito.when(categoryRepository.findByCategoryName(null)).thenReturn(category);
		assertEquals(Mockito.anyString(), categoryService.productsByCategory(null));
	}
}
