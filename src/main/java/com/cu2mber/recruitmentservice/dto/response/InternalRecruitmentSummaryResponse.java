package com.cu2mber.recruitmentservice.dto.response;

import com.cu2mber.recruitmentservice.domain.vo.StatusType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class InternalRecruitmentSummaryResponse {

    private Long recruitmentNo;

    private StatusType recruitmentStatus;

    private Long memberLocalNo;

    private Long eventNo;

    private String recruitmentTitle;

    private Long recruitmentPrice;

    private LocalTime recruitmentDepartTime;

    private LocalTime recruitmentReturnTime;

    @QueryProjection
    public InternalRecruitmentSummaryResponse(Long recruitmentNo, StatusType recruitmentStatus, Long memberLocalNo, Long eventNo, String recruitmentTitle, Long recruitmentPrice, LocalTime recruitmentDepartTime, LocalTime recruitmentReturnTime) {
        this.recruitmentNo = recruitmentNo;
        this.recruitmentStatus = recruitmentStatus;
        this.memberLocalNo = memberLocalNo;
        this.eventNo = eventNo;
        this.recruitmentTitle = recruitmentTitle;
        this.recruitmentPrice = recruitmentPrice;
        this.recruitmentDepartTime = recruitmentDepartTime;
        this.recruitmentReturnTime = recruitmentReturnTime;
    }
}
