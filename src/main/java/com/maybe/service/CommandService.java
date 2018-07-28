package com.maybe.service;


import com.maybe.generic.GenericService;
import com.maybe.pojo.Command;

import java.util.List;

/**
 * 用户 业务 接口
 **/
public interface CommandService extends GenericService<Command, String> {


    /**
     * 根据title查询
     *
     * @param name name
     * @return Command
     */
    Command selectByName(String name);

    /**
     * @return 统计个数
     */
    Integer count();

    /**
     * 模糊查询
     *
     * @param offset 偏移量
     * @param limit  长度
     * @param value  迷糊匹配 关键词
     * @return 列表
     */
    List<Command> fuzzyQuery(int offset, int limit, String value);

}
