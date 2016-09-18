package com.drizzle.model;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Required;


@javax.persistence.Entity
@Table
public class Profile {

	public Profile(int profile_account, byte[] photo, boolean auto_ubication, boolean estatus, int reputation) {
		this.profile_account = profile_account;
		this.photo = photo;
		this.auto_ubication = auto_ubication;
		this.estatus = estatus;
		this.reputation = reputation;
	}
	public Profile() {
		
	}

	@Id
	private int profile_account;
	
	@Lob
	private byte[] photo;
	
	//private String ubication;                      //CAMPO PENDIENTE CUANDO SE CREE ESTABA CLASE!!!
	
	private boolean auto_ubication;
	
	private boolean estatus;
	
	private int reputation;

	@OneToOne
	@MapsId
	@JoinColumn(name="id_account")	
	public int getProfile_account() {
		return profile_account;
	}
	
	@Required
	public void setProfile_account(int profile_account) {
		this.profile_account = profile_account;
	}
	
	public byte[] getPhoto() {
		return photo;
	}
	
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public boolean isAuto_ubication() {
		return auto_ubication;
	}
	@Required
	public void setAuto_ubication(boolean auto_ubication) {
		this.auto_ubication = auto_ubication;
	}

	public boolean isEstatus() {
		return estatus;
	}
	@Required
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	public int getReputation() {
		return reputation;
	}
	@Required
	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	@Override
	public String toString() {
		return "Profile [profile_account=" + profile_account + ", photo=" + photo + ", auto_ubication=" + auto_ubication
				+ ", estatus=" + estatus + ", reputation=" + reputation + "]";
	}
			
}
