package com.iamnh.test02;

import org.hibernate.Session;
import org.junit.Test;

import com.iamnh.model.Classroom;
import com.iamnh.model.Student;
import com.iamnh.util.HibernateUtil;

public class TestMany2One {
	@Test
	public void testAdd(){
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Classroom c = new Classroom();
			c.setGrade("2011");
			c.setName("计算机");
			session.save(c);
			Student stu1 = new Student();
			stu1.setClassroom(c);
			stu1.setName("abc");
			session.save(stu1);
			Student stu2 = new Student();
			stu2.setClassroom(c);
			stu2.setName("2232");
			session.save(stu2);
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
	public void testAdd2(){
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Student stu1 = new Student();
			stu1.setName("abc333");
			session.save(stu1);
			Student stu2 = new Student();
			stu2.setName("22322322");
			session.save(stu2);
			Classroom c = new Classroom();
			c.setGrade("2011");
			c.setName("计算机");
			session.save(c);
			stu2.setClassroom(c);
			stu1.setClassroom(c);
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
	public void testAdd3(){
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Classroom c = new Classroom();
			c.setGrade("2011");
			c.setName("计算机");
			//此时classroom没有存，所有添加student时没有外键，会报错
			Student stu1 = new Student();
			stu1.setClassroom(c);
			stu1.setName("3243432");
			session.save(stu1);
			Student stu2 = new Student();
			stu2.setClassroom(c);
			stu2.setName("2234434432");
			session.save(stu2);
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
	public void testAdd4(){
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Classroom c = new Classroom();
			c.setGrade("2011");
			c.setName("计算机");
			//此时classroom没有存，加了cascade="all" 会级联保存classroom
			Student stu1 = new Student();
			stu1.setClassroom(c);
			stu1.setName("3243432");
			session.save(stu1);
			Student stu2 = new Student();
			stu2.setClassroom(c);
			stu2.setName("2234434432");
			session.save(stu2);
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
			Student stu1 = (Student)session.load(Student.class, 2);
			System.out.println(stu1.getName());
			System.out.println(stu1.getClassroom().getName());//关联对象classroot也是延迟加载的
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
	public void testLoad2(){
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Classroom c = (Classroom)session.load(Classroom.class, 1);
			System.out.println(c.getStus().size());
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
	public void testDeleted(){
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();

			Student stu1 = (Student) session.get(Student.class, 10);
			session.delete(stu1); //报错，删除student会删除classroom,还有一个student依赖classroom
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
