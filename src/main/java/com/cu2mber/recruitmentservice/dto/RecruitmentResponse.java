package com.cu2mber.recruitmentservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RecruitmentResponse {

    Long recruitmentNo;

    @Setter
    String eventName;

    @Setter
    String memberName;

    @Setter
    String localName;

    @JsonProperty("departDate")
    LocalDate recruitDepartDate;

    @JsonProperty("endDate")
    LocalDate recruitEndDate;

    @JsonProperty("departTime")
    LocalTime recruitDepartTime;

    @JsonProperty("returnTime")
    LocalTime recruitReturnTime;

    @JsonProperty("amount")
    int recruitAmount;

    @JsonProperty("minHeadcount")
    int recruitMinHeadcount;

    @JsonProperty("maxHeadcount")
    int recruitMaxHeadcount;

    @JsonProperty("participantCount")
    int recruitParticipantCount;

    @JsonProperty("state")
    String recruitState;
}
