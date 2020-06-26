package com.bank.csm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.csm.entities.Account;

/**
 * This interface is Repository interface
 * for the JPA repository.
 *
 * @author kumar-sand
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {
	
	@Query("from Account c where c.accountNo = ?1")
	Optional<Account> getAccountByAccountNo(String accountNo);
}
