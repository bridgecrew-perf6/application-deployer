package com.kovalenko.deployer.backend.service.user;

import com.kovalenko.deployer.backend.model.user.UserModel;

import java.util.Optional;

public interface UserService {

    Optional<UserModel> findUserByLogin(String login);
    UserModel findUserByLoginOrThrow(String login);
    void save(UserModel userModel);
}
