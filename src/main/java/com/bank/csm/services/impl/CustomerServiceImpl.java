package com.bank.csm.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bank.csm.constants.Exceptions;
import com.bank.csm.dto.AccountDTO;
import com.bank.csm.dto.CustomerDTO;
import com.bank.csm.entities.Account;
import com.bank.csm.entities.Customer;
import com.bank.csm.exceptions.CommonAPIException;
import com.bank.csm.repositories.AccountRepository;
import com.bank.csm.repositories.BankBranchRepository;
import com.bank.csm.repositories.CustomerRepository;
import com.bank.csm.services.CustomerService;
import com.bank.csm.utils.SpecificationUtils;

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
	
	@Autowired
	private BankBranchRepository bankBranchRepository;

	@Override
	public CustomerDTO create(CustomerDTO customerDTO) throws CommonAPIException {
		createValidation(customerDTO);
		Customer customer = fromDTO(customerDTO);
		Optional.ofNullable(customer.getAccounts())
		.ifPresent(accounts -> {
			accounts.stream().forEach(account -> {
				account.setCustomer(customer);
			});
		});
		Customer customerAfterSave = customerRepository.saveAndFlush(customer);
		return fromEntity(customerAfterSave);
	}

	@Override
	public CustomerDTO update(CustomerDTO customerDTO) throws CommonAPIException {
		updateValidation(customerDTO);
		Customer customer = fromDTO(customerDTO);
		Optional.ofNullable(customer.getAccounts())
		.ifPresent(accounts -> {
			accounts.stream().forEach(account -> {
				account.setCustomer(customer);
			});
		});
		Customer customerAfterSave = customerRepository.saveAndFlush(customer);
		return fromEntity(customerAfterSave);
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
				CustomerDTO temp = fromEntity(customer);
				BeanUtils.copyProperties(temp, retVal);
			});
		});
		return retVal;
	}

	@Override
	public Page<CustomerDTO> getAll(String query, Pageable pageable) {
		Specification<Customer> specification = SpecificationUtils.buildSpecification(query, new ArrayList<>());
		Page<Customer> customerPage = customerRepository.findAll(specification, pageable);
		List<CustomerDTO> customerDTOList = customerPage.getContent().stream().map(customer -> {
			return fromEntity(customer);
			}).collect(Collectors.toList());
		return new PageImpl<CustomerDTO>(customerDTOList, pageable, customerPage.getTotalElements());
	}

	@Override
	public CustomerDTO getByAccountNo(String accountNo) {
		CustomerDTO retVal = new CustomerDTO();
		Optional<String> customerCifOptional = Optional.of(accountNo);
		customerCifOptional.ifPresent(customerCifString -> {
			Optional<Customer> customerOptional = customerRepository.getByAccountNo(customerCifString);
			customerOptional.ifPresent(customer -> {
				CustomerDTO temp = fromEntity(customer);
				BeanUtils.copyProperties(temp, retVal);
			});
		});
		return retVal;
	}

	@Override
	public BigDecimal doAdjustment(String accountNo, BigDecimal amount, boolean isPositive) throws CommonAPIException {
		List<BigDecimal> retVal = new ArrayList<>();
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
			retVal.add(dbAmount);
		});
		return !retVal.isEmpty()?retVal.get(0):null;
	}

	private void adjustmentValidation(String accountNo, BigDecimal amount) throws CommonAPIException {
		if(null == accountNo || null == amount || !customerRepository.existsByAccountNo(accountNo, 0l)) {
			throw new CommonAPIException(Exceptions.E0024);
		}
	}
	
	private CustomerDTO fromEntity(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		BeanUtils.copyProperties(customer, customerDTO, "accounts");
		Optional<Set<Account>> accountList = Optional.ofNullable(customer.getAccounts());
		accountList.ifPresent(accounts -> {
			customerDTO.setAccounts(
			accounts.stream().map(acnt -> {
				AccountDTO accountDTO = new AccountDTO();
				BeanUtils.copyProperties(acnt, accountDTO, "customer");
				return accountDTO;
			}).collect(Collectors.toSet())
			);
		});
		return customerDTO;
	}
	
	private Customer fromDTO(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDTO, customer, "accounts");
		Optional<Set<AccountDTO>> accountList = Optional.ofNullable(customerDTO.getAccounts());
		accountList.ifPresent(accounts -> {
			customer.setAccounts(
			accounts.stream().map(acnt -> {
				Account account = new Account();
				BeanUtils.copyProperties(acnt, account, "customer");
				return account;
			}).collect(Collectors.toSet())
			);
		});
		return customer;
	}
	
	private void createValidation(CustomerDTO customerDTO) throws CommonAPIException {
		if(null == customerDTO) {
			throw new CommonAPIException(Exceptions.E0024);
		}
		if(null != customerDTO.getId()) {
			throw new CommonAPIException(Exceptions.E0025);
		}
		if(customerRepository.existsByCif(customerDTO.getCif(), 0l)) {
			throw new CommonAPIException(Exceptions.E0028);
		}
		if(null != customerDTO.getAccounts()) {
			for(AccountDTO accountDTO:customerDTO.getAccounts()) {
				if(null != accountDTO) {
					if(!bankBranchRepository.existsByCode(accountDTO.getBranchCode(), 0l)) {
						throw new CommonAPIException(Exceptions.E0030);
					}
					else if(customerRepository.existsByAccountNo(accountDTO.getAccountNo(), accountDTO.getBranchCode(), 0l)) {
						throw new CommonAPIException(Exceptions.E0029);
					}
				}
			}
		}
	}
	
	private void updateValidation(CustomerDTO customerDTO) throws CommonAPIException {
		if(null == customerDTO) {
			throw new CommonAPIException(Exceptions.E0024);
		}
		if(null == customerDTO.getId()) {
			throw new CommonAPIException(Exceptions.E0025);
		}
		if(customerRepository.existsByCif(customerDTO.getCif(), customerDTO.getId())) {
			throw new CommonAPIException(Exceptions.E0028);
		}
		if(null != customerDTO.getAccounts()) {
			for(AccountDTO accountDTO:customerDTO.getAccounts()) {
				if(null != accountDTO) {
					if(!bankBranchRepository.existsByCode(accountDTO.getBranchCode(), 0l)) {
						throw new CommonAPIException(Exceptions.E0030);
					}
					else if(customerRepository.existsByAccountNo(accountDTO.getAccountNo(), accountDTO.getBranchCode(), accountDTO.getId())) {
						throw new CommonAPIException(Exceptions.E0029);
					}
				}
			}
		}
	}
	
}
