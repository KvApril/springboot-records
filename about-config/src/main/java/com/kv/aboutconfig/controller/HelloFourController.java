package com.kv.aboutconfig.controller;


import com.kv.aboutconfig.config.ConfigRandomBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloFourController {
    @Autowired
    ConfigRandomBean configRandomBean;

    @RequestMapping("/hello_four")
    public String helloFour(){
        return configRandomBean.toString();
    }
}
