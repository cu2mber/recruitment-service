package com.cu2mber.recruitmentservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record RecruitmentCreateRequest(
        Long eventNo,

        Integer localNo,

        @JsonProperty("title")
        String recruitTitle,

        @NotNull(message = "출발일은 반드시 입력해주세요.")
        @JsonProperty("departDate")
        LocalDate recruitDepartDate,

        @EqualsAndHashCode.Exclude
        @JsonProperty("endDate")
        LocalDateTime recruitEndDate,

        @EqualsAndHashCode.Exclude
        @NotNull(message = "출발 시간은 반드시 입력해주세요.")
        @JsonProperty("departTime")
        LocalTime recruitDepartTime,

        @EqualsAndHashCode.Exclude
        @NotNull(message = "출발 시간은 반드시 입력해주세요.")
        @JsonProperty("returnTime")
        LocalTime recruitReturnTime,

        @NotNull(message = "비용은 반드시 입력해주세요.")
        @Positive
        @JsonProperty("amount")
        BigDecimal recruitAmount,

        @NotNull(message = "최소 인원은 반드시 입력해주세요.(1명 이상)")
        @Min(1)
        @JsonProperty("minHeadcount")
        int recruitMinHeadcount,

        @NotNull(message = "최대 인원은 반드시 입력해주세요.")
        @JsonProperty("maxHeadcount")
        int recruitMaxHeadcount
) {
}
