package com.cu2mber.recruitmentservice.repository;

import com.cu2mber.recruitmentservice.dto.RecruitmentResponse;
import com.cu2mber.recruitmentservice.dto.SearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomRecruitmentRepository {

    Optional<RecruitmentResponse> findRecruitById(Long recruitmentNo);

    Page<RecruitmentResponse> findRecruitPage(SearchParam searchParam, Pageable pageable);
}
