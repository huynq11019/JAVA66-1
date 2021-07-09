package com.TTS.Rest;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TTS.DTO.AccountDTO;
import com.TTS.Entity.Account;
import com.TTS.Service.AccountService;
import com.TTS.maper.AccountMapper;

@RestController
@RequestMapping("/api/")
public class RestAccount {

	private static final Logger _log = Logger.getLogger(RestAccount.class);
//	private static final Log _log = LogFactory.getLog(RestAccount.class);
	@Autowired
	private AccountService accService;
	@Autowired
	private AccountMapper accountMapper;

	@GetMapping("/admin/account/getlist")
	public ResponseEntity<List<AccountDTO>> getAll() {
		System.err.println("đã kết nối");
//		List<AccountDTO> listOut = accService.getListUser().stream().map(accountMapper::toDto)
//				.collect(Collectors.toList());
//		return ResponseEntity.ok(accService.getListUser());
		try {
			List<AccountDTO> listOut = accountMapper.toListDto(accService.getListUser());
			_log.info("đã load danh sách user");
			return ResponseEntity.status(HttpStatus.OK).body(listOut);

		} catch (Exception e) {
			System.out.println("loi");
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/register")
	public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO acc) {
//		System.out.println("account is validate");
		_log.debug("account validated:" + acc.toString());

		Account account = accountMapper.toEntity(acc);
		Account accSaved = accService.createUser(account);
		AccountDTO accountDTO = accountMapper.toDto(accSaved);
		accountDTO.setPassword("password này đã được mã hóa");
		return ResponseEntity.ok(accountDTO);
	}

	@PutMapping("/admin/account/update")
	public ResponseEntity<Boolean> updateAccount(@Valid @RequestBody AccountDTO accDTO) {
		try {
			
			if (accDTO.getId() == null) {
				_log.warn("Account di không được để null");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
				
			}
			Account account = accountMapper.toEntity(accDTO);
			_log.info(account);
			accService.update(account);
			_log.info("Thực hiện udpate thành công");
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			_log.error("Thực hiện update không thành công", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		}
	}

}
