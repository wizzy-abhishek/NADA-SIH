package com.ai.aiml10.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@ToString
@RequiredArgsConstructor
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

}
