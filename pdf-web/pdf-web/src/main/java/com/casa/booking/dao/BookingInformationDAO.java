package com.casa.booking.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.casa.booking.model.BookingInformation;
import com.casa.booking.model.User;
import com.casa.booking.util.CasaConstants;

public class BookingInformationDAO {

	public void save(BookingInformation bookingDetails){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(bookingDetails);
		session.getTransaction().commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<BookingInformation> getByPNR(String pnr){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<BookingInformation> bookings=null;;
		try {
			Criteria cr = session.createCriteria(BookingInformation.class);
			cr.add(Restrictions.eq("pnr", pnr)).addOrder(Order.desc("bookingDate"));
			bookings = cr.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
			
		return bookings;
	}
	
	@SuppressWarnings("unchecked")
	public BookingInformation getByUUID(String uuid){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<BookingInformation> bookings=null;;
		try {
			Criteria cr = session.createCriteria(BookingInformation.class);
			cr.add(Restrictions.eq("uuid", uuid)).addOrder(Order.desc("bookingDate"));
			bookings = cr.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		if(bookings	!=null && bookings.size()>0)
			return bookings.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BookingInformation> getByPNR(String pnr, String role, User userObj, List<Long> storeIds){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<BookingInformation> bookings=null;;
		try {
			Criteria cr = session.createCriteria(BookingInformation.class);
			if(role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN)){
				cr.add(Restrictions.eq("pnr", pnr)).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_AFFILIATE)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.eq("pnr", pnr));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_DISTRICT_MANAGER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.eq("pnr", pnr));
				cj.add(Restrictions.in("storeId", storeIds));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_STORE_MANAGER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.eq("pnr", pnr));
				cj.add(Restrictions.eq("storeId", userObj.getStore().getStoreId()));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_CASHIER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.eq("pnr", pnr));
				cj.add(Restrictions.eq("userId", userObj.getOdyUserId()));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
			}
			bookings = cr.list();
			/*else {
				bookings = session.createQuery("from BookingInformation where pnr = ? and companyCode = ?").setString(0, pnr).setLong(1, companyCode).list();
			}*/
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
			
