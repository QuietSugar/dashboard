package com.maybe.controller;

import com.github.pagehelper.Page;
import com.maybe.pojo.Statement;
import com.maybe.service.StatementService;
import com.maybe.util.Util;
import com.maybe.vo.StatementList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Maybe has infinite possibilities
 *
 * @author Created by sugar on 2018/7/26
 */
@RestController
@RequestMapping(value = "statement", method = {RequestMethod.POST,RequestMethod.GET})
public class StatementController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatementController.class);

    @Resource
    StatementService statementService;

    @RequestMapping("/list")
    public StatementList list(
            //页数
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            //长度
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            //关键词
            @RequestParam(value = "statementId" ,required = false) String commandId
    ) {
        List<Statement> statements = statementService.selectByCommandId(page, limit, commandId);
        //总记录数
        long totalNum = ((Page) statements).getTotal();
        StatementList statementList = new StatementList();
        statementList.setStatements(statements);
        statementList.setTotal(totalNum);

        return statementList;
    }

    @RequestMapping("/edit")
    public String edit(Statement statement) throws Exception {
        return String.valueOf(statementService.update(statement));
    }

    @RequestMapping("/del")
    public String del(@RequestParam(value = "id") String id) throws Exception {

        return String.valueOf(statementService.delete(id));
    }


    @RequestMapping("/add")
    public int add(Statement statement) {
        statement.setId(Util.getUuid());
        return statementService.insert(statement);
    }

//    @RequestMapping("statement/info")
//    public void info(String statementName) {
//        statementService.selectBystatementName()
//    }
}
