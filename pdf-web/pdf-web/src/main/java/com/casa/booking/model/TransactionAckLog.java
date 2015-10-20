package com.casa.booking.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transaction_ack_log")
public class TransactionAckLog {

	@Id  
    @GeneratedValue
	private long id;
	
	@Column(name="companyCode")
	private long companyCode;
	
	@Column(name="companyName")
	private String companyName;
	
	@Column(name="casherCode")
	private String casherCode;
	
	@Column(name="casherName")
	private String casherName;
	
	@Column(name="xmlFileName")
	private String xmlFileName;
	
	@Column(name="postXmlRespStatus")
	private long postXmlRespStatus;
	
	@Column(name="postXmlRespMesg")
	private String postXmlRespMesg;
	
	@Column(name="transactiontimestamp")
	private Date transactionTimestamp;
	
	@Column(name="logtimestamp")
	private Date logTimestamp;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(long companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCasherCode() {
		return casherCode;
	}

	public void setCasherCode(String casherCode) {
		this.casherCode = casherCode;
	}

	public String getCasherName() {
		return casherName;
	}

	public void setCasherName(String casherName) {
		this.casherName = casherName;
	}

	public String getXmlFileName() {
		return xmlFileName;
	}

	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public long getPostXmlRespStatus() {
		return postXmlRespStatus;
	}

	public void setPostXmlRespStatus(long postXmlRespStatus) {
		this.postXmlRespStatus = postXmlRespStatus;
	}

	public String getPostXmlRespMesg() {
		return postXmlRespMesg;
	}

	public void setPostXmlRespMesg(String postXmlRespMesg) {
		this.postXmlRespMesg = postXmlRespMesg;
	}

	public Date getTransactionTimestamp() {
		return transactionTimestamp;
	}

	public void setTransactionTimestamp(Date transactionTimestamp) {
		this.transactionTimestamp = transactionTimestamp;
	}

	public Date getLogTimestamp() {
		return logTimestamp;
	}

	public void setLogTimestamp(Date logTimestamp) {
		this.logTimestamp = logTimestamp;
	}
	
}
