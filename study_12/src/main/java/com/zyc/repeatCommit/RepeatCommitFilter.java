package com.zyc.repeatCommit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description: 重复提交过滤
 *
 * @author zhaopeng
 */
@Slf4j
@Configuration
public class RepeatCommitFilter {


    @Autowired
    private RedisLockUtil redisLockUtil;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new Filter() {
            @Override
            public void init(FilterConfig filterConfig) throws ServletException {
                log.info(">>>>>>>>>过滤器 init!");
            }

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws IOException, ServletException {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                HttpServletRequest httpRequest = (HttpServletRequest) request;
                String requestURI = httpRequest.getRequestURI();
                String method = httpRequest.getMethod();
                //校验请求是否需要进行重复请求校验
                boolean skip = redisLockUtil.checkSkipUri(requestURI);
                if (!skip) {
                    chain.doFilter(request, httpResponse);
                    return;
                }
                log.info("==========初始化调用,对【{}】请求【{}】接口加锁==========", method, requestURI);
                String operation = requestURI + method;
                String unikey = "zyc";
                boolean ret = redisLockUtil.checkRepeatOperation(unikey, operation);
                if (ret) {
                    printWriter(httpResponse, Result.error(101, "重复提交"));
                    return;
                }
                chain.doFilter(request, httpResponse);
                redisLockUtil.expireValue(unikey, operation);
                log.info("===========请求完成,【{}】请求【{}】接口已解锁===========", method, requestURI);
            }

            private void printWriter(HttpServletResponse httpResponse, Result result) throws IOException, JsonProcessingException {
                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.setContentType("application/json; charset=utf-8");
                PrintWriter out = httpResponse.getWriter();
                out.write(objectMapper.writeValueAsString(result));
                out.flush();
                out.close();

            }


            @Override
            public void destroy() {
                log.info(">>>>>>>>>过滤器 destroy!");
            }

        });
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }
}
