package com.xiezhixian.blog.service;

import com.xiezhixian.blog.pojo.User;

/**
 * <Description> UserService
 *
 * @author 26802
 * @version 1.0
 * @ClassName UserService
 * @taskId
 * @see com.xiezhixian.blog.service
 */
public interface UserService {

    User checkUser(String username, String password);

    User getUserById(Integer id);

}
