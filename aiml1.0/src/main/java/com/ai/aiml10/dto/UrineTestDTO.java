package com.ai.aiml10.dto;

import com.ai.aiml10.enums.Status;
import jakarta.validation.constraints.*;

import java.util.Date;
import java.util.Objects;

public class UrineTestDTO {

    @NotBlank(message = "Urine Test ID cant be blank")
    private String testId;        // Unique identifier for the test

    @NotBlank(message = "Athletes ID for respective Urine Test is mandatory")
    private String athleteId;     // Reference to the Athlete

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
    private double tEratio;      // Testosterone to Epitestosterone ratio

    private boolean nandrolone;   // Presence of nandrolone (positive/negative)

    private boolean stimulants;   // Presence of stimulants (positive/negative)

    private Status condition;   // Result of the doping test (POSITIVE/NEGATIVE/SUSPICIOUS)

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(String athleteId) {
        this.athleteId = athleteId;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public double getSpecificGravity() {
        return specificGravity;
    }

    public void setSpecificGravity(double specificGravity) {
        this.specificGravity = specificGravity;
    }

    public double getCreatinine() {
        return creatinine;
    }

    public void setCreatinine(double creatinine) {
        this.creatinine = creatinine;
    }

    public double gettEratio() {
        return tEratio;
    }

    public void settEratio(double tEratio) {
        this.tEratio = tEratio;
    }

    public boolean isNandrolone() {
        return nandrolone;
    }

    public void setNandrolone(boolean nandrolone) {
        this.nandrolone = nandrolone;
    }

    public boolean isStimulants() {
        return stimulants;
    }

    public void setStimulants(boolean stimulants) {
        this.stimulants = stimulants;
    }

    public Status getCondition() {
        return condition;
    }

    public void setCondition(Status condition) {
        this.condition = condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrineTestDTO that = (UrineTestDTO) o;
        return Double.compare(specificGravity, that.specificGravity) == 0 && Double.compare(creatinine, that.creatinine) == 0 && tEratio == that.tEratio && nandrolone == that.nandrolone && stimulants == that.stimulants && Objects.equals(testId, that.testId) && Objects.equals(athleteId, that.athleteId) && Objects.equals(testDate, that.testDate) && condition == that.condition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, athleteId, testDate, specificGravity, creatinine, tEratio, nandrolone, stimulants, condition);
    }

}
