package com.TTS.Service;

import com.TTS.Entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

public interface AccountService {
	Account createUser(Account acc);

	Account getCurrentUser();

	Optional<Account> findOne(Integer id);

	Account UpdateAccountWithPermission(Account account);

	Optional<Account> findByEmail(String email);

	Page<Account> search(MultiValueMap<String, String> queryParams, Pageable pageable);

	Optional<Account> getUserWithAuthorities(Integer accountID);

	List<Account> getListUser();
	
	List<Account> getPage(int page, int limit, String sortBy, String order);
	
	Account update(Account acc);

	Account selfUpdate(Account acc);

	Long countActiveAccount();

	void deleteAll(List<Account> acc);

	List<String> getAllAuthorities();

	void updatePassword(Account acc, boolean selfUpdate);

}
