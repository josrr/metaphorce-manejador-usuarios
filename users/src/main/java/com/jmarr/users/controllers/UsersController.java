package com.jmarr.users.controllers;

import java.util.List;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmarr.users.controllers.request.UpdateUserRequest;
import com.jmarr.users.controllers.request.UserRequest;
import com.jmarr.users.controllers.response.UserResponse;
import com.jmarr.users.services.UserServiceI;

import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserServiceI userService;

    @GetMapping
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<List<UserResponse>> getAll(Authentication authentication) {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        UserResponse user = userService.getById(id);
        return user == null
            ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
            : ResponseEntity.ok(user);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create')")
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserRequest userRequest) {
        UserResponse response = userService.save(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('update')")
    public ResponseEntity<UserResponse> update(@RequestBody UpdateUserRequest userRequest) {
        UserResponse response = userService.update(userRequest);
        return response == null
            ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
            : ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('delete')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return userService.deleteById(id) == false
            ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
            : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
