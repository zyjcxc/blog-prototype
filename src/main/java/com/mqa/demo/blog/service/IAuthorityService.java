package com.mqa.demo.blog.service;

import com.mqa.demo.blog.domain.Authority;


/**
 * 权限接口
 * @author mengqa
 * @create 2018-05-10 11:17
 **/
public interface IAuthorityService {

    Authority getAuthorityById(Long id);
}
