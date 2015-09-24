package com.iamnh.test01;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

import com.iamnh.model.User;

public class FirstTest {

	@Test
	public void testSave(){
		Configuration cfg = new Configuration().configure();
//		cfg.buildSessionFactory()
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
		Session session = factory.openSession();
		session.beginTransaction();
		User u = new User();
		u.setNickname("张三");
		u.setUsername("zhangsan");
		u.setBorn(new Date());
		u.setPassword("123");
		session.save(u);
		session.getTransaction().commit();
	}
}
