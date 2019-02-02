package com.maybe.controller;

import com.maybe.service.UserService;
import com.maybe.vo.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Maybe has infinite possibilities
 *
 * @author Created by sugar on 2018/7/3
 */
@RestController
@RequestMapping(value = "login", method = {RequestMethod.POST}, produces = "application/json;charset=utf-8")
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    public static LoginUser user = new LoginUser();

    static {

        user.setRoles(new String[]{"user","admin"});
        user.setToken("user");
        user.setIntroduction("我是普通用户");
        user.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        user.setName("Normal User");
    }

    @Resource
    UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public LoginUser login(@RequestParam("username") String userName, @RequestParam("password") String password) {
        LOGGER.info("接收到参数：username{},password：{}", userName, password);


        userService.login(userName,password);


        return user;
    }


}
