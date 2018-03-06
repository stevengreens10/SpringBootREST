package me.stevengreen.springboot.security.model;

import me.stevengreen.springboot.document.UserDocument;
import me.stevengreen.springboot.document.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> roles;

    public CustomUserDetails(UserDocument user) {
        this.username = user.getUsername();
        this.password = user.getPasswordHash();
        setRoles(user.getRoles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        List<GrantedAuthority> roleList = new ArrayList<>();
        for (UserRole userRole : roles) {
            String role = userRole.toString();
            roleList.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        this.roles = roleList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
}