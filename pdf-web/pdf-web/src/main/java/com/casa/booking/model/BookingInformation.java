package com.casa.booking.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Lob;

@Entity
@Table(name="booking_information")
public class BookingInformation {
	@Id  
    @GeneratedValue 
	private long id;
	
	@Column(name="uuid")
	private String uuid;
	
	@Column(name="requestorId")
	private String requestorId;
	
	@Column(name="userId")
	private String userId;
	
	@Column(name="agentCode")
	private String agentCode;
	
	@Column(name="agentName")
	private String agentName;
	
	@Column(name="agentEmail")
	private String agentEmail;
	
	@Column(name="companyCode")
	private long companyCode;
	
	@Column(name="storeId")
	private long storeId;
	
	@Column(name="bookingDate")
	private Date bookingDate;
	
	@Column(name="travelDate")
	private Date travelDate;
	
	@Column(name="pnr")
	private String pnr;
	
	@Column(name="travellerName1")
	private String travellerName1;
	
	@Column(name="travellerName2")
	private String travellerName2;
	
	@Column(name="travellerName3")
	private String travellerName3;
	
	@Column(name="travellerName4")
	private String travellerName4;
	
	@Column(name="travellerName5")
	private String travellerName5;
	
	@Column(name="travellerName6")
	private String travellerName6;
	
	@Column(name="travellerName7")
	private String travellerName7;
	
	@Column(name="travellerName8")
	private String travellerName8;
	
	@Column(name="fromLocation")
	private String fromLocation;
	
	@Column(name="toLocation")
	private String toLocation;
	
	@Lob
	@Column(name="xmlContent")
	private String xmlContent;
	
	@Column(name="xmlFileName")
	private String xmlFileName;
	
	@Column(name="pdfTemplateName")
	private String pdfTemplateName;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="airline")
	private String airline;
	
	public BookingInformation() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRequestorId() {
		return requestorId;
	}

	public void setRequestorId(String requestorId) {
		this.requestorId = requestorId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentEmail() {
		return agentEmail;
	}

	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}
	
	public long getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(long companyCode) {
		this.companyCode = companyCode;
	}
	
	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getTravellerName1() {
		return travellerName1;
	}

	public void setTravellerName1(String travellerName1) {
		this.travellerName1 = travellerName1;
	}

	public String getTravellerName2() {
		return travellerName2;
	}

	public void setTravellerName2(String travellerName2) {
		this.travellerName2 = travellerName2;
	}

	public String getTravellerName3() {
		return travellerName3;
	}

	public void setTravellerName3(String travellerName3) {
		this.travellerName3 = travellerName3;
	}

	public String getTravellerName4() {
		return travellerName4;
	}

	public void setTravellerName4(String travellerName4) {
		this.travellerName4 = travellerName4;
	}

	public String getTravellerName5() {
		return travellerName5;
	}

	public void setTravellerName5(String travellerName5) {
		this.travellerName5 = travellerName5;
	}

	public String getTravellerName6() {
		return travellerName6;
	}

	public void setTravellerName6(String travellerName6) {
		this.travellerName6 = travellerName6;
	}

	public String getTravellerName7() {
		return travellerName7;
	}

	public void setTravellerName7(String travellerName7) {
		this.travellerName7 = travellerName7;
	}

	public String getTravellerName8() {
		return travellerName8;
	}

	public void setTravellerName8(String travellerName8) {
		this.travellerName8 = travellerName8;
	}

	public String getXmlContent() {
		return xmlContent;
	}

	public void setXmlContent(String xmlContent) {
		this.xmlContent = xmlContent;
	}

	public String getXmlFileName() {
		return xmlFileName;
	}

	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public String getPdfTemplateName() {
		return pdfTemplateName;
	}

	public void setPdfTemplateName(String pdfTemplateName) {
		this.pdfTemplateName = pdfTemplateName;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}
}
