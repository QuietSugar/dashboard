package com.maybe.dao;

import com.maybe.generic.GenericDao;
import com.maybe.pojo.Statement;
import com.maybe.pojo.StatementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StatementMapper extends GenericDao {
    long countByExample(StatementExample example);

    int deleteByExample(StatementExample example);

    int deleteByPrimaryKey(String id);

    int insert(Statement record);

    int insertSelective(Statement record);

    List<Statement> selectByExample(StatementExample example);

    Statement selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Statement record, @Param("example") StatementExample example);

    int updateByExample(@Param("record") Statement record, @Param("example") StatementExample example);

    int updateByPrimaryKeySelective(Statement record);

    int updateByPrimaryKey(Statement record);
}
