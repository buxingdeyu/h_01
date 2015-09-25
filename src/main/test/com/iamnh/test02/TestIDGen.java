package com.iamnh.test02;

import org.hibernate.Session;
import org.junit.Test;

import com.iamnh.model.Book;
import com.iamnh.util.HibernateUtil;

public class TestIDGen {
	
	@Test
	public void testAssign(){
		Session session = null;
		session = HibernateUtil.openSession();
		session.beginTransaction();
		Book b = new Book();
		b.setName("Thinking in java");
		b.setPrice(12.11);
		session.save(b);
		session.getTransaction().commit();
	}
}
