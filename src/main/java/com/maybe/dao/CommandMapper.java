package com.maybe.dao;

import com.maybe.generic.GenericDao;
import com.maybe.pojo.Command;
import com.maybe.pojo.CommandExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CommandMapper extends GenericDao {
    /**
     * @mbggenerated 2018-07-15
     */
    int countByExample(CommandExample example);

    /**
     * @mbggenerated 2018-07-15
     */
    int deleteByExample(CommandExample example);

    /**
     * @mbggenerated 2018-07-15
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbggenerated 2018-07-15
     */
    int insert(Command record);

    /**
     * @mbggenerated 2018-07-15
     */
    int insertSelective(Command record);

    /**
     * @mbggenerated 2018-07-15
     */
    List<Command> selectByExample(CommandExample example);

    /**
     * @mbggenerated 2018-07-15
     */
    Command selectByPrimaryKey(String id);

    /**
     * @mbggenerated 2018-07-15
     */
    int updateByExampleSelective(@Param("record") Command record, @Param("example") CommandExample example);

    /**
     * @mbggenerated 2018-07-15
     */
    int updateByExample(@Param("record") Command record, @Param("example") CommandExample example);

    /**
     * @mbggenerated 2018-07-15
     */
    int updateByPrimaryKeySelective(Command record);

    /**
     * @mbggenerated 2018-07-15
     */
    int updateByPrimaryKey(Command record);
}