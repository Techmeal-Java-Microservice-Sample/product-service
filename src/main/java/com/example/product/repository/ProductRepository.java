package com.example.product.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.product.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

	Product findByProductName(String productName);

}
