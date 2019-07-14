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

import com.hcl.ecommerce.dto.ProductDetailsDto;
import com.hcl.ecommerce.dto.ProductDto;
import com.hcl.ecommerce.exception.CategoryNameNotSameException;
import com.hcl.ecommerce.exception.ProductNameNotSameException;
import com.hcl.ecommerce.exception.ProductNotFoundException;
import com.hcl.ecommerce.model.Category;
import com.hcl.ecommerce.model.Product;
import com.hcl.ecommerce.repository.CategoryRepository;
import com.hcl.ecommerce.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTests {

	@Autowired
	ProductServiceImpl productService;

	@MockBean
	ProductRepository productRepository;

	@MockBean
	CategoryRepository categoryRepository;

	Product product = null;
	ProductDetailsDto productDetailsDto = null;
	Category category = null;
	List<Product> products = null;
	List<ProductDetailsDto> productDetailsDtos = null;
	ProductDto productDto = null;

	@Test
	public void addProductSuccess() {
		category = new Category();
		category.setCategoryName("Mobiles");
		product = new Product();
		product.setProductName("Iphone");
		productDto = new ProductDto();
		productDto.setCategoryName("Mobiles");
		productDto.setProductName("Iphone");
		Mockito.when(categoryRepository.findByCategoryName("Mobiles")).thenReturn(category);
		Mockito.when(productRepository.findByProductName("Redmi")).thenReturn(product);
		Mockito.when(productRepository.save(product)).thenReturn(product);
		assertEquals("Product added Successfully", productService.addProduct(productDto));
	}

	@Test(expected = ProductNameNotSameException.class)
	public void addProductFailure() {
		category = new Category();
		category.setCategoryName("Mobiles");
		product = new Product();
		product.setProductName("Iphone");
		productDto = new ProductDto();
		productDto.setCategoryName("Mobiles");
		productDto.setProductName("Iphone");
		Mockito.when(categoryRepository.findByCategoryName("Mobiles")).thenReturn(category);
		Mockito.when(productRepository.findByProductName("Iphone")).thenReturn(product);
		assertNotNull(product);
		assertEquals(Mockito.anyString(), productService.addProduct(productDto));
	}

	@Test(expected = CategoryNameNotSameException.class)
	public void addProductCategoryFailure() {
		category = new Category();
		category.setCategoryName("Mobiles");
		product = new Product();
		product.setProductName("Iphone");
		productDto = new ProductDto();
		productDto.setCategoryName("Mobiles");
		productDto.setProductName("Iphone");
		Mockito.when(categoryRepository.findByCategoryName("Mobile")).thenReturn(category);
		assertEquals(Mockito.anyString(), productService.addProduct(productDto));
	}

	@Test
	public void testProducts() {
		category = new Category(1L, "Mobiles", new Date(), products);
		product = new Product(1L, "IP001", "Iphone 5S", 23000.0, 4L, new Date(), category);
		products = new ArrayList<>();
		products.add(product);
		productDetailsDto = new ProductDetailsDto("IP001", "Iphone 5S", 23000.0, 4L, new Date());
		productDetailsDtos = new ArrayList<>();
		productDetailsDtos.add(productDetailsDto);
		Mockito.when(productRepository.findAll()).thenReturn(products);
		assertEquals(productDetailsDtos.size(), productService.products().size());
	}

	@Test
	public void testProduct() {
		category = new Category(1L, "Mobiles", new Date(), products);
		product = new Product(1L, "IP001", "Iphone 5S", 23000.0, 4L, new Date(), category);
		productDetailsDto = new ProductDetailsDto("IP001", "Iphone 5S", 23000.0, 4L, new Date());
		Mockito.when(productRepository.findByProductName(Mockito.anyString())).thenReturn(product);
		assertNotNull(product);
		assertEquals(productDetailsDto, productService.product(Mockito.anyString()));
	}

	@Test(expected = ProductNotFoundException.class)
	public void testProductFailure() {
		category = new Category(1L, "Mobiles", new Date(), products);
		product = new Product(1L, "IP001", "Iphone 5S", 23000.0, 4L, new Date(), category);
		productDetailsDto = new ProductDetailsDto("IP001", "Iphone 5S", 23000.0, 4L, new Date());
		Mockito.when(productRepository.findByProductName("Iphone 5S")).thenReturn(product);
		assertEquals(productDetailsDto, productService.product(Mockito.anyString()));
	}
}
