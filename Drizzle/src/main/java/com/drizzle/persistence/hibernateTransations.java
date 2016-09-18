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
			System.out.println("Insert Succesfull");
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
			System.out.println("RESULT!!!");	
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
			
			File photo = new File("/home/asus/Escritorio/images.jpeg");
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

			System.out.println("Error en el metodo registrarProfile - " + e.getMessage());
			/*String sentenciaSQL = "delete FROM Account where id_account = ?";
			Query query = session.createQuery(sentenciaSQL);	
			query.setParameter(0, idAccount+"");
			int result = query.executeUpdate();
			System.out.println("Result: " + result);*/
			return false;
			
		}finally {
			session.disconnect();
		}
		
	}
	
	
	
	
}
