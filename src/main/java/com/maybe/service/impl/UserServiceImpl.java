package com.maybe.service.impl;


import com.maybe.dao.UserMapper;
import com.maybe.generic.GenericDao;
import com.maybe.generic.AbstractGenericServiceImpl;
import com.maybe.pojo.User;
import com.maybe.pojo.UserExample;
import com.maybe.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户Service实现类
 */
@Service
public class UserServiceImpl extends AbstractGenericServiceImpl<User, String> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public GenericDao<User, String> getDao() {
        return userMapper;
    }

    @Override
    public User login(String userName, String password) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(userName);
        return userMapper.selectByExample(example).get(0);
    }
}
