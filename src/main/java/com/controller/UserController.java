package com.controller;

import com.model.User;
import com.service.UserService;
import com.util.EncryptionUtil;
import com.util.SnowflakeIdWorker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @Description
 * @Author Hexiaoshu
 * @Date 2021/5/1
 * @modify
 */
@Api(value = "用户控制器",tags = {"UserController"})
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户新增")
    @PostMapping
    public void add(User user, HttpServletResponse response) throws IOException {
        user.setId(SnowflakeIdWorker.id());
        user.setCreateTime(new Date());
        user.setPwd(EncryptionUtil.MD5Encode(user.getPwd(),"utf-8"));
        userService.insert(user);
        response.sendRedirect("/login");
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public void login(User user, HttpServletResponse response) throws IOException {
        User curUser = userService.getOneByParam(new User().setPhone(user.getPhone()));
        String pwd = EncryptionUtil.MD5Encode(user.getPwd(),"utf-8");
        if (pwd.equals(curUser.getPwd())){
            response.sendRedirect("/index");
        }else {
            response.sendRedirect("/login");
        }
    }




}
