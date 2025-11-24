package com.cu2mber.recruitmentservice.controller;

import com.cu2mber.recruitmentservice.dto.*;
import com.cu2mber.recruitmentservice.dto.request.RecruitmentDeleteRequest;
import com.cu2mber.recruitmentservice.dto.request.RecruitmentCreateRequest;
import com.cu2mber.recruitmentservice.dto.request.RecruitmentUpdateStateRequest;
import com.cu2mber.recruitmentservice.dto.response.RecruitmentListResponse;
import com.cu2mber.recruitmentservice.dto.response.RecruitmentResponse;
import com.cu2mber.recruitmentservice.service.RecruitmentService;
import com.cu2mber.recruitmentservice.vo.StatusType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/recruits")
@RequiredArgsConstructor
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @PostMapping
    public ResponseEntity<RecruitmentResponse> createRecruitment(@RequestBody @Valid RecruitmentCreateRequest request) {

        RecruitmentResponse response = recruitmentService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<Page<RecruitmentListResponse>> getRecruitmentPage(@RequestParam(required = false) SearchParam searchParam, @PageableDefault(size = 10) Pageable pageable) {

        RecruitmentListResponse recruitmentResponse = new RecruitmentListResponse(
                1L,
                StatusType.OPEN,
                1L,
                1L,
                "모집 제목",
                "작성자",
                LocalDateTime.now(),
                LocalDate.now(),
                LocalDateTime.now().plusDays(1)
        );
        Page<RecruitmentListResponse> response = new PageImpl<>(List.of(recruitmentResponse));
//        Page<RecruitmentResponse> response = recruitmentService.getRecruitPage(searchParam, pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{no}")
    public ResponseEntity<RecruitmentResponse> getRecruitment(@PathVariable("no") Long no) {

        RecruitmentResponse response = new RecruitmentResponse(
                no,
                "이벤트 이름" + no,
                "회원 이름",
                "행사 지역",
                "[지자체] 행사 이름",
                LocalDate.now(),
                LocalDateTime.now().plusDays(1),
                LocalTime.of(8, 0),
                LocalTime.of(21, 0),
                BigDecimal.valueOf(0),
                10,
                40,
                0,
                StatusType.OPEN.getDescription()
        );
//        RecruitmentResponse response = recruitmentService.getRecruitment(no);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{no}")
    public ResponseEntity<RecruitmentResponse> updateRecruitment(@PathVariable("no") Long no, @RequestBody @Valid RecruitmentCreateRequest request) {
        RecruitmentResponse response = new RecruitmentResponse(
                1L,
                "이벤트 이름",
                "회원 이름",
                "행사 지역",
                "[지자체] 행사 이름",
                request.getRecruitDepartDate(),
                request.getRecruitEndDate(),
                request.getRecruitDepartTime(),
                request.getRecruitReturnTime(),
                request.getRecruitAmount(),
                request.getRecruitMinHeadcount(),
                request.getRecruitMaxHeadcount(),
                0,
                StatusType.OPEN.getDescription()
        );
//        RecruitmentResponse response = recruitmentService.update(no, request);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{no}/state")
    public ResponseEntity<RecruitmentResponse> updateRecruitmentState(@PathVariable("no") Long no, @RequestBody RecruitmentUpdateStateRequest request) {
        RecruitmentResponse response = new RecruitmentResponse(
                no,
                "이벤트 이름" + no,
                "회원 이름",
                "행사 지역",
                "[지자체] 행사 이름",
                LocalDate.now(),
                LocalDateTime.now().plusDays(1),
                LocalTime.of(8, 0),
                LocalTime.of(21, 0),
                BigDecimal.valueOf(0),
                10,
                40,
                0,
                request.getStatusType().getDescription()
        );
        //        RecruitmentResponse response = recruitmentService.updateState(no, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{no}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecruitment(@PathVariable("no") Long no, @RequestBody RecruitmentDeleteRequest request) {

//        recruitmentService.delete(no, request);
    }

}
