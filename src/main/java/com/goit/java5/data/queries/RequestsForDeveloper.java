package com.goit.java5.data.queries;

import java.util.List;

import com.goit.java5.connection.HibernateUtil;
import com.goit.java5.data.entity.Developer;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RequestsForDeveloper {
	private Session openSession() {
		return HibernateUtil.getINSTANCE().getSessionFactory().openSession();
	}

	public void createDeveloper(Developer developer) {
		try (Session session = openSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(developer);
			transaction.commit();
		}
	}

	public Developer getDeveloperById(int id) {
		try (Session session = openSession()) {
			return session.get(Developer.class, id);
		}
	}

	public List<Developer> listAllDeveloper() {
		try (Session session = openSession()) {
			return session.createQuery("from Developer", Developer.class).list();
		}
	}

	public void deleteDeveloperById(int id) {
		try (Session session = openSession()) {
			Transaction transaction = session.beginTransaction();
			session.remove(getDeveloperById(id));
			transaction.commit();
		}
	}

	public void updateDeveloper(Developer developer) {
		try (Session session = openSession()) {
			Transaction transaction = session.beginTransaction();
			session.merge(developer);
			transaction.commit();
		}
	}
}
