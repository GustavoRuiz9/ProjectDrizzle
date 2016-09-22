package com.drizzle.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.drizzle.model.Account;
import com.drizzle.model.Profile;

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
	public static boolean consultarAccount(String emailAccount) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Account> results;
		String sentenciaSQL = "Select a FROM Account as a Where a.email = ?";
		
		try{
		
			session.beginTransaction();
			Query query = session.createQuery(sentenciaSQL);			
			query.setParameter(0,emailAccount);
			results = query.getResultList();
			System.out.println("Select Successful");	
			return results.isEmpty();
			
		}catch (Exception e) {
			System.out.println("Error en el metodo consultarAccount - " + e.getMessage());
			return false;
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
			
			File photo = new File("C:/Users/RICARDO OSPINA/WorkspaceSpring/ProjectDrizzle/Drizzle/src/main/webapp/resources/img/perfil/avatar.png");
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
	
	public static boolean actualizarProfile(Profile profile) {
		System.out.println("inicia metodo actualizarAccount!");
		Session session = HibernateUtil.getSessionFactory().openSession();
		
			try{
				session.beginTransaction();		
				session.update(profile);
				session.getTransaction().commit();
				return true;
				
			}catch (Exception e) {
				System.out.println("Error en el metodo actualizarAccount - " + e.getMessage());
				return false;
			}finally {
				session.disconnect();
			}

	}
	
	
	
	
	
	
}
