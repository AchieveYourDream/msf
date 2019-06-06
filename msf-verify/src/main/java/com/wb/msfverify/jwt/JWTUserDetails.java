package com.wb.msfverify.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Name: JWT用户
 * @Description: JWT用户
 * @CreatedBy: zoran
 * @CreateDate: 2018-04-21
 * @LastUpdatedBy: zoran
 * @LastUpdateDate 2018-04-21
 */
public class JWTUserDetails implements UserDetails {

    private String username;
    private String password;
    private String email;
    private String mobilephone;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    
    public JWTUserDetails(String username) {
        this.username = username;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }
    
    public JWTUserDetails(String userId, String username, String password, String email, String mobilephone, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobilephone = mobilephone;

        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.authorities = authorities;
    }
    
    // 获取权限列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    // 用户名
    @Override
    public String getUsername() {
        return username;
    }
    
    // 密码
    @Override
    public String getPassword() {
        return password;
    }

    // 账户是否未过期
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    // 账户是否未锁定
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    // 密码是否未过期
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    // 账户是否激活
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    
    public String getEmail() {
        return email;
    }

    public String getMobilephone() {
        return mobilephone;
    }
}
