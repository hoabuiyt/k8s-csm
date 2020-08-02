package com.bank.csm.services;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bank.csm.dto.BankBranchDTO;
import com.bank.csm.exceptions.CommonAPIException;

/**
 * Services for region.
 *
 * @author kumar-sand
 */
public interface BankBranchService {

	/**
	 * Creates the.
	 *
	 * @param BankBranchDTO the customer DTO
	 * @return the customer DTO
	 */
	public BankBranchDTO create(BankBranchDTO BankBranchDTO) throws CommonAPIException;
	
	/**
	 * Update.
	 *
	 * @param BankBranchDTO the customer DTO
	 * @return the customer DTO
	 */
	public BankBranchDTO update(BankBranchDTO BankBranchDTO) throws CommonAPIException;
	
	/**
	 * Removes the.
	 *
	 * @param BankBranchDTO the customer DTO
	 * @return the customer DTO
	 */
	public BankBranchDTO remove(String branchCode) throws CommonAPIException;
	
	/**
	 * Gets the.
	 *
	 * @param BankBranchDTO the customer DTO
	 * @return the customer DTO
	 */
	public BankBranchDTO get(String branchCode);
	
	/**
	 * Gets the all.
	 *
	 * @param query the query
	 * @param pageable the pageable
	 * @return the all
	 */
	public Page<BankBranchDTO> getAll(String query, Pageable pageable);
	
	/**
	 * Do adjustment.
	 *
	 * @param accountNo the account no
	 * @param amount the amount
	 * @return the boolean
	 */
	public BigDecimal doAdjustment(String branchCode, BigDecimal amount, boolean isPositive) throws CommonAPIException;
}
