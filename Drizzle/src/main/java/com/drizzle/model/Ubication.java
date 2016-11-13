package com.drizzle.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Table (name="ubication")
public class Ubication {
	
	public Ubication() {
		
	}
	
	public Ubication(int id_, String bar, int comuna, String punto_cardinal) {
		super();
		this.Id_ = id_;
		this.bar = bar;
		this.Comuna = comuna;
		this.Punto_cardinal = punto_cardinal;
	}
	
	
	int Id_;
	int Comuna;
	String bar;
	String Punto_cardinal;
	
	
	public int getId_() {
		return Id_;
	}
	@Required
	public void setId_(int id_) {
		Id_ = id_;
	}
	
	
	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

	public int getComuna() {
		return Comuna;
	}
	@Required
	public void setComuna(int comuna) {
		Comuna = comuna;
	}
	public String getPunto_cardinal() {
		return Punto_cardinal;
	}
	@Required
	public void setPunto_cardinal(String punto_cardinal) {
		Punto_cardinal = punto_cardinal;
	}

	@Override
	public String toString() {
		return "Ubication [Id_=" + Id_ + ", Comuna=" + Comuna + ", bar=" + bar + ", Punto_cardinal=" + Punto_cardinal
				+ "]";
	}
	
	
	

}
