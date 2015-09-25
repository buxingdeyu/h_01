package com.iamnh.test02;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.iamnh.model.User;
import com.iamnh.util.HibernateUtil;

public class TestCRUD {
	
	@Test
	public void testAdd() {
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = new User();
			u.setBorn(new Date());
			u.setNickname("步行的鱼1");
			u.setPassword("1111");
			u.setUsername("bcc1");
			session.save(u);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			if(null != session){
				session.getTransaction().rollback();
			}
		}finally{
			HibernateUtil.close(session);
		}
		
	}
	
	@Test
	public void testLoad(){
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			User u = (User)session.load(User.class, 3);
			System.out.println(u.toString());
		}catch(Exception e){
			e.printStackTrace();
			if(null != session){
				session.getTransaction().rollback();
			}
		}finally{
			HibernateUtil.close(session);
		}
	}
	@Test
	public void testDel(){
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = new User();
			u.setId(2);
			session.delete(u);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			if(null != session){
				session.getTransaction().rollback();
			}
		}finally{
			HibernateUtil.close(session);
		}
	}
	@Test
	public void testUpdate(){
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u =  (User)session.load(User.class,2);
			u.setBorn(new Date());
			u.setNickname("步行的鱼222");
			u.setPassword("111122");
			u.setUsername("bcc22");
			session.update(u);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			if(null != session){
				session.getTransaction().rollback();
			}
		}finally{
			HibernateUtil.close(session);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testList(){
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			List<User> users = session.createQuery("from User").list();
			System.out.println(users.size());
		}catch(Exception e){
			e.printStackTrace();
			if(null != session){
				session.getTransaction().rollback();
			}
		}finally{
			HibernateUtil.close(session);
		}
	}
	
	@Test
	public void testPager(){
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			List<User> users = session.createQuery("from User").setFirstResult(1).setMaxResults(2).list();
			System.out.println(users.size());
			for(User u : users){
				System.out.println(u.getId());
			}
		}catch(Exception e){
			e.printStackTrace();
			if(null != session){
				session.getTransaction().rollback();
			}
		}finally{
			HibernateUtil.close(session);
		}
	}
	
	
}
