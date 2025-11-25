package com.cu2mber.recruitmentservice.dto.request;

import com.cu2mber.recruitmentservice.domain.vo.StatusType;

public record RecruitmentUpdateStateRequest (
        StatusType statusType
) {

}
