package com.kma.corpomation.domain.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/index")
    public String admin(){
        return "index";
    }
}
