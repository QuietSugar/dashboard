package com.maybe.dao;

import com.maybe.generic.GenericDao;
import com.maybe.pojo.Task;
import com.maybe.pojo.TaskExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskMapper extends GenericDao {
    /**
     * @mbggenerated 2018-07-08
     */
    int countByExample(TaskExample example);

    /**
     * @mbggenerated 2018-07-08
     */
    int deleteByExample(TaskExample example);

    /**
     * @mbggenerated 2018-07-08
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbggenerated 2018-07-08
     */
    int insert(Task record);

    /**
     * @mbggenerated 2018-07-08
     */
    int insertSelective(Task record);

    /**
     * @mbggenerated 2018-07-08
     */
    List<Task> selectByExample(TaskExample example);

    /**
     * @mbggenerated 2018-07-08
     */
    Task selectByPrimaryKey(String id);

    /**
     * @mbggenerated 2018-07-08
     */
    int updateByExampleSelective(@Param("record") Task record, @Param("example") TaskExample example);

    /**
     * @mbggenerated 2018-07-08
     */
    int updateByExample(@Param("record") Task record, @Param("example") TaskExample example);

    /**
     * @mbggenerated 2018-07-08
     */
    int updateByPrimaryKeySelective(Task record);

    /**
     * @mbggenerated 2018-07-08
     */
    int updateByPrimaryKey(Task record);
}