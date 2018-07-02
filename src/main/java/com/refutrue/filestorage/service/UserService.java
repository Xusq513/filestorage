package com.refutrue.filestorage.service;

import com.refutrue.filestorage.domain.User;
import com.refutrue.filestorage.util.ResponseMsg;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    //用户注册
    public int addUser(User user);

    //用户登录
    public ResponseMsg loginUser(User user,HttpServletRequest request);

    //用户列表分页展示（暂时用不到）
    public List<User> findAllUser(int pageNum, int pageSize);
}
