package com.bank.csm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.csm.entities.BankBranch;

/**
 * This interface is Repository interface
 * for the JPA repository.
 *
 * @author kumar-sand
 */
@Repository
public interface BankBranchRepository extends JpaRepository<BankBranch, Long>, JpaSpecificationExecutor<BankBranch> {

	@Query("select case when count(b) > 0 then true else false end from BankBranch b where b.name = ?1 and id != ?2")
	boolean existsByName(String name, Long id);

	@Query("select case when count(b) > 0 then true else false end from BankBranch b where b.bankName = ?1 and id != ?2")
	boolean existsByBankName(String bankName, Long id);
	
	@Query("select case when count(b) > 0 then true else false end from BankBranch b where b.code = ?1 and id != ?2")
	boolean existsByCode(String code, Long id);

	@Query("select case when count(b) > 0 then true else false end from BankBranch b where b.bankCode = ?1 and id != ?2")
	boolean existsByBankCode(String bankCode, Long id);
	
	Optional<BankBranch> getByCode(String code);
}
