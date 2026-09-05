package com.cu2mber.recruitmentservice.dto.command;

import com.cu2mber.recruitmentservice.domain.vo.StatusType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record RecruitmentUpdateStateCommand(
        Long recruitmentNo,

        String role,

        Long memberNo,

        StatusType statusType
) {

}
