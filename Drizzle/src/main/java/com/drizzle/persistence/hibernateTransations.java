package com.drizzle.persistence;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.drizzle.model.Account;

public class hibernateTransations {

	public static boolean registrar(Account new_account) {

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
