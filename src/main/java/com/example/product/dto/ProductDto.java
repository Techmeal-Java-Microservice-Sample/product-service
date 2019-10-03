package com.example.product.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

	@ApiModelProperty(notes = "Name of the Product", name = "product name", required = true, value = "test name")
	private String productName;

	@ApiModelProperty(notes = "Description of the Product", name = "product description", required = true, value = "test product description")
	private String productDescription;

	@ApiModelProperty(notes = "Price of the Product", name = "product price", required = true, value = "0.0")
	private Double price;

	@ApiModelProperty(notes = "Expiry date of the Product", name = "product expiry date", required = true, value = "12-12-2019")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date expiryDate;

	public ProductDto() {
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public static class Builder {
		private String productName;
		private String productDescription;
		private Double price;
		private Date expiryDate;

		public Builder productName(String productName) {
			this.productName = productName;
			return this;
		}

		public Builder productDescription(String productDescription) {
			this.productDescription = productDescription;
			return this;
		}

		public Builder price(Double price) {
			this.price = price;
			return this;
		}

		public Builder expiryDate(Date expiryDate) {
			this.expiryDate = expiryDate;
			return this;
		}

		public ProductDto build() {
			return new ProductDto(this);
		}
	}

	private ProductDto(Builder builder) {
		this.productName = builder.productName;
		this.productDescription = builder.productDescription;
		this.price = builder.price;
		this.expiryDate = builder.expiryDate;
	}

	@Override
	public String toString() {
		return "ProductDto [productName=" + productName + ", productDescription=" + productDescription + ", price="
				+ price + ", expiryDate=" + expiryDate + "]";
	}
	
}
