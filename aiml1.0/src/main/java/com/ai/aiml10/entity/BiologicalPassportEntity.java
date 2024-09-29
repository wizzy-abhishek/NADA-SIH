package com.ai.aiml10.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Document(collection = "BiologicalPassport")
public class BiologicalPassportEntity {

    @Id
    private String passportId;    // Unique identifier for the passport

    private String athleteId;     // Reference to the Athlete

    private List<BloodTestEntity> bloodTests;  // List of blood test results over time

    private List<UrineTestEntity> urineTests;  // List of urine test results over time

    private Date startDate;       // Start date of monitoring

    private Date lastUpdated;     // Last update date

    private boolean suspicious;   // Whether the passport flags any suspicious patterns

    public BiologicalPassportEntity() {
        this.bloodTests  = new ArrayList<>(5);
        this.urineTests = new ArrayList<>(5);
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(String athleteId) {
        this.athleteId = athleteId;
    }

    public List<BloodTestEntity> getBloodTests() {
        return bloodTests;
    }

    public void setBloodTests(List<BloodTestEntity> bloodTests) {
        this.bloodTests = bloodTests;
    }

    public List<UrineTestEntity> getUrineTests() {
        return urineTests;
    }

    public void setUrineTests(List<UrineTestEntity> urineTests) {
        this.urineTests = urineTests;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean isSuspicious() {
        return suspicious;
    }

    public void setSuspicious(boolean suspicious) {
        this.suspicious = suspicious;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiologicalPassportEntity that = (BiologicalPassportEntity) o;
        return suspicious == that.suspicious && Objects.equals(passportId, that.passportId) && Objects.equals(athleteId, that.athleteId) && Objects.equals(bloodTests, that.bloodTests) && Objects.equals(urineTests, that.urineTests) && Objects.equals(startDate, that.startDate) && Objects.equals(lastUpdated, that.lastUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passportId, athleteId, bloodTests, urineTests, startDate, lastUpdated, suspicious);
    }
}
