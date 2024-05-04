package com.devxminds.donpipe.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

public class RoleConverter {
    public static List<GrantedAuthority> getAuthorities(int permissionId) {
        switch (permissionId) {
            case 1:
                return Collections.singletonList(new SimpleGrantedAuthority("admin"));
            case 2:
                return Collections.singletonList(new SimpleGrantedAuthority("lz"));
            case 3:
                return Collections.singletonList(new SimpleGrantedAuthority("bronze"));
            case 4:
                return Collections.singletonList(new SimpleGrantedAuthority("silver"));
            default:
                return Collections.emptyList();  // No roles for unknown IDs
        }
    }
}
