package com.ai.aiml10.dto;

import com.ai.aiml10.enums.Status;

import java.util.Date;
import java.util.Objects;

public class UrineTestDTO {

    private String testId;        // Unique identifier for the test

    private String athleteId;     // Reference to the Athlete

    private Date testDate;        // Date of the test

    private double specificGravity; // Urine specific gravity

    private double creatinine;    // Creatinine level (mg/dL)

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
