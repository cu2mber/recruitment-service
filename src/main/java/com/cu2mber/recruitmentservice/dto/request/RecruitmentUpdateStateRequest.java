package com.cu2mber.recruitmentservice.dto.request;

import com.cu2mber.recruitmentservice.vo.StatusType;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RecruitmentUpdateStateRequest {

    StatusType statusType;

}
