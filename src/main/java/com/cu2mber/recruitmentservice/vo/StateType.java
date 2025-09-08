package com.cu2mber.recruitmentservice.vo;

import lombok.Getter;

@Getter
public enum StateType {
    OPEN(1, "모집"),
    CLOSED(2, "마감"),
    ENDED(3, "종료");

    private int code;
    private String description;

    StateType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static StateType fromCode(int code) {
        for (StateType state : StateType.values()) {
            if (state.getCode() == code) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + code);
    }

    public static StateType fromDescription(String description) {
        for (StateType state : StateType.values()) {
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
