package com.devxminds.donpipe.resource;

import com.devxminds.donpipe.dto.UserDto;
import com.devxminds.donpipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto  = userService.findUserById(id);
        if(userDto != null) {
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/createUser")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        UserDto newUser = userService.newUser(userDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(newUser);
    }
}
