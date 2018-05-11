package com.mqa.demo.blog.dao;

import com.mqa.demo.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author mengqa
 * @create 018-05-04 14:50
 **/
@Repository
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * 根据用户姓名分布查询用户列表
     * @param name 名称
     * @param pageable 分页
     * @return 列表
     */
    Page<User> findByNameLike(String name, Pageable pageable);

    /**
     * 根据用户账号查询用户
     * @param username 用户名
     * @return 用户
     */
    User findByUsername(String username);
}
