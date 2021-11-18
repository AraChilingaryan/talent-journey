package com.disqo.authentication_service.rest;

import com.disqo.authentication_service.converter.UserConverter;

import com.disqo.authentication_service.persistance.model.User;
import com.disqo.authentication_service.rest.auth.model.AuthRequest;
import com.disqo.authentication_service.rest.dto.UserDTO;
import com.disqo.authentication_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @Autowired
    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserDTO> update(@PathVariable String username, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userConverter.convert(userService.update(username, userDTO)));
    }

    @GetMapping
    public List<? extends UserDTO> getAll() {
        return userConverter.bulkConvert(userService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<UserDTO> search(@RequestParam("username") String username) {
        return ResponseEntity.ok(userConverter.convert(userService.findByUsername(username)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userConverter.convert(userService.findById(id)));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userdto) {
       return ResponseEntity.ok(userConverter.convert(userService.register(userdto)));


    }



}
