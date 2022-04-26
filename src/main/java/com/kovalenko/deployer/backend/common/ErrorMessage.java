package com.kovalenko.deployer.backend.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {

    ENTITY_NOT_FOUND("error.entity.not.found");

    private String key;
}
