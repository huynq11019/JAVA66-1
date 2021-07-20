package com.TTS.Controller;

import com.TTS.Util.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ProductListController {
    @Autowired
    private SessionService session;
    @GetMapping("/product")
    public String index(){

        return "views/product";
    }

    // lấy sản phẩm theo danh mục

    //lấy sản phẩm theo id


}
