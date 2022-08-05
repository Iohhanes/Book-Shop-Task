package com.academia.bookshop.security;

import com.academia.bookshop.model.entity.User;
import com.academia.bookshop.security.JwtPrincipalInfoClaimKey;
import com.academia.bookshop.security.SecurityUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.*;

@Getter
@Setter
public class JwtUser implements OAuth2AuthenticatedPrincipal {
    private User user;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public JwtUser(Long id, String email, Map<String, Object> attributes) {
        this.user = new User(id, email);
        this.authorities = formAuthorities(attributes);
        this.attributes = attributes;
    }

    private Collection<? extends GrantedAuthority> formAuthorities(Map<String, Object> attributes) {
        Collection<GrantedAuthority> resultAuthorities = new HashSet<>();

        List<String> roles = SecurityUtils.getClaim(attributes, JwtPrincipalInfoClaimKey.ROLES);
        if (roles != null) {
            roles.forEach(role -> resultAuthorities.add(new SimpleGrantedAuthority(role)));
        }

        List<String> authorities = SecurityUtils.getClaim(attributes, JwtPrincipalInfoClaimKey.AUTHORITIES);
        if (authorities != null) {
            authorities.forEach(authority -> resultAuthorities.add(new SimpleGrantedAuthority(authority)));
        }

        return resultAuthorities;
    }

    @Override
    public String getName() {
        return this.user.getEmail();
    }
}
