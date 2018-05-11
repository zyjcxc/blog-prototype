package com.mqa.demo.jpatest.service.impl;

import com.mqa.demo.jpatest.dao.AuthorityDao;
import com.mqa.demo.jpatest.domain.Authority;
import com.mqa.demo.jpatest.service.IAuthorityService;
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
