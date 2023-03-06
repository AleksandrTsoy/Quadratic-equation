package ru.alex_tsoy.calculator.mapper;

import org.mapstruct.Mapper;
import ru.alex_tsoy.calculator.dto.request.QuadraticEquationCoefficientsDto;
import ru.alex_tsoy.calculator.dto.response.RootEquationDto;
import ru.alex_tsoy.calculator.entity.EquationData;

@Mapper(componentModel = "spring")
public interface EquationDataMapper {

    EquationData mapQuadraticEquationCoefficientsDtoToEquationData(QuadraticEquationCoefficientsDto dto);

    RootEquationDto mapEquationDataToDto(EquationData equationData);
}
