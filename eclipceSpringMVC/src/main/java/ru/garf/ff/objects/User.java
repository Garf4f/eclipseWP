package ru.garf.ff.objects;

import javax.validation.constraints.Size;

public class User {

	@Size(min = 6, message = "Login should be more than 6 characters")
	private String name;

	@Size(min = 5, max = 10, message = "Password should be from 5 to 6 characters")
	private String password;

	private boolean admin;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean isAdmin) {
		this.admin = isAdmin;
	}

}
