package com.cu2mber.recruitmentservice.domain.vo;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class StatusTypeConvert implements AttributeConverter<StatusType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(StatusType statusType) {
        return statusType.getCode();
    }

    @Override
    public StatusType convertToEntityAttribute(Integer integer) {
        return integer != null ? StatusType.fromCode(integer) : null;
    }
}
