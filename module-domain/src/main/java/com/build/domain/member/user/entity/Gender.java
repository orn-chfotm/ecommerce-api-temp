package com.build.domain.member.user.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Gender {
    MAN("M"), WOMAN("W");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public static Gender getByValue(String value) {
        return Arrays.stream(Gender.values())
                .filter(val -> val.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Gender value is not found"));
    }
}
