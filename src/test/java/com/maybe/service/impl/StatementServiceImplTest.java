package com.maybe.service.impl;

import com.maybe.pojo.Statement;
import com.maybe.service.StatementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Created by HuoXu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@Transactional
public class StatementServiceImplTest {
    @Resource
    private StatementService statementService;

    @Test
    public void selectByCommandId() {
        List<Statement> statements = statementService.selectByCommandId(0, 10, "005cdeb41ff648ad894213256c0282e3");
        for (Statement statement : statements) {
            System.out.println(statement);
        }
    }
}
