package com.drizzle.model;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Document (collection="estadistica")
public class Estadistica {
	
	
	public Estadistica() {
		
	}
	
	
	public Estadistica(Date fecha, String tipo, int comuna, int storm, int sunny, int rain, int tempered) {
		this.fecha = fecha;
		this.tipo = tipo;
		this.comuna = comuna;
		this.storm = storm;
		this.sunny = sunny;
		this.rain = rain;
		this.tempered = tempered;
	}
	
	@DateTimeFormat(style="yyyy-MM-dd HH:mm:ss")
	Date fecha;
	String tipo;
	int comuna;
	int storm;
	int sunny;
	int rain;
	int tempered;
	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public int getComuna() {
		return comuna;
	}


	public void setComuna(int comuna) {
		this.comuna = comuna;
	}


	public int getStorm() {
		return storm;
	}


	public void setStorm(int storm) {
		this.storm = storm;
	}


	public int getSunny() {
		return sunny;
	}


	public void setSunny(int sunny) {
		this.sunny = sunny;
	}


	public int getRain() {
		return rain;
	}


	public void setRain(int rain) {
		this.rain = rain;
	}


	public int getTempered() {
		return tempered;
	}


	public void setTempered(int tempered) {
		this.tempered = tempered;
	}


	@Override
	public String toString() {
		return "Estadistica [fecha=" + fecha + ", tipo=" + tipo + ", comuna=" + comuna + ", storm=" + storm + ", sunny="
				+ sunny + ", rain=" + rain + ", tempered=" + tempered + "]";
	}
	
	
	

}
