package com.drizzle.persistence;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.drizzle.model.Account;

public class hibernateTransations {

	public static boolean registrarAccount(Account new_account) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		System.out.println("Result Insert:" + session.save(new_account).toString());
		
		session.getTransaction().commit();

		session.disconnect();

		return true;

	}
	
	
	public static boolean consultarAccount(String emailAccount) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		String sentenciaSQL = "Select a FROM Account as a Where a.email = ?";
		
		Query query = session.createQuery(sentenciaSQL);
		
		query.setParameter(0,emailAccount);
		
		List<Account> results = query.getResultList();
		
		session.disconnect();

		return results.isEmpty();

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
	
}
