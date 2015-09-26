package com.iamnh.test02;

import org.hibernate.Session;
import org.junit.Test;

import com.iamnh.model.Classroom;
import com.iamnh.model.Comment;
import com.iamnh.model.Message;
import com.iamnh.model.Student;
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
	
	/**
	 * 双向一对多
	 */
	@Test
	public void testAdd01(){
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Student stu1 = new Student();
			stu1.setName("猪八戒");
			stu1.setNo("133");
			session.save(stu1);
			Student stu2 = new Student();
			stu2.setName("猪纠结");
			stu2.setNo("134");
			session.save(stu2);
			Classroom cls = new Classroom();
			cls.setGrade("1900");
			cls.setName("取经班");
			
			cls.addStu(stu1);
			cls.addStu(stu2);
			session.save(cls);
			session.getTransaction().commit();
			/**
			 * 也会发出5条SQL，3条插入两条更新，所以不建议在一的一端维护关系
			 * 在一的一方配置文件中加 inverse=true;明确一的端不维护关系
			 */
		}catch(Exception e){
			e.printStackTrace();
			if(null != session){
				session.getTransaction().rollback();
			}
		}finally{
			HibernateUtil.close(session);
		}
		
	}
	
	/**
	 * testAdd01方法优化
	 */
	@Test
	public void testAdd02(){
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Classroom cls = new Classroom();
			cls.setGrade("19002");
			cls.setName("取经班222");
			session.save(cls);
			Student stu1 = new Student();
			stu1.setName("猪八戒22");
			stu1.setNo("13322");
			stu1.setClassroom(cls);
			session.save(stu1);
			Student stu2 = new Student();
			stu2.setName("猪纠结22");
			stu2.setNo("13422");
			stu2.setClassroom(cls);
			session.save(stu2);
			
			session.getTransaction().commit();
			/**
			 * 也会发出5条SQL，3条插入两条更新，所以不建议在一的一端维护关系
			 * 在一的一方配置文件中加 inverse=true;明确一的端不维护关系
			 */
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
	public void testLoad03(){
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Classroom cla = (Classroom)session.load(Classroom.class, 7);
			System.out.println(cla.getStus().size());
			
			Student stu1 = (Student)session.load(Student.class, 17);
			System.out.println(stu1.getName()+"--"+stu1.getClassroom().getName());
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
