package com.TTS.Service;

import com.TTS.Entity.Authrority;
import com.TTS.Repo.AuthrorizeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private AuthrorizeRepo authrorizeRepo;

    public List<Authrority> loadAll(){

        return authrorizeRepo.findAll();
    }
}
