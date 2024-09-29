package com.ai.aiml10.entity;

import com.ai.aiml10.enums.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

@Document(collection = "UrineTest")
public class UrineTestEntity {

    @Id
    private String testId;          // Unique identifier for the test

    private String athleteId;       // Reference to the Athlete

    private Date testDate;          // Date of the test

    private double specificGravity; // Urine specific gravity Normal Range: 1.005 to 1.030

    private double creatinine;      // Creatinine level (mg/dL) Normal Range 0.5 to 2.5 mg/dL in urine (varies based on hydration level)

    private double tEratio;         // Testosterone to Epitestosterone Normal ratio Male 1:1 and Female sometimes it might increase due ro PCOS

    private boolean nandrolone;     // Presence of nandrolone (positive/negative)

    private boolean stimulants;     // Presence of stimulants (positive/negative)

    private Status condition;       // Result of the doping test (POSITIVE/NEGATIVE/SUSPICIOUS/UNDER_INVESTIGATION)

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
        UrineTestEntity that = (UrineTestEntity) o;
        return Double.compare(specificGravity, that.specificGravity) == 0 && Double.compare(creatinine, that.creatinine) == 0 && tEratio == that.tEratio && nandrolone == that.nandrolone && stimulants == that.stimulants && Objects.equals(testId, that.testId) && Objects.equals(athleteId, that.athleteId) && Objects.equals(testDate, that.testDate) && condition == that.condition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, athleteId, testDate, specificGravity, creatinine, tEratio, nandrolone, stimulants, condition);
    }

}
