package com.jmarr.users.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users_roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(UserRolesId.class)
public class UserRoles {
    @Id
    @Column(name="user_id")
    private Long userId;

    @Id
    @Column(name="role_id")
    private Integer roleId;
}
