package com.ai.aiml10.dto;

import lombok.*;

import java.io.Serializable;
import java.util.*;

@Data
@Getter
@Setter
@ToString
public class BiologicalPassportDTO implements Serializable {

    private String passportID;    // Unique identifier for the passport
    private String athletesID;     // Reference to the Athlete
    private Set<BloodTestDTO> bloodTests;  // List of blood test results over time
    private Set<UrineTestDTO> urineTests;  // List of urine test results over time
    private Date startDate;       // Start date of monitoring
    private Date lastUpdated;     // Last update date
    private boolean suspicious;   // Whether the passport flags any suspicious patterns

    public BiologicalPassportDTO() {
        this.bloodTests = new HashSet<>(5);
        this.urineTests = new HashSet<>(5);
    }

}
