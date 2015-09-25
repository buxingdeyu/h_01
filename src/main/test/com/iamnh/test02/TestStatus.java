package com.iamnh.test02;

import java.util.Date;

import org.hibernate.Session;
import org.junit.Test;

import com.iamnh.model.User;
import com.iamnh.util.HibernateUtil;

public class TestStatus {
	
	/**
	 * 瞬时状态
	 */
	@Test
	public void testTransient() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = new User();
			u.setBorn(new Date());
			u.setNickname("瞬时状态");
			u.setPassword("1111");
			u.setUsername("transient");
			//以上u是Transient瞬时状态
			//执行save后，被session管理，而且存数据存中已经存在，此时就是Persistent状态 
			session.save(u);
			session.getTransaction().commit();  //输出两条sql 一条insert一条update
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
	public void testPersistent02() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = new User();
			u.setBorn(new Date());
			u.setNickname("瞬时状态");
			u.setPassword("1111");
			u.setUsername("transient");
			//以上u是Transient瞬时状态
			//执行save后，被session管理，而且存数据存中已经存在，此时就是Persistent状态 
			session.save(u);
			//此时u是持久化状态，已经被session所管理，当提交时，会把session中的对象和目前的对象进行比较
			u.setNickname("ChangeNickName");
			session.update(u);  //没有意义，只有在commit时才会执行
			u.setBorn(new Date());
			session.update(u);//没有意义，只有在commit时才会执行
			session.getTransaction().commit();  //输出两条sql 一条insert一条update
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
	public void testPersistent01() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = new User();
			u.setBorn(new Date());
			u.setNickname("瞬时状态");
			u.setPassword("1111");
			u.setUsername("transient");
			//以上u是Transient瞬时状态
			//执行save后，被session管理，而且存数据存中已经存在，此时就是Persistent状态 
			session.save(u);
			//此时u是持久化状态，已经被session所管理，当提交时，会把session中的对象和目前的对象进行比较
			u.setNickname("ChangeNickName");
			session.getTransaction().commit();//输出两条sql 一条insert一条update
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
	public void testPersistent03() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = new User();
			u.setBorn(new Date());
			u.setNickname("中人饿");
			u.setPassword("faddf");
			u.setUsername("persistent");
			//以上u是Transient瞬时状态
			//执行save后，被session管理，而且存数据存中已经存在，此时就是Persistent状态 
			session.save(u);
			//此时u是持久化状态，已经被session所管理，当提交时，会把session中的对象和目前的对象进行比较
			session.save(u);//没有意义，只有在commit时才会执行
			session.update(u);  //没有意义，只有在commit时才会执行
			session.update(u);//没有意义，只有在commit时才会执行
			session.getTransaction().commit();  //输出一条insert sql
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
	public void testPersistent04() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = new User();
			u.setBorn(new Date());
			u.setNickname("中人饿");
			u.setPassword("faddf");
			u.setUsername("persistent");
			//以上u是Transient瞬时状态
			//执行save后，被session管理，而且存数据存中已经存在，此时就是Persistent状态 
			session.save(u);
			//此时u是持久化状态，已经被session所管理，当提交时，会把session中的对象和目前的对象进行比较
			session.save(u);//没有意义，只有在commit时才会执行
			session.update(u);  //没有意义，只有在commit时才会执行
			session.update(u);//没有意义，只有在commit时才会执行
			u.setUsername("sdagaw");
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
	public void testPersistent05() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = (User)session.load(User.class, 11);
			u.setNickname("322sfd");
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
	public void testPersistent06() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = (User)session.load(User.class, 10);
			session.update(u);
			session.getTransaction().commit();   //没sql load有延迟加载，只有在真正使用时才会发出sql
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
	public void testPersistent07() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = (User)session.get(User.class, 10);
			session.update(u);
			session.getTransaction().commit();   //一条select sql
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
	public void testPersistent08() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = (User)session.load(User.class, 10);
			u.setNickname("adsf");
			u.setUsername("213423");
			session.update(u);
			session.getTransaction().commit();   //没sql load有延迟加载，只有在真正使用时才会发出sql
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
	public void testPersistent09() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = (User)session.load(User.class, 10);
			session.update(u);
			session.getTransaction().commit();   
			session.beginTransaction();
			u.setUsername("213423");
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
	public void testDetach01() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = new User();
			u.setId(10);
			u.setUsername("abc");
			session.save(u);
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
	public void testDetach02() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = new User();
			u.setId(10);
			u.setUsername("abc");
			session.update(u);
			u.setNickname("testDetach02");
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
	public void testDetach03() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = new User();
			u.setId(10);
			u.setUsername("abc");
			session.update(u);
			u.setNickname("testDetach02");
			
			u.setId(33);
			session.getTransaction().commit();  //改ID会抛异常
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
	public void testDetach04() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = new User();
			u.setId(10);
			session.delete(u);
			//现在u就是瞬时对象,不被session所管理 
			u.setNickname("abc"); //u已经是瞬时对象，不会被session所管理，不会发出update
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
	public void testDetach05() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = new User();
			u.setNickname("abc");
			//现在u就是瞬时对象,不被session所管理 
			session.saveOrUpdate(u);
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
	public void testDetach06() {
		Session session = null;
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User u = (User)session.load(User.class, 3);
			User u2 = new User();
			u2.setId(3);
			u2.setPassword("ffff");
			session.merge(u2);
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
}
