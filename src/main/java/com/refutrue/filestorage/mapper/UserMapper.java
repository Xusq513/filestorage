package com.refutrue.filestorage.mapper;

import com.refutrue.filestorage.domain.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //后来新增加的
    List<User> selectUserByUsername(String username);

    List<User> selectAllUser();
}