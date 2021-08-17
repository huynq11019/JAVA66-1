package com.TTS.Controller;

import com.TTS.Entity.Order;
import com.TTS.Service.OrderService;
import com.TTS.sercurity.CustomUserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class MyAccountController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/au/myprofile")
    public String index(Model model) {
        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> myOrders = this.orderService.getOrderByUser(customUserDetail.getIdAccount());
        System.out.println(customUserDetail.getUsername());
//        System.out.println(myOrders);
        model.addAttribute("myOder", myOrders);
        return "views/myProfile";
    }
}
