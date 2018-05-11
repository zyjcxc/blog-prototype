package com.mqa.demo.jpatest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author mengqa
 * @create 2018-05-04 14:48
 **/
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotEmpty(message = "账号不能为空")
    @Size(min = 3, max = 20)
    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Size(max = 100)
    @Column(length = 100)
    private String password;

    @NotEmpty(message = "姓名不能为空")
    @Size(min = 2, max = 20)
    @Column(nullable = false, length = 200)
    private String name;

    @NotEmpty(message = "邮箱不能为空")
    @Size(max = 50)
    @Column(nullable = false, length = 50, unique = true)   // 映射为字段, 值不能为空
    @Email(message = "邮箱格式不对")
    private String email;

    @Column(length = 200)
    private String avatar;

    private Integer age;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 生成策略
    private Long id;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
