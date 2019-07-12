package com.hcl.ecommerce.service;

import static org.junit.Assert.assertEquals;

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
import com.hcl.ecommerce.model.Category;
import com.hcl.ecommerce.model.Product;
import com.hcl.ecommerce.repository.CategoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

	@Autowired
	CategoryServiceImpl categoryService;

	@MockBean
	CategoryRepository categoryRepository;
	
	Category category = null;
	Product product = null;
	CategoryDetailsDto categoryDetailsDto = null;
	List<Category> categories = null;
	List<CategoryDetailsDto> categoryDetailsDtos = null;
	List<Product> products = null;
	
	@Test
	public void testProducts() {
		category = new Category(1L, "Mobiles", new Date(), products);
		product = new Product(1L, "IP001", "Iphone 5S", 23000.0, 4L, new Date(), category);
		products = new ArrayList<>();
		products.add(product);
		categoryDetailsDto = new CategoryDetailsDto("Mobiles", products);
		categoryDetailsDtos = new ArrayList<>();
		categoryDetailsDtos.add(categoryDetailsDto);
		categories = new ArrayList<>();
		categories.add(category);
		Mockito.when(categoryRepository.findAll()).thenReturn(categories);
		assertEquals(categoryDetailsDtos.size(), categoryService.productsByCategory().size());
	}
}
