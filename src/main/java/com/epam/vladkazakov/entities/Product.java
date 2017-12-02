package com.epam.vladkazakov.entities;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "BD_TYPE")
public abstract class Product {
	@Id
	@GeneratedValue
	protected int id;
	protected String name;
	protected String category;
	protected String details;
	protected int count;
	@Transient
	protected int reiting;
	@Transient
	protected double price;
	@Transient
	protected int cartCount;
	protected String image;

	public Product() {

	}

	protected abstract String getDescription();
	public String getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getReiting() {
		return reiting;
	}

	public void setReiting(int reiting) {
		this.reiting = reiting;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getCartCount() {
		return cartCount;
	}

	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}

}
