package com.cu2mber.recruitmentservice.dto;

import com.cu2mber.recruitmentservice.vo.StateType;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RecruitmentUpdateStateRequest {

    StateType stateType;

}
