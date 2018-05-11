package com.mqa.demo.jpatest.service.impl;

import com.mqa.demo.jpatest.dao.UserDao;
import com.mqa.demo.jpatest.domain.User;
import com.mqa.demo.jpatest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * 用户服务实现
 * @author mengqa
 * @create 2018-05-10 11:22
 **/
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User registerUser(User user) {
        return userDao.save(user);
    }

    @Override
    public void removeUser(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id).get();
    }

    @Override
    public Page<User> listUsersByNameLike(String name, Pageable pageable) {
        name = "%" + name + "%";
        return userDao.findByNameLike(name, pageable);
    }
}
