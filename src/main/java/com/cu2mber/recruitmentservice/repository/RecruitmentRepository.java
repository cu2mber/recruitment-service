package com.cu2mber.recruitmentservice.repository;

import com.cu2mber.recruitmentservice.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
}
