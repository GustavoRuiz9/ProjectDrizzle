package com.drizzle.model;

import javax.persistence.Entity;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document (collection="comment")
public class Comment {
	
	int id_commentary;
	String description;
	Date date;
	int publication;
	int author;
	
	
	public Comment() {
		this.id_commentary = new ObjectId()._time();
	}
	
	public Comment(int id_commentary, String description, Date date, int publication, int author) {
		this.id_commentary = id_commentary;
		this.description = description;
		this.date = date;
		this.publication = publication;
		this.author = author;
	}
	
	public int getId_commentary() {
		return id_commentary;
	}
	public void setId_commentary(int id_comment) {
		this.id_commentary = id_comment;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getPublication() {
		return publication;
	}
	public void setPublication(int publication) {
		this.publication = publication;
	}
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "Comment [id_commentary=" + id_commentary + ", description=" + description + ", date=" + date
				+ ", publication=" + publication + ", author=" + author + "]";
	}

}
