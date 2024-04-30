package com.jmarr.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmarr.users.controllers.response.RoleResponse;
import com.jmarr.users.controllers.response.UserRolesResponse;
import com.jmarr.users.controllers.request.UserRolesRequest;
import com.jmarr.users.services.ResourceNotFoundException;
import com.jmarr.users.services.RoleServiceI;
import com.jmarr.users.services.UserServiceI;
import com.jmarr.users.services.UserRolesServiceI;


@RestController
@RequestMapping("/users")
public class UserRolesController {
    @Autowired
    private UserServiceI userService;

    @Autowired
    private UserRolesServiceI userRolesService;

    @Autowired
    private RoleServiceI roleService;

    @GetMapping("/{userId}/roles")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<UserRolesResponse> getById(@PathVariable Long userId) {
        UserRolesResponse userRoles = userRolesService.getByUserId(userId);
        return ResponseEntity.ok(userRoles);
    }

    @PostMapping("/{userId}/roles/{roleName}")
    @PreAuthorize("hasAuthority('create')")
    public ResponseEntity<UserRolesResponse> save(@PathVariable Long userId, @PathVariable String roleName) {
        userService.getById(userId);
        RoleResponse role = roleService.getByName(roleName);
        UserRolesResponse response = userRolesService.getById(userId, role.getId());
        if ( response == null )
            response = userRolesService.save(new UserRolesRequest(userId, role.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{userId}/roles/{roleName}")
    @PreAuthorize("hasAuthority('delete')")
    public ResponseEntity<Void> delete(@PathVariable Long userId, @PathVariable String roleName)
        throws RuntimeException
    {
        userService.getById(userId);
        RoleResponse role = roleService.getByName(roleName);
        UserRolesResponse response = userRolesService.getById(userId, role.getId());
        if ( response == null )
            throw new ResourceNotFoundException(String.format("No existe el usuario(%d) con rol(%s)",
                                                              userId, roleName));
        if ( userRolesService.deleteById(userId, role.getId()) )
            return ResponseEntity.status(HttpStatus.OK).build();
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
