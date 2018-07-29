package com.maybe.dao;

import com.maybe.generic.GenericDao;
import com.maybe.pojo.CommandDemo;
import com.maybe.pojo.CommandDemoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommandDemoMapper extends GenericDao {
    /**
     *
     * @mbggenerated 2018-07-28
     */
    int countByExample(CommandDemoExample example);

    /**
     *
     * @mbggenerated 2018-07-28
     */
    int deleteByExample(CommandDemoExample example);

    /**
     *
     * @mbggenerated 2018-07-28
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2018-07-28
     */
    int insert(CommandDemo record);

    /**
     *
     * @mbggenerated 2018-07-28
     */
    int insertSelective(CommandDemo record);

    /**
     *
     * @mbggenerated 2018-07-28
     */
    List<CommandDemo> selectByExample(CommandDemoExample example);

    /**
     *
     * @mbggenerated 2018-07-28
     */
    CommandDemo selectByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2018-07-28
     */
    int updateByExampleSelective(@Param("record") CommandDemo record, @Param("example") CommandDemoExample example);

    /**
     *
     * @mbggenerated 2018-07-28
     */
    int updateByExample(@Param("record") CommandDemo record, @Param("example") CommandDemoExample example);

    /**
     *
     * @mbggenerated 2018-07-28
     */
    int updateByPrimaryKeySelective(CommandDemo record);

    /**
     *
     * @mbggenerated 2018-07-28
     */
    int updateByPrimaryKey(CommandDemo record);
}