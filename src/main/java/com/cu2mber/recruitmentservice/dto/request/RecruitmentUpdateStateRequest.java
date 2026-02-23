package com.cu2mber.recruitmentservice.dto.request;

import com.cu2mber.recruitmentservice.domain.vo.StatusType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record RecruitmentUpdateStateRequest (
        @JsonProperty("status")
        StatusType statusType
) {

}
