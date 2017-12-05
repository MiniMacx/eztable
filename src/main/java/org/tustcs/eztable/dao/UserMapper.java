package org.tustcs.eztable.dao;

import org.apache.ibatis.annotations.Param;
import org.tustcs.eztable.entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findOne(@Param("userName") String userName,@Param("userPwd") String userPwd);

    User selectByUserName(String userName);

    List<User> selectAll();
}