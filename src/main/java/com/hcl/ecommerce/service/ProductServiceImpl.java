package com.hcl.ecommerce.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dto.ProductDetailsDto;
import com.hcl.ecommerce.dto.ProductDto;
import com.hcl.ecommerce.exception.CategoryNameNotSameException;
import com.hcl.ecommerce.exception.ProductNameNotSameException;
import com.hcl.ecommerce.exception.ProductNotFoundException;
import com.hcl.ecommerce.model.Category;
import com.hcl.ecommerce.model.Product;
import com.hcl.ecommerce.repository.CategoryRepository;
import com.hcl.ecommerce.repository.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService{
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public String addProduct(ProductDto productDto) {
			LOGGER.debug("ProductServiceImpl:addProduct {}{}{} ",productDto);
			Category category = categoryRepository.findByCategoryName(productDto.getCategoryName());
			Product product = productRepository.findByProductName(productDto.getProductName());
			if(category!=null) {
				if(product!=null) {
					throw new ProductNameNotSameException("Product Already Available.");
				}
				else {
						product = new Product();
						Date date = new Date();
						product.setLastUpdate(date);
						product.setCategory(category);
						BeanUtils.copyProperties(productDto, product);
						productRepository.save(product);
						return "Product added Successfully";
				}
			}
			else {
				throw new CategoryNameNotSameException("Ctegory Name Not Available.");
			}
			
	}

	public List<ProductDetailsDto> products() {
		LOGGER.debug("ProductServiceImpl:products ");
		List<Product> products = productRepository.findAll();
		List<ProductDetailsDto> productDetailsDtos = new ArrayList<>();
		for(Product product:products) {
			ProductDetailsDto productDetailsDto = new ProductDetailsDto();
			BeanUtils.copyProperties(product, productDetailsDto);
			productDetailsDtos.add(productDetailsDto);
		}
		return productDetailsDtos;
	}

	public ProductDetailsDto product(String productName) {
		LOGGER.debug("ProductServiceImpl:product {}",productName);
		Product product = productRepository.findByProductName(productName);
		if(product!=null) {
			ProductDetailsDto productDetailsDto = new ProductDetailsDto();
			BeanUtils.copyProperties(product, productDetailsDto);
			return productDetailsDto;
		}
		else {
			throw new ProductNotFoundException("Product Not Available.");
		}
	}


}
