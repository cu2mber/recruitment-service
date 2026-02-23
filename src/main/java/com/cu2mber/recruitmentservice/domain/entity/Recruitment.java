package com.cu2mber.recruitmentservice.domain.entity;

import com.cu2mber.recruitmentservice.domain.vo.StatusTypeConvert;
import com.cu2mber.recruitmentservice.domain.vo.StatusType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;

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

    @Column(name = "member_local_mapping_no", nullable = false)
    @Comment("회원번호")
    private Long memberLocalNo;

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
    private Long recruitmentPrice;

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

    private Recruitment(Long eventNo, Long memberLocalNo, String recruitTitle, LocalDate recruitDepartDate, LocalDateTime endDate, LocalTime recruitDepartTime, LocalTime recruitReturnTime, Long recruitmentPrice, int recruitMinHeadcount, int recruitMaxHeadcount, StatusType recruitState) {
        this.eventNo = eventNo;
        this.memberLocalNo = memberLocalNo;
        this.recruitTitle = recruitTitle;
        this.recruitDepartDate = recruitDepartDate;
        this.recruitEndDate = endDate;
        this.recruitDepartTime = recruitDepartTime;
        this.recruitReturnTime = recruitReturnTime;
        this.recruitmentPrice = recruitmentPrice;
        this.recruitMinHeadcount = recruitMinHeadcount;
        this.recruitMaxHeadcount = recruitMaxHeadcount;
        this.recruitState = recruitState;
    }

    public static Recruitment ofNewRecruitment(Long eventNo, Long memberNo, String recruitTitle, LocalDate departDate, LocalDateTime endDate, LocalTime departTime, LocalTime returnTime, Long amount, int minHeadcount, int maxHeadcount){
        LocalDateTime depart = LocalDateTime.of(departDate, departTime);

        if(depart.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("출발 날짜/시간은 지금 시간보다 이전이면 안됩니다.");
        }

        if(endDate.isAfter(depart)) {
            throw new IllegalStateException("마감 시간은 출발 시간보다 이후일 수 없습니다.");
        }

        if(departTime.isAfter(returnTime)) {
            throw new IllegalStateException("출발시간이 귀가시간 이후이면 안됩니다.");
        }

        return new Recruitment(eventNo, memberNo, recruitTitle, departDate, endDate, departTime, returnTime, amount, minHeadcount, maxHeadcount, StatusType.OPEN);
    }

    public void update(String title, LocalDate departDate, LocalDateTime endDate, LocalTime departTime, LocalTime returnTime, Long amount, Integer minHeadcount, Integer maxHeadcount){
        LocalDate finalDepartDate = departDate != null ? departDate : this.recruitDepartDate;
        LocalTime finalDepartTime = departTime != null ? departTime : this.recruitDepartTime;
        LocalDateTime finalDepart = LocalDateTime.of(finalDepartDate, finalDepartTime);

        LocalDateTime finalEndDate = endDate != null ? endDate : this.recruitEndDate;
        LocalTime finalReturnTime = returnTime != null ? returnTime : this.recruitReturnTime;

        validateTimes(finalDepart, finalEndDate, finalReturnTime);

        if(title != null) this.recruitTitle = title;
        this.recruitDepartDate = finalDepartDate;
        this.recruitDepartTime = finalDepartTime;
        this.recruitEndDate = finalEndDate;
        this.recruitReturnTime = finalReturnTime;

        if(amount != null) this.recruitmentPrice = amount;
        if(minHeadcount != null) this.recruitMinHeadcount = minHeadcount;
        if(maxHeadcount != null) this.recruitMaxHeadcount = maxHeadcount;
    }

    private void validateTimes(LocalDateTime depart, LocalDateTime end, LocalTime returnTime) {

        if(end.isAfter(depart)) {
            throw new IllegalArgumentException("마감 시간은 출발 시간보다 이후일 수 없습니다.");
        }

        if(depart.toLocalTime().isAfter(returnTime)) {
            throw new IllegalArgumentException("출발 시간은 귀가 시간 이후일 수 없습니다.");
        }
    }

    public void updateState(StatusType state) {
        this.recruitState = state;
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
        this.recruitState = StatusType.ENDED;
    }

    public void increaseParticipantCount(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("증가 인원은 1 이상이어야 합니다.");
        }

        if (this.recruitParticipantCount + count > this.recruitMaxHeadcount) {
            throw new IllegalStateException("모집 정원을 초과할 수 없습니다.");
        }

        this.recruitParticipantCount += count;
    }

    public void decreaseParticipantCount(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("감소 인원은 1 이상이어야 합니다.");
        }

        if (this.recruitParticipantCount - count < 0) {
            throw new IllegalStateException("참여 인원 수는 0보다 작아질 수 없습니다.");
        }

        this.recruitParticipantCount -= count;
    }


}
