package com.maybe.vo;

import com.maybe.pojo.Command;

import java.util.List;

/**
 * Maybe has infinite possibilities
 *
 * @author Created by Maybe on 2019/1/22
 */
public class CommandList {
    private long total;
    private List<Command> commands;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }
}
