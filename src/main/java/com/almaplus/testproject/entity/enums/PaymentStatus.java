package com.almaplus.testproject.entity.enums;

public enum PaymentStatus {
    PENDING,
    CONFIRMED,
    CANCELED;

    public boolean canConfirm() {
        return this == PENDING;
    }

    public boolean canCancel() {
        return this == PENDING;
    }
}
