package com.goit.java5.connection;

import com.goit.java5.data.entity.Companies;
import com.goit.java5.data.entity.Customers;
import com.goit.java5.data.entity.Developer;
import com.goit.java5.data.entity.Projects;
import com.goit.java5.data.entity.Skills;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final HibernateUtil INSTANCE;

	private final SessionFactory sessionFactory;

	static {
		INSTANCE = new HibernateUtil();
	}

	private HibernateUtil() {
		sessionFactory = new Configuration()
				.addAnnotatedClass(Companies.class)
				.addAnnotatedClass(Customers.class)
				.addAnnotatedClass(Developer.class)
				.addAnnotatedClass(Projects.class)
				.addAnnotatedClass(Skills.class)
				.buildSessionFactory();

	}

	public static HibernateUtil getINSTANCE() {
		return INSTANCE;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void close() {
		sessionFactory.close();
	}
}
