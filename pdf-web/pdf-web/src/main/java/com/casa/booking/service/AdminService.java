package com.casa.booking.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.casa.booking.dao.CompanyDAO;
import com.casa.booking.dao.PdfTemplateDAO;
import com.casa.booking.dao.StoreDAO;
import com.casa.booking.dao.UserDAO;
import com.casa.booking.dto.AffiliateDTO;
import com.casa.booking.dto.AffiliateDTOs;
import com.casa.booking.dto.AffiliateManageDTO;
import com.casa.booking.dto.DropdownDTO;
import com.casa.booking.dto.FieldErrorDTO;
import com.casa.booking.dto.StoreDTO;
import com.casa.booking.dto.StoreDTOs;
import com.casa.booking.dto.StoreManageDTO;
import com.casa.booking.dto.UserDTO;
import com.casa.booking.dto.UserDTOs;
import com.casa.booking.dto.UserManageDTO;
import com.casa.booking.model.Company;
import com.casa.booking.model.Store;
import com.casa.booking.model.User;
import com.casa.booking.util.CasaConstants;

public class AdminService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@Autowired
	private StoreDAO storeDAO;
	
	@Autowired
	private PdfTemplateDAO pdfTemplateDAO;
	
	public String changePassword(String userId, String currPwd, String newPwd){
		User u = userDAO.getUserById(userId.trim());
		if(u!=null && u.getPassword().equals(currPwd)){
			u.setPassword(newPwd);
			u.setIsDefaultPwd("No");
			userDAO.save(u);
			return "success";
		}else if(u!=null && !u.getPassword().equals(currPwd)){
			return "Incorrect Current Password";
		}else{
			return "User not found, please logout and login again";
		}
	}
	
	public String isDefaultPwd(String userId){
		User u = userDAO.getUserById(userId.trim());
		if(u!=null && u.getIsDefaultPwd()!=null && u.getIsDefaultPwd().equalsIgnoreCase("yes")){
			return "true";
		}else{
			return "false";
		}
	}
	
	public AffiliateDTOs getAllAffiliates(String role){
		AffiliateDTOs adto = new AffiliateDTOs();
		List<AffiliateDTO> dtos = new ArrayList<AffiliateDTO>();
		if(role!=null && role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN)){
			List<Company> list = companyDAO.getAll();
			if(list!=null){
				for(Company c : list){
					AffiliateDTO dto = new AffiliateDTO();
					dto.setRowId(""+c.getCompanyId());
					dto.setId(c.getCompanyId());
					dto.setAddress(c.getAddress());
					dto.setName(c.getCompanyName());
					dto.setCity(c.getCity());
					dto.setCountry(c.getCountry());
					dto.setPhone(c.getPhone());
					dto.setLogo(c.getLogo());
					dto.setEmail(c.getEmail());
					dto.setZip(c.getZip());
					dtos.add(dto);
				}
			}
		}
		adto.setData(dtos);
		return adto;
	}
	
	public List<DropdownDTO> getAllAffiliatesForDropdown(String role, String userId){
		List<DropdownDTO> dtos = new ArrayList<DropdownDTO>();
		if(role!=null && role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN)){
			List<Company> list = companyDAO.getAll();
			if(list!=null){
				for(Company c : list){
					DropdownDTO dto = new DropdownDTO();
					dto.setValue(""+c.getCompanyId());
					dto.setLabel(c.getCompanyName());
					dtos.add(dto);
				}
			}
		}else if(role!=null && (role.equalsIgnoreCase(CasaConstants.ROLE_AFFILIATE) || role.equalsIgnoreCase(CasaConstants.ROLE_DISTRICT_MANAGER)
				|| role.equalsIgnoreCase(CasaConstants.ROLE_STORE_MANAGER))){
			User u = userDAO.getUserById(userId);
			DropdownDTO dto = new DropdownDTO();
			dto.setValue(""+u.getStore().getCompany().getCompanyId());
			dto.setLabel(u.getStore().getCompany().getCompanyName());
			dtos.add(dto);
		}
		return dtos;
	}
	
	public List<DropdownDTO> getAllDistrictsForDropdown(String affiliateId, String role, String userId, String page){
		List<DropdownDTO> dtos = new ArrayList<DropdownDTO>();
		if(role!=null && (role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN) || role.equalsIgnoreCase(CasaConstants.ROLE_AFFILIATE))){
			if(affiliateId!=null && !"".equals(affiliateId)){
				Company c = companyDAO.getByCompanyCode(new Long(affiliateId));
				List<String> list = storeDAO.getUniqueDistricts(c);
				if(list!=null){
					for(String s : list){
						DropdownDTO dto = new DropdownDTO();
						dto.setValue(s);
						dto.setLabel(s);
						dtos.add(dto);
					}
				}
			}
			if(page!=null && page.equalsIgnoreCase("store")){
				DropdownDTO dto = new DropdownDTO();
				dto.setValue("c_new_dist");
				dto.setLabel("Create New District");
				dtos.add(dto);
			}
		}else if(role!=null && (role.equalsIgnoreCase(CasaConstants.ROLE_DISTRICT_MANAGER) || role.equalsIgnoreCase(CasaConstants.ROLE_STORE_MANAGER)) ){
			User u = userDAO.getUserById(userId);
			DropdownDTO dto = new DropdownDTO();
			dto.setValue(u.getStore().getDistrict());
			dto.setLabel(u.getStore().getDistrict());
			dtos.add(dto);
		}
		return dtos;
	}
	
	public List<DropdownDTO> getAllStoresForDropdown(String affiliateId, String district, String role, String userId){
		List<DropdownDTO> dtos = new ArrayList<DropdownDTO>();
		if(role!=null && (role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN) || 
				role.equalsIgnoreCase(CasaConstants.ROLE_AFFILIATE) || role.equalsIgnoreCase(CasaConstants.ROLE_DISTRICT_MANAGER))){
			if(affiliateId!=null && !"".equals(affiliateId) && district!=null && !"".equals(district)){
				List<Store> list = storeDAO.getStoresByDistrict(district, new Long(affiliateId.trim()));
				if(list!=null){
					for(Store s : list){
						DropdownDTO dto = new DropdownDTO();
						dto.setValue(""+s.getStoreId());
						dto.setLabel(s.getName());
						dtos.add(dto);
						System.out.println(s.getName());
					}
				}
			}
		}else if(role!=null && role.equalsIgnoreCase(CasaConstants.ROLE_STORE_MANAGER)){
			User u = userDAO.getUserById(userId);
			DropdownDTO dto = new DropdownDTO();
			dto.setValue(""+u.getStore().getStoreId());
			dto.setLabel(u.getStore().getName());
			dtos.add(dto);
		}
		return dtos;
	}
	
	public List<DropdownDTO> getAllRolesForDropdown(String role){
		List<DropdownDTO> dtos = new ArrayList<DropdownDTO>();
		
		DropdownDTO cashier = new DropdownDTO();
		cashier.setValue(CasaConstants.ROLE_CASHIER);
		cashier.setLabel(CasaConstants.ROLE_CASHIER);
		
		DropdownDTO storemgr = new DropdownDTO();
		storemgr.setValue(CasaConstants.ROLE_STORE_MANAGER);
		storemgr.setLabel(CasaConstants.ROLE_STORE_MANAGER);
		
		DropdownDTO distmgr = new DropdownDTO();
		distmgr.setValue(CasaConstants.ROLE_DISTRICT_MANAGER);
		distmgr.setLabel(CasaConstants.ROLE_DISTRICT_MANAGER);
		
		DropdownDTO afliate = new DropdownDTO();
		afliate.setValue(CasaConstants.ROLE_AFFILIATE);
		afliate.setLabel(CasaConstants.ROLE_AFFILIATE);
		
		DropdownDTO admin = new DropdownDTO();
		admin.setValue(CasaConstants.ROLE_ADMIN);
		admin.setLabel(CasaConstants.ROLE_ADMIN);
		
		if(role!=null && (role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN) || 
				role.equalsIgnoreCase(CasaConstants.ROLE_AFFILIATE) || role.equalsIgnoreCase(CasaConstants.ROLE_DISTRICT_MANAGER) 
				|| role.equalsIgnoreCase(CasaConstants.ROLE_STORE_MANAGER))){
			dtos.add(cashier);
		}
		if(role!=null && (role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN) || 
				role.equalsIgnoreCase(CasaConstants.ROLE_AFFILIATE) || role.equalsIgnoreCase(CasaConstants.ROLE_DISTRICT_MANAGER))){
			dtos.add(storemgr);
		}
		if(role!=null && (role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN) || role.equalsIgnoreCase(CasaConstants.ROLE_AFFILIATE))){
			dtos.add(distmgr);
			dtos.add(afliate);
		}
		if(role!=null && role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN)){
			dtos.add(admin);
		}
		return dtos;
	}
	
	public StoreDTOs getAllStores(String role, String userId){
		StoreDTOs adto = new StoreDTOs();
		List<StoreDTO> dtos = new ArrayList<StoreDTO>();
		List<Store> list = null;
		if(role!=null && role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN)){
			list = storeDAO.getAll();
		}else if(role!=null && role.equalsIgnoreCase(CasaConstants.ROLE_AFFILIATE)){
			User u = userDAO.getUserById(userId);
			list = storeDAO.getStoresByComapnyId(u.getStore().getCompany().getCompanyId());
		}else if(role!=null && role.equalsIgnoreCase(CasaConstants.ROLE_DISTRICT_MANAGER)){
			User u = userDAO.getUserById(userId);
			list = storeDAO.getStoresByDistrict(u.getStore().getDistrict(), u.getStore().getCompany().getCompanyId());
		}
		if(list!=null){
			for(Store s : list){
				StoreDTO dto = new StoreDTO();
				dto.setAffiliateId(""+s.getCompany().getCompanyId());
				dto.setAffiliateName(s.getCompany().getCompanyName());
				dto.setStoreName(s.getName());
				dto.setAddress(s.getAddress());
				dto.setDistrict(s.getDistrict());
				dto.setCity(s.getCity());
				dto.setCountry(s.getCity());
				dto.setPhone(s.getPhone());
				dto.setZip(s.getZip());
				dto.setDT_RowId(""+s.getStoreId());
				dto.setEmail(s.getEmail());
				dtos.add(dto);
			}
		}
		adto.setData(dtos);
		return adto;
	}
	
	public UserDTOs getAllUsers(String role, String userId){
		UserDTOs dtos = new UserDTOs();
		List<UserDTO> dtoList = new ArrayList<UserDTO>();
		List<User> users = null;
		List<String> notIncludeRoles = new ArrayList<String>();
		User me = userDAO.getUserById(userId);
		if(role!=null && role.equalsIgnoreCase(CasaConstants.ROLE_ADMIN)){
			//notIncludeRoles.add(CasaConstants.ROLE_ADMIN);
			users = userDAO.getAllActiveUsers();
		}else if(role!=null && role.equalsIgnoreCase(CasaConstants.ROLE_AFFILIATE)){
			notIncludeRoles.add(CasaConstants.ROLE_ADMIN);
			//notIncludeRoles.add(CasaConstants.ROLE_AFFILIATE);
			users = userDAO.getAllActiveUsersByCompanyId(me.getStore().getCompany().getCompanyId());
		}else if(role!=null && role.equalsIgnoreCase(CasaConstants.ROLE_DISTRICT_MANAGER)){
			notIncludeRoles.add(CasaConstants.ROLE_ADMIN);
			notIncludeRoles.add(CasaConstants.ROLE_AFFILIATE);
			notIncludeRoles.add(CasaConstants.ROLE_DISTRICT_MANAGER);
			users = userDAO.getAllActiveUsersByCompanyIdAndDist(me.getStore().getCompany().getCompanyId(),me.getStore().getDistrict());
		}else if(role!=null && role.equalsIgnoreCase(CasaConstants.ROLE_STORE_MANAGER)){
			notIncludeRoles.add(CasaConstants.ROLE_ADMIN);
			notIncludeRoles.add(CasaConstants.ROLE_AFFILIATE);
			notIncludeRoles.add(CasaConstants.ROLE_DISTRICT_MANAGER);
			notIncludeRoles.add(CasaConstants.ROLE_STORE_MANAGER);
			users = userDAO.getAllActiveUsersByStoreId(me.getStore().getStoreId());
		}
		if(users!=null){
			//users.add(me);
			for(User u : users){
				//if(u.getId()!=me.getId() && notIncludeRoles.contains(u.getRole()))
				if(notIncludeRoles.contains(u.getRole()))
					continue;
				UserDTO dto = new UserDTO();
				dto.setAddress(u.getAddress());
				dto.setAffiliateId(""+u.getStore().getCompany().getCompanyId());
				dto.setAffiliateName(u.getStore().getCompany().getCompanyName());
				dto.setCity(u.getCity());
				dto.setCountry(u.getCountry());
				dto.setDistrict(u.getStore().getDistrict());
				dto.setEmail(u.getEmail());
				dto.setExtn(u.getExtension());
				dto.setFax(u.getFax());
				dto.setFirstName(u.getFirstName());
				dto.setIata(u.getIATA());
				dto.setLastName(u.getLastName());
				dto.setOdyPassword(u.getOdyPassword());
				dto.setOdyUserId(u.getOdyUserId());
				//dto.setPassword(u.getPassword());
				dto.setPassword("");
				dto.setPhone(u.getPhoneNumber());
				dto.setRole(u.getRole());
				dto.setRowId(""+u.getId());
				dto.setState(u.getState());
				dto.setStoreId(""+u.getStore().getStoreId());
				dto.setStoreName(u.getStore().getName());
				dto.setUserId(u.getUserId());
				dto.setZip(u.getZip());
				dto.setCustomerCode(u.getCustomerCode());
				dtoList.add(dto);
			}
		}
		dtos.setData(dtoList);
		return dtos;
	}
	public AffiliateManageDTO manageAffiliate(MultivaluedMap<String,String> multivaluedMap){
		String action = multivaluedMap.get("action").get(0);
		AffiliateManageDTO manageDto = new AffiliateManageDTO();
		List<AffiliateDTO> dtos = new ArrayList<AffiliateDTO>();
		List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();
		manageDto.setData(dtos);
		manageDto.setFieldErrors(fieldErrors);
		if(action.equalsIgnoreCase("create")){
			Company c = prepareCompanyObj("0", multivaluedMap);
			Company exComp = companyDAO.getByCompanyCode(c.getCompanyId());
			if(exComp==null){
				try {
					c.setStatus(CasaConstants.ACTIVE_STATE);
					companyDAO.saveOrUpdate(c);
				} catch (Exception e) {
					e.printStackTrace();
					manageDto.setError("Server Exception, please try after sometime");
				}
				AffiliateDTO dto = new AffiliateDTO();
				dto.setRowId(""+c.getCompanyId());
				dto.setId(c.getCompanyId());
				dto.setAddress(c.getAddress());
				dto.setName(c.getCompanyName());
				dto.setCity(c.getCity());
				dto.setCountry(c.getCountry());
				dto.setPhone(c.getPhone());
				dto.setEmail(c.getEmail());
				dto.setZip(c.getZip());
				dto.setLogo(c.getLogo());
				manageDto.getData().add(dto);
			}else{
				FieldErrorDTO fError = new FieldErrorDTO();
				fError.setName("id");
				fError.setStatus("Affiliate ID already exist");
				manageDto.getFieldErrors().add(fError);
			}
			/*System.out.println("create");
			System.out.println(company.getAddress());
			System.out.println(company.getCity());
			System.out.println(company.getCompanyId());
			System.out.println(company.getCompanyName());
			System.out.println(company.getCountry());
			System.out.println(company.getLogo());
			System.out.println(company.getPhone());*/
			
		}else if(action.equalsIgnoreCase("edit")){
			Set<String> ids = getUniqueIds(multivaluedMap);
			for(String id : ids){
				Company c = prepareCompanyObj(id, multivaluedMap);
				try {
					c.setStatus(CasaConstants.ACTIVE_STATE);
					companyDAO.saveOrUpdate(c);
				} catch (Exception e) {
					e.printStackTrace();
					manageDto.setError("Server Exception, please try after sometime");
				}
				AffiliateDTO dto = new AffiliateDTO();
				dto.setRowId(""+c.getCompanyId());
				dto.setId(c.getCompanyId());
				dto.setAddress(c.getAddress());
				dto.setName(c.getCompanyName());
				dto.setCity(c.getCity());
				dto.setCountry(c.getCountry());
				dto.setPhone(c.getPhone());
				dto.setEmail(c.getEmail());
				dto.setZip(c.getZip());
				dto.setLogo(c.getLogo());
				manageDto.getData().add(dto);
			}
		}else if(action.equalsIgnoreCase("remove")){
			Set<String> ids = getUniqueIds(multivaluedMap);
			for(String id : ids){
				Company c = prepareCompanyObj(id, multivaluedMap);
				try {
					companyDAO.delete(c.getCompanyId());
				} catch (Exception e) {
					e.printStackTrace();
					manageDto.setError("Server Exception, please try after sometime");
				}
			}
		}
		return manageDto;
	}
	
	public StoreManageDTO manageStore(MultivaluedMap<String,String> multivaluedMap){
		String action = multivaluedMap.get("action").get(0);
		StoreManageDTO manageDto = new StoreManageDTO();
		List<StoreDTO> dtos = new ArrayList<StoreDTO>();
		List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();
		manageDto.setData(dtos);
		manageDto.setFieldErrors(fieldErrors);
		if(action.equalsIgnoreCase("create")){
			Store s = prepareStoreObj("0", multivaluedMap);
			boolean isValid=true;
			if(multivaluedMap.get("data[0][district]")!=null && multivaluedMap.get("data[0][district]").size()>0 
					&& (multivaluedMap.get("data[0][district]").get(0).toString()).equalsIgnoreCase("c_new_dist")){
				List<Store> ss = storeDAO.getStoresByDistrict(multivaluedMap.get("data[0][newDistrict]").get(0).toString(), s.getCompany().getCompanyId());
				if(ss!=null && ss.size()>0){
					FieldErrorDTO fError1 = new FieldErrorDTO();
					fError1.setName("newDistrict");
					fError1.setStatus("New District already exist");
					manageDto.getFieldErrors().add(fError1);
					isValid=false;
				}
			}
			List<Store> sss = storeDAO.getStoresByDistrictAndStoreName(s.getDistrict(),s.getName(),s.getCompany().getCompanyId());
			if(sss!=null && sss.size()>0){
				FieldErrorDTO fError2 = new FieldErrorDTO();
				fError2.setName("storeName");
				fError2.setStatus("Store name already exist");
				manageDto.getFieldErrors().add(fError2);
				isValid=false;
			}
			if(isValid){
				try {
					s.setStatus(CasaConstants.ACTIVE_STATE);
					storeDAO.saveOrUpdate(s);
				} catch (Exception e) {
					e.printStackTrace();
					manageDto.setError("Server Exception, please try after sometime");
				}
				StoreDTO dto = new StoreDTO();
				dto.setAffiliateId(""+s.getCompany().getCompanyId());
				dto.setAffiliateName(s.getCompany().getCompanyName());
				dto.setStoreName(s.getName());
				dto.setAddress(s.getAddress());
				dto.setDistrict(s.getDistrict());
				dto.setCity(s.getCity());
				dto.setCountry(s.getCountry());
				dto.setPhone(s.getPhone());
				dto.setZip(s.getZip());
				dto.setEmail(s.getEmail());
				dto.setDT_RowId(""+s.getStoreId());
				manageDto.getData().add(dto);
			}
		}else if(action.equalsIgnoreCase("edit")){
			Set<String> ids = getUniqueIds(multivaluedMap);
			for(String id : ids){
				Store s = prepareStoreObj(id, multivaluedMap);
				boolean isValid=true;
				if(multivaluedMap.get("data["+id+"][district]")!=null && multivaluedMap.get("data["+id+"][district]").size()>0 
						&& (multivaluedMap.get("data["+id+"][district]").get(0).toString()).equalsIgnoreCase("c_new_dist")){
					List<Store> ss = storeDAO.getStoresByDistrict(multivaluedMap.get("data["+id+"][newDistrict]").get(0).toString(), s.getCompany().getCompanyId());
					if(ss!=null && ss.size()>0){
						FieldErrorDTO fError1 = new FieldErrorDTO();
						fError1.setName("newDistrict");
						fError1.setStatus("New District already exist");
						manageDto.getFieldErrors().add(fError1);
						isValid=false;
					}
				}
				List<Store> sss = storeDAO.getStoresByDistrictAndStoreName(s.getDistrict(),s.getName(),s.getCompany().getCompanyId());
				if(sss!=null && sss.size()>0){
					String storeId = Long.toString(sss.get(0).getStoreId());
					if(storeId!=null && !storeId.equals(id)){
						FieldErrorDTO fError2 = new FieldErrorDTO();
						fError2.setName("storeName");
						fError2.setStatus("Store name already exist");
						manageDto.getFieldErrors().add(fError2);
						isValid=false;
					}
				}
				if(isValid){
					try {
						s.setStatus(CasaConstants.ACTIVE_STATE);
						storeDAO.saveOrUpdate(s);
					} catch (Exception e) {
						e.printStackTrace();
						manageDto.setError("Server Exception, please try after sometime");
					}
					StoreDTO dto = new StoreDTO();
					dto.setAffiliateId(""+s.getCompany().getCompanyId());
					dto.setAffiliateName(s.getCompany().getCompanyName());
					dto.setStoreName(s.getName());
					dto.setAddress(s.getAddress());
					dto.setDistrict(s.getDistrict());
					dto.setCity(s.getCity());
					dto.setCountry(s.getCountry());
					dto.setPhone(s.getPhone());
					dto.setZip(s.getZip());
					dto.setEmail(s.getEmail());
					dto.setDT_RowId(""+s.getStoreId());
					manageDto.getData().add(dto);
				}
			}
		}else if(action.equalsIgnoreCase("remove")){
			Set<String> ids = getUniqueIds(multivaluedMap);
			for(String id : ids){
				try {
					storeDAO.delete(new Long(id));
				} catch (NumberFormatException e) {
					e.printStackTrace();
					manageDto.setError("Server Exception, please try after sometime");
				}
			}
		}
		return manageDto;
	}
	
	public UserManageDTO manageUser(MultivaluedMap<String,String> multivaluedMap, String userId){
		String action = multivaluedMap.get("action").get(0);
		UserManageDTO manageDto = new UserManageDTO();
		List<UserDTO> dtos = new ArrayList<UserDTO>();
		List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();
		manageDto.setData(dtos);
		manageDto.setFieldErrors(fieldErrors);
		User owner = userDAO.getUserById(userId);
		if(action.equalsIgnoreCase("create")){
			User u = prepareUserObj("0", multivaluedMap);
			User exUser1 = userDAO.getUserById(u.getUserId());
			User exUser2 = userDAO.getUserByOdyUserId(u.getOdyUserId());
			//User exUser3 = userDAO.getUserByOdyUserId(u.getCustomerCode());
			if(exUser1==null && exUser2==null){
				u.setStatus(CasaConstants.ACTIVE_STATE);
				u.setLastModifiedTimestamp(new Date());
				u.setCreateTimestamp(new Date());
				u.setCreatedBy(owner.getId());
				u.setLastModifiedBy(owner.getId());
				//u.setIsDefaultPwd("Yes");
				try {
					userDAO.save(u);
				} catch (Exception e) {
					e.printStackTrace();
					manageDto.setError("Server Exception, please try after sometime");
				}
				UserDTO dto = new UserDTO();
				dto.setAddress(u.getAddress());
				dto.setAffiliateId(""+u.getStore().getCompany().getCompanyId());
				dto.setAffiliateName(u.getStore().getCompany().getCompanyName());
				dto.setCity(u.getCity());
				dto.setCountry(u.getCountry());
				dto.setDistrict(u.getStore().getDistrict());
				dto.setEmail(u.getEmail());
				dto.setExtn(u.getExtension());
				dto.setFax(u.getFax());
				dto.setFirstName(u.getFirstName());
				dto.setIata(u.getIATA());
				dto.setLastName(u.getLastName());
				dto.setOdyPassword(u.getOdyPassword());
				dto.setOdyUserId(u.getOdyUserId());
				//dto.setPassword(u.getPassword());
				dto.setPassword("");
				dto.setPhone(u.getPhoneNumber());
				dto.setRole(u.getRole());
				dto.setRowId(""+u.getId());
				dto.setState(u.getState());
				dto.setStoreId(""+u.getStore().getStoreId());
				dto.setStoreName(u.getStore().getName());
				dto.setUserId(u.getUserId());
				dto.setZip(u.getZip());
				dto.setCustomerCode(u.getCustomerCode());
				manageDto.getData().add(dto);
			} else {
				if(exUser1!=null){
					FieldErrorDTO fError = new FieldErrorDTO();
					fError.setName("userId");
					fError.setStatus("User ID already exist");
					manageDto.getFieldErrors().add(fError);
				}
				if(exUser2!=null){
					FieldErrorDTO fError = new FieldErrorDTO();
					fError.setName("odyUserId");
					fError.setStatus("Ody User ID is already mapped to user '"+exUser2.getUserId()+"'");
					manageDto.getFieldErrors().add(fError);
				}
				/*if(exUser3!=null){
					FieldErrorDTO fError = new FieldErrorDTO();
					fError.setName("customerCode");
					fError.setStatus("Customer Code is already mapped to user '"+exUser3.getUserId()+"'");
					manageDto.getFieldErrors().add(fError);
				}*/
			}
			
		}else if(action.equalsIgnoreCase("edit")){
			Set<String> ids = getUniqueIds(multivaluedMap);
			for(String id : ids){
				User u = prepareUserObj(id, multivaluedMap);
				u.setLastModifiedTimestamp(new Date());
				u.setLastModifiedBy(owner.getId());
				//u.setStatus("ACTIVE");
				try {
					userDAO.save(u);
				} catch (Exception e) {
					e.printStackTrace();
					manageDto.setError("Server Exception, please try after sometime");
				}
				UserDTO dto = new UserDTO();
				dto.setAddress(u.getAddress());
				dto.setAffiliateId(""+u.getStore().getCompany().getCompanyId());
				dto.setAffiliateName(u.getStore().getCompany().getCompanyName());
				dto.setCity(u.getCity());
				dto.setCountry(u.getCountry());
				dto.setDistrict(u.getStore().getDistrict());
				dto.setEmail(u.getEmail());
				dto.setExtn(u.getExtension());
				dto.setFax(u.getFax());
				dto.setFirstName(u.getFirstName());
				dto.setIata(u.getIATA());
				dto.setLastName(u.getLastName());
				dto.setOdyPassword(u.getOdyPassword());
				dto.setOdyUserId(u.getOdyUserId());
				//dto.setPassword(u.getPassword());
				dto.setPassword("");
				dto.setPhone(u.getPhoneNumber());
				dto.setRole(u.getRole());
				dto.setRowId(""+u.getId());
				dto.setState(u.getState());
				dto.setStoreId(""+u.getStore().getStoreId());
				dto.setStoreName(u.getStore().getName());
				dto.setUserId(u.getUserId());
				dto.setZip(u.getZip());
				dto.setCustomerCode(u.getCustomerCode());
				manageDto.getData().add(dto);
			}
		}else if(action.equalsIgnoreCase("remove")){
			Set<String> ids = getUniqueIds(multivaluedMap);
			for(String id : ids){
				//User u = prepareUserObj(id, multivaluedMap);
				if(id!=null && !id.equalsIgnoreCase("0")){
					User exUser = userDAO.getByPrimaryId(new Long(id));
					if(exUser!=null){
						exUser.setStatus(CasaConstants.DELETE_STATE);
						exUser.setLastModifiedTimestamp(new Date());
						exUser.setLastModifiedBy(owner.getId());
						userDAO.save(exUser);
					}else{
						System.out.println("Went worng user not exist in DB but got del request");
					}
				}
			}
		}
		return manageDto;
	}
	
	private Set<String> getUniqueIds(MultivaluedMap<String,String> multivaluedMap){
		Set<String> keys = multivaluedMap.keySet();
		Set<String> ids = new HashSet<String>();
		for(String key : keys){
			if(key.startsWith("data[")){
				ids.add(key.substring(key.indexOf("[")+1, key.indexOf("]")));
			}else{
				continue;
			}
		}
		return ids;
	}
	
	public Company prepareCompanyObj(String id, MultivaluedMap<String,String> multivaluedMap){
		Company company = new Company();
		company.setCompanyId(isEmpty(multivaluedMap.get("data["+id+"][id]"))?0l:new Long(multivaluedMap.get("data["+id+"][id]").get(0).toString()));
		company.setCompanyName(isEmpty(multivaluedMap.get("data["+id+"][name]"))?null:multivaluedMap.get("data["+id+"][name]").get(0).toString());
		company.setAddress(isEmpty(multivaluedMap.get("data["+id+"][address]"))?null:multivaluedMap.get("data["+id+"][address]").get(0).toString());
		company.setCity(isEmpty(multivaluedMap.get("data["+id+"][city]"))?null:multivaluedMap.get("data["+id+"][city]").get(0).toString());
		company.setCountry(isEmpty(multivaluedMap.get("data["+id+"][country]"))?null:multivaluedMap.get("data["+id+"][country]").get(0).toString());
		company.setPhone(isEmpty(multivaluedMap.get("data["+id+"][phone]"))?null:multivaluedMap.get("data["+id+"][phone]").get(0).toString());
		company.setLogo(isEmpty(multivaluedMap.get("data["+id+"][logo]"))?null:multivaluedMap.get("data["+id+"][logo]").get(0).toString());
		company.setEmail(isEmpty(multivaluedMap.get("data["+id+"][email]"))?null:multivaluedMap.get("data["+id+"][email]").get(0).toString());
		company.setZip(isEmpty(multivaluedMap.get("data["+id+"][zip]"))?null:multivaluedMap.get("data["+id+"][zip]").get(0).toString());
		return company;
	}
	
	public Store prepareStoreObj(String id, MultivaluedMap<String,String> multivaluedMap){
		Store store = new Store();
		Company c = companyDAO.getByCompanyCode(new Long(isEmpty(multivaluedMap.get("data["+id+"][affiliateId]"))?0l:new Long(multivaluedMap.get("data["+id+"][affiliateId]").get(0).toString())));
		store.setCompany(c);
		store.setStoreId(new Long(id));
		store.setName(isEmpty(multivaluedMap.get("data["+id+"][storeName]"))?null:multivaluedMap.get("data["+id+"][storeName]").get(0).toString());
		store.setAddress(isEmpty(multivaluedMap.get("data["+id+"][address]"))?null:multivaluedMap.get("data["+id+"][address]").get(0).toString());
		store.setCity(isEmpty(multivaluedMap.get("data["+id+"][city]"))?null:multivaluedMap.get("data["+id+"][city]").get(0).toString());
		store.setCountry(isEmpty(multivaluedMap.get("data["+id+"][country]"))?null:multivaluedMap.get("data["+id+"][country]").get(0).toString());
		store.setPhone(isEmpty(multivaluedMap.get("data["+id+"][phone]"))?null:multivaluedMap.get("data["+id+"][phone]").get(0).toString());
		store.setZip(isEmpty(multivaluedMap.get("data["+id+"][zip]"))?null:multivaluedMap.get("data["+id+"][zip]").get(0).toString());
		store.setEmail(isEmpty(multivaluedMap.get("data["+id+"][email]"))?null:multivaluedMap.get("data["+id+"][email]").get(0).toString());
		
		if(multivaluedMap.get("data["+id+"][district]")!=null && multivaluedMap.get("data["+id+"][district]").size()>0 
				&& (multivaluedMap.get("data["+id+"][district]").get(0).toString()).equalsIgnoreCase("c_new_dist")){
			store.setDistrict(isEmpty(multivaluedMap.get("data["+id+"][newDistrict]"))?null:multivaluedMap.get("data["+id+"][newDistrict]").get(0).toString());
		}else{
			store.setDistrict(isEmpty(multivaluedMap.get("data["+id+"][district]"))?null:multivaluedMap.get("data["+id+"][district]").get(0).toString());
		}				
		return store;
	}
	
	public User prepareUserObj(String id, MultivaluedMap<String,String> multivaluedMap){
		User u = null;
		if(id!=null && !"0".equalsIgnoreCase(id) && !"".equalsIgnoreCase(id))
		  u = userDAO.getByPrimaryId(new Long(id));
		else
		  u = new User();
		Store s = storeDAO.getById(new Long(isEmpty(multivaluedMap.get("data["+id+"][storeId]"))?0l:new Long(multivaluedMap.get("data["+id+"][storeId]").get(0).toString())));
		u.setAddress(isEmpty(multivaluedMap.get("data["+id+"][address]"))?null:multivaluedMap.get("data["+id+"][address]").get(0).toString());
		u.setCity(isEmpty(multivaluedMap.get("data["+id+"][city]"))?null:multivaluedMap.get("data["+id+"][city]").get(0).toString());
		u.setCountry(isEmpty(multivaluedMap.get("data["+id+"][country]"))?null:multivaluedMap.get("data["+id+"][country]").get(0).toString());
		//u.setCreateTimestamp(new Date());
		u.setEmail(isEmpty(multivaluedMap.get("data["+id+"][email]"))?null:multivaluedMap.get("data["+id+"][email]").get(0).toString());
		u.setExtension(isEmpty(multivaluedMap.get("data["+id+"][extn]"))?null:multivaluedMap.get("data["+id+"][extn]").get(0).toString());
		u.setFax(isEmpty(multivaluedMap.get("data["+id+"][fax]"))?null:multivaluedMap.get("data["+id+"][fax]").get(0).toString());
		u.setFirstName(isEmpty(multivaluedMap.get("data["+id+"][firstName]"))?null:multivaluedMap.get("data["+id+"][firstName]").get(0).toString());
		u.setIATA(isEmpty(multivaluedMap.get("data["+id+"][iata]"))?null:multivaluedMap.get("data["+id+"][iata]").get(0).toString());
		u.setId(new Long(id));
		//u.setLastModifiedTimestamp(new Date());
		u.setLastName(isEmpty(multivaluedMap.get("data["+id+"][lastName]"))?null:multivaluedMap.get("data["+id+"][lastName]").get(0).toString());
		u.setOdyPassword(isEmpty(multivaluedMap.get("data["+id+"][odyPassword]"))?null:multivaluedMap.get("data["+id+"][odyPassword]").get(0).toString());
		u.setOdyUserId(isEmpty(multivaluedMap.get("data["+id+"][odyUserId]"))?null:multivaluedMap.get("data["+id+"][odyUserId]").get(0).toString());
		if("0".equalsIgnoreCase(id)){
			u.setPassword(isEmpty(multivaluedMap.get("data["+id+"][password]"))?null:multivaluedMap.get("data["+id+"][password]").get(0).toString());
			u.setIsDefaultPwd("Yes");
		}else if(multivaluedMap.get("data["+id+"][changepwd]")!=null && multivaluedMap.get("data["+id+"][changepwd]").size()>0 
				&& (multivaluedMap.get("data["+id+"][changepwd]").get(0).toString()).equalsIgnoreCase("yes")){
			u.setPassword(isEmpty(multivaluedMap.get("data["+id+"][password]"))?null:multivaluedMap.get("data["+id+"][password]").get(0).toString());
			u.setIsDefaultPwd("Yes");
		}
		u.setPhoneNumber(isEmpty(multivaluedMap.get("data["+id+"][phone]"))?null:multivaluedMap.get("data["+id+"][phone]").get(0).toString());
		u.setRole(isEmpty(multivaluedMap.get("data["+id+"][role]"))?null:multivaluedMap.get("data["+id+"][role]").get(0).toString());
		u.setState(isEmpty(multivaluedMap.get("data["+id+"][state]"))?null:multivaluedMap.get("data["+id+"][state]").get(0).toString());
		
		u.setStore(s);
		u.setUserId(isEmpty(multivaluedMap.get("data["+id+"][userId]"))?null:multivaluedMap.get("data["+id+"][userId]").get(0).toString());
		u.setZip(isEmpty(multivaluedMap.get("data["+id+"][zip]"))?null:multivaluedMap.get("data["+id+"][zip]").get(0).toString());
		u.setCustomerCode(isEmpty(multivaluedMap.get("data["+id+"][customerCode]"))?null:multivaluedMap.get("data["+id+"][customerCode]").get(0).toString());
		return u;
	}
	private boolean isEmpty(List<String> list){
		if(list!=null && list.size()>0)
			return false;
		else
			return true;
	}
}
