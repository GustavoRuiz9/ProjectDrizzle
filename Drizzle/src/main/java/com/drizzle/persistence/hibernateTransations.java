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
import com.drizzle.model.Profile;
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
	public static List consultarAccount(String emailAccount) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Account> results;
		String sentenciaSQL = "Select a FROM Account as a Where a.email = ?";
		
		try{
		
			session.beginTransaction();
			Query query = session.createQuery(sentenciaSQL);			
			query.setParameter(0,emailAccount);
			results = query.getResultList();
			//System.out.println(query.getResultList()+"");
			System.out.println("Select Successful");
			//if(results.isEmpty())
				return results;
			
			
			
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
	
	
	public static boolean registrarProfile(int idAccount) {

		Profile new_profile = new Profile();
		new_profile.setProfile_account(idAccount);
		new_profile.setReputation(0);
		new_profile.setAuto_ubication(false);
		new_profile.setEstatus(true);
		
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
		String sentenciaSQL = "Select a.id_account,a.name,a.last_name,p.photo FROM Account a,Profile p Where p.profile_account = a.id_account and a.email = ?";
		
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
	
	
	
	
	
	
	
}
