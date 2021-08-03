package com.TTS.Repo;

import com.TTS.Entity.Authrority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthrorizeRepo extends JpaRepository<Authrority, String> {
}
