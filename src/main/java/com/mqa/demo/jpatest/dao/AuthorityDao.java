package com.mqa.demo.jpatest.dao;

import com.mqa.demo.jpatest.domain.Authority;
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
