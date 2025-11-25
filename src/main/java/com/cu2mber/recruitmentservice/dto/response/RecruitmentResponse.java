package com.cu2mber.recruitmentservice.dto.response;

import com.cu2mber.recruitmentservice.domain.vo.StatusType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RecruitmentResponse {

    @JsonProperty("no")
    Long recruitmentNo;

    @Setter
    String eventName;

    @JsonProperty("author")
    @Setter
    String memberName;

    @Setter
    String localName;

    @JsonProperty("title")
    String recruitTitle;

    @JsonProperty("departDate")
    LocalDate recruitDepartDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty("endDate")
    LocalDateTime recruitEndDate;

    @JsonProperty("departTime")
    LocalTime recruitDepartTime;

    @JsonProperty("returnTime")
    LocalTime recruitReturnTime;

    @JsonProperty("amount")
    BigDecimal recruitAmount;

    @JsonProperty("minHeadcount")
    int recruitMinHeadcount;

    @JsonProperty("maxHeadcount")
    int recruitMaxHeadcount;

    @JsonProperty("participantCount")
    int recruitParticipantCount = 0;

    @JsonProperty("state")
    String recruitState;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @EqualsAndHashCode.Exclude
    LocalDateTime createdAt;

    @QueryProjection
    public RecruitmentResponse(Long recruitmentNo, String recruitTitle, LocalDate recruitDepartDate, LocalDateTime recruitEndDate, LocalTime recruitDepartTime, LocalTime recruitReturnTime, BigDecimal recruitAmount, int recruitMinHeadcount, int recruitMaxHeadcount, int recruitParticipantCount, StatusType recruitState, LocalDateTime createdAt) {
        this.recruitmentNo = recruitmentNo;
        this.recruitTitle = recruitTitle;
        this.recruitDepartDate = recruitDepartDate;
        this.recruitEndDate = recruitEndDate;
        this.recruitDepartTime = recruitDepartTime;
        this.recruitReturnTime = recruitReturnTime;
        this.recruitAmount = recruitAmount;
        this.recruitMinHeadcount = recruitMinHeadcount;
        this.recruitMaxHeadcount = recruitMaxHeadcount;
        this.recruitParticipantCount = recruitParticipantCount;
        this.recruitState = recruitState.getDescription();
        this.createdAt = createdAt;
    }
}
