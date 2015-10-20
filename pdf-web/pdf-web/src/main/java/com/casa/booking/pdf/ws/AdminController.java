package com.casa.booking.pdf.ws;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.casa.booking.dto.AffiliateDTOs;
import com.casa.booking.dto.AffiliateManageDTO;
import com.casa.booking.dto.DropdownDTO;
import com.casa.booking.dto.FieldErrorDTO;
import com.casa.booking.dto.StoreDTOs;
import com.casa.booking.dto.StoreManageDTO;
import com.casa.booking.dto.Upload;
import com.casa.booking.dto.UserDTOs;
import com.casa.booking.dto.UserManageDTO;
import com.casa.booking.service.AdminService;
import com.casa.booking.util.CasaConstants;
import com.casa.booking.util.PropsReader;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Component
@Path("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@POST
	@Path("/changepwd")
	@Produces(MediaType.TEXT_PLAIN)
	public String changePwd(@FormParam("currpwd") String currpwd, @FormParam("newpwd") String newpwd,
			@FormParam("confnewpwd") String confnewpwd){
		System.out.println(currpwd+", "+newpwd+", "+confnewpwd);
		Subject subject = SecurityUtils.getSubject();
		return adminService.changePassword(subject.getPrincipal().toString(), currpwd, newpwd);
	}
	
	@GET
	@Path("/isdefaultpwd")
	@Produces(MediaType.TEXT_PLAIN)
	public String changePwd(){
		Subject subject = SecurityUtils.getSubject();
		return adminService.isDefaultPwd(subject.getPrincipal().toString());
	}
	
	@GET
    @Path("/affiliate")
    @Produces(MediaType.APPLICATION_JSON)
	public AffiliateDTOs getAffiliates(){
		String role = getRole();
		return adminService.getAllAffiliates(role);
	}
	
	@GET
    @Path("/dropdown/affiliate")
    @Produces(MediaType.APPLICATION_JSON)
	public List<DropdownDTO> getAffiliatesForDropdown(){
		Subject subject = SecurityUtils.getSubject();
		return adminService.getAllAffiliatesForDropdown(getRole(), subject.getPrincipal().toString());
	}
	
	@GET
    @Path("/dropdown/district")
    @Produces(MediaType.APPLICATION_JSON)
	public List<DropdownDTO> getDistrictsForDropdown(@DefaultValue("") @QueryParam("affiliate") String affiliate, @DefaultValue("") @QueryParam("page") String page){
		Subject subject = SecurityUtils.getSubject();
		return adminService.getAllDistrictsForDropdown(affiliate, getRole(), subject.getPrincipal().toString(), page);
	}
	
	@GET
    @Path("/dropdown/store")
    @Produces(MediaType.APPLICATION_JSON)
	public List<DropdownDTO> getStoresForDropdown(@DefaultValue("") @QueryParam("district") String district,
			@DefaultValue("") @QueryParam("affiliate") String affiliate){
		Subject subject = SecurityUtils.getSubject();
		return adminService.getAllStoresForDropdown(affiliate, district, getRole(), subject.getPrincipal().toString());
	}
	
	@GET
    @Path("/dropdown/role")
    @Produces(MediaType.APPLICATION_JSON)
	public List<DropdownDTO> getRolesForDropdown(){
		return adminService.getAllRolesForDropdown(getRole());
	}
	
	@GET
    @Path("/store")
    @Produces(MediaType.APPLICATION_JSON)
	public StoreDTOs getStores(){
		Subject subject = SecurityUtils.getSubject();
		return adminService.getAllStores(getRole(), subject.getPrincipal().toString());
	}
	
	@GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
	public UserDTOs getUsers(){
		Subject subject = SecurityUtils.getSubject();
		return adminService.getAllUsers(getRole(), subject.getPrincipal().toString());
	}
	
	@POST
    @Path("/affiliate/manage")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
    public AffiliateManageDTO uploadFile(@FormDataParam("upload") InputStream fis,
                    @FormDataParam("upload") FormDataContentDisposition fdcd) {
  
        OutputStream outpuStream = null;
        AffiliateManageDTO dto = new AffiliateManageDTO();
        if(fdcd!=null){
	        String fileName = fdcd.getFileName();
	        //System.out.println("File Name: " + fdcd.getFileName());
	        //System.out.println("File Size: " + fdcd.getSize());
	        //String filePath = "/tmp/" + fileName;
	        if(fileName!=null && (fileName.endsWith(".png") || fileName.endsWith(".PNG") || fileName.endsWith(".jpg") || fileName.endsWith(".JPG")
	        		|| fileName.endsWith(".gif") || fileName.endsWith(".GIF"))){
	        	String extn = fileName.substring(fileName.lastIndexOf(".")+1);
	        	if(fdcd.getSize()<=100000){
		        	fileName=fileName.replaceAll(" ", "_");
		        	String fileactualname = System.currentTimeMillis()+"_"+fileName;
			        String filePath = PropsReader.getInstance().getPropValue("affiliate.logo.file.path")+fileactualname;
			        System.out.println("File Path: " + filePath);
			        try {
			        	File original = new File(filePath);
			            int read = 0;
			            byte[] bytes = new byte[1024];
			            outpuStream = new FileOutputStream(original);
			            while ((read = fis.read(bytes)) != -1) {
			                outpuStream.write(bytes, 0, read);
			            }
			            outpuStream.flush();
			            outpuStream.close();
			            
			            BufferedImage img = ImageIO.read(original);
			            BufferedImage thumbImg = Scalr.resize(img, Method.QUALITY,Mode.AUTOMATIC, 50,50, Scalr.OP_ANTIALIAS);
			            ByteArrayOutputStream os = new ByteArrayOutputStream();
			            ImageIO.write(thumbImg,extn,os);
			            File f = new File(PropsReader.getInstance().getPropValue("affiliate.logo.file.path")+"thumb_"+fileactualname);
			            ImageIO.write(thumbImg, extn, f);
			        } catch(IOException iox){
			            iox.printStackTrace();
			            FieldErrorDTO error = new FieldErrorDTO();
			        	error.setName("logo");
			        	error.setStatus("Upload error please contact system admin");
			        	dto.setFieldErrors(new ArrayList<FieldErrorDTO>());
			        	dto.getFieldErrors().add(error);
			        } finally {
			            if(outpuStream != null){
			                try{outpuStream.close();} catch(Exception ex){}
			            }
			        }
			        Upload up = new Upload();
			        up.setId(fileactualname);
			        dto.setUpload(up);
	        	}else{
	        		FieldErrorDTO error = new FieldErrorDTO();
		        	error.setName("logo");
		        	error.setStatus("Logo Image must be smaller than 100K");
		        	dto.setFieldErrors(new ArrayList<FieldErrorDTO>());
		        	dto.getFieldErrors().add(error);
	        	}
	        }else{
	        	FieldErrorDTO error = new FieldErrorDTO();
	        	error.setName("logo");
	        	error.setStatus("Only png or jpg or gif image format allowed");
	        	dto.setFieldErrors(new ArrayList<FieldErrorDTO>());
	        	dto.getFieldErrors().add(error);
	        }
        }else{
        	System.out.println("now got value map........");
        }
        return dto;
    }

	
	@POST
    @Path("/affiliate/manage")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
	public AffiliateManageDTO affilateManage(MultivaluedMap<String,String> multivaluedMap){
		//System.out.println(multivaluedMap);
		return adminService.manageAffiliate(multivaluedMap);
	}
	
	@POST
    @Path("/store/manage")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
	public StoreManageDTO storeManage(MultivaluedMap<String,String> multivaluedMap){
		//System.out.println(multivaluedMap);
		return adminService.manageStore(multivaluedMap);
	}
	
	@POST
    @Path("/user/manage")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
	public UserManageDTO userManage(MultivaluedMap<String,String> multivaluedMap){
		//System.out.println(multivaluedMap);
		Subject subject = SecurityUtils.getSubject();
		return adminService.manageUser(multivaluedMap, subject.getPrincipal().toString());
	}
	
	@GET
    @Path("getlogo")
    @Produces("image/*")
	public Response getLogo(@DefaultValue("") @QueryParam("size") String size,
			@DefaultValue("") @QueryParam("logo") String logo){
		File file = null;
		if(size.equalsIgnoreCase("thumb")){
			file=new File(PropsReader.getInstance().getPropValue("affiliate.logo.file.path")+"thumb_"+logo);
		}else{
			file=new File(PropsReader.getInstance().getPropValue("affiliate.logo.file.path")+logo);
		}
		ResponseBuilder response = Response.ok((Object) file);
		return response.build();
	}
	
	private String getRole(){
		Subject subject = SecurityUtils.getSubject();
		//List<BookingDetailsDTO> dtos = new ArrayList<BookingDetailsDTO>();
		//subject.hasRole("admin")
		String role=null;
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
		}else if(subject.hasRole(CasaConstants.ROLE_ADMIN)){
			role=CasaConstants.ROLE_ADMIN;
		}
		return role;
	}
}
