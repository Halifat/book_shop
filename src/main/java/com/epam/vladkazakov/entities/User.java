package com.epam.vladkazakov.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String login;
	private String password;
	private boolean isAdmin;
	public User() {
	}

	public User(String name, String password, String login) {
		this.name = name;
		this.password = password;
		this.login = login;
		isAdmin = false;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}
	@Override
	public String toString() {
		return (id + ";" + name + ";" + login + ";");
	}
}
