package com.devxminds.donpipe.security;

import com.devxminds.donpipe.dto.UserDto;
import com.devxminds.donpipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager managerAuthentication;

    @PostMapping("/caralhos")

    public ResponseEntity<?> makeLogin(@RequestParam("email") String email,@RequestParam("senha") String password){
        try {
            UserDto userDto = userService.getUserByEmail(email);
            if(userDto != null && passwordEncoder.matches(password, userDto.getSenha())) {
                List<GrantedAuthority> authorities = RoleConverter.getAuthorities(userDto.getPermissao().getId);
                var token = new UsernamePasswordAuthenticationToken(email, null, authorities);
                var Authentication = managerAuthentication.authenticate(token);

                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity bla(UserDto userDto){
        var token = new UsernamePasswordAuthenticationToken(userDto.getEmail(), passwordEncoder.encode(userDto.getSenha()));
        var authenticate =  managerAuthentication.authenticate(token);

        return ResponseEntity.ok().body(authenticate);
    }


}
