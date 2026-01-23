package com.cu2mber.recruitmentservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record RecruitmentUpdateRequest(
        Long eventNo,

        Integer localNo,

        @JsonProperty("title")
        String recruitTitle,

        @JsonProperty("departDate")
        LocalDate recruitDepartDate,

        @JsonProperty("endDate")
        LocalDateTime recruitEndDate,

        @JsonProperty("departTime")
        LocalTime recruitDepartTime,

        @JsonProperty("returnTime")
        LocalTime recruitReturnTime,

        @Positive
        @JsonProperty("amount")
        Long recruitmentPrice,

        @Min(1)
        @JsonProperty("minHeadcount")
        int recruitMinHeadcount,

        @JsonProperty("maxHeadcount")
        int recruitMaxHeadcount
) {
}
