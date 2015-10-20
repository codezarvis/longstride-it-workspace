package com.casa.booking.pdf.ws;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.casa.booking.dto.FinanceArrayDTO;
import com.casa.booking.service.AccountingService;
import com.casa.booking.util.CasaConstants;

@Component
@Path("/accounts")
public class AccountController {

	@Autowired
	private AccountingService accountingService;
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return accountingService.test();
	}
	
	@GET
	@Path("/financedata")
	@Produces(MediaType.APPLICATION_JSON)
	public FinanceArrayDTO getFinanceData(@DefaultValue("") @QueryParam("pnr") String pnr, @DefaultValue("") @QueryParam("startDt") String startDt,
			@DefaultValue("") @QueryParam("endDt") String endDt){
		Subject subject = SecurityUtils.getSubject();
		
		String role=CasaConstants.ROLE_CASHIER;
		if(subject.hasRole(CasaConstants.ROLE_ADMIN)){
			role=CasaConstants.ROLE_ADMIN;
		}else if(subject.hasRole(CasaConstants.ROLE_AFFILIATE)){
			role=CasaConstants.ROLE_AFFILIATE;
		}else if(subject.hasRole(CasaConstants.ROLE_DISTRICT_MANAGER)){
			role=CasaConstants.ROLE_DISTRICT_MANAGER;
		}else if(subject.hasRole(CasaConstants.ROLE_STORE_MANAGER)){
			role=CasaConstants.ROLE_STORE_MANAGER;
		}else if(subject.hasRole(CasaConstants.ROLE_CASHIER)){
			role=CasaConstants.ROLE_CASHIER;
		}
		return accountingService.getFinacialData(pnr, startDt, endDt, subject.getPrincipal().toString(), role);
	}
}
