package com.bank.csm.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

/**
 * Account DTO class.
 *
 * @author kumar-sand
 */
public class AccountDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	final private static long serialVersionUID = 1l;

	private Long id;

	@NotNull
	private String branchCode;

	@NotNull
	private String accountNo;
	
	@NotNull
	private String purpose;
	
	@NotNull
	private Boolean active;
	
	private BigDecimal balance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
}
