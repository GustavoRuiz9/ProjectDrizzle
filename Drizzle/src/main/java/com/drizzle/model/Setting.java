package com.drizzle.model;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Required;

@javax.persistence.Entity
@Table
public class Setting {
	
	public Setting(int id_profile_account, boolean telefono, boolean correo) {
		this.id_account_profile = id_profile_account;
		this.telefono = telefono;
		this.correo = correo;
	}

	public Setting() {
		
	}

	
	@Id
	int id_account_profile;
	boolean telefono;
	boolean correo;
	
	@OneToOne
	@MapsId
	@JoinColumn(name="id_account")
	public int getId_profile_account() {
		return id_account_profile;
	}
	
	@Required
	public void setId_profile_account(int id_profile_account) {
		this.id_account_profile = id_profile_account;
	}
	public boolean isTelefono() {
		return telefono;
	}
	public void setTelefono(boolean telefono) {
		this.telefono = telefono;
	}
	public boolean isCorreo() {
		return correo;
	}
	public void setCorreo(boolean correo) {
		this.correo = correo;
	}

	@Override
	public String toString() {
		return "Setting [id_profile_account=" + id_account_profile + ", telefono=" + telefono + ", correo=" + correo
				+ "]";
	}
	
	

}
