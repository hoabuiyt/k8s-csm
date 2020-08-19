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

import com.bank.csm.annotations.EndpointMetadata;
import com.bank.csm.constants.CommonMessageConstants;
import com.bank.csm.constants.URLConstants;
import com.bank.csm.dto.BankBranchDTO;
import com.bank.csm.exceptions.CommonAPIException;
import com.bank.csm.services.BankBranchService;
import com.bank.csm.utils.AppWebUtils;

@RestController
@RequestMapping(URLConstants.RESOURCE_BANK_BRANCH + URLConstants.VERSION)
public class BankBranchController {
	
	public static final Logger LOGGER = Logger.getLogger(BankBranchController.class.getName());
	
	@Autowired
	private BankBranchService bankBranchService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody BankBranchDTO customer) throws CommonAPIException {
		LOGGER.info("Create - Calling");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_CREATED, HttpStatus.CREATED, bankBranchService.create(customer));
	}

	@PatchMapping
	public ResponseEntity<?> patch(@RequestBody BankBranchDTO customer) throws CommonAPIException {
		LOGGER.info("Patch - Calling");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_UPDATED, HttpStatus.OK, bankBranchService.update(customer));
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestParam String branchCode) throws CommonAPIException {
		LOGGER.info("Delete - Calling");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_DELETED, HttpStatus.OK, bankBranchService.remove(branchCode));
	}
	
	@GetMapping
	public ResponseEntity<?> getAll(Pageable pageable, @RequestParam(required = false) String query) {
		LOGGER.info("Get All - Calling");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_FETCHED, HttpStatus.OK, bankBranchService.getAll(query, pageable));
	}

	@GetMapping(URLConstants.RESOURCE_BANK_BY_BRANCHCODE)
	@EndpointMetadata(moduleName = "BankBranch", action = "GetRecord")
	public ResponseEntity<?> get(@RequestParam String branchCode) {
		LOGGER.info("Get - Calling");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_FETCHED, HttpStatus.OK, bankBranchService.get(branchCode));
	}

	@GetMapping(URLConstants.RESOURCE_BANK_DO_ADJUSTMENT)
	public ResponseEntity<?> getByAcNo(@RequestParam String branchCode, @RequestParam BigDecimal amount, @RequestParam boolean isPositive) throws CommonAPIException {
		LOGGER.info("Get By Account No - Calling");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_FETCHED, HttpStatus.OK, bankBranchService.doAdjustment(branchCode, amount, isPositive));
	}

}
