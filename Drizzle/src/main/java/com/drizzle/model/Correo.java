package com.drizzle.model;

public class Correo {
	
	
	public Correo() {
		
	}
	
	public Correo(String usuarioCorreo, String contrasenia, String rutaArchivo, String nombreArchivo, String destino,
			String asunto, String mensaje) {
		this.usuarioCorreo = usuarioCorreo;
		this.contrasenia = contrasenia;
		this.rutaArchivo = rutaArchivo;
		this.nombreArchivo = nombreArchivo;
		this.destino = destino;
		this.asunto = asunto;
		this.mensaje = mensaje;
	}
	
	
	String usuarioCorreo;
	String contrasenia;
	String rutaArchivo;
	String nombreArchivo;
	String destino;
	String asunto;
	String mensaje;
	
	
	public String getUsuarioCorreo() {
		return usuarioCorreo;
	}
	public void setUsuarioCorreo(String usuarioCorreo) {
		this.usuarioCorreo = usuarioCorreo;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getRutaArchivo() {
		return rutaArchivo;
	}
	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	@Override
	public String toString() {
		return "Correo [usuarioCorreo=" + usuarioCorreo + ", contrasenia=" + contrasenia + ", rutaArchivo="
				+ rutaArchivo + ", nombreArchivo=" + nombreArchivo + ", destino=" + destino + ", asunto=" + asunto
				+ ", mensaje=" + mensaje + "]";
	}
	

}
