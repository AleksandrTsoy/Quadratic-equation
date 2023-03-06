package ru.alex_tsoy.calculator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex_tsoy.calculator.dto.request.QuadraticEquationCoefficientsDto;
import ru.alex_tsoy.calculator.dto.response.RootEquationDto;
import ru.alex_tsoy.calculator.service.CalculationService;

@RestController
@RequestMapping("/calculation")
@RequiredArgsConstructor
public class CalculationController {

    private final CalculationService calculationService;

    @PostMapping("/result")
    public RootEquationDto getRootCalculation(
            @RequestBody QuadraticEquationCoefficientsDto coefficients) {
        return calculationService.getRootEquation(coefficients);
    }
}
