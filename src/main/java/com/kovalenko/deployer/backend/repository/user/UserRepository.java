package com.kovalenko.deployer.backend.repository.user;

import com.kovalenko.deployer.backend.model.user.UserModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRepository {

    @Insert("INSERT INTO users "  +
            " (login, \"password\", enabled, created_at, first_name, last_name) " +
            " VALUES(#{login}, #{password}, #{enabled}, #{createdAt}, #{firstName}, #{lastName})")
    void insertUser(UserModel userModel);

    @Results(id = "userMap", value = {
            @Result(property = "userId", column = "user_"),
            @Result(property = "login", column = "login"),
            @Result(property = "password", column = "password"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "roles", column = "user_", many = @Many(select = "com.kovalenko.deployer.backend.repository.user.RoleRepository.selectRolesByUser"))
    })
    @Select("SELECT user_, login, password, enabled, created_at, first_name, last_name " +
            "FROM users " +
            "WHERE login =#{login}")
    UserModel findUserByLogin(@Param(value = "login") String login);
}
