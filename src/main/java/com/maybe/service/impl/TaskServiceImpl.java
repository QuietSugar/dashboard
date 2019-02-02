package com.maybe.service.impl;


import com.github.pagehelper.PageHelper;
import com.maybe.dao.TaskMapper;
import com.maybe.generic.AbstractGenericServiceImpl;
import com.maybe.generic.GenericDao;
import com.maybe.pojo.Task;
import com.maybe.pojo.TaskExample;
import com.maybe.pojo.enumeration.TaskStatus;
import com.maybe.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户Service实现类
 */
@Service
public class TaskServiceImpl extends AbstractGenericServiceImpl<Task, String> implements TaskService {

    @Resource
    private TaskMapper taskMapper;

    @Override
    public GenericDao<Task, String> getDao() {
        return taskMapper;
    }

    @Override
    public Task selectByTitle(String username) {
        TaskExample example = new TaskExample();
        example.createCriteria().andTitleEqualTo(username);
        final List<Task> list = taskMapper.selectByExample(example);
        return list.get(0);
    }

    @Override
    public Integer count() {

        TaskExample example = new TaskExample();
        example.createCriteria();
        return taskMapper.countByExample(example);
    }

    @Override
    public List<Task> fuzzyQuery(int offset, int limit, String value) {
        PageHelper.offsetPage(offset, limit);
        TaskExample example = new TaskExample();
        if (!StringUtils.isEmpty(value)) {
            TaskExample.Criteria criteria1 = example.createCriteria().andTitleLike("%" + value + "%");
            TaskExample.Criteria criteria2 = example.createCriteria().andUrlLike("%" + value + "%");
            TaskExample.Criteria criteria3 = example.createCriteria().andContentLike("%" + value + "%");
            example.or(criteria1);
            example.or(criteria2);
            example.or(criteria3);
        }
        return taskMapper.selectByExample(example);
    }

    @Override
    public void complete(String id) {
        Task task = new Task();
        task.setId(id);
        task.setStatus(TaskStatus.OVERED.getStatus());
        taskMapper.updateByPrimaryKeySelective(task);
    }

}
