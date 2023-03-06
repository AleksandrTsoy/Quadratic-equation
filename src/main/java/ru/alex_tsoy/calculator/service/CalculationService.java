package ru.alex_tsoy.calculator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alex_tsoy.calculator.dto.request.QuadraticEquationCoefficientsDto;
import ru.alex_tsoy.calculator.dto.response.RootEquationDto;
import ru.alex_tsoy.calculator.entity.EquationData;
import ru.alex_tsoy.calculator.exceptions.RootNotFoundException;
import ru.alex_tsoy.calculator.mapper.EquationDataMapper;
import ru.alex_tsoy.calculator.repository.CalculationEquationRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalculationService {

    private final CalculationEquationRepository repository;

    private final EquationDataMapper mapper;

    public RootEquationDto getRootEquation(QuadraticEquationCoefficientsDto coefficients) {
        double discriminant = Math.pow(coefficients.getB(), 2) - 4 * coefficients.getA() * coefficients.getC();
        if (discriminant < 0) {
            throw new RootNotFoundException();
        }
        EquationData equationData = mapper.mapQuadraticEquationCoefficientsDtoToEquationData(coefficients);
        equationData.setX1((-equationData.getB() + Math.sqrt(discriminant)) / 2 / equationData.getA());
        equationData.setX2((-equationData.getB() - Math.sqrt(discriminant)) / 2 / equationData.getA());
        repository.save(equationData);
        log.info("Equation data save success.");
        return mapper.mapEquationDataToDto(equationData);
    }
}
