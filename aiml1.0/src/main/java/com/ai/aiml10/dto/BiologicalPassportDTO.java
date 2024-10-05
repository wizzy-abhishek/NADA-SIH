package com.ai.aiml10.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@RequiredArgsConstructor
@ToString
public class BiologicalPassportDTO {

    private String passportId;    // Unique identifier for the passport
    private String athleteId;     // Reference to the Athlete
    private List<BloodTestDTO> bloodTests;  // List of blood test results over time
    private List<UrineTestDTO> urineTests;  // List of urine test results over time
    private Date startDate;       // Start date of monitoring
    private Date lastUpdated;     // Last update date
    private boolean suspicious;   // Whether the passport flags any suspicious patterns

}