		return bookings;
	}
	
	@SuppressWarnings("unchecked")
	public List<BookingInformation> getByUserId(String userId, String role, User userObj, List<Long> storeIds){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<BookingInformation> bookings=null;;
		try {
			/*if(isAdmin){
				bookings = session.createQuery("from BookingInformation where pnr = ?").setString(0, userId).list();
			} else {
				bookings = session.createQuery("from BookingInformation where userId = ? and companyCode = ?").setString(0, userId).setLong(1, companyCode).list();
			}*/
			Criteria cr = session.createCriteria(BookingInformation.class);
			if(role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN)){
				cr.add(Restrictions.eq("userId", userId)).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_AFFILIATE)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.eq("userId", userId));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_DISTRICT_MANAGER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.eq("userId", userId));
				cj.add(Restrictions.in("storeId", storeIds));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_STORE_MANAGER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.eq("userId", userId));
				cj.add(Restrictions.eq("storeId", userObj.getStore().getStoreId()));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_CASHIER)){
				Conjunction cj = Restrictions.conjunction();
				if(!userId.equalsIgnoreCase(userObj.getOdyUserId()))
					return bookings;
				cj.add(Restrictions.eq("userId", userId));
				//cj.add(Restrictions.eq("userId", userObj.getOdyUserId()));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
			}
			bookings = cr.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
			
		return bookings;
	}
	public BookingInformation getById(long id){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		BookingInformation bi=null;
		try {
			 bi = (BookingInformation)session.get(BookingInformation.class, new Long(id));
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		return bi;
	}
	
	@SuppressWarnings("unchecked")
	public List<BookingInformation> getByTravellerName(String name, String role, User userObj, List<Long> storeIds){
		String nameLike = "%"+name+"%";
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		List<BookingInformation> bookings=null;
		try {
			Criteria cr = session.createCriteria(BookingInformation.class);
			Disjunction disjunction = Restrictions.disjunction();
			Criterion name1 = Restrictions.like("travellerName1",nameLike);
			Criterion name2 = Restrictions.like("travellerName2",nameLike);
			Criterion name3 = Restrictions.like("travellerName3",nameLike);
			Criterion name4 = Restrictions.like("travellerName4",nameLike);
			Criterion name5 = Restrictions.like("travellerName5",nameLike);
			Criterion name6 = Restrictions.like("travellerName6",nameLike);
			Criterion name7 = Restrictions.like("travellerName7",nameLike);
			Criterion name8 = Restrictions.like("travellerName8",nameLike);
			disjunction.add(name1);
			disjunction.add(name2);
			disjunction.add(name3);
			disjunction.add(name4);
			disjunction.add(name5);
			disjunction.add(name6);
			disjunction.add(name7);
			disjunction.add(name8);
			if(role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN)){
				cr.add(disjunction).addOrder(Order.desc("bookingDate"));
			} else if(role.equalsIgnoreCase(CasaConstants.ROLE_AFFILIATE)){
				cr.add(Restrictions.and(disjunction, Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()))).
				addOrder(Order.desc("bookingDate"));
			} else if(role.equalsIgnoreCase(CasaConstants.ROLE_DISTRICT_MANAGER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cj.add(Restrictions.in("storeId", storeIds));
				cr.add(Restrictions.and(disjunction, cj )).addOrder(Order.desc("bookingDate"));
			} else if(role.equalsIgnoreCase(CasaConstants.ROLE_STORE_MANAGER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cj.add(Restrictions.eq("storeId", userObj.getStore().getStoreId()));
				cr.add(Restrictions.and(disjunction, cj )).addOrder(Order.desc("bookingDate"));
			} else if(role.equalsIgnoreCase(CasaConstants.ROLE_CASHIER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cj.add(Restrictions.eq("userId", userObj.getOdyUserId()));
				cr.add(Restrictions.and(disjunction, cj )).addOrder(Order.desc("bookingDate"));
			}
			/*else {
				cr.add(Restrictions.and(disjunction, Restrictions.eq("companyCode", companyCode))).
				addOrder(Order.desc("bookingDate"));
			}*/
			bookings = cr.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		
		return bookings;
	}
	
	@SuppressWarnings("unchecked")
	public List<BookingInformation> getByBookingDate(Date date, String role, User userObj, List<Long> storeIds){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		//.getStore().getCompany().getCompanyId()
		List<BookingInformation> bookings = null;
		try {
			Criteria cr = session.createCriteria(BookingInformation.class);
			if(role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN)){
				cr.add(Restrictions.eq("bookingDate", date)).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_AFFILIATE)){
				cr.add(Restrictions.and(Restrictions.eq("bookingDate", date), Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()))).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_DISTRICT_MANAGER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.eq("bookingDate", date));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cj.add(Restrictions.in("storeId", storeIds));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
				//cr.add(Restrictions.and(Restrictions.eq("bookingDate", date), Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()))).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_STORE_MANAGER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.eq("bookingDate", date));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cj.add(Restrictions.eq("storeId", userObj.getStore().getStoreId()));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_CASHIER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.eq("bookingDate", date));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cj.add(Restrictions.eq("userId", userObj.getOdyUserId()));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
			}
			bookings = cr.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		
		return bookings;
	}
	
	@SuppressWarnings("unchecked")
	public List<BookingInformation> getByFromLocation(String fromLocation, String role, User userObj, List<Long> storeIds){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		List<BookingInformation> bookings=null;
		try {
			Criteria cr = session.createCriteria(BookingInformation.class);
			/*
			if(isAdmin){
				cr.add(Restrictions.like("fromLocation", "%"+fromLocation+"%")).addOrder(Order.desc("bookingDate"));
			} else {
				cr.add(Restrictions.and(Restrictions.like("fromLocation", "%"+fromLocation+"%"), Restrictions.eq("companyCode", companyCode))).addOrder(Order.desc("bookingDate"));
			}
			*/
			if(role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN)){
				cr.add(Restrictions.like("fromLocation", "%"+fromLocation+"%")).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_AFFILIATE)){
				cr.add(Restrictions.and(Restrictions.like("fromLocation", "%"+fromLocation+"%"), 
						Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()))).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_DISTRICT_MANAGER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.like("fromLocation", "%"+fromLocation+"%"));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cj.add(Restrictions.in("storeId", storeIds));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
				//cr.add(Restrictions.and(Restrictions.eq("bookingDate", date), Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()))).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_STORE_MANAGER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.like("fromLocation", "%"+fromLocation+"%"));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cj.add(Restrictions.eq("storeId", userObj.getStore().getStoreId()));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_CASHIER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.like("fromLocation", "%"+fromLocation+"%"));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cj.add(Restrictions.eq("userId", userObj.getOdyUserId()));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
			}
			bookings = cr.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		
		return bookings;
	}
	
	@SuppressWarnings("unchecked")
	public List<BookingInformation> getByToLocation(String toLocation, String role, User userObj, List<Long> storeIds){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		List<BookingInformation> bookings=null;
		try {
			Criteria cr = session.createCriteria(BookingInformation.class);
			/*
			if(isAdmin){
				cr.add(Restrictions.like("toLocation", "%"+toLocation+"%")).addOrder(Order.desc("bookingDate"));
			} else {
				cr.add(Restrictions.and(Restrictions.like("toLocation", "%"+toLocation+"%"), Restrictions.eq("companyCode", companyCode))).addOrder(Order.desc("bookingDate"));
			}
			*/
			if(role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN)){
				cr.add(Restrictions.like("toLocation", "%"+toLocation+"%")).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_AFFILIATE)){
				cr.add(Restrictions.and(Restrictions.like("toLocation", "%"+toLocation+"%"), 
						Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()))).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_DISTRICT_MANAGER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.like("toLocation", "%"+toLocation+"%"));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cj.add(Restrictions.in("storeId", storeIds));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
				//cr.add(Restrictions.and(Restrictions.eq("bookingDate", date), Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()))).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_STORE_MANAGER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.like("toLocation", "%"+toLocation+"%"));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cj.add(Restrictions.eq("storeId", userObj.getStore().getStoreId()));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_CASHIER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.like("toLocation", "%"+toLocation+"%"));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cj.add(Restrictions.eq("userId", userObj.getOdyUserId()));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
			}
			bookings = cr.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		
		return bookings;
	}
	
	@SuppressWarnings("unchecked")
	public List<BookingInformation> getByAirline(String airline, String role, User userObj, List<Long> storeIds){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		List<BookingInformation> bookings=null;
		try {
			Criteria cr = session.createCriteria(BookingInformation.class);
			/*
			if(isAdmin){
				cr.add(Restrictions.like("fromLocation", "%"+fromLocation+"%")).addOrder(Order.desc("bookingDate"));
			} else {
				cr.add(Restrictions.and(Restrictions.like("fromLocation", "%"+fromLocation+"%"), Restrictions.eq("companyCode", companyCode))).addOrder(Order.desc("bookingDate"));
			}
			*/
			if(role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN)){
				cr.add(Restrictions.like("airline", "%"+airline+"%")).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_AFFILIATE)){
				cr.add(Restrictions.and(Restrictions.like("fromLocation", "%"+airline+"%"), 
						Restrictions.eq("airline", userObj.getStore().getCompany().getCompanyId()))).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_DISTRICT_MANAGER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.like("airline", "%"+airline+"%"));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cj.add(Restrictions.in("storeId", storeIds));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
				//cr.add(Restrictions.and(Restrictions.eq("bookingDate", date), Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()))).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_STORE_MANAGER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.like("airline", "%"+airline+"%"));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cj.add(Restrictions.eq("storeId", userObj.getStore().getStoreId()));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_CASHIER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.like("airline", "%"+airline+"%"));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cj.add(Restrictions.eq("userId", userObj.getOdyUserId()));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
			}
			bookings = cr.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		return bookings;
	}
	
	@SuppressWarnings("unchecked")
	public List<BookingInformation> getBetweenTwoBookingDates(Date date1, Date date2, String role, User userObj, List<Long> storeIds){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		List<BookingInformation> bookings=null;
		try {
			Criteria cr = session.createCriteria(BookingInformation.class);
			if(role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN)){
				cr.add(Restrictions.and(Restrictions.ge("bookingDate", date1), Restrictions.le("bookingDate", date2))).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_AFFILIATE)){ 
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.ge("bookingDate", date1));
				cj.add(Restrictions.le("bookingDate", date2));
				cr.add(Restrictions.and(cj, Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()))).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_DISTRICT_MANAGER)){ 
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.ge("bookingDate", date1));
				cj.add(Restrictions.le("bookingDate", date2));
				cj.add(Restrictions.in("storeId", storeIds));
				cr.add(Restrictions.and(cj, Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()))).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_STORE_MANAGER)){ 
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.ge("bookingDate", date1));
				cj.add(Restrictions.le("bookingDate", date2));
				cj.add(Restrictions.eq("storeId", userObj.getStore().getStoreId()));
				cr.add(Restrictions.and(cj, Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()))).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_CASHIER)){ 
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.ge("bookingDate", date1));
				cj.add(Restrictions.le("bookingDate", date2));
				cj.add(Restrictions.eq("userId", userObj.getOdyUserId()));
				cr.add(Restrictions.and(cj, Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()))).addOrder(Order.desc("bookingDate"));
			}
			/*	else {
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.ge("bookingDate", date1));
				cj.add(Restrictions.le("bookingDate", date2));
				cr.add(Restrictions.and(cj, Restrictions.eq("companyCode", userObj.getStore().getStoreId()))).addOrder(Order.desc("bookingDate"));
			}*/
			bookings = cr.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		return bookings;
	}
	
	@SuppressWarnings("unchecked")
	public List<BookingInformation> getByAmountRange(Double startAmount, Double endAmount, String role, User userObj, List<Long> storeIds){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		List<BookingInformation> bookings=null;
		try {
			Criteria cr = session.createCriteria(BookingInformation.class);
			if(role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN)){
				cr.add(Restrictions.and(Restrictions.ge("amount", startAmount), Restrictions.le("amount", endAmount))).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_AFFILIATE)){ 
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.ge("amount", startAmount));
				cj.add(Restrictions.le("amount", endAmount));
				cr.add(Restrictions.and(cj, Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()))).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_DISTRICT_MANAGER)){ 
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.ge("amount", startAmount));
				cj.add(Restrictions.le("amount", endAmount));
				cj.add(Restrictions.in("storeId", storeIds));
				cr.add(Restrictions.and(cj, Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()))).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_STORE_MANAGER)){ 
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.ge("amount", startAmount));
				cj.add(Restrictions.le("amount", endAmount));
				cj.add(Restrictions.eq("storeId", userObj.getStore().getStoreId()));
				cr.add(Restrictions.and(cj, Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()))).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_CASHIER)){ 
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.ge("amount", startAmount));
				cj.add(Restrictions.le("amount", endAmount));
				cj.add(Restrictions.eq("userId", userObj.getOdyUserId()));
				cr.add(Restrictions.and(cj, Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()))).addOrder(Order.desc("bookingDate"));
			}
			/*	else {
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.ge("bookingDate", date1));
				cj.add(Restrictions.le("bookingDate", date2));
				cr.add(Restrictions.and(cj, Restrictions.eq("companyCode", userObj.getStore().getStoreId()))).addOrder(Order.desc("bookingDate"));
			}*/
			bookings = cr.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		return bookings;
	}
	
	@SuppressWarnings("unchecked")
	public List<BookingInformation> getByTravelDate(Date date, String role, User userObj, List<Long> storeIds){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<BookingInformation> bookings=null;
		try {
			Criteria cr = session.createCriteria(BookingInformation.class);
			/*if(isAdmin){
				cr.add(Restrictions.eq("travelDate", date)).addOrder(Order.desc("bookingDate"));
			} else {
				cr.add(Restrictions.and(Restrictions.eq("travelDate", date), Restrictions.eq("companyCode", companyCode))).addOrder(Order.desc("bookingDate"));
			}*/
			if(role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN)){
				cr.add(Restrictions.eq("travelDate", date)).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_AFFILIATE)){
				cr.add(Restrictions.and(Restrictions.eq("travelDate", date), Restrictions.eq("companyCode", 
						userObj.getStore().getCompany().getCompanyId()))).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_DISTRICT_MANAGER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.eq("travelDate", date));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cj.add(Restrictions.in("storeId", storeIds));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
				//cr.add(Restrictions.and(Restrictions.eq("bookingDate", date), Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()))).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_STORE_MANAGER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.eq("travelDate", date));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cj.add(Restrictions.eq("storeId", userObj.getStore().getStoreId()));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
			}else if(role.equalsIgnoreCase(CasaConstants.ROLE_CASHIER)){
				Conjunction cj = Restrictions.conjunction();
				cj.add(Restrictions.eq("travelDate", date));
				cj.add(Restrictions.eq("companyCode", userObj.getStore().getCompany().getCompanyId()));
				cj.add(Restrictions.eq("userId", userObj.getOdyUserId()));
				cr.add(cj).addOrder(Order.desc("bookingDate"));
			}
			bookings = cr.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		return bookings;
	}
	/*
	public static void main(String[] args) {
		BookingInformationDAO dao = new BookingInformationDAO();
		Calendar calendar = Calendar.getInstance ();
		Calendar calendar2 = Calendar.getInstance ();
		calendar2.set(Calendar.DAY_OF_MONTH,1);
		
		calendar2.set(Calendar.HOUR_OF_DAY, 0);
		calendar2.set(Calendar.MINUTE, 0);
		calendar2.set(Calendar.SECOND, 0);
		
		
		System.out.println(calendar2.getTime());
		System.out.println(calendar.getTime());
		
		//List<BookingInformation> li = dao.getBetweenTwoBookingDates(calendar2.getTime(), calendar.getTime(), false, 45243);
		//System.out.println(li.size());
	}
	*/
}
