package com.refutrue.filestorage.service;

import com.github.pagehelper.PageHelper;
import com.refutrue.filestorage.domain.User;
import com.refutrue.filestorage.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;//这里会报错，但是并不会影响

    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int loginUser(User user) {
        List<User>  users = userMapper.selectUserByUsername(user.getUserName());
        String password = user.getPassword();
        if(users.size() == 0)
        {
            System.out.print("提示：用户名不存在");
            return 0;
        }
        String password_s = users.get(0).getPassword();
        if(password_s == password)
        {
            System.out.print("提示：登录成功！");
            return 1;
        }
        else
        {
            System.out.print("提示：用户名或者密码错误！");
            return 0;
        }
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
