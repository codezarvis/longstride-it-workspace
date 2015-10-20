package com.casa.booking.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserDTO {

	@JsonProperty("DT_RowId")
	private String rowId;
	
	private String affiliateId;
	private String affiliateName;
	private String district;
	private String storeId;
	private String storeName;
	private String userId;
	private String password;
	private String odyUserId;
	private String odyPassword;
	private String role;
	private String firstName;
	private String lastName;
	private String iata;
	private String address;
	private String city;
	private String country;
	private String state;
	private String phone;
	private String extn;
	private String fax;
	private String zip;
	private String email;
	private String customerCode;
	private String changepwd="no";
	
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public String getAffiliateId() {
		return affiliateId;
	}
	public void setAffiliateId(String affiliateId) {
		this.affiliateId = affiliateId;
	}
	public String getAffiliateName() {
		return affiliateName;
	}
	public void setAffiliateName(String affiliateName) {
		this.affiliateName = affiliateName;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getIata() {
		return iata;
	}
	public void setIata(String iata) {
		this.iata = iata;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getExtn() {
		return extn;
	}
	public void setExtn(String extn) {
		this.extn = extn;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getChangepwd() {
		return changepwd;
	}
	public void setChangepwd(String changepwd) {
		this.changepwd = changepwd;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
}
