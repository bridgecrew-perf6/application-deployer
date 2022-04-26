package com.kovalenko.deployer.backend.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Entity {

    USER("User"),
    ROLE("Role");

    private String name;
}
