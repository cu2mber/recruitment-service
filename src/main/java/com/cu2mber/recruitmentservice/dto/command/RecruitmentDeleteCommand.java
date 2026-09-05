package com.cu2mber.recruitmentservice.dto.command;

public record RecruitmentDeleteCommand(
        Long recruitmentNo,

        Long eventNo,

        String role,

        Long memberNo,

        Long localNo
) {


}
