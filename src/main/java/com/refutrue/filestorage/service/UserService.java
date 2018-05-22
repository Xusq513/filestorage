package com.refutrue.filestorage.service;

import com.refutrue.filestorage.domain.User;

import java.util.List;

public interface UserService {
    //用户注册
    int addUser(User user);

    //用户登录
    int loginUser(User user);

    //用户列表分页展示（暂时用不到）
    List<User> findAllUser(int pageNum, int pageSize);
}
