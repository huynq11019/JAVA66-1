package com.TTS.Repo.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

import com.TTS.Entity.Account;
import com.TTS.Repo.CustomRepo.CustomAccountRepo;

public class CustomAccountRepoImpl implements CustomAccountRepo {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Account> search(MultiValueMap<String, String> queryParams, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(MultiValueMap<String, String> queryParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getListUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
