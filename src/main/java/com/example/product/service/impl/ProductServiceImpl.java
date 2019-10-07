package com.example.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.dto.ProductDto;
import com.example.product.entity.Product;
import com.example.product.exception.AlreadyExistException;
import com.example.product.exception.NotFoundException;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> products = (List<Product>) productRepository.findAll();
		if(products.isEmpty()) {
			throw new NotFoundException("Products not found");	
		}
		return products.stream()
						.map(p -> modelMapper.map(p, ProductDto.class))
						.collect(Collectors.toList());
	}

	@Override
	public ProductDto getProductByName(String productName) {
		Product product = productRepository.findByProductName(productName);
		if(product != null) {
			return modelMapper.map(product, ProductDto.class);
		} else {
			throw new NotFoundException("Product not found with name : "+productName);	
		}
	}

	@Override
	public ProductDto createProduct(ProductDto productDto) {
		if(productRepository.findByProductName(productDto.getProductName()) == null) {
			Product productToBeSaved = modelMapper.map(productDto, Product.class);
			Product savedProduct = productRepository.save(productToBeSaved);
			return modelMapper.map(savedProduct, ProductDto.class);
		} else {
			throw new AlreadyExistException("Product already exist with name : "+productDto.getProductName());
		}
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto) {
		Product product = productRepository.findByProductName(productDto.getProductName());
		if(product != null) {
			Product productToBeSaved = modelMapper.map(productDto, Product.class);
			productToBeSaved.setId(product.getId());
			Product savedProduct = productRepository.save(productToBeSaved);
			return modelMapper.map(savedProduct, ProductDto.class);			
		} else {
			throw new NotFoundException("Product not found with name : "+productDto.getProductName());
		}
	}

	@Override
	public void deleteProduct(String productName) {
		Product product = productRepository.findByProductName(productName);
		if(product != null) {
			productRepository.deleteById(product.getId());
		} else {
			throw new NotFoundException("Product not found with name : "+productName);
		}
	}
}
