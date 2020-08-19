package com.bank.csm.services;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bank.csm.dto.CustomerDTO;
import com.bank.csm.exceptions.CommonAPIException;

/**
 * Services for region.
 *
 * @author kumar-sand
 */
public interface CustomerService {

	/**
	 * Creates the.
	 *
	 * @param customerDTO the customer DTO
	 * @return the customer DTO
	 */
	public CustomerDTO create(CustomerDTO customerDTO) throws CommonAPIException;
	
	/**
	 * Update.
	 *
	 * @param customerDTO the customer DTO
	 * @return the customer DTO
	 */
	public CustomerDTO update(CustomerDTO customerDTO) throws CommonAPIException;
	
	/**
	 * Removes the.
	 *
	 * @param customerDTO the customer DTO
	 * @return the customer DTO
	 */
	public CustomerDTO remove(String customerCif);
	
	/**
	 * Gets the.
	 *
	 * @param customerDTO the customer DTO
	 * @return the customer DTO
	 */
	public CustomerDTO get(String customerCif);
	
	/**
	 * Gets the all.
	 *
	 * @param query the query
	 * @param pageable the pageable
	 * @return the all
	 */
	public Page<CustomerDTO> getAll(String query, Pageable pageable);
	
	/**
	 * Gets the by account no.
	 *
	 * @param accountNo the account no
	 * @return the by account no
	 */
	public CustomerDTO getByAccountNo(String accountNo);
	
	/**
	 * Do adjustment.
	 *
	 * @param accountNo the account no
	 * @param amount the amount
	 * @return the boolean
	 */
	public BigDecimal doAdjustment(String accountNo, BigDecimal amount, boolean isPositive) throws CommonAPIException;
}
