package com.oeso.recruitmentservice.repository;

import com.oeso.recruitmentservice.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
}
