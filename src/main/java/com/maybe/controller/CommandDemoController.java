package com.maybe.controller;

import com.github.pagehelper.Page;
import com.maybe.pojo.CommandDemo;
import com.maybe.service.CommandDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "commandDemo", method = {RequestMethod.POST})
public class CommandDemoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandDemoController.class);

    @Resource
    CommandDemoService commandDemoService;

    @RequestMapping("/list")
    public Map list(
            //偏移量
            @RequestParam(value = "start", defaultValue = "0") Integer offset,
            //长度
            @RequestParam(value = "length", defaultValue = "10") Integer limit,
            //关键词
            @RequestParam(value = "commandId") String commandId
    ) {
        List<CommandDemo> commandDemoList = commandDemoService.selectByCommandId(offset, limit, commandId);
        //总记录数
        long totalNum = ((Page) commandDemoList).getTotal();
        Map<Object, Object> info = new HashMap<>(limit);
        info.put("data", commandDemoList);
        info.put("total", totalNum);
        return info;
    }

    @RequestMapping("/edit")
    public String edit(CommandDemo commandDemo) throws Exception {
        return String.valueOf(commandDemoService.update(commandDemo));
    }

    @RequestMapping("/del")
    public String del(@RequestParam(value = "id") String id) throws Exception {

        return String.valueOf(commandDemoService.delete(id));
    }


    @RequestMapping("/add")
    public int add(CommandDemo commandDemo) {
        commandDemo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        return commandDemoService.insert(commandDemo);
    }

//    @RequestMapping("commandDemo/info")
//    public void info(String commandName) {
//        commandDemoService.selectByCommandName()
//    }
}
