package com.cu2mber.recruitmentservice.common.exception;

import lombok.Getter;

@Getter
public class RecruitmentException extends BusinessException{

    private final RecruitmentErrorCode errorCode;

    public RecruitmentException(RecruitmentErrorCode errorCode) {
        super(errorCode.getStatus().name(), errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public RecruitmentException(RecruitmentErrorCode errorCode, Object... args) {
        super(errorCode.getStatus().name(), errorCode.getMessage(), args);
        this.errorCode = errorCode;
    }
}
