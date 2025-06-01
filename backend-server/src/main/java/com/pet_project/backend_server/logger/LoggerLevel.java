package com.pet_project.backend_server.logger;

import lombok.Getter;

@Getter
public enum LoggerLevel {

    INFO("info"),
    WARN("warn"),
    ERROR("error");

    private String value;

    LoggerLevel(String value) {
        this.value = value;
    }
}
