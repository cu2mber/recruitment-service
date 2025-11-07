package com.cu2mber.recruitmentservice.dto;

import com.cu2mber.recruitmentservice.vo.StatusType;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StatusResponse {
    StatusType code;
    String label;

    public static StatusResponse from(StatusType status) {
        return new StatusResponse(status, status.getDescription());
    }

}
