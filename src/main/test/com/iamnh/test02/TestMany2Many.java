package com.iamnh.test02;

import org.hibernate.Session;
import org.junit.Test;

import com.iamnh.model.Admin;
import com.iamnh.model.Role;
import com.iamnh.util.HibernateUtil;

public class TestMany2Many {
	@Test
	public void testAdd(){
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Admin a1 = new Admin();
			a1.setName("a1");
			session.save(a1);
			Admin a2 = new Admin();
			a2.setName("a2");
			session.save(a2);
			Role r1 = new Role();
			r1.setName("管理员1");
			session.save(r1);
			
			Role r2 = new Role();
			r2.setName("管理员2");
			session.save(r2);
			r1.add(a1);
			r2.add(a1);
			r2.add(a2);
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
	public void testLoad01(){
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Admin a = (Admin)session.load(Admin.class, 5);
			for(Role r :a.getRoles()){
				System.out.println(r.getName());
			}
			
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
}
