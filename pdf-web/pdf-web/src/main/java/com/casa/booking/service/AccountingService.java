package com.casa.booking.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.casa.booking.dao.AccountingSysDAO;
import com.casa.booking.dao.UserDAO;
import com.casa.booking.dto.FinanceArrayDTO;
import com.casa.booking.model.User;

public class AccountingService {

	@Autowired
	private AccountingSysDAO accountingSysDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	public String test(){
		try {
			accountingSysDAO.getAccountData(null, "2015-05-01", "2015-10-11", "CAPLAZA");
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
			return "Error";
		}
		return "success";
	}
	
	public FinanceArrayDTO getFinacialData(String pnr, String startDt, String endDt, String user, String role){
		FinanceArrayDTO dto = new FinanceArrayDTO();
		try {
			User userObj = userDAO.getUserById(user);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			if(startDt!=null && !"".equals(startDt)){
				SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
				startDt=formatter.format(formatter1.parse(startDt));
				endDt=formatter.format(formatter1.parse(endDt));
			}
			if(pnr==null || "".equals(pnr)){
				if(startDt==null || "".equals(startDt) || endDt==null || "".equals(endDt)){
					Date today = new Date();
					startDt=formatter.format(today);
					endDt=formatter.format(today);
				}
			}
			
			if(pnr==null || "".equals(pnr)){
				pnr=null;
			}
			List<List<String>> data = accountingSysDAO.getAccountData(pnr, startDt, endDt, userObj.getCustomerCode());
			dto.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}
