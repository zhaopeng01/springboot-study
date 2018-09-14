package com.zyc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProController {

    @Value("${test.msg}")
    private String str;


    @GetMapping("/get")
    public Object getPro() {
        return str;
    }
}
