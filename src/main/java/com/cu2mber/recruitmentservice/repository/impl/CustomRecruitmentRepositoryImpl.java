package com.cu2mber.recruitmentservice.repository.impl;

import com.cu2mber.recruitmentservice.domain.entity.QRecruitment;
import com.cu2mber.recruitmentservice.domain.vo.StatusType;
import com.cu2mber.recruitmentservice.dto.response.*;
import com.cu2mber.recruitmentservice.dto.SearchParam;
import com.cu2mber.recruitmentservice.repository.CustomRecruitmentRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomRecruitmentRepositoryImpl implements CustomRecruitmentRepository {

    private final JPAQueryFactory queryFactory;

    QRecruitment qRecruitment = QRecruitment.recruitment;

    private JPAQuery<RecruitmentResponse> queryResponse(JPAQueryFactory query) {
        return query.select(
                        new QRecruitmentResponse(
                                qRecruitment.recruitmentNo,
                                qRecruitment.recruitmentTitle,
                                qRecruitment.recruitmentDepartDate,
                                qRecruitment.recruitmentEndDate,
                                qRecruitment.recruitmentDepartTime,
                                qRecruitment.recruitmentReturnTime,
                                qRecruitment.recruitmentPrice,
                                qRecruitment.recruitmentMinHeadcount,
                                qRecruitment.recruitmentMaxHeadcount,
                                qRecruitment.recruitmentParticipantCount,
                                qRecruitment.recruitmentState,
                                qRecruitment.createdAt)
                )
                .from(qRecruitment);
    }

    @Override
    public Optional<InternalRecruitmentSummaryResponse> findRecruitSummary(Long recruitmentNo) {
        InternalRecruitmentSummaryResponse response = queryFactory.select(
                new QInternalRecruitmentSummaryResponse(
                        qRecruitment.recruitmentNo,
                        qRecruitment.recruitmentState,
                        qRecruitment.memberLocalNo,
                        qRecruitment.eventNo,
                        qRecruitment.recruitmentTitle,
                        qRecruitment.recruitmentPrice,
                        qRecruitment.recruitmentDepartTime,
                        qRecruitment.recruitmentReturnTime
                ))
                .from(qRecruitment)
                .where(qRecruitment.recruitmentNo.eq(recruitmentNo))
                .fetchOne();
        return Optional.ofNullable(response);
    }

    @Override
    public Optional<RecruitmentResponse> findRecruit(Long recruitmentNo) {
        RecruitmentResponse response = queryResponse(queryFactory)
                .where(qRecruitment.recruitmentNo.eq(recruitmentNo)).fetchOne();

        return Optional.ofNullable(response);
    }

    @Override
    public Page<RecruitmentListResponse> findRecruitPage(SearchParam searchParam,
                                                         Pageable pageable) {

        List<RecruitmentListResponse> responseList = queryFactory.select(
                new QRecruitmentListResponse(
                        qRecruitment.recruitmentNo,
                        qRecruitment.recruitmentState,
                        qRecruitment.memberLocalNo,
                        qRecruitment.eventNo,
                        qRecruitment.recruitmentTitle,
                        qRecruitment.createdAt,
                        qRecruitment.recruitmentDepartDate,
                        qRecruitment.recruitmentEndDate
                )
        )
                .from(qRecruitment)
                .where(whereExpression(searchParam))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory.select(qRecruitment.count())
                .from(qRecruitment)
                .where(whereExpression(searchParam));

        return PageableExecutionUtils.getPage(responseList, pageable, count::fetchOne);
    }

    @Override
    public long softDeleteAllByMemberLocalNo(Long memberNo) {
        return queryFactory.update(qRecruitment)
                .set(qRecruitment.deletedAt, LocalDateTime.now())
                .set(qRecruitment.recruitmentState, StatusType.ENDED)
                .where(qRecruitment.memberLocalNo.eq(memberNo),
                        qRecruitment.deletedAt.isNull())
                .execute();
    }

    private BooleanBuilder whereExpression(SearchParam searchParam) {
        BooleanBuilder builder = new BooleanBuilder();

//        if(searchParam.getTerm() != null) {
//            builder.and(
//                    qRecruitment.
//            )
//        }

        return builder;
    }

}
