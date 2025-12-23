package com.cu2mber.recruitmentservice.repository;

import com.cu2mber.recruitmentservice.dto.response.InternalRecruitmentSummaryResponse;
import com.cu2mber.recruitmentservice.dto.response.RecruitmentListResponse;
import com.cu2mber.recruitmentservice.dto.response.RecruitmentResponse;
import com.cu2mber.recruitmentservice.dto.SearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomRecruitmentRepository {

    Optional<InternalRecruitmentSummaryResponse> findRecruitSummary(Long recruitmentNo);

    Optional<RecruitmentResponse> findRecruit(Long recruitmentNo);

    Page<RecruitmentListResponse> findRecruitPage(SearchParam searchParam, Pageable pageable);
}
