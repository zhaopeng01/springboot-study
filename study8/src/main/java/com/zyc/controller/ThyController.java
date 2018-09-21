package com.zyc.controller;

import com.zyc.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: Thymeleaf
 * @author zhaopeng
 * @email zp152527@163.com
 */

@Controller
@RequestMapping
public class ThyController {

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView();
        // 设置跳转的视图 默认映射到 src/main/resources/templates/{viewName}.html
        view.setViewName("index");
        // 设置属性
        view.addObject("title", "Thymeleaf页面");
        view.addObject("desc", "Welcome to Thymeleaf");
        Person person = new Person();
        person.setGender("男娃子");
        person.setEmail("77742344@qq.com");
        person.setName("赵鹏");
        view.addObject("person", person);
        return view;
    }

    @GetMapping("/index1")
    public String index1(HttpServletRequest request) {

        // 设置属性
        request.setAttribute("title", "Thymeleaf页面");
        request.setAttribute("desc", "Welcome to Thymeleaf");
        Person person = new Person();
        person.setGender("难道到这里就能变成个女娃子?");
        person.setEmail("77742344@qq.com");
        person.setName("赵鹏");
        request.setAttribute("person", person);
        // 返回的 index 默认映射到 src/main/resources/templates/xxxx.html
        return "index";
    }

}
