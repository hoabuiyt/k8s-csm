package com.bank.csm.services.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bank.csm.constants.Exceptions;
import com.bank.csm.dto.CustomerDTO;
import com.bank.csm.entities.Account;
import com.bank.csm.entities.Customer;
import com.bank.csm.exceptions.CommonAPIException;
import com.bank.csm.repositories.AccountRepository;
import com.bank.csm.repositories.CustomerRepository;
import com.bank.csm.services.CustomerService;

/**
 * Customer Service implementation.
 *
 * @author kumar-sand
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public CustomerDTO create(CustomerDTO customerDTO) throws CommonAPIException {
		
		
		return null;
	}

	@Override
	public CustomerDTO update(CustomerDTO customerDTO) throws CommonAPIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDTO remove(String customerCif) {
		CustomerDTO retVal = new CustomerDTO();
		Optional<String> customerCifOptional = Optional.of(customerCif);
		customerCifOptional.ifPresent(customerCifString -> {
			Optional<Customer> customerOptional = customerRepository.getByCif(customerCif);
			customerOptional.ifPresent(customer -> {
				customerRepository.deleteById(customer.getId());
				BeanUtils.copyProperties(customer, retVal);
			});
		});
		return retVal;
	}

	@Override
	public CustomerDTO get(String customerCif) {
		CustomerDTO retVal = new CustomerDTO();
		Optional<String> customerCifOptional = Optional.of(customerCif);
		customerCifOptional.ifPresent(customerCifString -> {
			Optional<Customer> customerOptional = customerRepository.getByCif(customerCif);
			customerOptional.ifPresent(customer -> {
				BeanUtils.copyProperties(customer, retVal);
			});
		});
		return retVal;
	}

	@Override
	public Page<CustomerDTO> getAll(String query, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDTO getByAccountNo(String accountNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean doAdjustment(String accountNo, BigDecimal amount, boolean isPositive) throws CommonAPIException {
		adjustmentValidation(accountNo, amount);
		Optional<Account> accountOptional = accountRepository.getAccountByAccountNo(accountNo);
		accountOptional.ifPresent(account -> {
			BigDecimal dbAmount = account.getBalance();
			dbAmount = null == dbAmount?new BigDecimal(0):dbAmount;
			if(isPositive) {
				dbAmount = dbAmount.add(amount);
			}
			else {
				dbAmount = dbAmount.subtract(amount);
			}
			account.setBalance(dbAmount);
			accountRepository.save(account);
		});
		return true;
	}

	private void adjustmentValidation(String accountNo, BigDecimal amount) throws CommonAPIException {
		if(null == accountNo || null == amount || !customerRepository.existsByAccountNo(accountNo, 0l)) {
			throw new CommonAPIException(Exceptions.E0024);
		}
	}
	
}
