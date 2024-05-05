package com.devxminds.donpipe.security;

import com.devxminds.donpipe.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = new User(userService.getUserByEmail(username).getEmail(),userService.getUserByEmail(username).getSenha(), RoleConverter.getAuthorities(Math.toIntExact(userService.getUserByEmail(username).getPermissao().getId())));

        return userDetails;
    }
}
