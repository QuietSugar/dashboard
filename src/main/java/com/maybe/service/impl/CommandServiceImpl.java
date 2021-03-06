package com.maybe.service.impl;


import com.github.pagehelper.PageHelper;
import com.maybe.dao.CommandMapper;
import com.maybe.generic.GenericDao;
import com.maybe.generic.AbstractGenericServiceImpl;
import com.maybe.pojo.Command;
import com.maybe.pojo.CommandExample;
import com.maybe.service.CommandService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户Service实现类
 */
@Service
public class CommandServiceImpl extends AbstractGenericServiceImpl<Command, String> implements CommandService {

    @Resource
    private CommandMapper commandMapper;

    @Override
    public GenericDao<Command, String> getDao() {
        return commandMapper;
    }

    @Override
    public Command selectByName(String name) {
        CommandExample example = new CommandExample();
        example.createCriteria().andNameEqualTo(name);
        final List<Command> list = commandMapper.selectByExample(example);
        return list.size() < 1 ? null : list.get(0);
    }

    @Override
    public Integer count() {
        CommandExample example = new CommandExample();
        example.createCriteria();
        return commandMapper.countByExample(example);
    }

    @Override
    public List<Command> fuzzyQuery(int offset, int limit, String value) {
        PageHelper.offsetPage(offset, limit);
        CommandExample example = new CommandExample();
        if (!StringUtils.isEmpty(value)) {
            CommandExample.Criteria criteria3 = example.createCriteria().andContentLike("%" + value + "%");
            example.or(criteria3);
        }
        return commandMapper.selectByExample(example);
    }

}
