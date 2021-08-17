package com.TTS.Rest;

import com.TTS.DTO.AccountDTO;
import com.TTS.DTO.OutPut;
import com.TTS.Entity.Account;
import com.TTS.Service.AccountService;
import com.TTS.Util.CookieService;
import com.TTS.maper.AccountMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//import org.apache.log4j.Logger;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@Slf4j
public class RestAccount {

    //	private static final Logger _log = Logger.getLogger(RestAccount.class);
//	private static final Log _log = LogFactory.getLog(RestAccount.class);
    @Autowired
    private AccountService accService;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private CookieService cookieService;

    @GetMapping("/admin/account/getlist")
    public ResponseEntity<List<AccountDTO>> getAll() {

        System.out.println(cookieService.getvalue("user"));
        try {
            List<AccountDTO> listOut = accountMapper.toListDto(accService.getListUser());
            log.info("đã load danh sách user");
            return ResponseEntity.status(HttpStatus.OK).body(listOut);

        } catch (Exception e) {
            System.out.println("loi");
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping("/admin/account/getPage")
    public ResponseEntity<OutPut<AccountDTO>> getPageAccount(@RequestParam(name = "page", defaultValue = "0") int page,
                                                             @RequestParam(name = "limit", defaultValue = "5") int limit,
                                                             @RequestParam(name = "sortby", defaultValue = "id") String sortBy,
                                                             @RequestParam(name = "order", defaultValue = "DESC") String order) {
        OutPut<AccountDTO> out = new OutPut<>();
        out.setPage(page);
        out.setLimit(limit);
        out.setOrder(order);
        out.setOrderBy(sortBy);
        out.setTotalElement(accService.countActiveAccount());
        out.setContent(accountMapper.toListDto(accService.getPage(page, limit, sortBy, order)));

        return ResponseEntity.ok(out);
    }

    @PostMapping("/register")
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO acc) {
//		System.out.println("account is validate");
        log.debug("account validated:" + acc.toString());

        Account account = accountMapper.toEntity(acc);
        Account accSaved = accService.createUser(account);
        AccountDTO accountDTO = accountMapper.toDto(accSaved);
        accountDTO.setPassword("password này đã được mã hóa");
        return ResponseEntity.ok(accountDTO);
    }

    @PutMapping("/admin/account/update") // phải là admin mới cho update những user khác nhưng khoong dược update mật
    // khẩu
    public ResponseEntity<Boolean> updateAccount(@Valid @RequestBody AccountDTO accDTO) {
        try {

            if (accDTO.getId() == null) {
                log.warn("Account di không được để null");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);

            }
            Account account = accountMapper.toEntity(accDTO);
            log.info(account.toString());
            accService.update(account);
            log.info("Thực hiện udpate thành công");
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            log.error("Thực hiện update không thành công", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }

    @GetMapping("/account/getinfor")
    @ApiOperation(value = "getInfor", notes = "Lấy thông tin Account từ Accesstoken")
    public ResponseEntity<AccountDTO> getInforByaccessToken() throws Exception {
        try {
            Account account = accService.getCurrentUser();
            AccountDTO dto = accountMapper.toDto(account);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("không thể trả vì infor");
        }
    }

    @PutMapping("/account/selfupdate")
    public AccountDTO selfAccount(@Valid @RequestBody AccountDTO AccDTO) {

        return null;
    }

    @GetMapping("/account/{accountID}")
    public AccountDTO getAccountByID(@PathVariable("accountID") Integer idAccount) {
        AccountDTO acc = accountMapper.toDto(accService.getUserWithAuthorities(idAccount).get());
        return acc;
    }

    @PutMapping("/admin/account/setAuthrority")
    public AccountDTO updatePermission(@RequestBody AccountDTO accountDTO) {
        System.out.println(accountDTO);
        Account account = accountMapper.toEntity(accountDTO);
//        accService.UpdateAccountWithPermission()
//        log.info(accountDTO.toString());
        Account accountSaved = accService.UpdateAccountWithPermission(account);

        System.out.println(accountSaved.getAuthrority());
        System.out.println(accountSaved);
        return accountMapper.toDto(accountSaved);
    }
}
