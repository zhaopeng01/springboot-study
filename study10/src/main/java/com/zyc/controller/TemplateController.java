package com.zyc.controller;

import com.zyc.entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @Description: 对象缓存
 * @author zhaopeng
 * @email zp152527@163.com
 */

@RestController
public class TemplateController {

    private static final Logger log = LoggerFactory.getLogger(TemplateController.class);

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @GetMapping("/template")
    public Object templateTest() {
        String key = "testUser";
        //添加对象到缓存
        redisCacheTemplate.opsForValue().set(key, new Users("zhangshan", "123456"));
        //获取对象
        Users users = (Users) redisCacheTemplate.opsForValue().get(key);
        log.info("[对象缓存结果] - [{}]", users);
        return "对象缓存返回结果为" + users;
    }

}
