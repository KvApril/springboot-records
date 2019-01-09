package com.kv.aboutconfig.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //通过注解注入属性值
    @Value("${com.kv.name}")
    private String name;

    @Value("${com.kv.say}")
    private String say;

    @RequestMapping("/hello")
    public String sayWhat(){
        return name+" say: " + say;
    }
}
