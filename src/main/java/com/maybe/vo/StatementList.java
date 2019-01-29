package com.maybe.vo;

import com.maybe.pojo.Statement;

import java.util.List;

/**
 * Maybe has infinite possibilities
 *
 * @author Created by Maybe on 2019/1/22
 */
public class StatementList {
    private long total;
    private List<Statement> statements;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }
}
