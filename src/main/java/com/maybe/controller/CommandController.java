package com.maybe.controller;

import com.github.pagehelper.Page;
import com.maybe.pojo.Command;
import com.maybe.service.CommandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping(value = "command", method = {RequestMethod.POST,RequestMethod.GET})
@Api(value="命令controller",tags={"命令操作接口"})
public class CommandController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandController.class);

    @Resource
    CommandService commandService;

    @RequestMapping(value = "/list", produces = "application/json;charset=utf-8")
    @ApiOperation( value = "命令列表", httpMethod = "POST", response = Map.class, notes = "命令列表展示-笔记")
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

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation( value = "命令列表", httpMethod = "POST", response = Map.class, notes = "命令列表展示-笔记")
    public String edit(Command command) throws Exception {
        return String.valueOf(commandService.update(command));
    }

    @RequestMapping("/del")
    public String del(@RequestParam(value = "id") String id) throws Exception {

        return String.valueOf(commandService.delete(id));
    }


    @RequestMapping("/add")
    public int add(Command command) {
        command.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        return commandService.insert(command);
    }

    @RequestMapping("/id/{id}")
    public Command selectById(@PathVariable("id") String id) {
        return commandService.selectById(id);
    }

    @RequestMapping("/name/{name}")
    public Command selectByName(@PathVariable("name") String name) {
        return commandService.selectByName(name);
    }
}
