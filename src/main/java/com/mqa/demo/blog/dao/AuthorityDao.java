package com.mqa.demo.blog.dao;

import com.mqa.demo.blog.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 权限Dao
 * @author mengqa
 * @create 018-05-04 14:50
 **/
@Repository
public interface AuthorityDao extends JpaRepository<Authority, Long> {

}
