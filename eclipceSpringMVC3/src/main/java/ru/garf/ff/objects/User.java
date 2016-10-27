package ru.garf.ff.objects;

import javax.validation.constraints.Size;

public class User {

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String name) {
		super();
		this.name = name;
	}

	@Size(min = 6, message = "{name.size.error}")
	private String name;

	@Size(min = 5, max = 10, message = "{password.size.error}")
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
