package com.TTS.Controller;

//import org.apache.log4j.Logger;

import com.TTS.DTO.RequestLogin;
import com.TTS.Util.CookieService;
import com.TTS.Util.SessionService;
import com.TTS.sercurity.CustomUserDetail;
import com.TTS.sercurity.JWT.TokenPovider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Slf4j
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenPovider tokenPovider;
    @Autowired
    private CookieService cookieService;
    @Autowired
    private SessionService sessionService;

    @GetMapping("/ulogin")
    public String loginForm(Model model) {
        model.addAttribute("loginStatus", "hello world");
        SecurityContextHolder.clearContext();
        cookieService.remoce("accesstoken");
        sessionService.remove("user");
        return "auth/LoginForm";
    }

    //    @PostMapping("/logind")
    @RequestMapping(value = "/logind", method = RequestMethod.POST)
    public String LoginAction(@Valid RequestLogin auth, Model model, Errors err, HttpSession session) {

        log.info(auth.toString());
        if (err.hasErrors()) {
            //hiển thị thông báo lỗi
            log.error("đăng nhập khoong thành coong");
            return "redirect:/ulogin";
        }
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getEmailLogin(),
                    auth.getPassword()));
            CustomUserDetail custom = (CustomUserDetail) authentication.getPrincipal();
            log.info("test Pricial" + custom);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenPovider.createToken((CustomUserDetail) authentication.getPrincipal());
            sessionService.set("user", "authentication.getPrincipal()");
            session.setAttribute("user",authentication.getPrincipal());
            cookieService.add("accesstoken", jwt, 1000, true);
//  System.err.println(session.getAttribute("user"));
            model.addAttribute("loginStatus", "Login fail không thành c");
            return "redirect:/home";
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return "redirect:/ulogin";
        }

    }
//    @GetMapping("/clogout")
//    public String loutOut(){
//        SecurityContextHolder.clearContext();
//        cookieService.remoce("accesstoken");
//        sessionService.remove("user");
//
//        return "redirect:/ulogin";
//    }

}
