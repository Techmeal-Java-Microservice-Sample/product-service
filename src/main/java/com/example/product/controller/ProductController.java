package com.example.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.dto.ProductDto;
import com.example.product.exception.ApplicationException;
import com.example.product.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("product")
@Api(value="Product Controller", description="operations pertaining to manage product", tags = {"Products"})
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	@ApiOperation(value = "Get all products", response = ResponseEntity.class)
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		List<ProductDto> productDtos = productService.getAllProducts();
		return new ResponseEntity<>(productDtos, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation(value = "Create Product", response = ResponseEntity.class)
	public ResponseEntity<ProductDto> create(@ApiParam("Product information for a new product to be created.") 
												@RequestBody ProductDto productDto) {
		validate(productDto);
		ProductDto savedProductDto = productService.createProduct(productDto);
		return new ResponseEntity<>(savedProductDto, HttpStatus.CREATED);
	}

	@GetMapping("/{productName}")
	@ApiOperation(value = "Get product by name", response = ResponseEntity.class)
	public ResponseEntity<ProductDto> getProductByName(@ApiParam("Name of the product to be obtain. Cannot be empty.")
														@PathVariable String productName) {
		if(productName == null) {
			throw new ApplicationException("product name should not be null");
		} 
		ProductDto productDto = productService.getProductByName(productName);
		return new ResponseEntity<>(productDto, HttpStatus.OK);
	}
	
	@PutMapping
	@ApiOperation(value = "Update product", response = ResponseEntity.class)
	public ResponseEntity<ProductDto> updateProduct(@ApiParam("Product information for a product to be updated.")
														@RequestBody ProductDto productDto) {
		validate(productDto);
		ProductDto updatedProductDto = productService.updateProduct(productDto);
		return new ResponseEntity<>(updatedProductDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/{productName}")
	@ApiOperation(value = "Delete product", response = ResponseEntity.class)
	public ResponseEntity<Object> deleteProduct(@ApiParam("Name of the product to be deleted. Cannot be empty.")
													@PathVariable String productName) {
		if(productName == null) {
			throw new ApplicationException("product name should not be null");
		}
		productService.deleteProduct(productName);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private void validate(ProductDto productDto) {
		
		if(productDto == null) {
			throw new ApplicationException("productDto should not be null");
		}
		
		if(productDto.getProductName() == null || productDto.getProductName().isEmpty()) {
			throw new ApplicationException("product name should not be null");
		}
		
		if(productDto.getProductDescription() == null || productDto.getProductDescription().isEmpty()) {
			throw new ApplicationException("product description should not be null");
		}
		
		if(productDto.getPrice() == null) {
			throw new ApplicationException("product price should not be null");
		}
		
		if(productDto.getExpiryDate() == null) {
			throw new ApplicationException("product expiry date should not be null");
		}
	}
}
