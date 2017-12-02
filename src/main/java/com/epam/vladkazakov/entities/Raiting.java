package com.epam.vladkazakov.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "raiting")
public class Raiting {
	@Id
	@GeneratedValue
	private int id;
	private int idUser;
	private int idProduct;
	private int value;

	public Raiting() {

	}

	public Raiting(int idUser, int idProduct, int value) {
		this.idUser = idUser;
		this.idProduct = idProduct;
		this.value = value;
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

	public int getValue() {
		return value;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static int stars(List<Raiting> raitings) {
		int sum = 0;
		for (int i = 0; i < raitings.size(); i++) {
			sum += raitings.get(i).getValue();
		}
		try {
		sum /= raitings.size();
		} catch (ArithmeticException e) {
			sum = 0;
			System.out.println("рейтинг товара: " + sum);
		}
		return sum;
	}

}
