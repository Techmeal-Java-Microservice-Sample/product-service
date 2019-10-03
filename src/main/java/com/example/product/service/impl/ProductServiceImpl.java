package com.example.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.dto.ProductDto;
import com.example.product.entity.Product;
import com.example.product.exception.AlreadyExistException;
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
		return products.stream()
						.map(p -> modelMapper.map(p, ProductDto.class))
						.collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> getProductsByName(String productName) {
		List<Product> products = productRepository.findByProductName(productName);
		return products.stream()
						.map(p -> modelMapper.map(p, ProductDto.class))
						.collect(Collectors.toList());
	}

	@Override
	public ProductDto getProductById(Long productId) {
		if(productRepository.findById(productId).isPresent()) {
			Product product = productRepository.findById(productId).get();
			return modelMapper.map(product, ProductDto.class);
		} else {
			return null;	
		}
	}

	@Override
	public ProductDto createProduct(ProductDto productDto) {
		
		if(!getProductsByName(productDto.getProductName()).isEmpty()) {
			throw new AlreadyExistException("product already exist");
		}
		
		Product productToBeSaved = modelMapper.map(productDto, Product.class);
		Product savedProduct = productRepository.save(productToBeSaved);
		return modelMapper.map(savedProduct, ProductDto.class);
	}
	
}
