package com.build.ecommerce.domain.order.entity;

import java.util.List;

public enum Status {
    COMPLET("성공"),
    FAIL("실패"),
    CANCLE("취소"),
    RELEASE("출고"),
    DELIVERY("배송중");

    Status(String value) {
        this.value = value;
    }

    private String value;

    public static List<Status> getCancleable() {
        return List.of(COMPLET);
    }
}
