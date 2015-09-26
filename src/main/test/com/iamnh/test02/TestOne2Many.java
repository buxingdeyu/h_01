package com.iamnh.test02;

import org.hibernate.Session;
import org.junit.Test;

import com.iamnh.model.Comment;
import com.iamnh.model.Message;
import com.iamnh.util.HibernateUtil;

public class TestOne2Many {
	@Test
	public void testAdd(){
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Comment c1 = new Comment();
			c1.setContent("comment01");
			Comment c2 = new Comment();
			c2.setContent("comment02");
			session.save(c1);
			session.save(c2);
			Message msg = new Message();
			msg.setContent("msg001");
			msg.setTitle("msgTitle01");
			msg.addComment(c1);
			msg.addComment(c2);
			session.save(msg);
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
			session.beginTransaction();
			Message msg = (Message)session.load(Message.class, 2);
			for(Comment cmt:msg.getComments()){
				System.out.println(cmt.getContent());
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
	@Test
	public void testLoad02(){
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Message msg = (Message)session.load(Message.class, 2);
			System.out.println(msg.getComments().size());
			for(Comment cmt:msg.getComments()){
				System.out.println(cmt.getContent());
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
