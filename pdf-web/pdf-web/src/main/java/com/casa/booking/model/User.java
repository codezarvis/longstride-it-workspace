package com.casa.booking.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	@Column(name="id")
	private long id;
	
	@Column(name="userId")
	private String userId;
	
	@Column(name="password")
	private String password;
	
	@Column(name="odyUserId")
	private String odyUserId;
	
	@Column(name="odyPassword")
	private String odyPassword;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="customerCode")
	private String customerCode;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phoneNumber")
	private String phoneNumber;
	
	@Column(name="extension")
	private String extension;
	
	@Column(name="fax")
	private String fax;
	
	@Column(name="IATA")
	private String IATA;
	
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="zip")
	private String zip;
	
	@Column(name="country")
	private String country;
	
	@Column(name="referral")
	private String referral;
	
	@Column(name="lastModifiedTimestamp")
	private Date lastModifiedTimestamp;
	
	@Column(name="createTimestamp")
	private Date createTimestamp;
	
	@Column(name="status")
	private String status;

	@Column(name="role")
	private String role;
	
	@Column(name="createdBy")
	private long createdBy;
	
	@Column(name="lastModifiedBy")
	private long lastModifiedBy;
	
	@ManyToOne
    @JoinColumn(name="storeId")
	private Store store;
	
	@Column(name="isDefaultPwd")
	private String isDefaultPwd;
	
	public User() {

	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getOdyUserId() {
		return odyUserId;
	}


	public void setOdyUserId(String odyUserId) {
		this.odyUserId = odyUserId;
	}


	public String getOdyPassword() {
		return odyPassword;
	}


	public void setOdyPassword(String odyPassword) {
		this.odyPassword = odyPassword;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public Store getStore() {
		return store;
	}


	public void setStore(Store store) {
		this.store = store;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getExtension() {
		return extension;
	}


	public void setExtension(String extension) {
		this.extension = extension;
	}


	public String getFax() {
		return fax;
	}


	public void setFax(String fax) {
		this.fax = fax;
	}


	public String getIATA() {
		return IATA;
	}


	public void setIATA(String iATA) {
		IATA = iATA;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getReferral() {
		return referral;
	}


	public void setReferral(String referral) {
		this.referral = referral;
	}


	public Date getLastModifiedTimestamp() {
		return lastModifiedTimestamp;
	}


	public void setLastModifiedTimestamp(Date lastModifiedTimestamp) {
		this.lastModifiedTimestamp = lastModifiedTimestamp;
	}


	public Date getCreateTimestamp() {
		return createTimestamp;
	}


	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}


	public long getLastModifiedBy() {
		return lastModifiedBy;
	}


	public void setLastModifiedBy(long lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}


	public String getIsDefaultPwd() {
		return isDefaultPwd;
	}


	public void setIsDefaultPwd(String isDefaultPwd) {
		this.isDefaultPwd = isDefaultPwd;
	}


	public String getCustomerCode() {
		return customerCode;
	}


	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

}
