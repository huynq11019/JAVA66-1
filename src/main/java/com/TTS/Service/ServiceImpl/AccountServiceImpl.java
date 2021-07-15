package com.TTS.Service.ServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.TTS.Entity.Account;
import com.TTS.Entity.Authrority;
import com.TTS.Repo.AccountRepo;
import com.TTS.Service.AccountService;
import com.TTS.Util.ParamUtil;
import com.TTS.Util.Validator;
import com.TTS.sercurity.CustomUserDetail;
import com.TTS.sercurity.RequestFilter;
import com.TTS.sercurity.JWT.TokenPovider;

@Service
@Transactional

public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ParamUtil param;
	@Autowired
	private TokenPovider jwtPovider;
//	private final static Logger _log = LoggerFactory.getLogger(AccountService.class);
	private final Logger _log = Logger.getLogger(RequestFilter.class);

	@Override
	public Account createUser(Account acc) {
		// TODO Auto-generated method stub
//		Account account = accountRepo.findById(acc.getId())
//				.orElseThrow(() -> new IndexOutOfBoundsException("ID account update không tồn tại"));
		if (acc.getId() == null) { // khi lưu mặc định id phải là null
			acc.setStatus(0); // Mặc định các trạng thái sẽ là null
			acc.setPasswordHash(passwordEncoder.encode(acc.getPasswordHash()));
			acc.setAuthrority(
					Validator.isNotNull(acc.getAuthrority()) ? acc.getAuthrority() : new HashSet<Authrority>() {
						private static final long serialVersionUID = 1L;
						{
							Authrority authro = new Authrority();
							authro.setName("USER");
							add(authro);
						}

					});
			_log.info("đã tạo một acocunt mới");
			System.out.println(acc);
			return accountRepo.save(acc);
		}
		_log.warn("thông tin tạo user không thành công vì id khác null");
		return null;
	}

	@Override
	public Account getCurrentUser() {
//lấy thông tin user qua accesstoken 

		Integer id = param.getAccountId();
		//
		Account curentAccount = accountRepo.findById(id).get();
//		_log.info(curentAccount.toString());
		return curentAccount;
	}

	@Override
	public Optional<Account> findOne(Integer id) {
		if (!accountRepo.existsById(id)) {
			_log.warn("lỗi tìm kiếm: id không tồn tại");
			return null;
		}
		_log.info("đã tìm được user có id là" + id);
		return accountRepo.findById(id);
	}

	@Override
	public Optional<Account> findByEmail(String email) {
		Optional<Account> acc = accountRepo.findByEmail(email);
		if (acc == null) {
			_log.warn("lỗi tìm kiếm: email không tồn tại");
			return null;
		}
//		_log.info("đã tìm được user có email là" + email);
		return acc;
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
	}

	@Override
	public Account update(Account acc) {
		if (acc.getId() == null) {
			return null;
		}
		Account account = accountRepo.findById(acc.getId())
				.orElseThrow(() -> new IndexOutOfBoundsException("ID account update không tồn tại"));
		acc.setPasswordHash(account.getPasswordHash());
		return accountRepo.save(acc);

	}

//	private Account setPropertyOfAccount(Account acc, AccountDTO dto) {
//		
//		return null;
//	}
	@Override
	public Account selfUpdate(Account acc) {
		// kiểm tra có phải đang người dùng đang tự uodate không
		Integer idAccountUpdate = acc.getId();
		Integer idAccesstoken = param.getAccountId();
		if(idAccesstoken == idAccountUpdate) {
			this.update(acc);
		}

		return null;
	}

	@Override
	public Long countActiveAccount() {
		//
		return null;
	}

	@Override
	public void deleteAll(List<Account> acc) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getAllAuthorities() {
		// lấy hết authroity của User
		return null;
	}

	@Override
	public void updatePassword(Account acc, boolean selfUpdate) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Account> getPage(int page, int limit, String sortBy, boolean order) {
		Direction directtion = Direction.DESC;
		if (order) {
			directtion = Direction.ASC;
		}
		Sort sort = Sort.by(directtion, sortBy);
		Pageable paging = PageRequest.of(page, limit, sort);
		return accountRepo.findAll(paging).getContent();

//		return null;
	}

}
