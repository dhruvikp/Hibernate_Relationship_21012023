package com.simplilearn.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.simplilearn.entity.Courses;
import com.simplilearn.entity.PhoneNumber;
import com.simplilearn.entity.Student;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory buildSessionFactory() {
		if (sessionFactory == null) {
			Configuration config = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
					.addAnnotatedClass(PhoneNumber.class).addAnnotatedClass(Courses.class);
			sessionFactory = config.buildSessionFactory();
		}
		return sessionFactory;
	}
}
