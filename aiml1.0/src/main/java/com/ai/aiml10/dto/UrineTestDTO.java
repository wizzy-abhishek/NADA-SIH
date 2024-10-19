package com.ai.aiml10.dto;

import com.ai.aiml10.entity.BiologicalPassportEntity;
import com.ai.aiml10.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class UrineTestDTO implements Serializable {

    @NotBlank(message = "Urine Test ID cant be blank")
    private String testID;        // Unique identifier for the test

    @NotBlank(message = "Athletes ID for respective Urine Test is mandatory")
    private String athletesID;     // Reference to the Athlete

    @NotNull(message = "Urine Test date is required")
    @PastOrPresent(message = "Date of Urine test should be past or present")
    private Date testDate;        // Date of the test

    @Positive(message = "Specific gravity must be positive")
    @Digits(integer = 1, fraction = 3, message = "Specific gravity must be a valid number with up to 1 digit before and 3 after the decimal point")
    @DecimalMin(value = "0.5", message = "Specific gravity must be at least 0.5")
    @DecimalMax(value = "2.5", message = "Specific gravity must be at most 2.5")
    private double specificGravity; // Urine specific gravity

    @Positive(message = "Creatinine level must be positive")
    @Digits(integer = 1, fraction = 3, message = "Creatinine level must be a valid number with up to 1 digit before and 3 after the decimal point")
    @DecimalMin(value = "0.05", message = "Creatinine level must be at least 0.05 mg/dL")
    @DecimalMax(value = "2.5", message = "Creatinine level must be at most 2.5 mg/dL")
    private double creatinine;    // Creatinine level (mg/dL)

    @Positive(message = "Testosterone to Epitestosterone ratio must be positive")
    @Digits(integer = 1, fraction = 2, message = "Testosterone to Epitestosterone ratio must be a valid number with up to 1 digit before and 2 after the decimal point")
    @DecimalMin(value = "0.1", message = "Testosterone to Epitestosterone ratio must be at least 0.1")
    @DecimalMax(value = "10.0", message = "Testosterone to Epitestosterone ratio must be at most 10.0")
    @JsonProperty("tEratio")
    private double tEratio;      // Testosterone to Epitestosterone ratio

    private boolean nandrolone;   // Presence of nandrolone (positive/negative)

    private boolean stimulants;   // Presence of stimulants (positive/negative)

    private Status condition;   // Result of the doping test (POSITIVE/NEGATIVE/SUSPICIOUS)

}
