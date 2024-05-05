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

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    public User getUserByEmail(String email) {
        return modelMapper.map(userService.getUserByEmail(email), User.class);
    }

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
  
    @PostMapping("/changePermission")
    public ResponseEntity<UserDto> changePermission(@RequestBody UserDto userDto, Long permissionId) {
        Optional<User> userEncontrado = userService.changePermission(userDto.getId(), permissionId);
        if(userEncontrado.isPresent()) {
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
