package com.oeso.recruitmentservice.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RecruitmentDeleteRequest {

    long eventNo;

    long memberNo;

    long localNo;

}
