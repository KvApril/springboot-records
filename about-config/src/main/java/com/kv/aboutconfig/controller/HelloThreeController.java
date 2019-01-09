package com.kv.aboutconfig.controller;

import com.kv.aboutconfig.config.ConfigValueBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloThreeController {
    @Autowired
    ConfigValueBean configValueBean;

    @RequestMapping("/hello_three")
    public String helloThree(){
        return configValueBean.getName()+" say: "+configValueBean.getSay();
    }
}
