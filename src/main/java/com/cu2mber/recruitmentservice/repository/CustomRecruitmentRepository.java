package com.cu2mber.recruitmentservice.repository;

import com.cu2mber.recruitmentservice.dto.response.InternalRecruitmentSummaryResponse;
import com.cu2mber.recruitmentservice.dto.response.RecruitmentListResponse;
import com.cu2mber.recruitmentservice.dto.response.RecruitmentResponse;
import com.cu2mber.recruitmentservice.dto.SearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * 모집(Recruitment) 도메인에 대한 커스텀 조회 Repository 인터페이스입니다.
 * <p>
 * 단건 조회, 목록 조회, 검색 조건 기반 페이징 조회 등
 * 복합 조회 로직을 Querydsl 등을 통해 구현하기 위해 사용됩니다.
 * <p>
 * 외부 API 응답용 조회와
 * 내부 비즈니스 로직에서 사용하는 조회를 명확히 구분합니다.
 */
public interface CustomRecruitmentRepository {

    /**
     * 모집 단건에 대한 내부 처리용 요약 정보를 조회합니다.
     * <p>
     * 신청 가능 여부 검증, 상태 확인 등
     * 서비스 내부 비즈니스 로직에서 사용됩니다.
     *
     * @param recruitmentNo 모집 식별 번호
     * @return 내부 처리용 모집 요약 정보 (존재하지 않으면 {@code Optional.empty()})
     */
    Optional<InternalRecruitmentSummaryResponse> findRecruitSummary(Long recruitmentNo);

    /**
     * 모집 단건의 상세 정보를 조회합니다.
     * <p>
     * 모집 상세 화면 등 외부 API 응답으로 사용됩니다.
     *
     * @param recruitmentNo 모집 식별 번호
     * @return 모집 상세 응답 정보 (존재하지 않으면 {@code Optional.empty()})
     */
    Optional<RecruitmentResponse> findRecruit(Long recruitmentNo);

    /**
     * 검색 조건에 따라 모집 목록을 페이징 방식으로 조회합니다.
     * <p>
     * 지역, 날짜, 상태 등 다양한 검색 조건을 기반으로
     * 모집 리스트 화면에서 사용됩니다.
     *
     * @param searchParam 검색 조건 객체
     * @param pageable    페이지 요청 정보
     * @return 검색 조건이 적용된 모집 목록 (페이징 결과)
     */
    Page<RecruitmentListResponse> findRecruitPage(SearchParam searchParam, Pageable pageable);
}
