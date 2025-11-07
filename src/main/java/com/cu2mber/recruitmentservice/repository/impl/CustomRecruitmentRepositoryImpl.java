package com.cu2mber.recruitmentservice.repository.impl;

import com.cu2mber.recruitmentservice.domain.QRecruitment;
import com.cu2mber.recruitmentservice.dto.response.QRecruitmentResponse;
import com.cu2mber.recruitmentservice.dto.response.RecruitmentResponse;
import com.cu2mber.recruitmentservice.dto.SearchParam;
import com.cu2mber.recruitmentservice.repository.CustomRecruitmentRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

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
                                qRecruitment.recruitDepartDate,
                                qRecruitment.recruitEndDate,
                                qRecruitment.recruitDepartTime,
                                qRecruitment.recruitReturnTime,
                                qRecruitment.recruitAmount,
                                qRecruitment.recruitMinHeadcount,
                                qRecruitment.recruitMaxHeadcount,
                                qRecruitment.recruitParticipantCount,
                                qRecruitment.recruitState)
                )
                .from(qRecruitment);
    }

    @Override
    public Optional<RecruitmentResponse> findRecruit(Long recruitmentNo) {
        RecruitmentResponse response = queryResponse(queryFactory)
                .where(qRecruitment.recruitmentNo.eq(recruitmentNo)).fetchOne();

        return Optional.ofNullable(response);
    }

    @Override
    public Page<RecruitmentResponse> findRecruitPage(SearchParam searchParam,
                                                     Pageable pageable) {

        List<RecruitmentResponse> responseList = queryResponse(queryFactory)
                .where(whereExpression(searchParam))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory.select(qRecruitment.count())
                .from(qRecruitment)
                .where(whereExpression(searchParam));

        return PageableExecutionUtils.getPage(responseList, pageable, count::fetchOne);
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
