package com.kovalenko.deployer.backend.util.init;

import com.kovalenko.deployer.backend.model.user.Role;
import com.kovalenko.deployer.backend.model.user.RoleModel;
import com.kovalenko.deployer.backend.model.user.UserModel;
import com.kovalenko.deployer.backend.model.user.UserRoleModel;
import com.kovalenko.deployer.backend.repository.user.RoleRepository;
import com.kovalenko.deployer.backend.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Profile("!test")
@Component
@Transactional
@RequiredArgsConstructor
public class DefaultUserInitializer implements CommandLineRunner {

    private static final String DEFAULT_USER_NAME = "defaultUser";

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Value("${application.config.default.username}")
    private String defaultUserLogin;
    @Value("${application.config.default.password}")
    private String defaultUserPassword;

    @Override
    public void run(String... args) throws Exception {
        UserModel user = userRepository.findUserByLogin(defaultUserLogin);
        if (Objects.isNull(user)) {
            UserModel defaultUser = UserModel.builder()
                    .login(defaultUserLogin)
                    .password(passwordEncoder.encode(defaultUserPassword))
                    .enabled(true)
                    .createdAt(LocalDateTime.now())
                    .firstName(DEFAULT_USER_NAME)
                    .lastName(DEFAULT_USER_NAME)
                    .build();
            userRepository.insertUser(defaultUser);
            UserModel savedUser = userRepository.findUserByLogin(defaultUserLogin);

            RoleModel role = roleRepository.findRoleByName(Role.DEFAULT_ADMIN);
            if (Objects.isNull(role)) {
                throw new RuntimeException("role not found");
            }
            UserRoleModel userRole = UserRoleModel.builder()
                    .userId(savedUser.getUserId())
                    .roleId(role.getRoleId())
                    .build();
            roleRepository.insertUserRole(userRole);
        }
    }
}
