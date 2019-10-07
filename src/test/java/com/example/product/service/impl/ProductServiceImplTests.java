package com.example.product.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.product.dto.ProductDto;
import com.example.product.entity.Product;
import com.example.product.exception.AlreadyExistException;
import com.example.product.exception.NotFoundException;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTests {

	private static final Date TEST_PRODUCT_EXPIRY_DATE = new Date();
	private static final Double TEST_PRODUCT_PRICE = 20.0;
	private static final String TEST_PRODUCT_DESC = "Product-Test Desc";
	private static final String TEST_PRODUCT_NAME = "Product-Test";
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Autowired
	private ProductService productService;

	@MockBean
	private ProductRepository productRepository;
	
	@MockBean
	private ModelMapper mapper;
	
	
	@Test
	public void testGetAllProductWhenProductNotFoundThenThrowException() {
		thrown.expect(NotFoundException.class);
        thrown.expectMessage("Products not found");

        when(productRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
        
		//then
        productService.getAllProducts();
	}
	
	@Test
	public void testGetAllProduct() {
		//given
		List<Product> products = new ArrayList<>();
		
		Product productOne = new Product.Builder()
				.productName(TEST_PRODUCT_NAME+" 1")
				.productDescription(TEST_PRODUCT_DESC+" 1")
				.price(TEST_PRODUCT_PRICE)
				.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
				.build();
		
		Product productTwo = new Product.Builder()
				.productName(TEST_PRODUCT_NAME+" 2")
				.productDescription(TEST_PRODUCT_DESC+" 2")
				.price(TEST_PRODUCT_PRICE)
				.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
				.build();
		
		products.add(productOne);
		products.add(productTwo);
		
		//when
        when(productRepository.findAll()).thenReturn(products);
        
		//then
        List<ProductDto> allProducts = productService.getAllProducts();
        
        assertNotNull(allProducts);
		assertFalse(allProducts.isEmpty());
		assertEquals(TEST_PRODUCT_NAME+" 1", allProducts.stream().findFirst().get().getProductName());
        
        verify(productRepository).findAll();
	}
	
	@Test
	public void testGetProductByNameWhenProductNotFoundThenThrowException() {
	
		thrown.expect(NotFoundException.class);
        thrown.expectMessage("Product not found with name : "+TEST_PRODUCT_NAME);

        //
        when(productRepository.findByProductName(TEST_PRODUCT_NAME)).thenReturn(null);
        
		//then
        productService.getProductByName(TEST_PRODUCT_NAME);
        
        verify(productRepository).findByProductName(TEST_PRODUCT_NAME);
	}
	
	@Test
	public void testGetProductByName() {
	
		Product product = new Product.Builder()
				.productName(TEST_PRODUCT_NAME)
				.productDescription(TEST_PRODUCT_DESC)
				.price(TEST_PRODUCT_PRICE)
				.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
				.build();
        //
        when(productRepository.findByProductName(TEST_PRODUCT_NAME)).thenReturn(product);
        
		//then
        ProductDto actualProduct = productService.getProductByName(TEST_PRODUCT_NAME);
        
        assertNotNull(actualProduct);
		assertEquals(TEST_PRODUCT_NAME, actualProduct.getProductName());
        
        verify(productRepository).findByProductName(TEST_PRODUCT_NAME);
	}

	@Test
	public void testCreateProductWhenProductAlreadyExistThenThrowException() {
	
		Product product = new Product.Builder()
				.productName(TEST_PRODUCT_NAME)
				.productDescription(TEST_PRODUCT_DESC)
				.price(TEST_PRODUCT_PRICE)
				.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
				.build();
		
		ProductDto productDto = new ProductDto.Builder()
				.productName(TEST_PRODUCT_NAME)
				.productDescription(TEST_PRODUCT_DESC)
				.price(TEST_PRODUCT_PRICE)
				.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
				.build();
		
		
		thrown.expect(AlreadyExistException.class);
        thrown.expectMessage("Product already exist with name : "+product.getProductName());

        //
        when(productRepository.findByProductName(TEST_PRODUCT_NAME)).thenReturn(product);
        
		//then
        productService.createProduct(productDto);
        
        verify(productRepository).findByProductName(TEST_PRODUCT_NAME);
	}
	
	@Test
	public void testCreateProduct() {
	
		Product product = new Product.Builder()
				.productName(TEST_PRODUCT_NAME)
				.productDescription(TEST_PRODUCT_DESC)
				.price(TEST_PRODUCT_PRICE)
				.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
				.build();
		
		ProductDto productDto = new ProductDto.Builder()
				.productName(TEST_PRODUCT_NAME)
				.productDescription(TEST_PRODUCT_DESC)
				.price(TEST_PRODUCT_PRICE)
				.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
				.build();

        //
        when(productRepository.findByProductName(TEST_PRODUCT_NAME)).thenReturn(null);
        when(mapper.map(productDto, Product.class)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(mapper.map(product, ProductDto.class)).thenReturn(productDto);
        
		//then
        ProductDto savedProduct = productService.createProduct(productDto);
        
        assertNotNull(savedProduct);
		assertEquals(TEST_PRODUCT_NAME, savedProduct.getProductName());
        
        verify(productRepository).findByProductName(TEST_PRODUCT_NAME);
        verify(productRepository).save(product);
	}

	@Test
	public void testUodateProductWhenProductNotFoundThenThrowException() {
	
		ProductDto productDto = new ProductDto.Builder()
				.productName(TEST_PRODUCT_NAME)
				.productDescription(TEST_PRODUCT_DESC)
				.price(TEST_PRODUCT_PRICE)
				.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
				.build();
		
		thrown.expect(NotFoundException.class);
        thrown.expectMessage("Product not found with name : "+TEST_PRODUCT_NAME);

        //
        when(productRepository.findByProductName(TEST_PRODUCT_NAME)).thenReturn(null);
        
		//then
        productService.updateProduct(productDto);
        
        verify(productRepository).findByProductName(TEST_PRODUCT_NAME);
	}
	
	@Test
	public void testUpdateProduct() {
	
		Product product = new Product.Builder()
				.productName(TEST_PRODUCT_NAME)
				.productDescription(TEST_PRODUCT_DESC)
				.price(TEST_PRODUCT_PRICE)
				.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
				.build();
		
		ProductDto productDto = new ProductDto.Builder()
				.productName(TEST_PRODUCT_NAME)
				.productDescription(TEST_PRODUCT_DESC)
				.price(TEST_PRODUCT_PRICE)
				.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
				.build();

        //
        when(productRepository.findByProductName(TEST_PRODUCT_NAME)).thenReturn(product);
        when(mapper.map(productDto, Product.class)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(mapper.map(product, ProductDto.class)).thenReturn(productDto);
        
		//then
        ProductDto savedProduct = productService.updateProduct(productDto);
        
        assertNotNull(savedProduct);
		assertEquals(TEST_PRODUCT_NAME, savedProduct.getProductName());
        
        verify(productRepository).findByProductName(TEST_PRODUCT_NAME);
        verify(productRepository).save(product);
	}

	@Test
	public void testDeleteProductWhenProductNotFoundThenThrowException() {
	
		ProductDto productDto = new ProductDto.Builder()
				.productName(TEST_PRODUCT_NAME)
				.productDescription(TEST_PRODUCT_DESC)
				.price(TEST_PRODUCT_PRICE)
				.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
				.build();
		
		thrown.expect(NotFoundException.class);
        thrown.expectMessage("Product not found with name : "+TEST_PRODUCT_NAME);

        //
        when(productRepository.findByProductName(TEST_PRODUCT_NAME)).thenReturn(null);
        
		//then
        productService.updateProduct(productDto);
        
        verify(productRepository).findByProductName(TEST_PRODUCT_NAME);
	}
}
