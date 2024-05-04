package com.devxminds.donpipe.resource;

import com.devxminds.donpipe.dto.UserDto;
import com.devxminds.donpipe.entidade.User;
import com.devxminds.donpipe.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.findUserById(id);
        if (userDto != null) {
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> registerUser(UserDto userDto) {
        UserDto newUser = userService.newUser(userDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(newUser);
    }
    @GetMapping("/auth")
    public String authenticateUser(UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User userFromDb = modelMapper.map(userService.getUserByEmail(userDto.getEmail()), User.class);
        if(encoder.matches(userDto.getSenha(), userFromDb.getSenha())){
            return "login";
        } else {
            return "redirect:/login";
        }

    }

}