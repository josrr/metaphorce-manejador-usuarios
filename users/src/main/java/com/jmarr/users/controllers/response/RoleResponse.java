package com.jmarr.users.controllers.response;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {
    private Integer id;
    private String name;
    private Collection<PrivilegeResponse> privileges;
}
