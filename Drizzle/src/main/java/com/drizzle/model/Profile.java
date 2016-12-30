package com.drizzle.model;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Required;


@javax.persistence.Entity
@Table
public class Profile {

	public Profile(int profile_account, byte[] photo, String verification, boolean estatus, int reputation) {
		this.profile_account = profile_account;
		this.photo = photo;
		this.verification = verification;
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
	
	private String verification;
	
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

	public boolean isEstatus() {
		return estatus;
	}
	@Required
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	
	public String getVerification() {
		return verification;
	}
	public void setVerification(String verification) {
		this.verification = verification;
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
		return "Profile [profile_account=" + profile_account + ", photo=" + photo + ", verification=" + verification
				+ ", estatus=" + estatus + ", reputation=" + reputation + "]";
	}
			
}
