package com.casa.booking.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.casa.booking.model.Company;
import com.casa.booking.model.Store;
import com.casa.booking.model.User;
import com.casa.booking.util.CasaConstants;

public class StoreDAO {

	public void saveOrUpdate(Store store){
		if(store.getDistrict()!=null){
			store.setDistrict(store.getDistrict().toUpperCase());
		}
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(store);
		session.getTransaction().commit();
		session.close();
	}
	/*
	public Store getByCompanyCode(long storeId){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Store store=null;;
		try {
			List<Store> cList = session.createQuery("from Store where code = ?").setLong(0, companyCode).list();
			//store = (Store)session.get(Store.class, storeId);

		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
			
		return store;
	}
	*/
	public Store getById(long storeId){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Store store=null;;
		try {
			store = (Store)session.get(Store.class, storeId);

		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
			
		return store;
	}
	
	public void delete(long storeId){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Store store=null;
		try {
			store = (Store)session.get(Store.class, storeId);
			if(store!=null){
				session.beginTransaction();
				//session.delete(store);
				store.setStatus(CasaConstants.DELETE_STATE);
				Set<User> users = store.getUsers();
				if(users!=null){
					for(User u : users){
						u.setStatus(CasaConstants.DELETE_STATE);
						session.saveOrUpdate(u);
					}
				}
				session.saveOrUpdate(store);
				session.getTransaction().commit();
			}

		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Store> getStoresByDistrict(String dist, long companyId){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<Store> stores=null;
		try {
			dist=dist.toUpperCase();
			stores = session.createQuery("from Store where district = ? and company.companyId = ? and status = ?")
					.setString(0, dist).setLong(1, companyId).setString(2, CasaConstants.ACTIVE_STATE).list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stores;
	}
	
	@SuppressWarnings("unchecked")
	public List<Store> getStoresByDistrictAndStoreName(String dist, String storeName, long companyId){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<Store> stores=null;
		try {
			dist=dist.toUpperCase();
			stores = session.createQuery("from Store where district = ? and name = ? and company.companyId = ? and status = ?")
					.setString(0, dist).setString(1, storeName).setLong(2, companyId).setString(3, CasaConstants.ACTIVE_STATE).list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stores;
	}
	
	@SuppressWarnings("unchecked")
	public List<Store> getStoresByComapnyId(long companyId){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<Store> stores=null;
		try {
			stores = session.createQuery("from Store where company.companyId = ? and status = ?")
					.setLong(0, companyId).setString(1, CasaConstants.ACTIVE_STATE).list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stores;
	}
	
	@SuppressWarnings("unchecked")
	public List<Store> getAll(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<Store> stores=null;
		try {
			stores = session.createQuery("from Store where status = ?")
					.setString(0, CasaConstants.ACTIVE_STATE).list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stores;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getUniqueDistricts(Company affiliate){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<String> stores=null;
		try {
			stores = session.createCriteria(Store.class).add(Restrictions.eq("company",affiliate)).add(Restrictions.eq("status",CasaConstants.ACTIVE_STATE))
					.setProjection(Projections.distinct(Projections.property("district"))).list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stores;
	}
	
	public static void main(String[] args) {
		StoreDAO dao = new StoreDAO();
		List<Store> li = dao.getStoresByComapnyId(45243l);
		System.out.println(li.size());
		
	}
}
