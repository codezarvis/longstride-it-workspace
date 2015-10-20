package com.casa.booking.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;

import com.casa.booking.model.User;
import com.casa.booking.util.CasaConstants;

public class UserDAO {

	public void save(User user){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(user);
		session.getTransaction().commit();
		session.close();
	}
	
	public User getByPrimaryId(long primaryKey){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		User user=null;;
		try {
			user = (User)session.get(User.class, primaryKey);

		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
			
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public User getUserById(String userId){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(User.class);
		List<User> user = null;
		try{
			Conjunction cj = Restrictions.conjunction();
			cj.add(Restrictions.eq("userId", userId));
			cj.add(Restrictions.eq("status", CasaConstants.ACTIVE_STATE));
			user = cr.add(cj).list();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		if(user!=null && user.size()>0)
			return user.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public User getUserByOdyUserId(String odyUserId){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(User.class);
		List<User> user = null;
		try{
			Conjunction cj = Restrictions.conjunction();
			cj.add(Restrictions.eq("odyUserId", odyUserId));
			cj.add(Restrictions.eq("status", CasaConstants.ACTIVE_STATE));
			user = cr.add(cj).list();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		if(user!=null && user.size()>0)
			return user.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public User getUserByCustomerCode(String custCode){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(User.class);
		List<User> user = null;
		try{
			Conjunction cj = Restrictions.conjunction();
			cj.add(Restrictions.eq("customerCode", custCode));
			cj.add(Restrictions.eq("status", CasaConstants.ACTIVE_STATE));
			user = cr.add(cj).list();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		if(user!=null && user.size()>0)
			return user.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public User getUserByOdyId(String odyUserId){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<User> user = null;
		try{
			user = session.createQuery("from User where odyUserId = ?").setString(0, odyUserId).list();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		if(user!=null && user.size()>0)
			return user.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllActiveUsers(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<User> users = null;
		try{
			users = session.createQuery("from User where status = ?").setString(0, CasaConstants.ACTIVE_STATE).list();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return users;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllActiveUsersByCompanyId(long affiliateId){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<User> users = null;
		try{
			users = session.createQuery("from User where status = ? and store.company.companyId = ?").setString(0, CasaConstants.ACTIVE_STATE).setLong(1, affiliateId).list();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return users;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllActiveUsersByCompanyIdAndDist(long affiliateId, String dist){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<User> users = null;
		try{
			users = session.createQuery("from User where status = ? and store.company.companyId = ? and store.district = ? ").
					setString(0, CasaConstants.ACTIVE_STATE).setLong(1, affiliateId).setString(2, dist).list();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return users;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllActiveUsersByStoreId(long storeId){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<User> users = null;
		try{
			users = session.createQuery("from User where status = ? and store.storeId = ?").setString(0, CasaConstants.ACTIVE_STATE).setLong(1, storeId).list();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return users;
	}
}
