package com.kovalenko.deployer.backend.service.user.impl;

import com.kovalenko.deployer.backend.common.Entity;
import com.kovalenko.deployer.backend.common.ErrorMessage;
import com.kovalenko.deployer.backend.exception.ApplicationException;
import com.kovalenko.deployer.backend.model.user.UserModel;
import com.kovalenko.deployer.backend.repository.user.UserRepository;
import com.kovalenko.deployer.backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MessageSourceAccessor messages;

    @Override
    public Optional<UserModel> findUserByLogin(String login) {
        return Optional.ofNullable(userRepository.findUserByLogin(login));
    }

    @Override
    public UserModel findUserByLoginOrThrow(String login) {
        return findUserByLogin(login)
            .orElseThrow(() -> new ApplicationException(messages
                .getMessage(ErrorMessage.ENTITY_NOT_FOUND.getKey(), new Object[]{Entity.USER.getName(), login})));
    }

    @Override
    public void save(UserModel userModel) {
        userRepository.insertUser(userModel);
    }
}
