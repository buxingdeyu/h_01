package com.iamnh.test02;

import org.hibernate.Session;
import org.junit.Test;

import com.iamnh.model.Course;
import com.iamnh.model.Teacher;
import com.iamnh.model.TeacherCourse;
import com.iamnh.util.HibernateUtil;

/**
 * 多对多，由两个一对多实现
 * @author BccLj
 *
 */
public class TestMany2Many2 {
	
	@Test
	public void testAdd01(){

		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Teacher t1 = new Teacher();
			t1.setName("老张");
			session.save(t1);
			Teacher t2= new Teacher();
			t2.setName("老刘");
			session.save(t2);
			Course c1 = new Course();
			c1.setName("数据结构");
			session.save(c1);
			Course c2 = new Course();
			c2.setName("计算机组成原理");
			session.save(c2);
			
			TeacherCourse tc1 = new TeacherCourse();
			tc1.setAch(33.1);
			tc1.setTeacher(t1);
			tc1.setCourse(c1);
			session.save(tc1);
			TeacherCourse tc2 = new TeacherCourse();
			tc2.setAch(133.1);
			tc2.setTeacher(t1);
			tc2.setCourse(c2);
			session.save(tc2);
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
			Teacher t1 = (Teacher)session.load(Teacher.class, 3);
			System.out.println(t1.getName());
			for(TeacherCourse tc :t1.getTcs()){
				System.out.println(tc.getCourse().getName()+":"+tc.getAch());
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
