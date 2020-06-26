package com.bank.csm.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

/**
 * BankBranch DTO class.
 *
 * @author kumar-sand
 */
public class BankBranchDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	final private static long serialVersionUID = 1l;
	
	private Long id;

	@NotNull
	private String bankName;
	
	@NotNull
	private String name;
	
	@NotNull
	private String bankCode;
	
	@NotNull
	private String code;

	private BigDecimal balance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
