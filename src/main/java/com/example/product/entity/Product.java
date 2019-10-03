package com.example.product.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "productName")
	private String productName;

	@Column(name = "productDescription")
	private String productDescription;

	@Column(name = "price")
	private Double price;

	@Column(name = "expiryDate")
	private Date expiryDate;

	public Product() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		private Long id;
		private String productName;
		private String productDescription;
		private Double price;
		private Date expiryDate;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

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

		public Product build() {
			return new Product(this);
		}
	}

	private Product(Builder builder) {
		this.id = builder.id;
		this.productName = builder.productName;
		this.productDescription = builder.productDescription;
		this.price = builder.price;
		this.expiryDate = builder.expiryDate;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", productDescription=" + productDescription
				+ ", price=" + price + ", expiryDate=" + expiryDate + "]";
	}
	
}
