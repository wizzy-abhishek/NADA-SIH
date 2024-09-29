package com.ai.aiml10.dto;

import com.ai.aiml10.enums.Status;
import jakarta.validation.constraints.*;

import java.util.Date;
import java.util.Objects;

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

    public double getHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(double hemoglobin) {
        this.hemoglobin = hemoglobin;
    }

    public double getHematocrit() {
        return hematocrit;
    }

    public void setHematocrit(double hematocrit) {
        this.hematocrit = hematocrit;
    }

    public double getRedBloodCells() {
        return redBloodCells;
    }

    public void setRedBloodCells(double redBloodCells) {
        this.redBloodCells = redBloodCells;
    }

    public double getReticulocytes() {
        return reticulocytes;
    }

    public void setReticulocytes(double reticulocytes) {
        this.reticulocytes = reticulocytes;
    }

    public double getEpoLevel() {
        return epoLevel;
    }

    public void setEpoLevel(double epoLevel) {
        this.epoLevel = epoLevel;
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
        BloodTestDTO that = (BloodTestDTO) o;
        return Double.compare(hemoglobin, that.hemoglobin) == 0 && Double.compare(hematocrit, that.hematocrit) == 0 && Double.compare(redBloodCells, that.redBloodCells) == 0 && Double.compare(reticulocytes, that.reticulocytes) == 0 && Double.compare(epoLevel, that.epoLevel) == 0 && Objects.equals(testId, that.testId) && Objects.equals(athleteId, that.athleteId) && Objects.equals(testDate, that.testDate) && condition == that.condition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, athleteId, testDate, hemoglobin, hematocrit, redBloodCells, reticulocytes, epoLevel, condition);
    }
}
