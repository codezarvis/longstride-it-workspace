package com.casa.booking.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;

import com.casa.booking.model.Offers;

public class OffersDAO {

	public void save(Offers offer){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(offer);
		session.getTransaction().commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public Offers getStoreOffer(Long storeId, Long compnayId, Date expiryDate){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<Offers> templates = null;
		try{
			Criteria cr = session.createCriteria(Offers.class);
			Conjunction cj = Restrictions.conjunction();
			cj.add(Restrictions.eq("companyId", compnayId));
			cj.add(Restrictions.eq("storeId", storeId));
			cj.add(Restrictions.ge("expiryDate", expiryDate));
			cr.add(cj);
			templates=cr.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
		if(templates!=null && templates.size()>0)
			return templates.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public Offers getRegionOffer(String region, Long compnayId, Date expiryDate){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<Offers> templates = null;
		try{
			Criteria cr = session.createCriteria(Offers.class);
			Conjunction cj = Restrictions.conjunction();
			cj.add(Restrictions.eq("companyId", compnayId));
			cj.add(Restrictions.eq("region", region));
			cj.add(Restrictions.ge("expiryDate", expiryDate));
			cr.add(cj);
			templates=cr.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
		if(templates!=null && templates.size()>0)
			return templates.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public Offers getAffiliateOffer(Long compnayId, Date expiryDate){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<Offers> templates = null;
		try{
			Criteria cr = session.createCriteria(Offers.class);
			Conjunction cj = Restrictions.conjunction();
			cj.add(Restrictions.eq("companyId", compnayId));
			cj.add(Restrictions.ge("expiryDate", expiryDate));
			cr.add(cj);
			templates=cr.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
		if(templates!=null && templates.size()>0)
			return templates.get(0);
		else
			return null;
	}
	
	public static void main(String[] args) {
		OffersDAO dao = new OffersDAO();
		System.out.println(dao.getAffiliateOffer(new Long(86705), new Date()));
	}
}
