package com.example.product.service;

import java.util.List;

import com.example.product.dto.ProductDto;

public interface ProductService {
	
	List<ProductDto> getAllProducts();
	List<ProductDto> getProductsByName(String productName);
	
	ProductDto getProductById(Long productId);
	ProductDto createProduct(ProductDto productDto);
}
