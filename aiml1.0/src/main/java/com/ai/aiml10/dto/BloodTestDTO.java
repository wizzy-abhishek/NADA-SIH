package com.ai.aiml10.dto;

import com.ai.aiml10.enums.Status;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

@Data
@RequiredArgsConstructor
@ToString
public class BloodTestDTO {

    @NotBlank(message = "Blood Test ID cant be blank")
    private String testId;        // Unique identifier for the test

    @NotBlank(message = "Athletes ID for respective Blood Test is mandatory")
    private String athleteId;     // Reference to the Athlete

    @NotNull(message = "Blood Test date is required")
    @PastOrPresent(message = "Date of Blood test should be past or present")
    private Date testDate;        // Date of the test

    @Positive(message = "Hemoglobin level must be positive")
    @Digits(integer = 2, fraction = 2, message = "Hemoglobin level must be a valid number with up to 2 digits before and 2 after the decimal point")
    @DecimalMin(value = "2.0", message = "Hemoglobin level must be at least 2.0 g/dL")
    @DecimalMax(value = "50", message = "Hemoglobin level must be at most 50 g/dL")
    private double hemoglobin;    // Hemoglobin level (g/dL)  Normal Range: Men: 13.5 to 17.5 g/dL Women: 12.0 to 15.5 g/dL

    @Positive(message = "Hematocrit percentage must be positive")
    @Digits(integer = 2, fraction = 2, message = "Hematocrit percentage must be a valid number with up to 2 digits")
    @DecimalMin(value = "6", message = "Hematocrit percentage must be at least 6%")
    @DecimalMax(value = "80", message = "Hematocrit percentage must be at most 50%")
    private double hematocrit;    // Hematocrit percentage (%)  Normal Range:Men: 41% to 50% Women: 36% to 44%

    @Positive(message = "Red blood cell count must be positive")
    @Digits(integer = 2, fraction = 3, message = "Red blood cell count must be a valid number with up to 2 digit before and 3 after the decimal point")
    @DecimalMin(value = "0.1", message = "Red blood cell count must be at least 0.1 million cells/µL")
    @DecimalMax(value = "60.9", message = "Red blood cell count must be at most 60.9 million cells/µL")
    private double redBloodCells;           // Red blood cell count (million cells/µL)

    @Positive(message = "Reticulocyte percentage must be positive")
    @Digits(integer = 2, fraction = 3, message = "Reticulocyte percentage must be a valid number with up to 2 digit before and 3 after the decimal point")
    @DecimalMin(value = "0.0", message = "Reticulocyte percentage must be at least 0.0%")
    @DecimalMax(value = "30", message = "Reticulocyte percentage must be at most 30%")
    private double reticulocytes;        // Reticulocytes percentage (%) Normal Range: 0.5% to 2.5% of total red blood cells.

    @Positive(message = "Erythropoietin level must be positive")
    @Digits(integer = 2, fraction = 3, message = "Erythropoietin level must be a valid number with up to 2 digits and 3 decimal point")
    @DecimalMin(value = "0", message = "Erythropoietin level must be at least 0 mIU/mL")
    @DecimalMax(value = "99", message = "Erythropoietin level must be at most 99 mIU/mL")
    private double epoLevel;     // Erythropoietin hormone level (mIU/mL)  Normal Range: 4 to 24 mIU/mL

    private Status condition;     // Result of the doping test (POSITIVE/NEGATIVE/SUSPICIOUS)


}
