package com.zyc.controller;

import com.zyc.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/user")
@Api(value = "用户测试模块")
public class UserController {

    @ApiOperation(value = "添加用户", notes = "根据参数添加用户")
    @PostMapping(value = "/add")
    public Object getUser() {
        return new User("zyc", "123123", "1761110");
    }
}