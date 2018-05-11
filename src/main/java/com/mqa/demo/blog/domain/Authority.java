package com.mqa.demo.blog.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @author mengqa
 * @create 2018-05-11 15:09
 **/
@Entity
@Data
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
