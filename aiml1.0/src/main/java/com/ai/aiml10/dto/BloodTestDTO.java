package com.ai.aiml10.dto;

import com.ai.aiml10.enums.Status;

import java.util.Date;
import java.util.Objects;

public class BloodTestDTO {

    private String testId;        // Unique identifier for the test

    private String athleteId;     // Reference to the Athlete

    private Date testDate;        // Date of the test

    private double hemoglobin;    // Hemoglobin level (g/dL)  Normal Range: Men: 13.5 to 17.5 g/dL Women: 12.0 to 15.5 g/dL

    private double hematocrit;    // Hematocrit percentage (%)  Normal Range:Men: 41% to 50% Women: 36% to 44%

    private double redBloodCells; // Red blood cell count (million cells/µL)

    private double reticulocytes; // Reticulocytes percentage (%) Normal Range: 0.5% to 2.5% of total red blood cells.

    private double epoLevel;      // Erythropoietin hormone level (mIU/mL)  Normal Range: 4 to 24 mIU/mL

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
