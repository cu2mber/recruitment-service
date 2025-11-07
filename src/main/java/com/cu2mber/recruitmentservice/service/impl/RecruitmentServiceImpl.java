package com.cu2mber.recruitmentservice.service.impl;

import com.cu2mber.recruitmentservice.common.exception.RecruitmentErrorCode;
import com.cu2mber.recruitmentservice.common.exception.RecruitmentException;
import com.cu2mber.recruitmentservice.domain.Recruitment;
import com.cu2mber.recruitmentservice.dto.*;
import com.cu2mber.recruitmentservice.dto.request.RecruitmentDeleteRequest;
import com.cu2mber.recruitmentservice.dto.request.RecruitmentRequest;
import com.cu2mber.recruitmentservice.dto.request.RecruitmentUpdateStateRequest;
import com.cu2mber.recruitmentservice.dto.response.RecruitmentListResponse;
import com.cu2mber.recruitmentservice.dto.response.RecruitmentResponse;
import com.cu2mber.recruitmentservice.repository.RecruitmentRepository;
import com.cu2mber.recruitmentservice.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class RecruitmentServiceImpl implements RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    @Override
    public RecruitmentResponse create(RecruitmentRequest request) {

        Recruitment recruitment = Recruitment.ofNewRecruitment(
                request.getEventNo(),
                request.getMemberNo(),
                request.getLocalNo(),
                request.getRecruitDepartDate(),
                request.getRecruitEndDate() == null ? LocalDate.now().minusDays(1) : request.getRecruitEndDate(),
                request.getRecruitDepartTime(),
                request.getRecruitReturnTime(),
                request.getRecruitAmount(),
                request.getRecruitMinHeadcount(),
                request.getRecruitMaxHeadcount());

        recruitmentRepository.save(recruitment);
        return getRecruitmentResponse(recruitment);
    }

    @Override
    public RecruitmentResponse update(Long recruitmentNo, RecruitmentRequest request) {

        Recruitment recruitment = recruitmentRepository.findById(recruitmentNo)
                .orElseThrow(() -> new RecruitmentException(RecruitmentErrorCode.NOT_FOUND));

        recruitment.update(
                recruitment.getRecruitDepartTime(),
                recruitment.getRecruitReturnTime(),
                recruitment.getRecruitAmount(),
                recruitment.getRecruitMinHeadcount(),
                recruitment.getRecruitMaxHeadcount()
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
        return recruitmentRepository.findRecruit(recruitmentNo)
                .orElseThrow(() -> new RecruitmentException(RecruitmentErrorCode.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<RecruitmentListResponse> getRecruitPage(SearchParam searchParam, Pageable pageable) {

        // todo: 모집 지역 이름 찾기

        // todo: 행사 이름 찾기

        // todo: 작성자 이름 찾기


        return null;
    }

    @Override
    public void delete(Long recruitmentNo, RecruitmentDeleteRequest request) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentNo)
                .orElseThrow(() -> new RecruitmentException(RecruitmentErrorCode.NOT_FOUND));

        recruitment.delete();
    }


    private RecruitmentResponse getRecruitmentResponse(Recruitment recruitment) {
        return new RecruitmentResponse(
                recruitment.getRecruitmentNo(),
                null,
                null,
                null,
                recruitment.getRecruitDepartDate(),
                recruitment.getRecruitEndDate(),
                recruitment.getRecruitDepartTime(),
                recruitment.getRecruitReturnTime(),
                recruitment.getRecruitAmount(),
                recruitment.getRecruitMinHeadcount(),
                recruitment.getRecruitMaxHeadcount(),
                recruitment.getRecruitParticipantCount(),
                recruitment.getRecruitState().getDescription());
    }
}
