package rs.diplomski.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rs.diplomski.model.Account;
import rs.diplomski.sys.repository.CustomRepository;

@Repository
public interface AccountRepository extends CustomRepository<Account, Long> {
	
	@Query(value = "SELECT * FROM account acc WHERE acc.acc_username = :accUsername AND acc.acc_password = :accPassword", nativeQuery = true)
	Account getAccountByUsernameAndPassword(@Param("accUsername") String accUsername, @Param("accPassword") String accPassword);
	
	@Query(value = "SELECT * FROM account acc WHERE acc.acc_email = :accEmail", nativeQuery = true)
	Account getAccountByEmail(@Param("accEmail") String accEmail);
	
	@Query(value = "SELECT * FROM account acc WHERE acc.acc_username = :accUsername", nativeQuery = true)
	Account findAccountByUsername(@Param("accUsername") String accUsername);
	
	@Query(value = "SELECT * FROM account acc WHERE acc.acc_role = :rolId", nativeQuery = true)
	List<Account> findAccountsByRole(@Param("rolId") Long rolId);
}
