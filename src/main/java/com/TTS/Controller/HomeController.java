package com.TTS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"/home","/"})
//    public String index(){
//        return "layout/layout";
//    }
    public String index(){
        return "views/index";
    }
}
