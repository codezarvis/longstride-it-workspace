package com.casa.booking.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;

import com.casa.booking.model.PdfTemplate;

public class PdfTemplateDAO {

	public void save(PdfTemplate pdfTemplate){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(pdfTemplate);
		session.getTransaction().commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public PdfTemplate getStoreTemplate(Long storeId, Long compnayId){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<PdfTemplate> templates = null;
		try{
			Criteria cr = session.createCriteria(PdfTemplate.class);
			Conjunction cj = Restrictions.conjunction();
			cj.add(Restrictions.eq("companyId", compnayId));
			cj.add(Restrictions.eq("storeId", storeId));
			cj.add(Restrictions.ge("expiryDate", new Date()));
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
	public PdfTemplate getRegionTemplate(String region, Long compnayId){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<PdfTemplate> templates = null;
		try{
			Criteria cr = session.createCriteria(PdfTemplate.class);
			Conjunction cj = Restrictions.conjunction();
			cj.add(Restrictions.eq("companyId", compnayId));
			cj.add(Restrictions.eq("region", region));
			cj.add(Restrictions.ge("expiryDate", new Date()));
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
	public PdfTemplate getAffiliateTemplate(Long compnayId){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<PdfTemplate> templates = null;
		try{
			Criteria cr = session.createCriteria(PdfTemplate.class);
			Conjunction cj = Restrictions.conjunction();
			cj.add(Restrictions.eq("companyId", compnayId));
			cj.add(Restrictions.ge("expiryDate", new Date()));
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
		PdfTemplateDAO dao = new PdfTemplateDAO();
		System.out.println(dao.getAffiliateTemplate(new Long(86705)));
	}
}
