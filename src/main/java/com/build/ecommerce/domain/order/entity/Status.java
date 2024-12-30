package com.build.ecommerce.domain.order.entity;

public enum Status {
    COMPLET("성공"),
    FAIL("실패"),
    CANCLE("취소");

    Status(String value) {
        this.value = value;
    }

    private String value;
}
