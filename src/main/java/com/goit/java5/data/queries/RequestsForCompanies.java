package com.goit.java5.data.queries;

import java.util.List;

import com.goit.java5.connection.HibernateUtil;
import com.goit.java5.data.entity.Companies;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class RequestsForCompanies {

	private Session openSession() {
		return HibernateUtil.getINSTANCE().getSessionFactory().openSession();
	}

	public void createCompanies(Companies companies) {
		try (Session session = openSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(companies);
			transaction.commit();
		}
	}

	public Companies getCompaniesById(int id) {
		try (Session session = openSession()) {
			return session.get(Companies.class, id);
		}
	}

	public List<Companies> selectAllCompanies() {
		try (Session session = openSession()) {
			return session.createQuery("from Companies", Companies.class).list();
		}
	}

	public void deleteCompaniesById(int id) {
		try (Session session = openSession()) {
			Transaction transaction = session.beginTransaction();
			session.remove(getCompaniesById(id));
			transaction.commit();
		}
	}

	public void updateCompanies(Companies companies) {
		try (Session session = openSession()) {
			Transaction transaction = session.beginTransaction();
			session.merge(companies);
			transaction.commit();
		}
	}
}
