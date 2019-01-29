package com.maybe.service;


import com.maybe.generic.GenericService;
import com.maybe.pojo.Statement;

import java.util.List;

/**
 * 语句
 **/
public interface StatementService extends GenericService<Statement, String> {


    /**
     * @param page      页数
     * @param limit       长度
     * @param statementId statementId
     * @return StatementList
     */
    List<Statement> selectByCommandId(int page, int limit, String statementId);
}
