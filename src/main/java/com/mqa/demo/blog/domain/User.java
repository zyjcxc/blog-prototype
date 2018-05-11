package com.mqa.demo.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author mengqa
 * @create 2018-05-04 14:48
 **/
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

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

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //  需将 List<Authority> 转成 List<SimpleGrantedAuthority>，否则前端拿不到角色列表名称
        List<SimpleGrantedAuthority> simpleAuthorities = new ArrayList<>();
        for(GrantedAuthority authority : this.authorities){
            simpleAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        return simpleAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setEncodePassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePasswd = encoder.encode(password);
        this.password = encodePasswd;
    }
}
