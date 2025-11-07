package com.cu2mber.recruitmentservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RecruitmentRequest {

    Long eventNo;

    Long memberNo;

    int localNo;

    @NotNull(message = "출발일은 반드시 입력해주세요.")
    @JsonProperty("departDate")
    LocalDate recruitDepartDate;

    @JsonProperty("endDate")
    LocalDate recruitEndDate;

    @NotNull(message = "출발 시간은 반드시 입력해주세요.")
    @JsonProperty("departTime")
    LocalTime recruitDepartTime;

    @NotNull(message = "출발 시간은 반드시 입력해주세요.")
    @JsonProperty("returnTime")
    LocalTime recruitReturnTime;

    @NotNull(message = "비용은 반드시 입력해주세요.")
    @Positive
    @JsonProperty("amount")
    int recruitAmount;

    @NotNull(message = "최소 인원은 반드시 입력해주세요.(1명 이상)")
    @Min(1)
    @JsonProperty("minHeadcount")
    int recruitMinHeadcount;

    @NotNull(message = "최대 인원은 반드시 입력해주세요.")
    @JsonProperty("maxHeadcount")
    int recruitMaxHeadcount;

}
