package com.cu2mber.recruitmentservice.dto.response;

import com.cu2mber.recruitmentservice.domain.vo.StatusType;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StatusResponse {
    StatusType code;

    int value;

    String label;

    public static StatusResponse from(StatusType status) {
        return new StatusResponse(status, status.getCode(), status.getDescription());
    }

    public static List<StatusResponse> from(StatusType[] values) {
        return Arrays.stream(values)
                .map(v -> new StatusResponse(
                        v,
                        v.getCode(),
                        v.getDescription()
                ))
                .toList();
    }
}
