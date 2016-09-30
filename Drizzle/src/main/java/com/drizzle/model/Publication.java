package com.drizzle.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Required;

@Entity
@Table (name="publication")
public class Publication {

	public Publication() {
		
	}
	
	public Publication(int id_publication, String date, int author, String weather) {
		this.id_publication = id_publication;
		this.date = date;
		this.author = author;
		this.weather = weather;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id_publication;
	String date;
	int author;
	String weather;

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
				+ weather + "]";
	}

}