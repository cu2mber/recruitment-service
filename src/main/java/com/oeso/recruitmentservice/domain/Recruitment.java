package com.oeso.recruitmentservice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;

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

    @Column(name = "local_depart_time", columnDefinition = "time", nullable = false)
    @Comment("출발시간")
    private LocalTime departTime;

    @Column(name = "local_return_time", columnDefinition = "time", nullable = false)
    @Comment("귀가시간")
    private LocalTime returnTime;

    @Column(name = "local_amount", columnDefinition = "tinyint", nullable = false)
    @Comment("금액")
    private int amount;

    @Column(name = "local_min_headcount", columnDefinition = "tinyint", nullable = false)
    @Comment("최소인원")
    private int minHeadcount;

    @Column(name = "local_max_headcount", columnDefinition = "tinyint", nullable = false)
    @Comment("최대인원")
    private int maxHeadcount;

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

    public Recruitment(Long eventNo, Long memberNo, int localNo, LocalTime departTime, LocalTime returnTime, int amount, int minHeadcount, int maxHeadcount) {
        this.eventNo = eventNo;
        this.memberNo = memberNo;
        this.localNo = localNo;
        this.departTime = departTime;
        this.returnTime = returnTime;
        this.amount = amount;
        this.minHeadcount = minHeadcount;
        this.maxHeadcount = maxHeadcount;
    }

    public Recruitment ofNewRecruitment(Long eventNo, Long memberNo, int localNo, LocalTime departTime, LocalTime returnTime, int amount, int minHeadcount, int maxHeadcount){
        return new Recruitment(eventNo, memberNo, localNo, departTime, returnTime, amount, minHeadcount, maxHeadcount);
    }

    private void update(LocalTime departTime, LocalTime returnTime, int amount, int minHeadcount, int maxHeadcount){
        this.departTime = departTime;
        this.returnTime = returnTime;
        this.amount = amount;
        this.minHeadcount = minHeadcount;
        this.maxHeadcount = maxHeadcount;
    }
}
