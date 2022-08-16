package com.goit.java5.data.queries;

import java.util.List;

import com.goit.java5.connection.HibernateUtil;
import com.goit.java5.data.entity.Skills;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RequestsForSkills {
	private Session openSession() {
		return HibernateUtil.getINSTANCE().getSessionFactory().openSession();
	}

	public void createSkills(Skills skills) {
		try (Session session = openSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(skills);
			transaction.commit();
		}
	}

	public Skills getSkillsById(int id) {
		try (Session session = openSession()) {
			return session.get(Skills.class, id);
		}
	}

	public List<Skills> listAllSkills() {
		try (Session session = openSession()) {
			return session.createQuery("from Skills", Skills.class).list();
		}
	}

	public void deleteSkillsById(int id) {
		try (Session session = openSession()) {
			Transaction transaction = session.beginTransaction();
			session.remove(getSkillsById(id));
			transaction.commit();
		}
	}

	public void updateSkills(Skills skills) {
		try (Session session = openSession()) {
			Transaction transaction = session.beginTransaction();
			session.merge(skills);
			transaction.commit();
		}
	}
}
