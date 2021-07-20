package com.TTS.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

import com.TTS.Entity.Account;

public interface AccountService {
	Account createUser(Account acc);

	Account getCurrentUser();

	Optional<Account> findOne(Integer id);


	Optional<Account> findByEmail(String email);

	Page<Account> search(MultiValueMap<String, String> queryParams, Pageable pageable);

	Optional<Account> getUserWithAuthorities();

	List<Account> getListUser();
	
	List<Account> getPage(int page, int limit, String sortBy, boolean order);
	
	Account update(Account acc);

	Account selfUpdate(Account acc);

	Long countActiveAccount();

	void deleteAll(List<Account> acc);

	List<String> getAllAuthorities();

	void updatePassword(Account acc, boolean selfUpdate);

}
