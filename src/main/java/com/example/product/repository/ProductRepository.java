package com.example.product.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.product.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> findByProductName(String productName);

}
