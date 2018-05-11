package com.mqa.demo.blog.service;

import com.mqa.demo.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * 用户服务接口
 * @author mengqa
 * @create 2018-05-10 11:17
 **/
public interface IUserService {

    /**
     * 新增用户
     * @param user
     */
    User saveUser(User user);
    /**
     * 编辑用户
     * @param user
     */
    User updateUser(User user);

    /**
     * 注册用户
     * @param user
     * @return
     */
    User registerUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void removeUser(Long id);

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 根据用户姓名查找用户
     * @param name
     * @param pageable
     * @return
     */
    Page<User> listUsersByNameLike(String name, Pageable pageable);

}
