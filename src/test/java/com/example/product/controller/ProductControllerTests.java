package com.example.product.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.product.common.CommonUtil;
import com.example.product.dto.ProductDto;
import com.example.product.exception.ApplicationException;
import com.example.product.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTests {

	private static final Date TEST_PRODUCT_EXPIRY_DATE = new Date();

	private static final Double TEST_PRODUCT_PRICE = 20.0;

	private static final String TEST_PRODUCT_DESC = "Product-Test Desc";

	private static final String TEST_PRODUCT_NAME = "Product-Test";

	private static final Double TEST_PRODUCT_PRICE_UPDATE = 30.0;

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Autowired
	private ProductController productController;
	
	@MockBean
	private ProductService productService;
	
	@MockBean
	private CommonUtil commonUtil;
	
	@Test
	public void testCreateProductWhenProductInfoIsNullThenThrowException() {
		thrown.expect(ApplicationException.class);
        thrown.expectMessage("productDto should not be null");
		

		//then
        productController.create(null);
	}
	
	@Test
	public void testCreateProductWhenProductNameIsNullThenThrowException() {

		thrown.expect(ApplicationException.class);
        thrown.expectMessage("product name should not be null");
        
		//Given
		ProductDto productDto = new ProductDto.Builder()
											.productName(null)
											.productDescription("test product")
											.price(TEST_PRODUCT_PRICE)
											.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
											.build();
		
		//then
		 productController.create(productDto);
		
	}
	
	@Test
	public void testCreateProductWhenProductDescriptionIsNullThenThrowException() {

		thrown.expect(ApplicationException.class);
        thrown.expectMessage("product description should not be null");
        
		//Given
		ProductDto productDto = new ProductDto.Builder()
											.productName(TEST_PRODUCT_NAME)
											.productDescription(null)
											.price(TEST_PRODUCT_PRICE)
											.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
											.build();
		
		//then
		 productController.create(productDto);
		
	}

	@Test
	public void testCreateProductWhenProductPriceIsNullThenThrowException() {

		thrown.expect(ApplicationException.class);
        thrown.expectMessage("product price should not be null");
        
		//Given
		ProductDto productDto = new ProductDto.Builder()
											.productName(TEST_PRODUCT_NAME)
											.productDescription(TEST_PRODUCT_DESC)
											.price(null)
											.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
											.build();
		
		//then
		 productController.create(productDto);
		
	}
	
	@Test
	public void testCreateProductWhenProductExpiryDateIsNullThenThrowException() {

		thrown.expect(ApplicationException.class);
        thrown.expectMessage("product expiry date should not be null");
        
		//Given
		ProductDto productDto = new ProductDto.Builder()
											.productName(TEST_PRODUCT_NAME)
											.productDescription(TEST_PRODUCT_DESC)
											.price(TEST_PRODUCT_PRICE)
											.expiryDate(null)
											.build();
		
		//then
		 productController.create(productDto);
		
	}
	
	@Test
	public void testCreateProduct() {

		//Given
		ProductDto productDto = new ProductDto.Builder()
											.productName(TEST_PRODUCT_NAME)
											.productDescription(TEST_PRODUCT_DESC)
											.price(TEST_PRODUCT_PRICE)
											.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
											.build();
		//when
		when(productService.createProduct(productDto)).thenReturn(productDto);
		doNothing().when(commonUtil).publishEvent("New product create : ["+productDto.toString()+"]", "INFO");
		
		//then
		ResponseEntity<ProductDto> response = productController.create(productDto);
	 	assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(productDto.getProductName(), response.getBody().getProductName());
		
		verify(productService).createProduct(productDto);
		verify(commonUtil).publishEvent("New product create : ["+productDto.toString()+"]", "INFO");
	}
	
	@Test
	public void testUpdateProductWhenProductInfoIsNullThenThrowException() {
		thrown.expect(ApplicationException.class);
        thrown.expectMessage("productDto should not be null");
		

		//then
        productController.updateProduct(null);
	}
	
	@Test
	public void testUpdateProductWhenProductNameIsNullThenThrowException() {

		thrown.expect(ApplicationException.class);
        thrown.expectMessage("product name should not be null");
        
		//Given
		ProductDto productDto = new ProductDto.Builder()
											.productName(null)
											.productDescription("test product")
											.price(TEST_PRODUCT_PRICE)
											.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
											.build();
		
		//then
		 productController.updateProduct(productDto);
		
	}
	
	@Test
	public void testUpdateProductWhenProductDescriptionIsNullThenThrowException() {

		thrown.expect(ApplicationException.class);
        thrown.expectMessage("product description should not be null");
        
		//Given
		ProductDto productDto = new ProductDto.Builder()
											.productName(TEST_PRODUCT_NAME)
											.productDescription(null)
											.price(TEST_PRODUCT_PRICE)
											.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
											.build();
		
		//then
		 productController.updateProduct(productDto);
		
	}

	@Test
	public void testUpdateProductWhenProductPriceIsNullThenThrowException() {

		thrown.expect(ApplicationException.class);
        thrown.expectMessage("product price should not be null");
        
		//Given
		ProductDto productDto = new ProductDto.Builder()
											.productName(TEST_PRODUCT_NAME)
											.productDescription(TEST_PRODUCT_DESC)
											.price(null)
											.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
											.build();
		
		//then
		 productController.updateProduct(productDto);
		
	}
	
	@Test
	public void testUpdateProductWhenProductExpiryDateIsNullThenThrowException() {

		thrown.expect(ApplicationException.class);
        thrown.expectMessage("product expiry date should not be null");
        
		//Given
		ProductDto productDto = new ProductDto.Builder()
											.productName(TEST_PRODUCT_NAME)
											.productDescription(TEST_PRODUCT_DESC)
											.price(TEST_PRODUCT_PRICE)
											.expiryDate(null)
											.build();
		
		//then
		 productController.updateProduct(productDto);
		
	}
	
	@Test
	public void testUpdateProduct() {

		//Given
		ProductDto productDto = new ProductDto.Builder()
											.productName(TEST_PRODUCT_NAME)
											.productDescription(TEST_PRODUCT_DESC)
											.price(TEST_PRODUCT_PRICE)
											.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
											.build();
		
		ProductDto updatedProductDto = new ProductDto.Builder()
											.productName(TEST_PRODUCT_NAME)
											.productDescription(TEST_PRODUCT_DESC)
											.price(TEST_PRODUCT_PRICE_UPDATE)
											.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
											.build();
		
		//when
		when(productService.updateProduct(updatedProductDto)).thenReturn(updatedProductDto);
		
		//then
		ResponseEntity<ProductDto> response = productController.updateProduct(updatedProductDto);
	 	assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(updatedProductDto.getProductName(), response.getBody().getProductName());
		assertEquals(TEST_PRODUCT_PRICE_UPDATE, response.getBody().getPrice());
		
		verify(productService).updateProduct(updatedProductDto);
	}
	
	@Test
	public void testDeleteProductWhenProductNameIsNullThenThrowException() {

		thrown.expect(ApplicationException.class);
        thrown.expectMessage("product name should not be null");
		
		//then
		productController.deleteProduct(null);
	}
	
	@Test
	public void testDeleteProduct() {

		doNothing().when(productService).deleteProduct(TEST_PRODUCT_NAME);
		//then
		ResponseEntity<Object> response = productController.deleteProduct(TEST_PRODUCT_NAME);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		verify(productService).deleteProduct(TEST_PRODUCT_NAME);
		
	}
	
	@Test
	public void testGetAllProducts() {

		//Given
		ProductDto productDto = new ProductDto.Builder()
											.productName(TEST_PRODUCT_NAME)
											.productDescription(TEST_PRODUCT_DESC)
											.price(TEST_PRODUCT_PRICE)
											.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
											.build();
		List<ProductDto> productDtos = new ArrayList<>();
		productDtos.add(productDto);
		//when
		when(productService.getAllProducts()).thenReturn(productDtos);
		
		//then
		ResponseEntity<List<ProductDto>> response = productController.getAllProducts();
	 	assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.FOUND, response.getStatusCode());
		assertEquals(productDto.getProductName(), response.getBody().stream().findFirst().get().getProductName());
		verify(productService).getAllProducts();
	}
	
	@Test
	public void testGetProductByNameWhenProductNameIsNullThenThrowException() {

		thrown.expect(ApplicationException.class);
        thrown.expectMessage("product name should not be null");
        
		//Given
		ProductDto productDto = new ProductDto.Builder()
											.productName(TEST_PRODUCT_NAME)
											.productDescription(TEST_PRODUCT_DESC)
											.price(TEST_PRODUCT_PRICE)
											.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
											.build();
		//when
		when(productService.createProduct(productDto)).thenReturn(productDto);
		
		//then
		productController.getProductByName(null);
		
		verify(productService).createProduct(productDto);
	}
	
	@Test
	public void testGetProductByName() {

		//Given
		ProductDto productDto = new ProductDto.Builder()
											.productName(TEST_PRODUCT_NAME)
											.productDescription(TEST_PRODUCT_DESC)
											.price(TEST_PRODUCT_PRICE)
											.expiryDate(TEST_PRODUCT_EXPIRY_DATE)
											.build();
		//when
		when(productService.getProductByName(TEST_PRODUCT_NAME)).thenReturn(productDto);
		
		//then
		ResponseEntity<ProductDto> response = productController.getProductByName(TEST_PRODUCT_NAME);
	 	assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.FOUND, response.getStatusCode());
		assertEquals(productDto.getProductName(), response.getBody().getProductName());
		
		verify(productService).getProductByName(TEST_PRODUCT_NAME);
	}
}
