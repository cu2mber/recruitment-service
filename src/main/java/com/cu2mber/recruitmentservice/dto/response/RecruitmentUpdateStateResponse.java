package com.cu2mber.recruitmentservice.dto.response;

import com.cu2mber.recruitmentservice.domain.vo.StatusType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record RecruitmentUpdateStateResponse(
        @JsonProperty("no")
        Long recruitmentNo,

        @JsonProperty("title")
        String recruitmentTitle,

        @JsonProperty("status")
        StatusType statusType
) {

}
