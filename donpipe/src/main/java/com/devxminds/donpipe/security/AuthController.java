package com.devxminds.donpipe.security;

import com.devxminds.donpipe.dto.UserDto;
import com.devxminds.donpipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
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
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")

    public ResponseEntity<?> makeLogin(@RequestParam("email") String email,@RequestParam("senha") String password){
        try {
            UserDto userDto = userService.getUserByEmail(email);
            if(userDto != null && passwordEncoder.matches(password, userDto.getSenha())) {
                List<GrantedAuthority> authorities = RoleConverter.getAuthorities(Math.toIntExact(userDto.getPermissao().getId()));
                var token = new UsernamePasswordAuthenticationToken(email, password, authorities);
                var Authentication = managerAuthentication.authenticate(token);
                String jwt = jwtUtil.generateToken(email);
                return ResponseEntity.ok(jwt);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/caralho")
    public ResponseEntity bla(UserDto userDto){
        var token = new UsernamePasswordAuthenticationToken(userDto.getEmail(), passwordEncoder.encode(userDto.getSenha()));
        var authenticate =  managerAuthentication.authenticate(token);

        return ResponseEntity.ok().body(authenticate);
    }


}
