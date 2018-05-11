package com.mqa.demo.jpatest.service;

import com.mqa.demo.jpatest.domain.Authority;


/**
 * 权限接口
 * @author mengqa
 * @create 2018-05-10 11:17
 **/
public interface IAuthorityService {

    Authority getAuthorityById(Long id);
}
