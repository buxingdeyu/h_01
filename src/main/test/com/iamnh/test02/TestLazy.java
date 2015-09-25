package com.iamnh.test02;

import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import org.junit.Test;

import com.iamnh.dao.UserDao;
import com.iamnh.model.User;
import com.iamnh.util.HibernateUtil;

public class TestLazy {
	@Test
	public void testLazy01() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = (User)session.load(User.class, 2);  //延迟加载，不发SQL，只有在真正用到这个对象时才会查询
			/**
			 * 延迟加载指的就是，当完成load操作之后，并不会马上发出sql,
			 * 当完成load之后，u其实是一个代理对象，这个代理对象中仅仅只有一个id的值 
			 * 
			 */
			System.out.println(u.getId()); //拿怕数据库里没有id等于2的记录也会输出2
			System.out.println(u.getUsername());
			session.getTransaction().commit();   
		} catch (Exception e) {
			e.printStackTrace();
			if (null != session) {
				session.getTransaction().rollback();
			}
		} finally {
			HibernateUtil.close(session);
		}

	}
	/**
	 * get与load的区别，如果数据库中没有该记录
	 * get会抛空指针异常
	 * load会抛ObjectNotFoundException
	 */
	@Test
	public void testLazy03() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = (User)session.get(User.class, 2);  //延迟加载，不发SQL，只有在真正用到这个对象时才会查询
			/**
			 * 延迟加载指的就是，当完成load操作之后，并不会马上发出sql,
			 * 当完成load之后，u其实是一个代理对象，这个代理对象中仅仅只有一个id的值 
			 * 
			 */
			System.out.println(u.getId()); //拿怕数据库里没有id等于2的记录也会输出2
			System.out.println(u.getUsername());
			session.getTransaction().commit();   
		} catch (Exception e) {
			e.printStackTrace();
			if (null != session) {
				session.getTransaction().rollback();
			}
		} finally {
			HibernateUtil.close(session);
		}

	}
	@Test
	public void testLazy02() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = (User)session.get(User.class, 3);
			session.getTransaction().commit();   
		} catch (Exception e) {
			e.printStackTrace();
			if (null != session) {
				session.getTransaction().rollback();
			}
		} finally {
			HibernateUtil.close(session);
		}

	}
	
	@Test(expected = LazyInitializationException.class)
	public void testQuestion01(){
		UserDao dao = new UserDao();
		User u = dao.load(1);
		System.out.println(u.getNickname());
		
	}
	@Test 
	public void testQuestion02(){
		UserDao dao = new UserDao();
		User u = dao.load1(1);
		System.out.println(u.getNickname());
		
	}
}
