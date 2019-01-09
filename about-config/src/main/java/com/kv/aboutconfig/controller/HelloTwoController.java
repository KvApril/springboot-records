package com.kv.aboutconfig.controller;

import com.kv.aboutconfig.config.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloTwoController {
    @Autowired
    ConfigBean configBean;

    @RequestMapping("/hello_two")
    public String helloTwo(){
        return configBean.getName()+" say: "+configBean.getSay();
    }
}
