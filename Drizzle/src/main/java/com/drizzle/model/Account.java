package com.drizzle.model;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Required;

@javax.persistence.Entity
@Table
public class Account {

	public Account(){
		
	}


	public Account(String name, String last_name, String email, byte[] password, String birth_date,
			String number_phone) {
		this.name = name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.birth_date = birth_date;
		this.number_phone = number_phone;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_account;
	
	private String name;
	
	private String last_name;
	
	private String email;
	
	private byte[] password;
	
	private String birth_date;
	
	private String number_phone;
	
	
	
	public int getId_account() {
		return id_account;
	}
	
	public void setId_account(int id_account) {
		this.id_account = id_account;
	}

	public String getName() {
		return name;
	}
	@Required
	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}
	@Required
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}
	@Required
	public void setEmail(String email) {
		this.email = email;
	}

	

	public byte[] getPassword() {
		return password;
	}


	public void setPassword(byte[] password) {
		this.password = password;
	}


	public String getBirth_date() {
		return birth_date;
	}
	@Required
	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	public String getNumber_phone() {
		return number_phone;
	}
	
	public void setNumber_phone(String number_phone) {
		this.number_phone = number_phone;
	}

	@Override
	public String toString() {
		return "Account [id_account=" + id_account + ", name=" + name + ", last_name=" + last_name + ", email=" + email
				+ ", password=" + password + ", birth_date=" + birth_date + ", number_phone=" + number_phone + "]";
	}
	

}
