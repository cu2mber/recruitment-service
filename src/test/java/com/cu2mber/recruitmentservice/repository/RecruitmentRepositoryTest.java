package com.cu2mber.recruitmentservice.repository;

import com.cu2mber.recruitmentservice.common.config.QuerydslConfig;
import com.cu2mber.recruitmentservice.domain.entity.Recruitment;
import com.cu2mber.recruitmentservice.domain.vo.StatusType;
import com.cu2mber.recruitmentservice.dto.response.InternalRecruitmentSummaryResponse;
import com.cu2mber.recruitmentservice.dto.response.RecruitmentListResponse;
import com.cu2mber.recruitmentservice.dto.response.RecruitmentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Import(QuerydslConfig.class)
@ActiveProfiles("test")
@DataJpaTest
class RecruitmentRepositoryTest {

    @Autowired
    RecruitmentRepository recruitmentRepository;

    @Autowired
    TestEntityManager entityManager;

    Recruitment recruitment1;

    @BeforeEach
    void setUp() {
        recruitment1 = Recruitment.ofNewRecruitment(1L, 1L, "김해 가야 측제 모집", LocalDate.of(2026,1, 30), LocalDateTime.of(2026, 1, 29,23,59), LocalTime.of(9,0), LocalTime.of(18,30), 2000L, 10, 20);
        Recruitment recruitment2 = Recruitment.ofNewRecruitment(2L, 1L, "부산 불꽃놀이 측제 모집", LocalDate.of(2026,1, 30), LocalDateTime.of(2026, 1, 29,23,59), LocalTime.of(9,0), LocalTime.of(18,30), 2000L, 10, 20);
        Recruitment recruitment3 = Recruitment.ofNewRecruitment(3L, 1L, "진해 군항제 모집", LocalDate.of(2026,1, 30), LocalDateTime.of(2026, 1, 29,23,59), LocalTime.of(9,0), LocalTime.of(18,30), 2000L, 10, 20);

        entityManager.persistAndFlush(recruitment1);
        entityManager.persistAndFlush(recruitment2);
        entityManager.persistAndFlush(recruitment3);
    }

    @Test
    @DisplayName("모집 조회 - 내부 API")
    void findRecruitSummary() {
        Optional<InternalRecruitmentSummaryResponse> result = recruitmentRepository.findRecruitSummary(recruitment1.getRecruitmentNo());

        assertTrue(result.isPresent());

        assertAll(
                () -> assertEquals("김해 가야 측제 모집", result.get().getRecruitmentTitle()),
                () -> assertEquals(1L, result.get().getEventNo()),
                () -> assertEquals(StatusType.OPEN, result.get().getRecruitmentStatus())
        );
    }

    @Test
    @DisplayName("모집 상세 조회")
    void findRecruit() {
        Optional<RecruitmentResponse> result = recruitmentRepository.findRecruit(recruitment1.getRecruitmentNo());

        assertTrue(result.isPresent());

        assertAll(
                () -> assertEquals("김해 가야 측제 모집", result.get().getRecruitmentTitle()),
                () -> assertEquals(LocalDate.of(2026,1, 30), result.get().getRecruitDepartDate()),
                () -> assertEquals(LocalDateTime.of(2026, 1, 29,23,59), result.get().getRecruitEndDate()),
                () -> assertEquals(2000, result.get().getRecruitmentPrice())
        );
    }

    @Test
    @DisplayName("모집 목록 페이징 조회")
    void findRecruitPage() {
        Page<RecruitmentListResponse> results = recruitmentRepository.findRecruitPage(null, Pageable.ofSize(5));

        assertFalse(results.getContent().isEmpty());
        assertEquals(3, results.getTotalElements());


        var first = results.getContent().getFirst();
        var second = results.getContent().get(1);
        var third = results.getContent().get(2);
        assertAll(
                () -> assertEquals("김해 가야 측제 모집", first.getRecruitmentTitle()),
                () -> assertEquals("부산 불꽃놀이 측제 모집", second.getRecruitmentTitle()),
                () -> assertEquals("진해 군항제 모집", third.getRecruitmentTitle())
        );
    }
}