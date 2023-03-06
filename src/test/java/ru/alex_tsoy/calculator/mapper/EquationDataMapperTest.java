package ru.alex_tsoy.calculator.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.alex_tsoy.calculator.dto.request.QuadraticEquationCoefficientsDto;
import ru.alex_tsoy.calculator.dto.response.RootEquationDto;
import ru.alex_tsoy.calculator.entity.EquationData;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EquationDataMapperTest {

    @InjectMocks
    private EquationDataMapperImpl equationDataMapper;

    @Test
    void mapQuadraticEquationCoefficientsDtoToEquationData() {
        QuadraticEquationCoefficientsDto dto = getCoefficientsDto();
        EquationData expectedEquationData = getEquationData();

        EquationData actualEquationData = equationDataMapper.mapQuadraticEquationCoefficientsDtoToEquationData(dto);

        assertEquals(expectedEquationData.getA(), actualEquationData.getA());
        assertEquals(expectedEquationData.getB(), actualEquationData.getB());
        assertEquals(expectedEquationData.getC(), actualEquationData.getC());
    }

    @Test
    void mapEquationDataToDto() {
        RootEquationDto expectedDto = getRootEquationDto();
        EquationData equationData = getEquationData();

        RootEquationDto actualDto = equationDataMapper.mapEquationDataToDto(equationData);

        assertEquals(expectedDto, actualDto);
    }

    private QuadraticEquationCoefficientsDto getCoefficientsDto() {
        QuadraticEquationCoefficientsDto dto = new QuadraticEquationCoefficientsDto();
        dto.setA(1.0);
        dto.setB(5.0);
        dto.setC(4.0);
        return dto;
    }

    private EquationData getEquationData() {
        EquationData data = new EquationData();
        data.setA(1.0);
        data.setB(5.0);
        data.setC(4.0);
        data.setX1(-1.0);
        data.setX2(-4.0);
        return data;
    }

    private RootEquationDto getRootEquationDto() {
        RootEquationDto dto = new RootEquationDto();
        dto.setX1(-1.0);
        dto.setX2(-4.0);
        return dto;
    }
}