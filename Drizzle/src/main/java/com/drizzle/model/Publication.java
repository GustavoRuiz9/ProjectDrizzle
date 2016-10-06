package com.drizzle.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Required;

import com.mysql.jdbc.Blob;

@Entity
@Table (name="publication")
public class Publication {

	public Publication() {
		
	}
	

	


	public Publication(int id_publication, String date, int author, String weather, String descripcion, byte[] photo) {
		super();
		this.id_publication = id_publication;
		this.date = date;
		this.author = author;
		this.weather = weather;
		Descripcion = descripcion;
		this.photo = photo;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id_publication;
	String date;
	int author;
	String weather;
	String Descripcion;
	byte[] photo;
	
	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public int getId_publication() {
		return id_publication;
	}

	@Required
	public void setId_publication(int id_publication) {
		this.id_publication = id_publication;
	}

	public String getDate() {
		return date;
	}

	@Required
	public void setDate(String date) {
		this.date = date;
	}

	public int getAuthor() {
		return author;
	}

	@Required
	public void setAuthor(int author) {
		this.author = author;
	}

	public String getWeather() {
		return weather;
	}

	@Required
	public void setWeather(String weather) {
		this.weather = weather;
	}


	@Override
	public String toString() {
		return "Publication [id_publication=" + id_publication + ", date=" + date + ", author=" + author + ", weather="
				+ weather + ", Descripcion=" + Descripcion + ", photo=" + photo + "]";
	}

}