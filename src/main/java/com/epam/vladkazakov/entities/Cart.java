package com.epam.vladkazakov.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@GeneratedValue
	private int id;
	private int idUser;
	private int idProduct;
	private int count;

	public Cart() {
	}

	public Cart(int idUser, int idProduct, int count) {
		this.idUser = idUser;
		this.idProduct = idProduct;
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public int getIdUser() {
		return idUser;
	}

	public int getCount() {
		return count;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return (id + ";" + idProduct + ";" + idUser + ";" + count);
	}

}
