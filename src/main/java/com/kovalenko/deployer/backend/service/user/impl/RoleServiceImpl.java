package com.kovalenko.deployer.backend.service.user.impl;

import com.kovalenko.deployer.backend.common.Entity;
import com.kovalenko.deployer.backend.common.ErrorMessage;
import com.kovalenko.deployer.backend.exception.ApplicationException;
import com.kovalenko.deployer.backend.model.user.Role;
import com.kovalenko.deployer.backend.model.user.RoleModel;
import com.kovalenko.deployer.backend.model.user.UserRoleModel;
import com.kovalenko.deployer.backend.repository.user.RoleRepository;
import com.kovalenko.deployer.backend.service.user.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final MessageSourceAccessor messages;

    @Override
    public Optional<RoleModel> findRoleByName(Role role) {
        return Optional.ofNullable(roleRepository.selectRoleByName(Role.DEFAULT_ADMIN));
    }

    @Override
    public RoleModel findRoleByNameOrThrow(Role role) {
        return findRoleByName(Role.DEFAULT_ADMIN)
            .orElseThrow(() -> new ApplicationException(messages
                .getMessage(ErrorMessage.ENTITY_NOT_FOUND.getKey(), new Object[]{Entity.ROLE.getName(), Role.DEFAULT_ADMIN})));
    }

    @Override
    public void saveUserRole(UserRoleModel userRole) {
        roleRepository.insertUserRole(userRole);
    }
}
