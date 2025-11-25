package com.cu2mber.recruitmentservice.domain.entity;

import com.cu2mber.recruitmentservice.domain.vo.StatusTypeConvert;
import com.cu2mber.recruitmentservice.domain.vo.StatusType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "recruitments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitment_no")
    private Long recruitmentNo;

    @Column(name = "event_no", nullable = false)
    @Comment("행사번호")
    private Long eventNo;

    @Column(name = "member_no", nullable = false)
    @Comment("회원번호")
    private Long memberNo;

    @Column(name = "local_no", nullable = false)
    @Comment("지자체번호")
    private int localNo;

    @Column(name = "recruit_title", nullable = false, length = 100)
    @Comment("모집 제목")
    private String recruitTitle;

    @Column(name = "recruit_depart_date", nullable = false)
    @Comment("출발일")
    private LocalDate recruitDepartDate;

    @Column(name = "recruit_end_date", nullable = false)
    @Comment("마감일시")
    private LocalDateTime recruitEndDate;

    @Column(name = "recruit_depart_time", columnDefinition = "time", nullable = false)
    @Comment("출발시간")
    private LocalTime recruitDepartTime;

    @Column(name = "recruit_return_time", columnDefinition = "time", nullable = false)
    @Comment("귀가시간")
    private LocalTime recruitReturnTime;

    @Column(name = "recruit_amount", nullable = false)
    @Comment("금액")
    private BigDecimal recruitAmount;

    @Column(name = "recruit_min_headcount", columnDefinition = "tinyint", nullable = false)
    @Comment("최소인원")
    private int recruitMinHeadcount;

    @Column(name = "recruit_max_headcount", columnDefinition = "tinyint", nullable = false)
    @Comment("최대인원")
    private int recruitMaxHeadcount;

    @Convert(converter = StatusTypeConvert.class)
    @Column(name = "recruit_state", columnDefinition = "tinyint", nullable = false)
    @Comment("모집상태")
    private StatusType recruitState;

    @Column(name = "participant_count", columnDefinition = "tinyint", nullable = true)
    @Comment("참여인원수")
    private int recruitParticipantCount;

    @Column(name = "created_at", nullable = false)
    @Comment("생성일자")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    @Comment("수정일자")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", nullable = true)
    @Comment("삭제일자")
    private LocalDateTime deletedAt;

    @PrePersist
    private void prePersist(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    private Recruitment(Long eventNo, Long memberNo, int localNo, String recruitTitle, LocalDate recruitDepartDate, LocalDateTime endDate, LocalTime recruitDepartTime, LocalTime recruitReturnTime, BigDecimal recruitAmount, int recruitMinHeadcount, int recruitMaxHeadcount, StatusType recruitState) {
        this.eventNo = eventNo;
        this.memberNo = memberNo;
        this.localNo = localNo;
        this.recruitTitle = recruitTitle;
        this.recruitDepartDate = recruitDepartDate;
        this.recruitEndDate = endDate;
        this.recruitDepartTime = recruitDepartTime;
        this.recruitReturnTime = recruitReturnTime;
        this.recruitAmount = recruitAmount;
        this.recruitMinHeadcount = recruitMinHeadcount;
        this.recruitMaxHeadcount = recruitMaxHeadcount;
        this.recruitState = recruitState;
    }

    public static Recruitment ofNewRecruitment(Long eventNo, Long memberNo, int localNo, String recruitTitle, LocalDate departDate, LocalDateTime endDate, LocalTime departTime, LocalTime returnTime, BigDecimal amount, int minHeadcount, int maxHeadcount){
        return new Recruitment(eventNo, memberNo, localNo, recruitTitle, departDate, endDate, departTime, returnTime, amount, minHeadcount, maxHeadcount, StatusType.OPEN);
    }

    public void update(String recruitTitle, LocalTime departTime, LocalTime returnTime, BigDecimal amount, int minHeadcount, int maxHeadcount){
        this.recruitTitle = recruitTitle;
        this.recruitDepartTime = departTime;
        this.recruitReturnTime = returnTime;
        this.recruitAmount = amount;
        this.recruitMinHeadcount = minHeadcount;
        this.recruitMaxHeadcount = maxHeadcount;
    }

    public void updateState(StatusType state) {
        this.recruitState = state;
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }

}
