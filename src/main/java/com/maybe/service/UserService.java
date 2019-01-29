package com.maybe.service;

import com.maybe.generic.GenericService;
import com.maybe.pojo.User;

/**
 * 用户
 **/
public interface UserService extends GenericService<User, String> {
    /**
     * @param userName 用户名
     * @param password 密码
     * @return StatementList
     */
    User login(String userName, String password);
}
