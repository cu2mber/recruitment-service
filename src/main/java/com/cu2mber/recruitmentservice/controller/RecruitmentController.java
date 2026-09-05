package com.cu2mber.recruitmentservice.controller;

import com.cu2mber.recruitmentservice.dto.*;
import com.cu2mber.recruitmentservice.dto.command.RecruitmentCreateCommand;
import com.cu2mber.recruitmentservice.dto.command.RecruitmentDeleteCommand;
import com.cu2mber.recruitmentservice.dto.command.RecruitmentUpdateCommand;
import com.cu2mber.recruitmentservice.dto.command.RecruitmentUpdateStateCommand;
import com.cu2mber.recruitmentservice.dto.request.RecruitmentDeleteRequest;
import com.cu2mber.recruitmentservice.dto.request.RecruitmentCreateRequest;
import com.cu2mber.recruitmentservice.dto.request.RecruitmentUpdateRequest;
import com.cu2mber.recruitmentservice.dto.request.RecruitmentUpdateStateRequest;
import com.cu2mber.recruitmentservice.dto.response.RecruitmentListResponse;
import com.cu2mber.recruitmentservice.dto.response.RecruitmentResponse;
import com.cu2mber.recruitmentservice.dto.response.RecruitmentUpdateStateResponse;
import com.cu2mber.recruitmentservice.dto.response.StatusResponse;
import com.cu2mber.recruitmentservice.service.RecruitmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruits")
@RequiredArgsConstructor
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @PostMapping
    public ResponseEntity<RecruitmentResponse> createRecruitment(@RequestBody @Valid RecruitmentCreateRequest request,
                                                                 @RequestHeader("X-Role") String role,
                                                                 @RequestHeader("X-Member-No") Long memberNo) {

        RecruitmentCreateCommand command = new RecruitmentCreateCommand(
                request.eventNo(),
                role,
                memberNo,
                request.recruitTitle(),
                request.recruitDepartDate(),
                request.recruitEndDate(),
                request.recruitDepartTime(),
                request.recruitReturnTime(),
                request.recruitmentPrice(),
                request.recruitMinHeadcount(),
                request.recruitMaxHeadcount()
        );

        RecruitmentResponse response = recruitmentService.create(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<Page<RecruitmentListResponse>> getRecruitmentPage(@RequestParam(required = false) SearchParam searchParam, @PageableDefault(size = 10) Pageable pageable) {

        Page<RecruitmentListResponse> response = recruitmentService.getRecruitPage(searchParam, pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{no}")
    public ResponseEntity<RecruitmentResponse> getRecruitment(@PathVariable("no") Long no) {
        RecruitmentResponse response = recruitmentService.getRecruitment(no);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status")
    public ResponseEntity<List<StatusResponse>> getStatus() {
        List<StatusResponse> response = recruitmentService.getStatus();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{no}")
    public ResponseEntity<RecruitmentResponse> updateRecruitment(@PathVariable("no") Long no,
                                                                 @RequestBody @Valid RecruitmentUpdateRequest request,
                                                                 @RequestHeader("X-Role") String role,
                                                                 @RequestHeader("X-Member-No") Long memberNo) {

        RecruitmentUpdateCommand command = new RecruitmentUpdateCommand(
                no,
                request.eventNo(),
                role,
                memberNo,
                request.recruitTitle(),
                request.recruitDepartDate(),
                request.recruitEndDate(),
                request.recruitDepartTime(),
                request.recruitReturnTime(),
                request.recruitmentPrice(),
                request.recruitMinHeadcount(),
                request.recruitMaxHeadcount()
        );

        RecruitmentResponse response = recruitmentService.update(command);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{no}/status")
    public ResponseEntity<RecruitmentUpdateStateResponse> updateRecruitmentState(@PathVariable("no") Long no,
                                                                                 @RequestBody RecruitmentUpdateStateRequest request,
                                                                                 @RequestHeader("X-Role") String role,
                                                                                 @RequestHeader("X-Member-No") Long memberNo) {
//        RecruitmentResponse response = new RecruitmentResponse(
//                no,
//                "이벤트 이름" + no,
//                "회원 이름",
//                "행사 지역",
//                "[지자체] 행사 이름",
//                LocalDate.now(),
//                LocalDateTime.now().plusDays(1),
//                LocalTime.of(8, 0),
//                LocalTime.of(21, 0),
//                0L,
//                10,
//                40,
//                0,
//                request.statusType().getDescription(),
//                LocalDateTime.now()
//        );

        RecruitmentUpdateStateCommand command = new RecruitmentUpdateStateCommand(
                no,
                role,
                memberNo,
                request.statusType()
        );
        RecruitmentUpdateStateResponse response = recruitmentService.updateState(command);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{no}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteRecruitment(@PathVariable("no") Long no,
                                                  @RequestBody RecruitmentDeleteRequest request,
                                                  @RequestHeader("X-Role") String role,
                                                  @RequestHeader("X-Member-No") Long memberNo) {


        RecruitmentDeleteCommand command = new RecruitmentDeleteCommand(
                no,
                null, //TODO event no가 왜 있을까?
                role,
                memberNo,
                null //TODO local no가 왜 있을까?
        );

        recruitmentService.delete(command);

        return ResponseEntity.noContent().build();
    }

}
