package com.casa.booking.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.casa.booking.util.CasaConstants;

@Controller
public class CasaController {

	@RequestMapping("/test.do")
	public ModelAndView test(){
		return new ModelAndView("sample");
	}
	
	@RequestMapping("/login.do")
	public ModelAndView login(){
		return new ModelAndView("login");
	}
	
	@RequestMapping("/reports/bookings.do")
	public ModelAndView bookings(){
		return new ModelAndView("view_booking_details");
	}
	
	@RequestMapping("/admin/manage.do")
	public ModelAndView admin(){
		Subject subject = SecurityUtils.getSubject();
		
		if(subject.hasRole(CasaConstants.ROLE_ADMIN)){
			return new ModelAndView("admin");
		}else if(subject.hasRole(CasaConstants.ROLE_AFFILIATE)){
			return new ModelAndView("affiliate");
		}else if(subject.hasRole(CasaConstants.ROLE_DISTRICT_MANAGER)){
			return new ModelAndView("affiliate");
		}else if(subject.hasRole(CasaConstants.ROLE_STORE_MANAGER)){
			return new ModelAndView("store_mgr");
		}else if(subject.hasRole(CasaConstants.ROLE_CASHIER)){
			return new ModelAndView("no_access");
		}
		return new ModelAndView("no_access");
	}
	
	@RequestMapping("/home/services.do")
	public ModelAndView services() {
		System.out.println("In Services");
		return new ModelAndView("services");
	}
}
