package com.lakshmi.exittestapi.entities;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Products {

	@Id
	int ID;
	@Column(name = "Name")
	String name;
	@Column(name = "brand")
	String brand;
	@Column(name = "description")
	String description;
	@Column(name = "code")
	String code;

	@Lob
	@Column(name = "image", columnDefinition = "MEDIUMBLOB")
	private byte[] image;
	@Column(name = "price")
	double price;

	public int getID() {
		return ID;
	}

	@Override
	public String toString() {
		return "Products [ID=" + ID + ", name=" + name + ", brand=" + brand + ", description=" + description + ", code="
				+ code + ", image=" + Arrays.toString(image) + ", price=" + price + "]";
	}

	public void setID(int iD) {
		this.ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getPrice() {
		return price;
	}
	
	

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Products(int iD, String name, String brand, String description, String code, byte[] image, double price) {
		super();
		ID = iD;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.code = code;
		this.image = image;
		this.price = price;
	}

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
