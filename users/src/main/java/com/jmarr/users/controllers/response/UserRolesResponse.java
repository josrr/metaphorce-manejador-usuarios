package com.jmarr.users.controllers.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRolesResponse {
    private Long userId;
    private Integer roleId;
}
