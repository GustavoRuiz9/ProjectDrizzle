package com.drizzle.model;

import javax.persistence.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document (collection="like")
public class Like {
	
	
	int author;
	int usuario;
	int id_publicacion;

	public Like() {		
		
	}
	
	
	public Like(int author, int usuario, int id_publicacion) {		
		this.author = author;
		this.usuario = usuario;
		this.id_publicacion = id_publicacion;
	}
	
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	public int getId_publicacion() {
		return id_publicacion;
	}
	public void setId_publicacion(int id_publicacion) {
		this.id_publicacion = id_publicacion;
	}


	@Override
	public String toString() {
		return "Like [author=" + author + ", usuario=" + usuario + ", id_publicacion=" + id_publicacion + "]";
	}
	

}
