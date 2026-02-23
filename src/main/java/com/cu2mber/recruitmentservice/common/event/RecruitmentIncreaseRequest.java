package com.cu2mber.recruitmentservice.common.event;

import java.util.UUID;

public record RecruitmentIncreaseRequest(
        Long memberNo,

        Long recruitmentNo,

        String recruitTitle,

        Integer participantCount,

        String paymentKey,

        String idempotencyKey,

        UUID orderId
) {

}
