package com.drizzle.model;

import org.springframework.beans.factory.annotation.Required;

public class Account {
	
	private String name;
	private String lastName;
	private String email;
	private String contrasena;
	private String birth;
	private int phone;
	
	public String getName() {
		return name;
	}
	
	@Required
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName() {
		return lastName;
	}
	@Required
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	@Required
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	@Required
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public int getPhone() {
		return phone;
	}
	@Required
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getContrasena() {
		return contrasena;
	}
	@Required
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public String toString() {
		return "Account [name=" + name + ", lastName=" + lastName + ", email=" + email + ", contrasena=" + contrasena
				+ ", birth=" + birth + ", phone=" + phone + "]";
	}
	
	

}
