package com.ai.aiml10.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class BiologicalPassportDTO {

    private String passportId;    // Unique identifier for the passport
    private String athleteId;     // Reference to the Athlete
    private List<BloodTestDTO> bloodTests;  // List of blood test results over time
    private List<UrineTestDTO> urineTests;  // List of urine test results over time
    private Date startDate;       // Start date of monitoring
    private Date lastUpdated;     // Last update date
    private boolean suspicious;   // Whether the passport flags any suspicious patterns

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

    public List<BloodTestDTO> getBloodTests() {
        return bloodTests;
    }

    public void setBloodTests(List<BloodTestDTO> bloodTests) {
        this.bloodTests = bloodTests;
    }

    public List<UrineTestDTO> getUrineTests() {
        return urineTests;
    }

    public void setUrineTests(List<UrineTestDTO> urineTests) {
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
        BiologicalPassportDTO that = (BiologicalPassportDTO) o;
        return suspicious == that.suspicious && Objects.equals(passportId, that.passportId) && Objects.equals(athleteId, that.athleteId) && Objects.equals(bloodTests, that.bloodTests) && Objects.equals(urineTests, that.urineTests) && Objects.equals(startDate, that.startDate) && Objects.equals(lastUpdated, that.lastUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passportId, athleteId, bloodTests, urineTests, startDate, lastUpdated, suspicious);
    }
}
