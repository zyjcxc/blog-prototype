package com.mqa.demo.blog.service.impl;

import com.mqa.demo.blog.dao.AuthorityDao;
import com.mqa.demo.blog.domain.Authority;
import com.mqa.demo.blog.service.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mengqa
 * @create 2018-05-11 15:23
 **/
@Service
public class AuthorityServiceImpl implements IAuthorityService {

    @Autowired
    private AuthorityDao authorityDao;

    @Override
    public Authority getAuthorityById(Long id) {
        return authorityDao.getOne(id);
    }
}
