package com.cu2mber.recruitmentservice.service;

import com.cu2mber.recruitmentservice.dto.*;
import com.cu2mber.recruitmentservice.dto.command.RecruitmentCreateCommand;
import com.cu2mber.recruitmentservice.dto.request.RecruitmentDeleteRequest;
import com.cu2mber.recruitmentservice.dto.request.RecruitmentCreateRequest;
import com.cu2mber.recruitmentservice.dto.request.RecruitmentUpdateStateRequest;
import com.cu2mber.recruitmentservice.dto.response.RecruitmentListResponse;
import com.cu2mber.recruitmentservice.dto.response.RecruitmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentService {

    RecruitmentResponse create(RecruitmentCreateCommand command);

    RecruitmentResponse update(Long recruitmentNo, RecruitmentCreateRequest request);

    RecruitmentResponse updateState(Long recruitmentNo, RecruitmentUpdateStateRequest request);

    RecruitmentResponse getRecruitment(Long recruitmentNo);

    Page<RecruitmentListResponse> getRecruitPage(SearchParam searchParam, Pageable pageable);

    void delete(Long recruitmentNo, RecruitmentDeleteRequest request);
}
