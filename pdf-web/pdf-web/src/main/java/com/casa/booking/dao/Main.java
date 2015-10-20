package com.casa.booking.dao;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.casa.booking.model.PdfTemplate;

public class Main {

	public static void main(String[] args) {

		//System.out.println("Hibernate One-To-One example (Annotation)");
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		PdfTemplate template = new PdfTemplate();
		template.setCompanyId(45243L);
		template.setCreateDate(new Date());
		template.setStoreId(2L);
		template.setTemplateType("Region");
		template.setRegion("South");
		template.setFileName("casa_south_06_01_2015.xsl");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 30);
		template.setExpiryDate(cal.getTime());
		session.saveOrUpdate(template);
		/*
		Company c = (Company)session.get(Company.class, 45243L);
		Store st = new Store();
		st.setCompany(c);
		st.setDistrict("BLR");
		st.setLocation("MG Road");
		st.setState("KA");
		session.save(st); */
		
		//Store store = (Store)session.get(Store.class, 1L);
		//User user = (User)session.get(User.class, "prasank");
		//System.out.println(user.getName()+", "+user.getStatus()+", "+user.getUserId());
		//System.out.println(user.getStore().getLocation());
		//System.out.println(user.getStore().getCompany().getCompanyName());
		/*
		User user = new User();
		user.setUserId("prasanth");
		user.setName("Prasanth KK");
		user.setEmail("prasank@gmail.com");
		user.setOdyUserId("477899");
		user.setPassword("123abc");
		user.setOdyPassword("123abc");
		user.setStatus("ACTIVE");
		user.setStore(store);
		user.setTemplatePref("store");
		user.setPhoneNumber("9742703692");
		session.save(user);
		*/
		/*
		Set<User> users = store.getUsers();
		for(User u : users){
			System.out.println(u.getName());
		}
		*/
		//System.out.println(store.getDistrict()+", "+store.getLocation());
		//System.out.println(store.getCompany().getCompanyName());
		//session.save(user);
		/*
		User user1 = new User();
		user1.setUserId("27872");
		user1.setPassword("casa123");
		user1.setName("Claudia Colio");
		user1.setCompanyId(45243);
		
		User user2 = new User();
		user2.setUserId("31418");
		user2.setPassword("casa123");
		user2.setName("Tony VEGA");
		user2.setCompanyId(45243);
		
		User user3 = new User();
		user3.setUserId("31550");
		user3.setPassword("casa123");
		user3.setName("nazaret guzman");
		user3.setCompanyId(86705);
		
		
		User user4 = new User();
		user4.setUserId("47650");
		user4.setPassword("casa123");
		user4.setName("ROSA LEON");
		user4.setCompanyId(86933);
		*/
		
		//session.save(user1);
		//session.save(user2);
		//session.save(user3);
		
		//session.getTransaction().commit();
		/*
		Company c1 = new Company();
		c1.setCompanyId(45243);
		c1.setCompanyName("Casa Travel");
		
		Company c8 = new Company();
		c8.setCompanyId(86705);
		c8.setCompanyName("MoneyGram");
		
		Company c2 = new Company();
		c2.setCompanyId(86933);
		c2.setCompanyName("Order Express");
		
		Company c3 = new Company();
		c3.setCompanyId(96720);
		c3.setCompanyName("UnBank");
		
		Company c4 = new Company();
		c4.setCompanyId(97945);
		c4.setCompanyName("CFSC");
		
		Company c5 = new Company();
		c5.setCompanyId(98389);
		c5.setCompanyName("Barri Group");
		
		Company c6 = new Company();
		c6.setCompanyId(98897);
		c6.setCompanyName("Payomatic");
		
		Company c7 = new Company();
		c7.setCompanyId(99621);
		c7.setCompanyName("Micro Manos");
		
		session.save(c7);
		session.save(c1);
		session.save(c2);
		session.save(c3);
		session.save(c4);
		session.save(c5);
		session.save(c6);
		session.save(c8);
		*/
		session.getTransaction().commit();
		session.close();
		
		/*
		BookingInformation bookingDetails = new BookingInformation();
		bookingDetails.setAgentCode("VRL");
		bookingDetails.setAgentEmail("pras@123.com");
		bookingDetails.setAgentName("VRL123");
		bookingDetails.setPnr("XYUI56");
		bookingDetails.setTravelDate(new Date());
		bookingDetails.setBookingDate(new Date());
		bookingDetails.setTravellerName1("prasanth");
		bookingDetails.setTravellerName2("prasanth2");
		bookingDetails.setTravellerName3("prasanth3");
		bookingDetails.setXmlContent("<xml>content<xml>");
		bookingDetails.setXmlFileName("123.xml");
		//session.save(bookingDetails);

		
		//List<BookingDetails> bookings = session.createQuery("from BookingDetails where pnr = ?").setString(0, "XYUI567").list();
		String nameLike = "%Nth2";
		Criteria cr = session.createCriteria(BookingInformation.class);
		Criterion name1 = Restrictions.like("travellerName1",nameLike);
		Criterion name2 = Restrictions.like("travellerName2",nameLike);
		Criterion name3 = Restrictions.like("travellerName3",nameLike);
		Criterion name4 = Restrictions.like("travellerName4",nameLike);
		Criterion name5 = Restrictions.like("travellerName5",nameLike);
		Criterion name6 = Restrictions.like("travellerName6",nameLike);
		Criterion name7 = Restrictions.like("travellerName7",nameLike);
		Criterion name8 = Restrictions.like("travellerName8",nameLike);
		cr.add(Restrictions.disjunction()
				.add(name1)
				.add(name2)
				.add(name3)
				.add(name4)
				.add(name5)
				.add(name6)
				.add(name7)
				.add(name8)).addOrder(Order.desc("bookingDate"));
		
		List<BookingInformation> bookings = cr.list();
		for (BookingInformation booking : bookings) {
			System.out.println(booking.getAgentCode() + " , "
					+ booking.getPnr() + ", "
					+ booking.getXmlContent());
		}
		
		
		//session.getTransaction().commit();
		session.close();*/

	}
}
