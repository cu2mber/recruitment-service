package com.cu2mber.recruitmentservice.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RecruitmentErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INVALID_STATUS_TRANSITION(HttpStatus.BAD_REQUEST, "허용되지 않은 모집 상태 변경입니다."),

    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다."),

    NOT_FOUND(HttpStatus.NOT_FOUND, "해당 모집을 찾을 수 없습니다. : [%s]"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Recruitment 서비스에서 오류가 발생했습니다."),
    EVENT_SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "Event 서비스와 통신 중 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String message;
}
