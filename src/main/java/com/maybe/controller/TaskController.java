package com.maybe.controller;

import com.github.pagehelper.Page;
import com.maybe.pojo.Task;
import com.maybe.pojo.enumeration.TaskStatus;
import com.maybe.service.TaskService;
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
 * @author Created by sugar on 2018/7/3
 */
@RestController
@RequestMapping(value = "task", method = {RequestMethod.POST})
public class TaskController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Resource
    TaskService taskService;

    @RequestMapping("/list")
    public Map list(
            //偏移量
            @RequestParam(value = "start", defaultValue = "0") Integer offset,
            //长度
            @RequestParam(value = "length", defaultValue = "10") Integer limit,
            //获取用户过滤框里的字符
            @RequestParam(value = "search[value]", defaultValue = "") String searchValue,
            @RequestParam(value = "draw") Integer draw,
            @RequestParam(value = "hello", defaultValue = "") String hello
    ) throws Exception {
        LOGGER.info("自定义参数" + hello);
        List<Task> taskList = taskService.fuzzyQuery(offset, limit, searchValue);

        //总记录数
        long totalNum = ((Page) taskList).getTotal();

        Map<Object, Object> info = new HashMap<>(limit);
        info.put("data", taskList);
        info.put("total", totalNum);
        info.put("draw", draw);
        return info;
    }

    @RequestMapping("/edit")
    public String edit(Task task) throws Exception {
        return String.valueOf(taskService.update(task));
    }

    @RequestMapping("/del")
    public String del(@RequestParam(value = "id") String id) throws Exception {

        return String.valueOf(taskService.delete(id));
    }

    /**
     * 完成任务，或者已归档
     *
     * @param id id
     * @throws Exception
     */
    @RequestMapping("/complete")
    public void complete(@RequestParam(value = "id") String id) throws Exception {
        taskService.complete(id);
    }

    @RequestMapping("/add")
    public void add(Task task) {
        task.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        task.setStatus(TaskStatus.INIT.getStatus());
        taskService.insert(task);
    }

    /**
     * 随机添加数据
     *
     * @param num 添加数量
     * @return
     * @throws Exception
     */
    @RequestMapping("/init")
    public void init(@RequestParam(defaultValue = "0") Integer num) throws Exception {
        for (int i = 0; i < num; i++) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            Task task = new Task();
            task.setId(uuid);
            task.setTitle("标题" + uuid);
            task.setContent("内容" + uuid);
            task.setRemarks("备注" + uuid);
            task.setUrl("URL" + uuid);
            taskService.insert(task);
        }
    }
}
