package ru.alex_tsoy.calculator.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.alex_tsoy.calculator.dto.request.QuadraticEquationCoefficientsDto;
import ru.alex_tsoy.calculator.dto.response.RootEquationDto;
import ru.alex_tsoy.calculator.entity.EquationData;
import ru.alex_tsoy.calculator.exceptions.RootNotFoundException;
import ru.alex_tsoy.calculator.mapper.EquationDataMapper;
import ru.alex_tsoy.calculator.repository.CalculationEquationRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculationServiceTest {

    @InjectMocks
    private CalculationService calculationService;

    @Mock
    private CalculationEquationRepository repository;

    @Mock
    EquationDataMapper mapper;

    @Test
    void getRootEquation_DiscriminatorMoreThanZero() {
        when(mapper.mapQuadraticEquationCoefficientsDtoToEquationData(getCoefficientsDto()))
                .thenReturn(getEquationData());
        when(mapper.mapEquationDataToDto(getEquationData()))
                .thenReturn(getRootEquationDto());
        when(repository.save(getEquationData()))
                .thenReturn(getEquationData());

        RootEquationDto rootEquation = calculationService.getRootEquation(getCoefficientsDto());

        assertEquals(-1.0, rootEquation.getX1());
        assertEquals(-4.0, rootEquation.getX2());
        verify(mapper, times(1))
                .mapQuadraticEquationCoefficientsDtoToEquationData(getCoefficientsDto());
        verify(mapper, times(1))
                .mapEquationDataToDto(getEquationData());
        verify(repository, times(1))
                .save(getEquationData());
    }

    @Test
    void getRootEquation_DiscriminatorLessThanZero() {
        QuadraticEquationCoefficientsDto dto = getCoefficientsDto();
        dto.setA(10.0);

        assertThrows(RootNotFoundException.class, () -> calculationService.getRootEquation(dto));
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