package com.iamnh.test02;

import org.hibernate.Session;
import org.junit.Test;

import com.iamnh.model.IDCard;
import com.iamnh.model.Person;
import com.iamnh.model.User;
import com.iamnh.util.HibernateUtil;

public class TestOne2One {
	@Test
	public void testAdd() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Person p = new Person();
			p.setName("3223");
			session.save(p);
			
			IDCard card = new IDCard();
			card.setNo("sdf");
			card.setPerson(p);
			session.save(card);
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
	public void testAdd02() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Person p = new Person();
			p.setName("3223");
			session.save(p);
			
			IDCard card = new IDCard();
			card.setNo("sdf");
			card.setPerson(p);
			session.save(card);
			IDCard card2 = new IDCard();
			card2.setNo("sdf");
			card2.setPerson(p);
			session.save(card2);
			session.getTransaction().commit();   //报了错，unique后保存两个对象后保存报错
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
	public void testAdd03() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			/**
			 * 由于使用的是IDCard来维护关系（外键在哪一端就由哪一端来维护），
			 * 通过p.setIdCard就无效，所以关系不会更新
			 */
			IDCard card = new IDCard();
			card.setNo("sdf");
			session.save(card);
			Person p = new Person();
			p.setName("3223");
			p.setIdCard(card); 
			session.save(p); 
			
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
	public void testAdd04() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			/**
			 * 使用Pesron来维护关系，可以更新关系
			 */
			Person p = new Person();
			p.setName("bbbb1");
			session.save(p); 
			IDCard card = new IDCard();
			card.setNo("1111a");
			card.setPerson(p);
			session.save(card);
			
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
	public void testLoad01() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Person p = (Person)session.load(Person.class, 8);
			//只要取出的是没有维护关系的这一方，会自动将关联对象取出
			//由于person端没有维护关系，所以不会延迟加载
			System.out.println(p.getName());
			System.out.println(p.getIdCard().getNo());
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
	public void testLoad02() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			IDCard id = (IDCard)session.load(IDCard.class, 3);
			/**
			 * 没有使用Idcard的persion会延迟加载 ，目前只是发一条sql
			 */
			System.out.println(id.getNo());
			
			//取person,同时也会取出person的idcard,这里不会使用join来取出
			System.out.println(id.getPerson().getName());
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
	 * 最佳实践：one2one的使用最好不要使用双向关联，如果要使用双向关联，尽量从没有维护关系的一边取数据
	 * hibernate会自动完成join，仅仅只会发出一条sql,
	 * 如果使用维护关系取数据，在通过延迟加载取关联对象时，会同时再取persion和Idcard关联，所以会发3条sql
	 */
}
