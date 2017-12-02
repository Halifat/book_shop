package com.vladkazakov.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_order")
public class Order {
	@Id
	@GeneratedValue
	private int id;
	private int idUserOrder;
	private int idProductOrder;
	private int countOrder;

	public Order() {
		
	}

	public Order(int idUser, int idProduct, int count) {
		this.idUserOrder = idUser;
		this.idProductOrder = idProduct;
		this.countOrder = count;
	}

	public int getCount() {
		return countOrder;
	}

	public int getId() {
		return id;
	}

	public int getIdProduct() {
		return idProductOrder;
	}

	public int getIdUser() {
		return idUserOrder;
	}

	public void setCount(int count) {
		this.countOrder = count;
	}

	public void setIdProduct(int idProduct) {
		this.idProductOrder = idProduct;
	}

	public void setIdUser(int idUser) {
		this.idUserOrder = idUser;
	}

	@Override
	public String toString() {
		return (id + ";" + idProductOrder + ";" + idUserOrder + ";" + countOrder);
	}

}
