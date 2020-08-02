package com.bank.csm.controllers;

import java.math.BigDecimal;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.csm.constants.CommonMessageConstants;
import com.bank.csm.constants.URLConstants;
import com.bank.csm.dto.CustomerDTO;
import com.bank.csm.exceptions.CommonAPIException;
import com.bank.csm.services.CustomerService;
import com.bank.csm.utils.AppWebUtils;

@RestController
@RequestMapping(URLConstants.RESOURCE_CSM + URLConstants.VERSION)
public class CustomerController {
	
	public static final Logger LOGGER = Logger.getLogger(CustomerController.class.getName());
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CustomerDTO customer) throws CommonAPIException {
		LOGGER.info("Create - Calling");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_CREATED, HttpStatus.CREATED, customerService.create(customer));
	}

	@PatchMapping
	public ResponseEntity<?> patch(@RequestBody CustomerDTO customer) throws CommonAPIException {
		LOGGER.info("Patch - Calling");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_UPDATED, HttpStatus.OK, customerService.update(customer));
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestParam String customerCif) {
		LOGGER.info("Delete - Calling");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_DELETED, HttpStatus.OK, customerService.remove(customerCif));
	}
	
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam(required = false) String query, Pageable pageable) {
		LOGGER.info("Get All - Calling");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_FETCHED, HttpStatus.OK, customerService.getAll(query, pageable));
	}

	@GetMapping(URLConstants.RESOURCE_CSM_BY_CIF)
	public ResponseEntity<?> get(@RequestParam String cif) {
		LOGGER.info("Get - Calling");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_FETCHED, HttpStatus.OK, customerService.get(cif));
	}

	@GetMapping(URLConstants.RESOURCE_CSM_BY_ACCOUNTNO)
	public ResponseEntity<?> getByAcNo(@RequestParam String accountNo) {
		LOGGER.info("Get By Account No - Calling");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_FETCHED, HttpStatus.OK, customerService.getByAccountNo(accountNo));
	}

	@GetMapping(URLConstants.RESOURCE_CSM_BY_ADJ_ACC)
	public ResponseEntity<?> doAjustment(@RequestParam String accountNo, @RequestParam BigDecimal amount, @RequestParam boolean isPositive) throws CommonAPIException {
		LOGGER.info("Do adjustment of account - Calling");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_FETCHED, HttpStatus.OK, customerService.doAdjustment(accountNo, amount, isPositive));
	}
}
