package com.cu2mber.recruitmentservice.dto;

import com.cu2mber.recruitmentservice.vo.StateType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
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

    @QueryProjection
    public RecruitmentResponse(Long recruitmentNo, LocalDate recruitDepartDate, LocalDate recruitEndDate, LocalTime recruitDepartTime, LocalTime recruitReturnTime, int recruitAmount, int recruitMinHeadcount, int recruitMaxHeadcount, int recruitParticipantCount, StateType recruitState) {
        this.recruitmentNo = recruitmentNo;
        this.recruitDepartDate = recruitDepartDate;
        this.recruitEndDate = recruitEndDate;
        this.recruitDepartTime = recruitDepartTime;
        this.recruitReturnTime = recruitReturnTime;
        this.recruitAmount = recruitAmount;
        this.recruitMinHeadcount = recruitMinHeadcount;
        this.recruitMaxHeadcount = recruitMaxHeadcount;
        this.recruitParticipantCount = recruitParticipantCount;
        this.recruitState = recruitState.getDescription();
    }
}
