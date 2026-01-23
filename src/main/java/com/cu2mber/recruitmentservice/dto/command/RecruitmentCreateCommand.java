package com.cu2mber.recruitmentservice.dto.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record RecruitmentCreateCommand(
        Long eventNo,

        Long memberLocalNo,

        String recruitTitle,

        LocalDate recruitDepartDate,

        LocalDateTime recruitEndDate,

        LocalTime recruitDepartTime,

        LocalTime recruitReturnTime,

        Long recruitmentPrice,

        int recruitMinHeadcount,

        int recruitMaxHeadcount
) {
}
