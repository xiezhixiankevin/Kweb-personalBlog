package com.xiezhixian.blog.service.impl;

import com.xiezhixian.blog.mapper.UserMapper;
import com.xiezhixian.blog.pojo.User;
import com.xiezhixian.blog.pojo.UserExample;
import com.xiezhixian.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <Description> UserServiceImpl
 *
 * @author 26802
 * @version 1.0
 * @ClassName UserServiceImpl
 * @taskId
 * @see com.xiezhixian.blog.service.impl
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User checkUser(String username, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);
        if(users.isEmpty()){
            return null;
        }else{
            return users.get(0);
        }

    }

    @Override
    public User getUserById(Integer id) {

        return userMapper.selectByPrimaryKey(id);

    }


}
