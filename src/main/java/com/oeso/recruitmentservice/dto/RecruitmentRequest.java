package com.oeso.recruitmentservice.dto;

import lombok.*;

import java.time.LocalTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RecruitmentRequest {

    Long eventNo;

    Long memberNo;

    Long localNo;

    LocalTime departTime;

    LocalTime returnTime;

    int amount;

    int minHeadcount;

    int maxHeadcount;

}
