package com.maybe.service.impl;


import com.github.pagehelper.PageHelper;
import com.maybe.dao.CommandDemoMapper;
import com.maybe.dao.CommandMapper;
import com.maybe.generic.GenericDao;
import com.maybe.generic.GenericServiceImpl;
import com.maybe.pojo.Command;
import com.maybe.pojo.CommandDemo;
import com.maybe.pojo.CommandDemoExample;
import com.maybe.pojo.CommandExample;
import com.maybe.service.CommandDemoService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户Service实现类
 */
@Service
public class CommandDemoServiceImpl extends GenericServiceImpl<CommandDemo, String> implements CommandDemoService {

    @Resource
    private CommandDemoMapper commandDemoMapper;
    @Resource
    private CommandMapper commandMapper;

    @Override
    public int insert(CommandDemo model) {
        return commandDemoMapper.insertSelective(model);
    }

    @Override
    public int update(CommandDemo model) {
        return commandDemoMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public int delete(String id) {
        return commandDemoMapper.deleteByPrimaryKey(id);
    }


    @Override
    public CommandDemo selectById(String id) {
        return commandDemoMapper.selectByPrimaryKey(id);
    }

    @Override
    public GenericDao<CommandDemo, String> getDao() {
        return commandDemoMapper;
    }

    @Override
    public List<CommandDemo> selectByCommandId(int offset, int limit, String commandId) {
        PageHelper.offsetPage(offset, limit);
        CommandDemoExample example = new CommandDemoExample();
        example.createCriteria().andCommandIdEqualTo(commandId);
        return commandDemoMapper.selectByExample(example);
    }

    @Override
    public List<CommandDemo> selectByCommandName(int offset, int limit, String commandName) {
        //先查询主表
        CommandExample commandExample = new CommandExample();
        commandExample.createCriteria().andNameEqualTo(commandName);
        List<Command> commands = commandMapper.selectByExample(commandExample);
        if (commands.size() < 1) {
            return new ArrayList<>();
        }
        return selectByCommandId(offset, limit, commands.get(0).getId());
    }

}
