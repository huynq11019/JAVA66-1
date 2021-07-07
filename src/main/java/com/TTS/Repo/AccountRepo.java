package com.TTS.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TTS.Entity.Account;
import com.TTS.Repo.CustomRepo.CustomAccountRepo;
@Repository
public interface AccountRepo extends JpaRepository<Account, Integer>,CustomAccountRepo{
	public Optional<Account> findByEmail(String email);
}
