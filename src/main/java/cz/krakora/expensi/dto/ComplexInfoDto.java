package cz.krakora.expensi.dto;

import lombok.Data;

@Data
public class ComplexInfoDto {
    private double maxIncome;
    private double avgIncome;
    private double medianIncome;
    private double maxOutcome;
    private double avgOutcome;
    private double medianOutcome;
}
