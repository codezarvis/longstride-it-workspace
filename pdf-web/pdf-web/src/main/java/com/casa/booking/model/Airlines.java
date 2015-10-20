package com.casa.booking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="airlines")
public class Airlines {

	@Id
	@Column(name="id")
	private long id;
	
	@Column(name="airline_code")
	private String airlineCode;
	
	@Column(name="airline_full_name")
	private String airlineFullName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public String getAirlineFullName() {
		return airlineFullName;
	}

	public void setAirlineFullName(String airlineFullName) {
		this.airlineFullName = airlineFullName;
	}
	
}
