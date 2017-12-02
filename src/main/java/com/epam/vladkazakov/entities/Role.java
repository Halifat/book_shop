package com.epam.vladkazakov.entities;

public class Role {
	private String role;
	private String name;

	public Role(String role, String name) {
		this.name = name;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}

}
