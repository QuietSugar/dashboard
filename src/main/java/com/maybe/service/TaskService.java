package com.maybe.service;


import com.maybe.generic.GenericService;
import com.maybe.pojo.Task;

import java.util.List;

/**
 * 用户 业务 接口
 **/
public interface TaskService extends GenericService<Task, String> {


    /**
     * 根据title查询
     *
     * @param title
     * @return
     */
    Task selectByTitle(String title);

    /**
     * 统计个数
     */
    Integer count();


    List<Task> fuzzyQuery(int offset, int limit, String value);
}
