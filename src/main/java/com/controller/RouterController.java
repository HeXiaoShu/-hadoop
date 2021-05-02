package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description 登录控制器
 * @Author Hexiaoshu
 * @Date 2021/5/1
 * @modify
 */
@Controller
public class RouterController {

    /**
     * 登录跳转
     * @return login
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 首页
     * @return index
     */
    @GetMapping("/index")
    public String index(){
        return "index";
    }




}
