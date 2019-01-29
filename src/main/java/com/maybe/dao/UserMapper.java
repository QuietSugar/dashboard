package com.maybe.dao;

import com.maybe.generic.GenericDao;
import com.maybe.pojo.User;
import com.maybe.pojo.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends GenericDao {
    /**
     *
     * @mbggenerated 2019-01-22
     */
    int countByExample(UserExample example);

    /**
     *
     * @mbggenerated 2019-01-22
     */
    int deleteByExample(UserExample example);

    /**
     *
     * @mbggenerated 2019-01-22
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2019-01-22
     */
    int insert(User record);

    /**
     *
     * @mbggenerated 2019-01-22
     */
    int insertSelective(User record);

    /**
     *
     * @mbggenerated 2019-01-22
     */
    List<User> selectByExample(UserExample example);

    /**
     *
     * @mbggenerated 2019-01-22
     */
    User selectByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2019-01-22
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    /**
     *
     * @mbggenerated 2019-01-22
     */
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    /**
     *
     * @mbggenerated 2019-01-22
     */
    int updateByPrimaryKeySelective(User record);

    /**
     *
     * @mbggenerated 2019-01-22
     */
    int updateByPrimaryKey(User record);
}