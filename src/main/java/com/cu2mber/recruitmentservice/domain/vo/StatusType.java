package com.cu2mber.recruitmentservice.domain.vo;

import lombok.Getter;

@Getter
public enum StatusType {
    OPEN(1, "진행 중"),
    CLOSED(2, "마감"),
    ENDED(3, "종료");

    private int code;
    private String description;

    StatusType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static StatusType fromCode(int code) {
        for (StatusType state : StatusType.values()) {
            if (state.getCode() == code) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + code);
    }

    public static StatusType fromDescription(String description) {
        for (StatusType state : StatusType.values()) {
            if (state.getDescription().equals(description)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid description: " + description);
    }

    public boolean isOpen() {
        return this == OPEN;
    }

    public boolean isClosed() {
        return this == CLOSED;
    }

    public boolean isEnded() {
        return this == ENDED;
    }

}
