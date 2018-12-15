package com.zyc.controller;

import com.zyc.common.Assert;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionDemoController {
    private static final String ID_NUM = null;

    @RequestMapping("/get")
    public String getName() {
        Assert.notNull(ID_NUM, "ID");
        return "id :" + ID_NUM + "author is zyc";
    }

    @RequestMapping("/param")
    public String getParam() {
        if (StringUtils.isEmpty(ID_NUM)) {
            throw new IllegalArgumentException("Id 为空!");
        }
        return "result success !";
    }
}
