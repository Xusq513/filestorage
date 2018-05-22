package com.refutrue.filestorage.service;

import com.github.pagehelper.PageHelper;
import com.refutrue.filestorage.controller.UserController;
import com.refutrue.filestorage.domain.User;
import com.refutrue.filestorage.mapper.UserMapper;
import com.refutrue.filestorage.util.ResponseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;//这里会报错，但是并不会影响

    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public ResponseMsg loginUser(User user) {
        ResponseMsg responseMsg = new ResponseMsg();
        List<User>  users = userMapper.selectUserByUsername(user.getUserName());
        String password = user.getPassword();
        if(users == null || users.size() == 0){
            logger.info("【" + user.getUserName() + "】用户不存在!");
            responseMsg.setCode("500");
            responseMsg.setSuccess(false);
            responseMsg.setMessage("用户不存在!");
            return responseMsg;
        }
        String password_s = users.get(0).getPassword();
        if(password.equals(password_s)){
            logger.info("【" + user.getUserName() + "】用户匹配成功!");
        }else{
            logger.info("【" + user.getUserName() + "】用户名或者密码错误!");
            responseMsg.setCode("500");
            responseMsg.setSuccess(false);
            responseMsg.setMessage("用户名或者密码错误!");
        }
        return responseMsg;
    }

    /*
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     * */
    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAllUser();
    }
}
