package com.cu2mber.recruitmentservice.service;

import com.cu2mber.recruitmentservice.dto.*;
import com.cu2mber.recruitmentservice.dto.command.*;
import com.cu2mber.recruitmentservice.dto.request.RecruitmentDeleteRequest;
import com.cu2mber.recruitmentservice.dto.request.RecruitmentUpdateStateRequest;
import com.cu2mber.recruitmentservice.dto.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 모집(Recruitment) 도메인의 비즈니스 로직을 처리합니다.
 * <p>
 * 모집 생성, 수정, 상태 변경, 조회, 삭제 등
 * 모집 라이프사이클 전반에 대한 처리를 담당합니다.
 * <p>
 * 외부 API 응답용 기능과
 * 내부 비즈니스 로직에서 사용하는 조회 기능을 함께 제공합니다.
 */
public interface RecruitmentService {

    /**
     * 새로운 모집을 생성합니다.
     * 권한: 지역 관리자
     *
     * @param command 모집 생성에 필요한 요청 데이터
     * @return 생성된 모집 상세 정보
     */
    RecruitmentResponse create(RecruitmentCreateCommand command);

    /**
     * 모집 정보를 수정합니다.
     * 권한: 전체 관리자, 지역 관리자
     *
     * @param command 모집 수정에 필요한 요청 데이터
     * @return 수정된 모집 상세 정보
     */
    RecruitmentResponse update(RecruitmentUpdateCommand command);

    /**
     * 모집의 상태를 변경합니다.
     * <p>
     * 모집 상태(예: OPEN, CLOSED 등)를 변경하며,
     * 상태 변경 가능 여부에 대한 검증을 포함합니다.
     * 권한: 전체 관리자, 지역 관리자
     *
     * @param recruitmentUpdateStateCommand  모집 상태 변경 요청 데이터
     * @return 상태가 변경된 모집 상세 정보
     */
    RecruitmentUpdateStateResponse updateState(RecruitmentUpdateStateCommand recruitmentUpdateStateCommand);

    /**
     * 모집 단건의 상세 정보를 조회합니다.
     *
     * @param recruitmentNo 조회할 모집 식별 번호
     * @return 모집 상세 정보
     */
    RecruitmentResponse getRecruitment(Long recruitmentNo);

    /**
     * 검색 조건에 따라 모집 목록을 페이징 방식으로 조회합니다.
     *
     * @param searchParam 검색 조건 객체
     * @param pageable    페이지 요청 정보
     * @return 모집 목록 페이징 결과
     */
    Page<RecruitmentListResponse> getRecruitPage(SearchParam searchParam, Pageable pageable);

    /**
     * 상태 정보를 조회합니다.
     *
     * @return 상태 정보
     */
    List<StatusResponse> getStatus();

    /**
     * 모집을 삭제합니다.
     * <p>
     * 삭제 권한 및 삭제 가능 상태 여부를 검증한 후
     * 모집을 소프트 삭제 처리합니다.
     * 권한: 전체 관리자, 지역 관리자
     *
     * @param recruitmentDeleteCommand  모집 삭제 요청 데이터
     */
    void delete(RecruitmentDeleteCommand recruitmentDeleteCommand);

    /**
     * 관련된 모집을 전체 삭제합니다.
     * <p>
     * 삭제 권한 및 삭제 가능 상태 여부를 검증한 후
     * 모집을 소프트 삭제 처리합니다.
     * 권한: 지역 관리자
     *
     * @param recruitmentDeleteAllCommand  모집 삭제 요청 데이터
     */
    void deleteAll(RecruitmentDeleteAllCommand recruitmentDeleteAllCommand);

    /**
     * 모집 단건에 대한 내부 처리용 요약 정보를 조회합니다.
     * <p>
     * 신청 가능 여부 검증, 결제 금액 계산 등
     * 서비스 내부 비즈니스 로직에서 사용됩니다.
     *
     * @param recruitmentNo 모집 식별 번호
     * @return 내부 처리용 모집 요약 정보
     */
    InternalRecruitmentSummaryResponse getRecruitmentSummary(Long recruitmentNo);
}
