package com.goit.java5.data.queries;

import java.util.List;

import com.goit.java5.connection.HibernateUtil;
import com.goit.java5.data.entity.Customers;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class RequestsForCustomers {
	private Session openSession() {
		return HibernateUtil.getINSTANCE().getSessionFactory().openSession();
	}

	public void createCustomers(Customers customers) {
		try (Session session = openSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(customers);
			transaction.commit();
		}
	}

	public Customers getCustomersById(int id) {
		try (Session session = openSession()) {
			return session.get(Customers.class, id);
		}
	}

	public List<Customers> selectAllCustomers() {
		try (Session session = openSession()) {
			return session.createQuery("from Customers", Customers.class).list();
		}
	}

	public void deleteCustomersById(int id) {
		try (Session session = openSession()) {
			Transaction transaction = session.beginTransaction();
			session.remove(getCustomersById(id));
			transaction.commit();
		}
	}

	public void updateCustomers(Customers customers) {
		try (Session session = openSession()) {
			Transaction transaction = session.beginTransaction();
			session.merge(customers);
			transaction.commit();
		}
	}
}
