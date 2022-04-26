package com.kovalenko.deployer.backend.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserRoleModel {

    private Integer userRoleId;
    private Integer userId;
    private Integer roleId;
}
