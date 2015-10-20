package com.casa.booking.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.casa.booking.model.TransactionAckLog;

public class TransactionAckLogDAO {

	public void save(TransactionAckLog transactionAckLog){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(transactionAckLog);
		session.getTransaction().commit();
		session.close();
	}

}
