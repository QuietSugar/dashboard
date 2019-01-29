package com.maybe.controller;

import com.maybe.service.UserService;
import com.maybe.vo.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Maybe has infinite possibilities
 *
 * @author Created by sugar on 2018/7/3
 */
@RestController
@RequestMapping(value = "user", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Resource
    UserService userService;

    @RequestMapping("/info")
    public LoginUser test(@RequestParam(value = "token") String token) {
        LOGGER.info("接收到参数：token{}", token);
        return LoginController.user;
    }
}
