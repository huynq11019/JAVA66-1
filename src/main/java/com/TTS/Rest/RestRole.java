package com.TTS.Rest;

import com.TTS.Entity.Authrority;
import com.TTS.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
public class RestRole {
    @Autowired
    public RoleService roleService;
    @GetMapping("/api/authrow")
    public ResponseEntity<List<Authrority>> getAllAuthro (){
        return ResponseEntity.ok().body(roleService.loadAll());
    }
}
