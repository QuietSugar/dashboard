package com.maybe.dao;

import com.maybe.generic.GenericDao;
import com.maybe.pojo.Statement;
import com.maybe.pojo.StatementExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StatementMapper extends GenericDao {
    /**
     * @mbggenerated 2019-01-28
     */
    int countByExample(StatementExample example);

    /**
     * @mbggenerated 2019-01-28
     */
    int deleteByExample(StatementExample example);

    /**
     * @mbggenerated 2019-01-28
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbggenerated 2019-01-28
     */
    int insert(Statement record);

    /**
     * @mbggenerated 2019-01-28
     */
    int insertSelective(Statement record);

    /**
     * @mbggenerated 2019-01-28
     */
    List<Statement> selectByExample(StatementExample example);

    /**
     * @mbggenerated 2019-01-28
     */
    Statement selectByPrimaryKey(String id);

    /**
     * @mbggenerated 2019-01-28
     */
    int updateByExampleSelective(@Param("record") Statement record, @Param("example") StatementExample example);

    /**
     * @mbggenerated 2019-01-28
     */
    int updateByExample(@Param("record") Statement record, @Param("example") StatementExample example);

    /**
     * @mbggenerated 2019-01-28
     */
    int updateByPrimaryKeySelective(Statement record);

    /**
     * @mbggenerated 2019-01-28
     */
    int updateByPrimaryKey(Statement record);
}