package com.cu2mber.recruitmentservice.controller;

import com.cu2mber.recruitmentservice.dto.response.InternalRecruitmentSummaryResponse;
import com.cu2mber.recruitmentservice.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/recruits")
@RequiredArgsConstructor
public class InternalRecruitmentController {

    private final RecruitmentService recruitmentService;

    @GetMapping("/{no}")
    ResponseEntity<InternalRecruitmentSummaryResponse> getRecruitSummary(@PathVariable("no")Long no) {

        InternalRecruitmentSummaryResponse response = recruitmentService.getRecruitmentSummary(no);

        return ResponseEntity.ok(response);
    }

}
