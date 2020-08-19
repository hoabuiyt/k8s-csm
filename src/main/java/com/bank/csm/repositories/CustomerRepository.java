package com.bank.csm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.csm.entities.Customer;

/**
 * This interface is Repository interface
 * for the JPA repository.
 *
 * @author kumar-sand
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

	@Query("select case when count(b) > 0 then true else false end from Customer b where b.cif = ?1 and id != ?2")
	boolean existsByCif(String cif, Long id);

	@Query("select case when count(b) > 0 then true else false end from Account b where b.accountNo = ?1 and branchCode = ?2 and id != ?3")
	boolean existsByAccountNo(String accountNo, String branchCode, Long id);

	@Query("select case when count(b) > 0 then true else false end from Account b where b.accountNo = ?1 and id != ?2")
	boolean existsByAccountNo(String accountNo, Long id);
	
	Optional<Customer> getByCif(String cif);
	
	@Query("select c from Customer c inner join c.accounts a where a.accountNo = ?1")
	Optional<Customer> getByAccountNo(String accountNo);
}
