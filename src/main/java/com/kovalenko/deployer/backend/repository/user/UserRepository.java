package com.kovalenko.deployer.backend.repository.user;

import com.kovalenko.deployer.backend.model.user.UserModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRepository {

    @Insert("insert into users(name,email) values(#{name},#{email})")
    @SelectKey(statement="call identity()", keyProperty="user_", before=false, resultType=Integer.class)
    void insertUser(UserModel userModel);

    @Select("select id, name, email from users WHERE id=#{user_}")
    UserModel findUserByLogin(Integer userId);
}
