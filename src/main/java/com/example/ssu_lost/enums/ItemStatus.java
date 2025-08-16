package com.example.ssu_lost.enums;

public enum ItemStatus {
    FINDING, KEEPING, COMPLETE;

    public static ItemStatus fromString(String status) {
        return ItemStatus.valueOf(status.toUpperCase());
    }
}
