package com.hcl.ecommerce.controller;

import java.util.List;

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

import com.hcl.ecommerce.dto.ProductDetailsDto;
import com.hcl.ecommerce.dto.ProductDto;
import com.hcl.ecommerce.service.IProductService;
@RestController
@RequestMapping("/api")
public class ProductController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	IProductService productService;
	
	@PostMapping("/addProduct")
	public ResponseEntity<String> addProduct(ProductDto productDto) {
		LOGGER.debug("ProductController:addProduct {}{}{} ",productDto);
		String response = productService.addProduct(productDto);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/products")
	public ResponseEntity<List<ProductDetailsDto>> products(){
		LOGGER.debug("ProductController:addProduct ");
		List<ProductDetailsDto> productDetailsDto  = productService.products();
		return new ResponseEntity<>(productDetailsDto,HttpStatus.ACCEPTED);
	}
	@GetMapping("/product/{productName}")
	public ResponseEntity<ProductDetailsDto> product(@PathVariable("productName") String productName){
		LOGGER.debug("ProductController:addProduct {} ",productName);
		ProductDetailsDto productDetailsDto = productService.product(productName);
		return new ResponseEntity<>(productDetailsDto,HttpStatus.ACCEPTED);
	}
}
