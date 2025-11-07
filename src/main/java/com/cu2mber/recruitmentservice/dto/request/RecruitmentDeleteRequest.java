package com.cu2mber.recruitmentservice.dto.request;

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
