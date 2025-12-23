package com.cu2mber.recruitmentservice.service.impl;

import com.cu2mber.recruitmentservice.common.exception.RecruitmentErrorCode;
import com.cu2mber.recruitmentservice.common.exception.RecruitmentException;
import com.cu2mber.recruitmentservice.domain.entity.Recruitment;
import com.cu2mber.recruitmentservice.dto.*;
import com.cu2mber.recruitmentservice.dto.command.RecruitmentCreateCommand;
import com.cu2mber.recruitmentservice.dto.command.RecruitmentUpdateCommand;
import com.cu2mber.recruitmentservice.dto.request.RecruitmentDeleteRequest;
import com.cu2mber.recruitmentservice.dto.request.RecruitmentUpdateStateRequest;
import com.cu2mber.recruitmentservice.dto.response.InternalRecruitmentSummaryResponse;
import com.cu2mber.recruitmentservice.dto.response.RecruitmentListResponse;
import com.cu2mber.recruitmentservice.dto.response.RecruitmentResponse;
import com.cu2mber.recruitmentservice.repository.RecruitmentRepository;
import com.cu2mber.recruitmentservice.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class RecruitmentServiceImpl implements RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

//    private final EventClient eventClient;

    @Override
    public RecruitmentResponse create(RecruitmentCreateCommand command) {

        // 모집 생성
        Recruitment recruitment = Recruitment.ofNewRecruitment(
                command.eventNo(),
                command.memberLocalNo(),
                command.recruitTitle(),
                command.recruitDepartDate(),
                command.recruitEndDate() == null ? LocalDateTime.now().minusDays(1) : command.recruitEndDate(),
                command.recruitDepartTime(),
                command.recruitReturnTime(),
                command.recruitAmount(),
                command.recruitMinHeadcount(),
                command.recruitMaxHeadcount()
        );

        recruitmentRepository.save(recruitment);
        return getRecruitmentResponse(recruitment);
    }

    @Override
    public RecruitmentResponse update(RecruitmentUpdateCommand command) {

        Recruitment recruitment = recruitmentRepository.findById(command.recruitmentNo())
                .orElseThrow(() -> new RecruitmentException(RecruitmentErrorCode.NOT_FOUND));

        recruitment.update(
            command.recruitTitle(),
            command.recruitDepartDate(),
            command.recruitEndDate(),
            command.recruitDepartTime(),
            command.recruitReturnTime(),
            command.recruitAmount(),
            command.recruitMinHeadcount(),
            command.recruitMaxHeadcount()
        );


        return getRecruitmentResponse(recruitment);
    }

    @Override
    public RecruitmentResponse updateState(Long recruitmentNo, RecruitmentUpdateStateRequest request) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentNo)
                .orElseThrow(() -> new RecruitmentException(RecruitmentErrorCode.NOT_FOUND));

        recruitment.updateState(recruitment.getRecruitState());

        return getRecruitmentResponse(recruitment);
    }

    @Transactional(readOnly = true)
    @Override
    public RecruitmentResponse getRecruitment(Long recruitmentNo) {

        // todo: 모집 지역 이름 찾기

        // todo: 행사 이름 찾기

        // todo: 작성자 이름 찾기

        return recruitmentRepository.findRecruit(recruitmentNo)
                .orElseThrow(() -> new RecruitmentException(RecruitmentErrorCode.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<RecruitmentListResponse> getRecruitPage(SearchParam searchParam, Pageable pageable) {
        return recruitmentRepository.findRecruitPage(searchParam, pageable);
    }

    @Override
    public void delete(Long recruitmentNo, RecruitmentDeleteRequest request) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentNo)
                .orElseThrow(() -> new RecruitmentException(RecruitmentErrorCode.NOT_FOUND));

        recruitment.delete();
    }

    @Transactional(readOnly = true)
    @Override
    public InternalRecruitmentSummaryResponse getRecruitmentSummary(Long recruitmentNo) {
        return recruitmentRepository.findRecruitSummary(recruitmentNo)
                .orElseThrow(() -> new RecruitmentException(RecruitmentErrorCode.NOT_FOUND));
    }


    private RecruitmentResponse getRecruitmentResponse(Recruitment recruitment) {
        return new RecruitmentResponse(
                recruitment.getRecruitmentNo(),
                null,
                null,
                null,
                recruitment.getRecruitTitle(),
                recruitment.getRecruitDepartDate(),
                recruitment.getRecruitEndDate(),
                recruitment.getRecruitDepartTime(),
                recruitment.getRecruitReturnTime(),
                recruitment.getRecruitAmount(),
                recruitment.getRecruitMinHeadcount(),
                recruitment.getRecruitMaxHeadcount(),
                recruitment.getRecruitParticipantCount(),
                recruitment.getRecruitState().getDescription(),
                recruitment.getCreatedAt()
        );
    }
}
