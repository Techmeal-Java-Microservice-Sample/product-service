package com.example.product.service;

import java.util.List;

import com.example.product.dto.ProductDto;

public interface ProductService {
	
	List<ProductDto> getAllProducts();
	ProductDto getProductByName(String productName);
	ProductDto getProductById(Long productId);
	ProductDto createProduct(ProductDto productDto);
	ProductDto updateProduct(ProductDto productDto);
	void deleteProduct(String productName);
}
