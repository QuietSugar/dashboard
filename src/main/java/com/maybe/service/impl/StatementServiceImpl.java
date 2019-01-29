package com.maybe.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.maybe.dao.CommandMapper;
import com.maybe.dao.StatementMapper;
import com.maybe.generic.GenericDao;
import com.maybe.generic.GenericServiceImpl;
import com.maybe.pojo.Command;
import com.maybe.pojo.CommandExample;
import com.maybe.pojo.Statement;
import com.maybe.pojo.StatementExample;
import com.maybe.service.StatementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户Service实现类
 */
@Service
public class StatementServiceImpl extends GenericServiceImpl<Statement, String> implements StatementService {

    @Resource
    private StatementMapper statementMapper;
    @Resource
    private CommandMapper commandMapper;

    @Override
    public int insert(Statement model) {
        return statementMapper.insertSelective(model);
    }

    @Override
    public int update(Statement model) {
        return statementMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public int delete(String id) {
        return statementMapper.deleteByPrimaryKey(id);
    }


    @Override
    public Statement selectById(String id) {
        return statementMapper.selectByPrimaryKey(id);
    }

    @Override
    public GenericDao<Statement, String> getDao() {
        return statementMapper;
    }

    @Override
    public List<Statement> selectByCommandId(int page, int limit, String commandId) {

        PageHelper.startPage(page, limit);
        StatementExample example = new StatementExample();
        if (StringUtil.isNotEmpty(commandId)) {
            example.createCriteria().andCommandIdEqualTo(commandId);
        }
        return statementMapper.selectByExample(example);
    }
}
