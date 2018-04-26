package com.refutrue.filestorage.service;

import com.refutrue.filestorage.domain.User;

import java.util.List;

public interface UserService {

    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);
}
