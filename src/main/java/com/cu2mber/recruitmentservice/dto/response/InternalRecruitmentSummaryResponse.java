package com.cu2mber.recruitmentservice.dto.response;

import com.cu2mber.recruitmentservice.domain.vo.StatusType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

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

    private BigDecimal price;

    @QueryProjection
    public InternalRecruitmentSummaryResponse(Long recruitmentNo, StatusType recruitmentStatus, Long memberLocalNo, Long eventNo, String recruitmentTitle, BigDecimal price) {
        this.recruitmentNo = recruitmentNo;
        this.recruitmentStatus = recruitmentStatus;
        this.memberLocalNo = memberLocalNo;
        this.eventNo = eventNo;
        this.recruitmentTitle = recruitmentTitle;
        this.price = price;
    }
}
