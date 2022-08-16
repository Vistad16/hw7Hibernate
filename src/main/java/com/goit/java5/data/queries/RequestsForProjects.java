package com.goit.java5.data.queries;

import java.util.List;

import com.goit.java5.connection.HibernateUtil;
import com.goit.java5.data.entity.Projects;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RequestsForProjects {
	private Session openSession() {
		return HibernateUtil.getINSTANCE().getSessionFactory().openSession();
	}

	public void createProjects(Projects projects) {
		try (Session session = openSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(projects);
			transaction.commit();
		}
	}

	public Projects getProjectsById(int id) {
		try (Session session = openSession()) {
			return session.get(Projects.class, id);
		}
	}

	public List<Projects> listAllProjects() {
		try (Session session = openSession()) {
			return session.createQuery("from Projects", Projects.class).list();
		}
	}

	public void deleteProjectsById(int id) {
		try (Session session = openSession()) {
			Transaction transaction = session.beginTransaction();
			session.remove(getProjectsById(id));
			transaction.commit();
		}
	}

	public void updateProjects(Projects projects) {
		try (Session session = openSession()) {
			Transaction transaction = session.beginTransaction();
			session.merge(projects);
			transaction.commit();
		}
	}
}
