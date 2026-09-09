package com.cu2mber.recruitmentservice.repository;

import com.cu2mber.recruitmentservice.domain.entity.Recruitment;
import jakarta.persistence.LockModeType;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long>, CustomRecruitmentRepository {

    // 행사별 모집 조회 -> 검색할 때 필요?
    List<Recruitment> findByEventNo(Long eventNo);
    // 지자체별 행사별 모집 날짜 조회 -> 신청할 떄 필요
    List<Recruitment> findByEventNoAndMemberLocalNo(Long eventNo, Long memberLocalNo);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @NonNull
    Optional<Recruitment> findById(@NonNull Long recruitment);

    List<Recruitment> findAllByMemberLocalNo(Long memberLocalNo);
}
