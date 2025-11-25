package com.cu2mber.recruitmentservice.dto.command;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record RecruitmentUpdateCommand(
        Long recruitmentNo,

        Long eventNo,

        Integer localNo,

        Long memberNo,

        String recruitTitle,

        LocalDate recruitDepartDate,

        LocalDateTime recruitEndDate,

        LocalTime recruitDepartTime,

        LocalTime recruitReturnTime,

        BigDecimal recruitAmount,

        int recruitMinHeadcount,

        int recruitMaxHeadcount
) {
}
