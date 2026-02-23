package com.cu2mber.recruitmentservice.common.event;

import com.cu2mber.recruitmentservice.common.exception.RecruitmentErrorCode;
import com.cu2mber.recruitmentservice.common.exception.RecruitmentException;
import com.cu2mber.recruitmentservice.domain.entity.Recruitment;
import com.cu2mber.recruitmentservice.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecruitmentEventListener {

    private final RecruitmentRepository recruitmentRepository;

    @RabbitListener(queues = "${message.queue.payment}")
    public void handleIncreaseEvent(RecruitmentIncreaseRequest request) {
        Recruitment recruitment = recruitmentRepository.findById(request.recruitmentNo())
                .orElseThrow(() -> new RecruitmentException(RecruitmentErrorCode.NOT_FOUND));

        recruitment.increaseParticipantCount(request.participantCount());
        recruitmentRepository.save(recruitment);
    }

    @RabbitListener()
    public void handleDecreaseEvent(RecruitmentDecreaseEvent event) {
        Recruitment recruitment = recruitmentRepository.findById(event.getRecruitmentNo())
                .orElseThrow(() -> new RecruitmentException(RecruitmentErrorCode.NOT_FOUND));

        recruitment.decreaseParticipantCount(event.getParticipant());
    }
}
