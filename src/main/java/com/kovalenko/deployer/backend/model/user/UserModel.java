package com.kovalenko.deployer.backend.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserModel {

    private Integer userId;
    private String login;
    private String password;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private String firstName;
    private String lastName;
    private Set<RoleModel> roles;
}
