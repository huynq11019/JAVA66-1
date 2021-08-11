package com.TTS.Controller;

import com.TTS.Entity.Order;
import com.TTS.Service.OrderService;
import com.TTS.Service.ShopingCartItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.sql.SQLException;

@Slf4j
@Controller
public class CheckoutController {
    @Autowired
    private ShopingCartItem cart;
    @Autowired
    private OrderService orderService;

    @GetMapping("/au/checkout")
    public String indexCheckout(Model model, @ModelAttribute("order") Order order) {
        if (cart.getAmout() == 0) {
            return "redirect:/cart";
        }

        return "views/checkout";
    }

    @PostMapping("/au/confirmOrder")
    public String confirmOrder(Model model, @Valid @ModelAttribute("order") Order order, Errors err, BindingResult bindingResult) {
        log.info(order.toString());
        if (bindingResult.hasErrors()) {
            log.error("valid không thành công");
            System.err.println(bindingResult);
            return "views/checkout";
        }
        Order myOrder = new Order();
        try {
            myOrder = orderService.create(order);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("orderSaved"+ myOrder);
//        return "views/checkout";
        return "redirect:/home";
    }


    @ModelAttribute("cart")
    public ShopingCartItem getPdfPropertyName() {
        return cart;
    }
}
