package ru.alex_tsoy.calculator.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.alex_tsoy.calculator.dto.request.QuadraticEquationCoefficientsDto;
import ru.alex_tsoy.calculator.exceptions.RootNotFoundException;
import ru.alex_tsoy.calculator.exceptions.handler.DefaultRestExceptionHandler;
import ru.alex_tsoy.calculator.service.CalculationService;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(CalculationController.class)
class CalculationControllerTest {

    @MockBean
    CalculationService calculationService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = getMockMvc();
    }

    @Test
    void getRootCalculation_isFound() throws Exception {
        mockMvc.perform(post("/calculation/result")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"a\": 1,\n" +
                                "  \"b\": 5,\n" +
                                "  \"c\": 4\n" +
                                "}"))
                .andExpect(status().isOk());
    }

    @Test
    void getRootCalculation_isNotFound() throws Exception {
        doThrow(new RootNotFoundException()).when(calculationService).getRootEquation(getCoefficientsDto());

        mockMvc.perform(post("/calculation/result")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"a\": 10,\n" +
                                "  \"b\": 5,\n" +
                                "  \"c\": 4\n" +
                                "}"))
                .andExpect(status().isNotFound()).andReturn();
    }

    private MockMvc getMockMvc() {
        return standaloneSetup(new CalculationController(calculationService))
                .setControllerAdvice(new DefaultRestExceptionHandler())
                .build();
    }

    private QuadraticEquationCoefficientsDto getCoefficientsDto() {
        QuadraticEquationCoefficientsDto dto = new QuadraticEquationCoefficientsDto();
        dto.setA(10.0);
        dto.setB(5.0);
        dto.setC(4.0);
        return dto;
    }

}