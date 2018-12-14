package com.zyc.repeatCommit;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaopeng
 * @description: redis工具类
 */
@Component
public class RedisLockUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${repeat.urls}")
    private String urls;

    public boolean checkSkipUri(String uri) {
        if (Strings.isNullOrEmpty(urls)) return false;
        String[] skipURI = urls.split(",");
        for (String tmp : skipURI) {
            if (uri.endsWith(tmp)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkRepeatOperation(String uniqueKey, String operation) {
        boolean ret = false;
        String key = "RepeatRequest" + uniqueKey;
        Date now = new Date();
        String isExist = stringRedisTemplate.opsForValue().get(key + operation);
        if (Strings.isNullOrEmpty(isExist)) {
            stringRedisTemplate.opsForValue().set(key + operation, String.valueOf(now.getTime()), 60, TimeUnit.SECONDS);
        } else {
            ret = true;
        }
        return ret;
    }

    public void expireValue(String uniqueKey, String operation) {
        String key = "RepeatRequest" + uniqueKey;
        stringRedisTemplate.boundValueOps(key + operation).expire(0, TimeUnit.SECONDS);
    }
}
