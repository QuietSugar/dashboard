package com.maybe.service;


import com.maybe.generic.GenericService;
import com.maybe.pojo.CommandDemo;

import java.util.List;

/**
 * 用户 业务 接口
 **/
public interface CommandDemoService extends GenericService<CommandDemo, String> {


    /**
     * @param offset 偏移量
     * @param limit  长度
     * @param commandId  commandId
     * @return  CommandDemoList
     */
    List<CommandDemo> selectByCommandId(int offset, int limit,String commandId);

    /**
     * @param offset 偏移量
     * @param limit  长度
     * @param commandName  commandName
     * @return  CommandDemoList
     */
    List<CommandDemo> selectByCommandName(int offset, int limit,String commandName);

}
