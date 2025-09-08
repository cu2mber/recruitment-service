package com.cu2mber.recruitmentservice.vo;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class StateTypeConvert implements AttributeConverter<StateType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(StateType stateType) {
        return stateType.getCode();
    }

    @Override
    public StateType convertToEntityAttribute(Integer integer) {
        return integer != null ? StateType.fromCode(integer) : null;
    }
}
