package com.casa.booking.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.casa.booking.model.Airlines;

public class AirlinesDAO {

	public void saveOrUpdate(Airlines airlines){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(airlines);
		session.getTransaction().commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public Airlines getByAirlineCode(String code){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<Airlines> airlinesList=null;;
		try {
			airlinesList = session.createQuery("from Airlines where airlineCode = ?").setString(0, code).list();
			/*Criteria cr = session.createCriteria(Airlines.class);
			cr.add(Restrictions.eq("airlineCode", code));*/
			//airlinesList = cr.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		if(airlinesList!=null && airlinesList.size()>0)
			return airlinesList.get(0);
		else
			return null;
	}

	public Airlines getById(Long id){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Airlines al = null;
		try{
			al = (Airlines)session.get(Airlines.class, id);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return al;
	}
	public static void main(String[] args) {
		AirlinesDAO dao = new AirlinesDAO();
		System.out.println(dao.getByAirlineCode("AA"));
		//System.out.println(dao.getById(1l));
		
	}
}
