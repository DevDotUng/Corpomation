package com.kma.corpomation.domain.user.dto;

public enum UserRole {

    USER("USER"),
    ADMIN("ADMIN");

    String userRole;

    UserRole(String userRole) {
        this. userRole = userRole;
    }

    public String value() {
        return userRole;
    }
}