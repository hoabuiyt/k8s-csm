package com.bank.csm.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotNull;

/**
 * Customer DTO class.
 *
 * @author kumar-sand
 */
public class CustomerDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	final private static long serialVersionUID = 1l;
	
	private Long id;

	@NotNull
	private String cif;
	
	@NotNull
	private String name;
	
	@NotNull
	private Boolean active;
	
	private String email;

	private String mobileNo;
	
	private String addressLine1;
	
	private String addressLine2;
	
	private String district;
	
	private String state;
	
	private String country;
	
	private Set<AccountDTO> accounts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<AccountDTO> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<AccountDTO> accounts) {
		this.accounts = accounts;
	}
}
