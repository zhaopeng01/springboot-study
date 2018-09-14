package com.zyc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetLog {
    private Logger log = LoggerFactory.getLogger(GetLog.class);

    @GetMapping("log")
    public void seeLog() {
        log.info("log info method");
        log.error("log info method");
        log.warn("log info method");
    }
}
