package com.TTS.Controller;

import com.TTS.Entity.Product;
import com.TTS.Service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class ProductDetail {
    @Autowired
    private ProductService productService;

    @GetMapping("/produx/{id}")
    public String productDetail(@PathVariable("id") String idProduct, Model model) {
        try {
            Integer parse = Integer.parseInt(idProduct);
            Product pro = productService.getProductByid(parse);
            model.addAttribute("product", pro);
            log.info(pro.toString());
            return "views/productDetail";
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return "redirect:/error";
        }
    }
}
