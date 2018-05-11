package com.mqa.demo.blog.dao;

import com.mqa.demo.blog.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author mengqa
 * @create 2018-05-04 14:50
 **/
public interface UserDao extends CrudRepository<User, Long> {


}
