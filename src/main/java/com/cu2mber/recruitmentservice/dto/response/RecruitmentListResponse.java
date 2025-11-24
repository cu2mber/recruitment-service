package com.cu2mber.recruitmentservice.dto.response;

import com.cu2mber.recruitmentservice.vo.StatusType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class RecruitmentListResponse {

    @JsonProperty("no")
    Long recruitmentNo;

    @JsonProperty("status")
    StatusResponse recruitmentStatus;

    @JsonIgnore
    Long localNo;

    @JsonIgnore
    Long eventNo;

    @Setter
    @JsonProperty("title")
    String recruitmentTitle;

    @Setter
    @JsonProperty("author")
    String recruitmentAuthor;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @EqualsAndHashCode.Exclude
    LocalDateTime createdAt;

    @JsonProperty("departDate")
    LocalDate recruitmentDepartDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty("endDate")
    @EqualsAndHashCode.Exclude
    LocalDateTime recruitmentEndDate;

    @QueryProjection
    public RecruitmentListResponse(Long recruitmentNo, StatusType recruitState, Long localNo, Long eventNo, String recruitmentTitle, String recruitmentAuthor, LocalDateTime createdAt, LocalDate recruitmentDepartDate, LocalDateTime recruitmentEndDate) {
        this.recruitmentNo = recruitmentNo;
        this.recruitmentStatus = StatusResponse.from(recruitState);
        this.localNo = localNo;
        this.eventNo = eventNo;
        this.recruitmentTitle = recruitmentTitle;
        this.recruitmentAuthor = recruitmentAuthor;
        this.recruitmentDepartDate = recruitmentDepartDate;
        this.createdAt = createdAt;
        this.recruitmentEndDate = recruitmentEndDate;
    }
}
