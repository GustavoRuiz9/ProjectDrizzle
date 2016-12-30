package com.drizzle.model;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.mysql.jdbc.Blob;
//pulido
@Entity
@Document (collection="publication")
public class Publication {

	public Publication() {
		 this.id_publication = new ObjectId()._time();
	}
	

	public Publication(int id_publication, Date date, int author, String weather, String descripcion, String id_Barrio,
			byte[] photo, int ptos_publicacion) {
		this.id_publication = id_publication;
		this.date = date;
		this.author = author;
		this.weather = weather;
		Descripcion = descripcion;
		Id_Barrio = id_Barrio;
		this.photo = photo;
		this.ptos_publicacion = ptos_publicacion;
	}


	@Id
	@GenericGenerator(name="generator", strategy="increment")
	int id_publication;
	@DateTimeFormat(style="yyyyMMdd'T'HHmmss.SSS")
	Date date;
	int author;
	String weather;
	String Descripcion;
	String Id_Barrio;
	byte[] photo;
	int ptos_publicacion;
	

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

	public Date getDate() {
		return date;
	}

	@Required
	public void setDate(Date date) {
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
	
	
	public String getId_Barrio() {
		return Id_Barrio;
	}

	@Required
	public void setId_Barrio(String id_Barrio) {
		Id_Barrio = id_Barrio;
	}
	
	

	public int getPtos_publicacion() {
		return ptos_publicacion;
	}

	@Required
	public void setPtos_publicacion(int ptos_publicacion) {
		this.ptos_publicacion = ptos_publicacion;
	}


	@Override
	public String toString() {
		return "Publication [id_publication=" + id_publication + ", date=" + date + ", author=" + author + ", weather="
				+ weather + ", Descripcion=" + Descripcion + ", Id_Barrio=" + Id_Barrio + ", photo="
				+ Arrays.toString(photo) + ", ptos_publicacion=" + ptos_publicacion + "]";
	}

	

}