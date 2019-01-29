package com.maybe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maybe.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Maybe has infinite possibilities
 *
 * @author Created by sugar on 2018/7/3
 */
@RestController
@RequestMapping(value = "spring", method = {RequestMethod.POST, RequestMethod.GET}
//, produces = "application/json;charset=utf-8"
)
public class SpringController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringController.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * json对象请求
     */
    @RequestMapping("/test")
    public Map jsonObject(@RequestParam(value = "name", required = false) String name) {
        System.out.println("--------");
        Map<String, String> map = new HashMap<>(1);
        map.put("name", name);
        LOGGER.debug(map.toString());
        return map;
    }

    /**
     * 标准的json请求数据
     * 对于前端来说，传过来的不是json对象，而是json字符串
     */
    @RequestMapping("/json/str")
    public Map jsonStr(@RequestBody HashMap<String, String> map) {

        return map;
    }

    /**
     * 以对象的形式提交
     *
     * @param user
     * @return
     */
    @RequestMapping("/user")
    @ResponseBody
    public User map(@RequestBody User user) {
        LOGGER.debug(user.toString());
        return user;
    }


}


