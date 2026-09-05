package com.cu2mber.recruitmentservice.service.impl;

import com.cu2mber.recruitmentservice.common.exception.RecruitmentErrorCode;
import com.cu2mber.recruitmentservice.common.exception.RecruitmentException;
import com.cu2mber.recruitmentservice.domain.entity.Recruitment;
import com.cu2mber.recruitmentservice.domain.vo.StatusType;
import com.cu2mber.recruitmentservice.dto.*;
import com.cu2mber.recruitmentservice.dto.command.RecruitmentCreateCommand;
import com.cu2mber.recruitmentservice.dto.command.RecruitmentDeleteCommand;
import com.cu2mber.recruitmentservice.dto.command.RecruitmentUpdateCommand;
import com.cu2mber.recruitmentservice.dto.command.RecruitmentUpdateStateCommand;
import com.cu2mber.recruitmentservice.dto.response.*;
import com.cu2mber.recruitmentservice.repository.RecruitmentRepository;
import com.cu2mber.recruitmentservice.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class RecruitmentServiceImpl implements RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

//    private final EventClient eventClient;

    @Override
    public RecruitmentResponse create(RecruitmentCreateCommand command) {

        if(!"ROLE_GOV".equals(command.role())) {
            throw new RecruitmentException(RecruitmentErrorCode.FORBIDDEN);
        }

        // 모집 생성
        Recruitment recruitment = Recruitment.ofNewRecruitment(
                command.eventNo(),
                command.memberNo(),
                command.recruitTitle(),
                command.recruitDepartDate(),
                command.recruitEndDate() == null ? LocalDateTime.now().minusDays(1) : command.recruitEndDate(),
                command.recruitDepartTime(),
                command.recruitReturnTime(),
                command.recruitmentPrice(),
                command.recruitMinHeadcount(),
                command.recruitMaxHeadcount()
        );

        recruitmentRepository.save(recruitment);
        return getRecruitmentResponse(recruitment);
    }

    @Override
    public RecruitmentResponse update(RecruitmentUpdateCommand command) {
        Long recruitmentNo = command.recruitmentNo();

        Recruitment recruitment = recruitmentRepository.findById(recruitmentNo)
                .orElseThrow(() -> new RecruitmentException(RecruitmentErrorCode.NOT_FOUND));

        // 권한 체크
        checkRole(command.role(), command.memberLocalNo(), recruitmentNo);

        recruitment.update(
            command.recruitTitle(),
            command.recruitDepartDate(),
            command.recruitEndDate(),
            command.recruitDepartTime(),
            command.recruitReturnTime(),
            command.recruitmentPrice(),
            command.recruitMinHeadcount(),
            command.recruitMaxHeadcount()
        );

        return getRecruitmentResponse(recruitment);
    }

    @Override
    public RecruitmentUpdateStateResponse updateState(RecruitmentUpdateStateCommand command) {
        Long recruitmentNo = command.recruitmentNo();

        Recruitment recruitment = recruitmentRepository.findById(recruitmentNo)
                .orElseThrow(() -> new RecruitmentException(RecruitmentErrorCode.NOT_FOUND));

        // 권한 체크
        checkRole(command.role(), command.memberNo(), recruitmentNo);

        if (!recruitment.getRecruitmentState().canTransitionTo(command.statusType())) {
            throw new RecruitmentException(RecruitmentErrorCode.INVALID_STATUS_TRANSITION);
        }

        recruitment.updateState(command.statusType());

        return new RecruitmentUpdateStateResponse(
                recruitmentNo,
                recruitment.getRecruitmentTitle(),
                command.statusType()
        );
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
    public List<StatusResponse> getStatus() {
        return StatusResponse.from(StatusType.values());
    }

    @Override
    public void delete(RecruitmentDeleteCommand command) {
        Long recruitmentNo = command.recruitmentNo();
        Recruitment recruitment = recruitmentRepository.findById(recruitmentNo)
                .orElseThrow(() -> new RecruitmentException(RecruitmentErrorCode.NOT_FOUND));

        // 권한 체크
        checkRole(command.role(), command.memberNo(), recruitmentNo);

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
                recruitment.getMemberLocalNo(),
                null,
                null,
                recruitment.getRecruitmentTitle(),
                recruitment.getRecruitmentDepartDate(),
                recruitment.getRecruitmentEndDate(),
                recruitment.getRecruitmentDepartTime(),
                recruitment.getRecruitmentReturnTime(),
                recruitment.getRecruitmentPrice(),
                recruitment.getRecruitmentMinHeadcount(),
                recruitment.getRecruitmentMaxHeadcount(),
                recruitment.getRecruitmentParticipantCount(),
                recruitment.getRecruitmentState().getDescription(),
                recruitment.getCreatedAt()
        );
    }

    private void checkRole(String role, Long memberNo, Long recruitNo) {
        // TODO 관리자 권한 수정: 무조건 되게 하면 안 될듯
        if("ROLE_ADMIN".equals(role)) {
            return;
        }

        if("ROLE_GOV".equals(role)) {
            RecruitmentResponse recruitment = recruitmentRepository.findRecruit(recruitNo)
                    .orElseThrow(() -> new RecruitmentException(RecruitmentErrorCode.NOT_FOUND));

            if(!Objects.equals(recruitment.getRecruitmentMemberNo(), memberNo)) {
                throw new RecruitmentException(RecruitmentErrorCode.FORBIDDEN);
            }
        }
    }
}
