package com.build.ecommerce.domain.product.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Category {
    FASHION("fashion"),
    BEAUTY("beauty"),
    FOOD("food"),
    DIGITAL("digital"),
    TOY("toy");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public static Category getByValue(String category) {
        return Arrays.stream(Category.values())
                .filter(val -> val.getValue().equals(category))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product Category is not found."));
    }
}
