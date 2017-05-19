package com.dufeng.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class IndexController {
    
    @RequestMapping("/")
    @ResponseBody
    String index() {
        return "Hello, World";
    }
    
    public static void main(String[] args) {
        SpringApplication.run(IndexController.class, args);
    }
}
