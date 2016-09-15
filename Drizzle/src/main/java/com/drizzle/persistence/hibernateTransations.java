package com.drizzle.persistence;

import org.hibernate.Session;

import com.drizzle.model.Account;

public class hibernateTransations {

	public static boolean registrar(Account new_account) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		session.save(new_account);

		session.getTransaction().commit();

		session.disconnect();

		return true;

	}
}
