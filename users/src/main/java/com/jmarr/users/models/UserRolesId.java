package com.jmarr.users.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRolesId implements Serializable
{
    private Long userId;
    private Integer roleId;
}
