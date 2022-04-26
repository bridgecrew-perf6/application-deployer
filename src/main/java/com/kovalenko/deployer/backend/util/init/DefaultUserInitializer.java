package com.kovalenko.deployer.backend.util.init;

import com.kovalenko.deployer.backend.model.user.Role;
import com.kovalenko.deployer.backend.model.user.RoleModel;
import com.kovalenko.deployer.backend.model.user.UserModel;
import com.kovalenko.deployer.backend.model.user.UserRoleModel;
import com.kovalenko.deployer.backend.service.user.RoleService;
import com.kovalenko.deployer.backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Profile("!test")
@Component
@Transactional
@RequiredArgsConstructor
public class DefaultUserInitializer implements CommandLineRunner {

    private static final String DEFAULT_USER_NAME = "defaultUser";

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;
    private final MessageSourceAccessor messages;

    @Value("${application.config.default.username}")
    private String defaultUserLogin;
    @Value("${application.config.default.password}")
    private String defaultUserPassword;

    @Override
    public void run(String... args) throws Exception {
        Optional<UserModel> user = userService.findUserByLogin(defaultUserLogin);
        if (user.isEmpty()) {
            UserModel defaultUser = buildUser();
            userService.save(defaultUser);
            UserModel savedUser = userService.findUserByLoginOrThrow(defaultUserLogin);
            RoleModel role = roleService.findRoleByNameOrThrow(Role.DEFAULT_ADMIN);
            UserRoleModel userRole = buildUserRole(savedUser, role);
            roleService.saveUserRole(userRole);
        }
    }

    private UserRoleModel buildUserRole(UserModel savedUser, RoleModel role) {
        return UserRoleModel.builder()
            .userId(savedUser.getUserId())
            .roleId(role.getRoleId())
            .build();
    }

    private UserModel buildUser() {
        return UserModel.builder()
            .login(defaultUserLogin)
            .password(passwordEncoder.encode(defaultUserPassword))
            .enabled(true)
            .createdAt(LocalDateTime.now())
            .firstName(DEFAULT_USER_NAME)
            .lastName(DEFAULT_USER_NAME)
            .build();
    }
}
