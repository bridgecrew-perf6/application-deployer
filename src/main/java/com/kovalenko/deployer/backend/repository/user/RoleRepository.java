package com.kovalenko.deployer.backend.repository.user;

import com.kovalenko.deployer.backend.model.user.Role;
import com.kovalenko.deployer.backend.model.user.RoleModel;
import com.kovalenko.deployer.backend.model.user.UserModel;
import com.kovalenko.deployer.backend.model.user.UserRoleModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface RoleRepository {

    @Results(id = "roleMap", value = {
            @Result(property = "roleId", column = "role_"),
            @Result(property = "role", column = "role")
    })
    @Select("SELECT role_, role FROM roles WHERE role = #{role}::role_enum")
    RoleModel findRoleByName(@Param(value = "role") Role role);

    @Select("SELECT role_, role FROM roles")
    @ResultMap("roleMap")
    Set<RoleModel> findAllRoles();

    @Select("SELECT r.role_, r.role " +
            "FROM roles r " +
            "INNER JOIN user_role ur ON r.role_ = ur.role_ " +
            "WHERE ur.user_ = #{userId}")
    @ResultMap("roleMap")
    Set<RoleModel> findRolesByUser(@Param(value = "userId") Integer userId);

    @Insert("INSERT INTO user_role " +
            "(user_, role_) " +
            "VALUES(#{userId}, #{roleId})")
    void insertUserRole(UserRoleModel userRole);
}
