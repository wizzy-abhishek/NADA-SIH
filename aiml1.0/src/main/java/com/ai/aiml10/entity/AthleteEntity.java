package com.ai.aiml10.entity;

import com.ai.aiml10.enums.Gender;
import com.ai.aiml10.enums.Sport;
import com.ai.aiml10.enums.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "Athletes")
public class AthleteEntity {

    @Id
    private String athletesID ;

    private String athletesName ;

    private Integer age ;

    private Gender gender ;

    private String mobileNum ;

    private Sport sport ;

    private List<String> bloodTestIds;          // List of blood test IDs

    private List<String> urineTestIds;          // List of urine test IDs

    private String biologicalPassportId;        // ID for the biological passport

    private List<String> anomalyDetails;        // Detailed description of detected anomalies

    private String lastAnalyzedTestId;          // ID of the last test analyzed by the AI

    private boolean everBanned ;

    private boolean permanentBanned ;

    private Status suspicion ;

    public AthleteEntity() {
        this.bloodTestIds = new ArrayList<>(5);
        this.urineTestIds = new ArrayList<>(5);
        this.anomalyDetails = new ArrayList<>(5);
    }

    public String getAthletesID() {
        return athletesID;
    }

    public void setAthletesID(String athletesID) {
        this.athletesID = athletesID;
    }

    public String getAthletesName() {
        return athletesName;
    }

    public void setAthletesName(String athletesName) {
        this.athletesName = athletesName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public List<String> getBloodTestIds() {
        return bloodTestIds;
    }

    public void setBloodTestIds(List<String> bloodTestIds) {
        this.bloodTestIds = bloodTestIds;
    }

    public List<String> getUrineTestIds() {
        return urineTestIds;
    }

    public void setUrineTestIds(List<String> urineTestIds) {
        this.urineTestIds = urineTestIds;
    }

    public String getBiologicalPassportId() {
        return biologicalPassportId;
    }

    public void setBiologicalPassportId(String biologicalPassportId) {
        this.biologicalPassportId = biologicalPassportId;
    }

    public List<String> getAnomalyDetails() {
        return anomalyDetails;
    }

    public void setAnomalyDetails(List<String> anomalyDetails) {
        this.anomalyDetails = anomalyDetails;
    }

    public String getLastAnalyzedTestId() {
        return lastAnalyzedTestId;
    }

    public void setLastAnalyzedTestId(String lastAnalyzedTestId) {
        this.lastAnalyzedTestId = lastAnalyzedTestId;
    }

    public boolean isEverBanned() {
        return everBanned;
    }

    public void setEverBanned(boolean everBanned) {
        this.everBanned = everBanned;
    }

    public boolean isPermanentBanned() {
        return permanentBanned;
    }

    public void setPermanentBanned(boolean permanentBanned) {
        this.permanentBanned = permanentBanned;
    }

    public Status getSuspicion() {
        return suspicion;
    }

    public void setSuspicion(Status suspicion) {
        this.suspicion = suspicion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AthleteEntity that = (AthleteEntity) o;
        return everBanned == that.everBanned && permanentBanned == that.permanentBanned && Objects.equals(athletesID, that.athletesID) && Objects.equals(athletesName, that.athletesName) && Objects.equals(age, that.age) && gender == that.gender && Objects.equals(mobileNum, that.mobileNum) && sport == that.sport && Objects.equals(bloodTestIds, that.bloodTestIds) && Objects.equals(urineTestIds, that.urineTestIds) && Objects.equals(biologicalPassportId, that.biologicalPassportId) && Objects.equals(anomalyDetails, that.anomalyDetails) && Objects.equals(lastAnalyzedTestId, that.lastAnalyzedTestId) && suspicion == that.suspicion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(athletesID, athletesName, age, gender, mobileNum, sport, bloodTestIds, urineTestIds, biologicalPassportId, anomalyDetails, lastAnalyzedTestId, everBanned, permanentBanned, suspicion);
    }
}
