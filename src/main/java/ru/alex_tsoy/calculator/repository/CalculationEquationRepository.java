package ru.alex_tsoy.calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex_tsoy.calculator.entity.EquationData;

@Repository
public interface CalculationEquationRepository extends JpaRepository<EquationData, Long> {
}
