package com.TTS.Rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TTS.Entity.Account;
import com.TTS.Service.AccountService;

@RestController
@RequestMapping("/api/acount")
public class RestAccount {
//public ResponseEntity<Account> 
	private AccountService  accService;
	public ResponseEntity<List<Account>> getAll(){
		
		return ResponseEntity.ok(accService.getListUser());
	}
}
