package com.bank.csm.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bank.csm.constants.Exceptions;
import com.bank.csm.dto.BankBranchDTO;
import com.bank.csm.entities.BankBranch;
import com.bank.csm.exceptions.CommonAPIException;
import com.bank.csm.repositories.BankBranchRepository;
import com.bank.csm.services.BankBranchService;
import com.bank.csm.utils.SpecificationUtils;

@Service
public class BankBranchServiceImpl implements BankBranchService {
	
	@Autowired
	private BankBranchRepository bankBranchRepository;

	@Override
	public BankBranchDTO create(BankBranchDTO bankBranchDTO) throws CommonAPIException {
		createValidation(bankBranchDTO);
		BankBranch bankBranch = new BankBranch();
		BeanUtils.copyProperties(bankBranchDTO, bankBranch);
		bankBranch = bankBranchRepository.saveAndFlush(bankBranch);
		BeanUtils.copyProperties(bankBranch, bankBranchDTO);
		return bankBranchDTO;
	}

	@Override
	public BankBranchDTO update(BankBranchDTO bankBranchDTO) throws CommonAPIException {
		updateValidation(bankBranchDTO);
		Optional<BankBranch> bankBranchOptional = bankBranchRepository.findById(bankBranchDTO.getId());
		bankBranchOptional.ifPresent(bankBranch -> {
			BeanUtils.copyProperties(bankBranchDTO, bankBranch);
			bankBranch = bankBranchRepository.saveAndFlush(bankBranch);
			BeanUtils.copyProperties(bankBranch, bankBranchDTO);
		});
		return bankBranchDTO;
	}

	@Override
	public BankBranchDTO remove(String branchCode) {
		BankBranchDTO retVal = new BankBranchDTO();
		Optional<String> branchCodeOptional = Optional.of(branchCode);
		branchCodeOptional.ifPresent(branchCodeString -> {
			Optional<BankBranch> bankBranchOptional = bankBranchRepository.getByCode(branchCode);
			bankBranchOptional.ifPresent(bankBranch -> {
				bankBranchRepository.deleteById(bankBranch.getId());
				BeanUtils.copyProperties(bankBranch, retVal);
			});
		});
		return retVal;
	}

	@Override
	public BankBranchDTO get(String branchCode) {
		/**
		 * Commented for testing
		 */
		/*
		long startTime = System.currentTimeMillis();
		System.out.println("Start Time: " + startTime);
		EndpointMetadata metadata = AppWebUtils.obtainEndPointMetadata();
		long endTime = System.currentTimeMillis();
		System.out.println("End   Time: " + endTime);
		System.out.println("Difference: " + (endTime-startTime));
		*/
		BankBranchDTO retVal = new BankBranchDTO();
		Optional<String> branchCodeOptional = Optional.of(branchCode);
		branchCodeOptional.ifPresent(branchCodeString -> {
			Optional<BankBranch> bankBranchOptional = bankBranchRepository.getByCode(branchCode);
			bankBranchOptional.ifPresent(bankBranch -> {
				BeanUtils.copyProperties(bankBranch, retVal);
			});
		});
		return retVal;
	}

	@Override
	public Page<BankBranchDTO> getAll(String query, Pageable pageable) {
		Specification<BankBranch> specification = SpecificationUtils.buildSpecification(query, new ArrayList<>());
		Page<BankBranch> bankBranchPage = bankBranchRepository.findAll(specification, pageable);
		List<BankBranchDTO> bankBranchDTOList = bankBranchPage.getContent().stream().map(bankBranch -> {
			BankBranchDTO temp = new BankBranchDTO();
			BeanUtils.copyProperties(bankBranch, temp);
			return temp;
			}).collect(Collectors.toList());
		return new PageImpl<BankBranchDTO>(bankBranchDTOList, pageable, bankBranchPage.getTotalElements());
	}

	@Override
	public BigDecimal doAdjustment(String branchCode, BigDecimal amount, boolean isPositive) throws CommonAPIException {
		List<BigDecimal> retVal = new ArrayList<>();
		adjustmentValidation(branchCode, amount);
		Optional<BankBranch> bankBranchOptional = bankBranchRepository.getByCode(branchCode);
		bankBranchOptional.ifPresent(bankBranch -> {
			BigDecimal oldAmount = bankBranch.getBalance();
			oldAmount = null == oldAmount?new BigDecimal(0):oldAmount;
			if(isPositive) {
				oldAmount = oldAmount.add(amount);
			}
			else {
				oldAmount = oldAmount.subtract(amount);
			}
			bankBranch.setBalance(oldAmount);
			bankBranchRepository.save(bankBranch);
			retVal.add(oldAmount);
		});
		return !retVal.isEmpty()?retVal.get(0):null;
	}
	
	private void adjustmentValidation(String branchCode, BigDecimal amount) throws CommonAPIException {
		if(null == branchCode || null == amount) {
			throw new CommonAPIException(Exceptions.E0025);
		}
		if(bankBranchRepository.existsByName(branchCode, 0l)) {
			throw new CommonAPIException(Exceptions.E0026);
		}
	}

	private void createValidation(BankBranchDTO bankBranchDTO) throws CommonAPIException {
		if(null == bankBranchDTO) {
			throw new CommonAPIException(Exceptions.E0024);
		}
		if(null != bankBranchDTO.getId()) {
			throw new CommonAPIException(Exceptions.E0025);
		}
		if(bankBranchRepository.existsByName(bankBranchDTO.getName(), bankBranchDTO.getId()) || bankBranchRepository.existsByCode(bankBranchDTO.getCode(), bankBranchDTO.getId())) {
			throw new CommonAPIException(Exceptions.E0026);
		}
		if(bankBranchRepository.existsByBankName(bankBranchDTO.getName(), bankBranchDTO.getId()) || bankBranchRepository.existsByBankCode(bankBranchDTO.getCode(), bankBranchDTO.getId())) {
			throw new CommonAPIException(Exceptions.E0027);
		}
	}
	
	private void updateValidation(BankBranchDTO bankBranchDTO) throws CommonAPIException {
		if(null == bankBranchDTO) {
			throw new CommonAPIException(Exceptions.E0024);
		}
		if(null == bankBranchDTO.getId()) {
			throw new CommonAPIException(Exceptions.E0025);
		}
		if(bankBranchRepository.existsByName(bankBranchDTO.getName(), bankBranchDTO.getId()) || bankBranchRepository.existsByCode(bankBranchDTO.getCode(), bankBranchDTO.getId())) {
			throw new CommonAPIException(Exceptions.E0026);
		}
		if(bankBranchRepository.existsByBankName(bankBranchDTO.getName(), bankBranchDTO.getId()) || bankBranchRepository.existsByBankCode(bankBranchDTO.getCode(), bankBranchDTO.getId())) {
			throw new CommonAPIException(Exceptions.E0027);
		}
	}

}
