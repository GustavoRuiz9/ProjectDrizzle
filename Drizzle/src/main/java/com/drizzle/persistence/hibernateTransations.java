package com.drizzle.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.drizzle.model.Account;
import com.drizzle.model.Like;
import com.drizzle.model.Profile;
import com.drizzle.model.Setting;

import org.apache.soap.encoding.soapenc.Base64;
import com.mysql.jdbc.Blob;

public class hibernateTransations {

	public static boolean registrarAccount(Account new_account) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try{
			
			session.beginTransaction();		
			session.save(new_account);
			session.getTransaction().commit();
			System.out.println("Insert Successful");
			return true;
			
		}catch (Exception e) {
			System.out.println("Error en el metodo registrarAccount - " + e.getMessage());
			return false;
		}finally {
			session.disconnect();
		}

	}
	
	
	@SuppressWarnings("finally")
	public static Object[] consultarAccount(String emailAccount) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Object[]> results;
		Object[] resul= new Object[0];
		String sentenciaSQL = "Select a.email, a.password, p.estatus FROM Account a, Profile p Where a.email = ? and a.id_account = p.profile_account";
		
		try{
		
			session.beginTransaction();
			Query query = session.createQuery(sentenciaSQL);			
			query.setParameter(0,emailAccount);
			results = query.getResultList();
			if(results.isEmpty()){
			}else{
				resul=results.get(0);	
			}
			
			//System.out.println(query.getResultList()+"");
			System.out.println("Select Successful");
			//if(results.isEmpty())
			System.out.println("consulta Account " + query.toString());
				return resul;
			
			
			
		}catch (Exception e) {
			System.out.println("Error en el metodo consultarAccount - " + e.getMessage());
			return null;
		}finally {
			session.disconnect();
		}
		
	}
	
	
	public static boolean getprofileimg(Account new_account) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		session.save(new_account);
			
		String hql = "Select a FROM Account as a Where a.email = ?";
		
		Query query = session.createQuery(hql);
		
		query.setParameter(0,new_account.getEmail()+"");
		
		List<Account> results = query.getResultList();

		session.getTransaction().commit();

		session.disconnect();

		return true;

	}
	
	
	public static boolean registrarProfile(int idAccount,String verification) {

		Profile new_profile = new Profile();
		new_profile.setProfile_account(idAccount);
		new_profile.setReputation(0);
		new_profile.setVerification(verification);
		new_profile.setEstatus(false);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try{
			//File photo = new File("/opt/tomcat/webapps/drizzleweb/resources/img/perfil/profile.png");
			File photo = new File("C:/Users/RICARDO OSPINA/WorkspaceSpring/ProjectDrizzle/IMG/avatar.png");
			byte[] photoBytes = new byte[(int)photo.length()];
			
			FileInputStream fs = new FileInputStream(photo);
			fs.read(photoBytes);
			fs.close();
			
			new_profile.setPhoto(photoBytes);
			
			session.beginTransaction();
			session.save(new_profile);
			session.getTransaction().commit();
						
			return true;
			
		}catch (Exception e) {
			borrarAccount(new_profile.getProfile_account());
			System.out.println("Error en el metodo registrarProfile - " + e.getMessage());
			return false;
			
		}finally {
			session.disconnect();
		}
		
	}
	
	public static boolean borrarAccount(int id_account) {
		System.out.println("inicia metodo borrarAccount!");
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try{
			
			Account account = new Account();
			account.setId_account(id_account);
			session.beginTransaction();		
			session.delete(id_account);
			session.getTransaction().commit();
			System.out.println("Delete Successful ");
			return true;
			
		}catch (Exception e) {
			System.out.println("Error en el metodo borrarAccount - " + e.getMessage());
			return false;
		}finally {
			session.disconnect();
		}

	}
	
	public static boolean actualizarProfile(byte[] photo,int Id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Profile profile=(Profile)session.get(Profile.class, Id);
		profile.setPhoto(photo);
		
			try{
				session.beginTransaction();		
				session.update(profile);
				session.getTransaction().commit();
				session.disconnect();
				return true;
				
			}catch (Exception e) {
				System.out.println("Error en el metodo actualizarAccount - " + e.getMessage());
				return false;
			}finally {
				session.disconnect();
			}

	}
	
	
	@SuppressWarnings("finally")
	public static Object [] consultarDatosSesion(String emailAccount) {
		Session session = HibernateUtil.getSessionFactory().openSession();		
		Object [] datos;
		String sentenciaSQL = "Select a.id_account,a.name,a.last_name,p.photo,a.password FROM Account a,Profile p Where p.profile_account = a.id_account and a.email = ?";
		
		try{
			//HttpSession sesion = request.getSession();
			
			session.beginTransaction();
			Query query = session.createQuery(sentenciaSQL);
			//System.out.println("entro hay");
			query.setParameter(0,emailAccount.toString());
			//results = query.getResultList();
			List<Object[]> listDatos = query.getResultList();
			// for (Object[] datos : listDatos) {
			datos = listDatos.get(0);  
			System.out.println(datos[0] + "--" + datos[1] + "--" + datos[2] + "--" + (byte[])datos[3]);
			return datos;
			

		}catch (Exception e) {
			System.out.println("Error en el metodo consultarDatosUsuario - " + e.getMessage());
			return null;
		}finally {
			session.disconnect();
		}

	}
	
	
	public static Object [] validarUsuario(String email,String verification) {
		Session session = HibernateUtil.getSessionFactory().openSession();		
		Object [] datos;
		String sentenciaSQL = "Select a.name,a.last_name,a.id_account FROM Account a,Profile p Where p.profile_account = a.id_account and p.estatus = 0 and  a.email = ? and p.verification = ?";
		
		try{
			//HttpSession sesion = request.getSession();
			
			session.beginTransaction();
			Query query = session.createQuery(sentenciaSQL);
			//System.out.println("entro hay");
			query.setParameter(0,email);
			query.setParameter(1,verification);
			//results = query.getResultList();
			List<Object[]> listDatos = query.getResultList();
			// for (Object[] datos : listDatos) {
			datos = listDatos.get(0);  
			System.out.println(datos[0] + "--" + datos[1]+"--"+datos[2] );
			return datos;
			

		}catch (Exception e) {
			System.out.println("Error en el metodo validarUsuario - " + e.getMessage());
			return null;
		}finally {
			session.disconnect();
		}

	}
	
	
	
	@SuppressWarnings("finally")
	public static Object [] consultarDatos(int id_Author) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Object [] datos;
		String sentenciaSQL = "Select a.name,a.last_name,p.photo FROM Account a,Profile p Where p.profile_account = a.id_account and a.id_account = ?";
		
		try{
		
			session.beginTransaction();
			Query query = session.createQuery(sentenciaSQL);
			query.setParameter(0,id_Author);
			List<Object[]> results = query.getResultList();
			datos=results.get(0);
			System.out.println("Select Successful Datos");
			//System.out.println(datos[0] + "--" + datos[1] + "--" + datos[2] + "--");
			
			return datos;
			
			
		}catch (Exception e) {
			System.out.println("Error en el metodo consultarAccount - " + e.getMessage());
			return null;
		}finally {
			session.disconnect();
		}
		
	}
	
	public static String consultarAuthor(int IdAccount) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Object [] datos;
		String sentenciaSQL = "Select a.name,a.last_name,p.photo FROM Account a,Profile p Where p.profile_account = a.id_account and a.id_account = ?";
		
		try{
		
			session.beginTransaction();
			Query query = session.createQuery(sentenciaSQL);			
			query.setParameter(0,IdAccount);
			List<Object[]> results = query.getResultList();
			datos=results.get(0);
			//System.out.println(query.getResultList()+"");
			System.out.println("Select Successful Author");
			//if(results.isEmpty())
			String datosAuthor = ",\"nombre_author\":\""+ datos[0] + " " + datos[1] + "\",\"profile\":\"" + Base64.encode((byte[])datos[2]) + "\"}";
			
			return datosAuthor;
			
			
		}catch (Exception e) {
			System.out.println("Error en el metodo consultarAccount - " + e.getMessage());
			return null;
		}finally {
			session.disconnect();
		}
		
	}
	
	public static boolean actualizarReputacion(int author,String Ptn) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		//String sentenciaSQL = "Update a.reputation FROM Profile p Where p.profile_account = a.id_account and a.id_account = " + IdAccount;
		
		//Profile profile = (Profile)session.get(Profile.class, IdAccount);
		
			try{
				session.beginTransaction();
				
				String sentenciaSQL=("UPDATE profile SET profile.reputation = profile.reputation "+Ptn+ " where profile.profile_account = "+author);
				session.createSQLQuery(sentenciaSQL).executeUpdate();
				session.getTransaction().commit();
				return true;
		
			
		}catch (Exception e) {
			System.out.println("Error en el metodo actualizarReputacion - " + e.getMessage());
			return false;
		}finally {
			session.disconnect();
		}
		
	}
	
	public static boolean habilitarUsuario(int id_account) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		//String sentenciaSQL = "Update a.reputation FROM Profile p Where p.profile_account = a.id_account and a.id_account = " + IdAccount;
		
		//Profile profile = (Profile)session.get(Profile.class, IdAccount);
		
			try{
				session.beginTransaction();
				
				String sentenciaSQL=("UPDATE profile SET profile.estatus = "+true+" where profile.profile_account = "+id_account);
				session.createSQLQuery(sentenciaSQL).executeUpdate();
				session.getTransaction().commit();
				return true;
		
			
		}catch (Exception e) {
			System.out.println("Error en el metodo HablitarUsuario - " + e.getMessage());
			return false;
		}finally {
			session.disconnect();
		}
		
	}
	
	public static int ObtVlEdt(int Usuario) {
		System.out.println("EL usurio es :"+Usuario);
		Session session = HibernateUtil.getSessionFactory().openSession();
		int vlr=0;
		String sentenciaSQL = "Select p FROM Profile p Where p.profile_account = ?";
							
		try{
		
			session.beginTransaction();
			Query query = session.createQuery(sentenciaSQL);
			query.setParameter(0,Usuario);
			List<Profile> results = query.getResultList();
			int Rpt=results.get(0).getReputation();
			System.out.println("Select Successful ObtVlEdt");
			
			if(Rpt<=50){
				vlr=1;
				System.out.println("Puso 1");
				
			}else if(Rpt>50 && Rpt<70){
				vlr=3;
				
			}else if(Rpt>=70 && Rpt<100){
				vlr=5;
				
			}else{
				vlr=10;
			}
			
			return vlr;
			
			
		}catch (Exception e) {
			System.out.println("Error en el metodo  -  ObtVlEdt() " + e.getMessage());
			return -1;
		}finally {
			session.disconnect();
		}
		
	}
	
	public static String consultarDatosProfile(int IdAccount) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Object [] datos;
		String sentenciaSQL = "Select a.name,a.last_name,a.email,a.birth_date,a.number_phone,p.reputation,p.photo,s.correo,s.telefono FROM Account a,Profile p, Setting s Where p.profile_account = a.id_account and p.profile_account = s.id_account_profile and a.id_account = ?";
		
		try{
		
			session.beginTransaction();
			Query query = session.createQuery(sentenciaSQL);			
			query.setParameter(0,IdAccount);
			List<Object[]> results = query.getResultList();
			datos=results.get(0);
			//System.out.println(query.getResultList()+"");
			System.out.println("Select Successful Author");
			//if(results.isEmpty())
			String datosProfile = "[{\"nombre_author\":\""+ datos[0] + " " + datos[1] + "\", \"email\":\"" + datos[2] + "\", \"birth_date\":\"" + datos[3]    
					+ "\",\"number_phone\":\"" + datos[4] + "\",\"reputation\":" + datos[5] + ",\"profile\":\"" + Base64.encode((byte[])datos[6]) + 
					"\",\"id_author\":\"" +IdAccount + "\",\"correo\":"+datos[7]+",\"telefono\":"+datos[8]+"}]";
			
			return datosProfile;
			
			
		}catch (Exception e) {
			System.out.println("Error en el metodo consultarDatosProfile - " + e.getMessage());
			return null;
		}finally {
			session.disconnect();
		}
		
	}
	
	public static boolean registrarAjustes(Setting new_setting) {
		System.out.println("Entra a reg ajustes!");
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try{
			
			session.beginTransaction();		
			session.save(new_setting);
			session.getTransaction().commit();
			System.out.println("Insert Successful");
			return true;
			
		}catch (Exception e) {
			System.out.println("Error en el metodo registrarAjustes - " + e.getMessage());
			return false;
		}finally {
			session.disconnect();
		}

	}
	
	public static boolean actualizarAjustes(int author, boolean checkcorreo , boolean checktelefono, String nombre, String apellido, String contrasena, int telefono ) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
			try{
				session.beginTransaction();
				
				String sentenciaSQL= ("UPDATE Account INNER JOIN Setting ON Account.id_account = Setting.id_account_profile SET  "+
						 "Account.name = '" + nombre + "', Account.last_name = '" + apellido + "', Account.password = '" + contrasena + "', Account.number_phone = " + telefono 
						 + ", Setting.correo = "+checkcorreo + ", Setting.telefono = " + checktelefono + " where Account.id_account = "+author);
				System.out.println(sentenciaSQL);
				/*
				 * 
				 * UPDATE tabla1
        INNER JOIN tabla2 ON tabla1.ID = tabla2.ID 
        INNER JOIN tabla3 ON tabla1.ID = tabla3.ID 
        SET tabla1.campo1='$...', tabla2.campo2='$...', tabla3.campo3='$...'  
        WHERE tabla1.ID = '$ID'";
				 * 
				 * 
				 */
				
				session.createSQLQuery(sentenciaSQL).executeUpdate();
				session.getTransaction().commit();
				return true;
		
			
		}catch (Exception e) {
			System.out.println("Error en el metodo actualizarAjustes - " + e.getMessage());
			return false;
		}finally {
			session.disconnect();
		}
		
	}
	
	public static String consultarSettings(int IdAccount) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Object [] datos;
		String sentenciaSQL = "Select a.name,a.last_name,a.email,a.number_phone, s.correo, s.telefono, a.password FROM Account a, Setting s  Where a.id_account = s.id_account_profile and a.id_account = ?";
		System.out.println("consulta Settings " + sentenciaSQL);
		try{
		
			session.beginTransaction();
			Query query = session.createQuery(sentenciaSQL);			
			query.setParameter(0,IdAccount);
			List<Object[]> results = query.getResultList();
			datos=results.get(0);
			//System.out.println(query.getResultList()+"");
			System.out.println("Select Successful Settings");
			//if(results.isEmpty())
			String datosProfile = "[{\"nombre\":\""+ datos[0] + "\",\"apellido\": \"" + datos[1] + "\", \"email\":\"" + datos[2] + "\", \"number_phone\":" + datos[3]    
					+ ",\"correo\":" + datos[4] + ",\"telefono\":" + datos[5] + ",\"contrasena\":\"" + datos[6]	+ "\"}]";
			
			return datosProfile;
			
			
		}catch (Exception e) {
			System.out.println("Error en el metodo ConsultarSetings - " + e.getMessage());
			return null;
		}finally {
			session.disconnect();
		}
		
	}

	
	
	
	
	
	
}
