package com.kovalenko.deployer.backend.service.user;

import com.kovalenko.deployer.backend.model.user.Role;
import com.kovalenko.deployer.backend.model.user.RoleModel;
import com.kovalenko.deployer.backend.model.user.UserRoleModel;

import java.util.Optional;

public interface RoleService {

    Optional<RoleModel> findRoleByName(Role role);
    RoleModel findRoleByNameOrThrow(Role role);
    void saveUserRole(UserRoleModel userRole);
}
