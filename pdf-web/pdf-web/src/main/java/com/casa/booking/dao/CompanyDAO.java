package com.casa.booking.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.casa.booking.model.Company;
import com.casa.booking.model.Store;
import com.casa.booking.model.User;
import com.casa.booking.util.CasaConstants;

public class CompanyDAO {

	public void saveOrUpdate(Company company){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(company);
		session.getTransaction().commit();
		session.close();
	}
	
	public Company getByCompanyCode(long companyCode){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Company company=null;;
		try {
				//List<Company> cList = session.createQuery("from Company where code = ?").setLong(0, companyCode).list();
			company = (Company)session.get(Company.class, companyCode);

		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
			
		return company;
	}
	
	public void delete(long companyCode){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Company company=null;;
		try {
			company = (Company)session.get(Company.class, companyCode);
			if(company!=null){
				session.beginTransaction();
				Set<Store> stores = company.getStores();
				if(stores!=null){
					for(Store s : stores){
						s.setStatus(CasaConstants.DELETE_STATE);
						Set<User> users = s.getUsers();
						if(users!=null){
							for(User u : users){
								u.setStatus(CasaConstants.DELETE_STATE);
								session.saveOrUpdate(u);
							}
						}
						session.saveOrUpdate(s);
					}
				}
				company.setStatus(CasaConstants.DELETE_STATE);
				session.saveOrUpdate(company);
				//session.delete(company);
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
	public List<Company> getAll(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<Company> cList=null;;
		try {
			//cList = session.createCriteria(Company.class).list();
			cList = session.createQuery("from Company where status = ?")
						.setString(0, CasaConstants.ACTIVE_STATE).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
			
		return cList;
	}
}
