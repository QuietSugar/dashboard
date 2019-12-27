package com.maybe.service.impl;


import com.github.pagehelper.PageHelper;
import com.maybe.dao.StatementMapper;
import com.maybe.generic.AbstractGenericServiceImpl;
import com.maybe.generic.GenericDao;
import com.maybe.pojo.Statement;
import com.maybe.pojo.StatementExample;
import com.maybe.service.StatementService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户Service实现类
 */
@Service
public class StatementServiceImpl extends AbstractGenericServiceImpl<Statement, String> implements StatementService {
    @Resource
    private StatementMapper statementMapper;

    @Override
    public GenericDao<Statement, String> getDao() {
        return null;
    }

    @Override
    public List<Statement> selectByCommandId(int page, int limit, String statementId) {
        PageHelper.offsetPage(page, limit);
        StatementExample example = new StatementExample();
        if (!StringUtils.isEmpty(statementId)) {
            StatementExample.Criteria criteria3 = example.createCriteria().andIdEqualTo(statementId);
            example.or(criteria3);
        }
        return statementMapper.selectByExample(example);
    }
}
