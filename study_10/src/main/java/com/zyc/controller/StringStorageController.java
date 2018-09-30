package com.zyc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 字符串缓存
 * @author zhaopeng
 * @email zp152527@163.com
 */

@RestController
public class StringStorageController {
    private static final Logger log = LoggerFactory.getLogger(StringStorageController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/string")
    public Object stringStrorage() {
        stringRedisTemplate.opsForValue().set("key1", "This is a String");
        final String key1 = stringRedisTemplate.opsForValue().get("key1");
        log.info("[字符串缓存结果] - [{}]", key1);
        return "字符串缓存返回结果为" + key1;
    }
}
