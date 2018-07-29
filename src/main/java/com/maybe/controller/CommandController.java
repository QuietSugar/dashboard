package com.maybe.controller;

import com.github.pagehelper.Page;
import com.maybe.pojo.Command;
import com.maybe.service.CommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Maybe has infinite possibilities
 *
 * @author Created by sugar on 2018/7/26
 */
@RestController
public class CommandController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandController.class);

    @Resource
    CommandService commandService;

    @RequestMapping("command/list")
    public Map list(
            //偏移量
            @RequestParam(value = "start", defaultValue = "0") Integer offset,
            //长度
            @RequestParam(value = "length", defaultValue = "10") Integer limit,
            //关键词
            @RequestParam(value = "keyword", defaultValue = "") String keyword
    ) {
        List<Command> commandList = commandService.fuzzyQuery(offset, limit, keyword);
        //总记录数
        long totalNum = ((Page) commandList).getTotal();
        Map<Object, Object> info = new HashMap<>(limit);
        info.put("data", commandList);
        info.put("total", totalNum);
        return info;
    }

    @RequestMapping("command/edit")
    public String edit(Command command) throws Exception {
        return String.valueOf(commandService.update(command));
    }

    @RequestMapping("command/del")
    public String del(@RequestParam(value = "id") String id) throws Exception {

        return String.valueOf(commandService.delete(id));
    }


    @RequestMapping("command/add")
    public void add(Command command) {
        command.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        commandService.insert(command);
    }

    @RequestMapping("command/id/{id}")
    public Command selectById(@PathVariable("id") String id) {
        return commandService.selectById(id);
    }

    @RequestMapping("command/name/{name}")
    public Command selectByName(@PathVariable("name") String name) {
        return commandService.selectByName(name);
    }
}
