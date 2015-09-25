package com.iamnh.dao;

import org.hibernate.Session;

import com.iamnh.model.User;
import com.iamnh.util.HibernateUtil;

public class UserDao {
	public void update(User u){

		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User tu =  (User)session.load(User.class,1);
			tu.setUsername(u.getUsername());
			tu.setNickname(u.getNickname());
			tu.setPassword(u.getPassword());
		}catch(Exception e){
			e.printStackTrace();
			if(null != session){
				session.getTransaction().rollback();
			}
		}finally{
			HibernateUtil.close(session);
		}
	
	}
	
	public User load(int id){
		User tu  = null;
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			tu=  (User)session.load(User.class,id);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateUtil.close(session);
		}
		return tu;
	}
	
	public User load1(int id){
		User tu  = null;
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			tu=  (User)session.get(User.class,id);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateUtil.close(session);
		}
		return tu;
	}
}
