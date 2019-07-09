package com.hcl.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerce.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	Product findByProductName(String productName);

}
