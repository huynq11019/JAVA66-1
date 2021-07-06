package com.TTS.Service.ServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.TTS.Entity.Account;
import com.TTS.Entity.Authrority;
import com.TTS.Repo.AccountRepo;
import com.TTS.Service.AccountService;
import com.TTS.Util.Validator;
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepo accountRepo;

	@Override
	public Account createUser(Account acc) {
		// TODO Auto-generated method stub
		if (acc.getId() != null) { // khi lưu mặc định id phải là null
			acc.setStatus(0);
			acc.setAuthrority(
					Validator.isNotNull(acc.getAuthrority()) ? acc.getAuthrority() : new HashSet<Authrority>() {
						private static final long serialVersionUID = 1L;
						{
							Authrority authro = new Authrority();
							authro.setName("ROLE_USER");
							add(authro);
						}

					});
			accountRepo.save(acc);
		}
		return null;
	}

	@Override
	public Account getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Account> findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Account> findByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Account> findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Account> search(MultiValueMap<String, String> queryParams, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Account> getUserWithAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getListUser() {
		return accountRepo.findAll();
//		return null;
	}

	@Override
	public Account update(Account acc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account selfUpdate(Account acc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account updateByMobile(Account acc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countActiveAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll(List<Account> acc) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getAllAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePassword(Account acc, boolean selfUpdate) {
		// TODO Auto-generated method stub

	}

}
