package com.casa.booking.dto;

public class FinanceDTO {
	private String issuedDate;
	private String agency;
	private String pnr;
	private String eTicketNbr;
	private String passengerName;
	private String airline;
	private Double achamt;
	private Double ccamt;
	private Double commissioncc;
	private Double profit;
	public String getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(String issuedDate) {
		this.issuedDate = issuedDate;
	}
	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}
	public String getPnr() {
		return pnr;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	public String geteTicketNbr() {
		return eTicketNbr;
	}
	public void seteTicketNbr(String eTicketNbr) {
		this.eTicketNbr = eTicketNbr;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public Double getAchamt() {
		return achamt;
	}
	public void setAchamt(Double achamt) {
		this.achamt = achamt;
	}
	public Double getCcamt() {
		return ccamt;
	}
	public void setCcamt(Double ccamt) {
		this.ccamt = ccamt;
	}
	public Double getCommissioncc() {
		return commissioncc;
	}
	public void setCommissioncc(Double commissioncc) {
		this.commissioncc = commissioncc;
	}
	public Double getProfit() {
		return profit;
	}
	public void setProfit(Double profit) {
		this.profit = profit;
	}
	
	
}
