package com.photowey.spring.cloud.authorization.server.jpa.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;

/**
 * {@code UserAuthority}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
@Embeddable
public class UserAuthority implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private String authority;

    public UserAuthority() {
    }

    public UserAuthority(String authority) {
        Assert.hasText(authority, "A granted authority textual representation is required");
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SimpleGrantedAuthority) {
            return this.authority.equals(((UserAuthority) obj).authority);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.authority.hashCode();
    }

    @Override
    public String toString() {
        return this.authority;
    }
}
