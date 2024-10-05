package com.ai.aiml10.entity;

import com.ai.aiml10.enums.Status;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;
@Data
@ToString
@RequiredArgsConstructor
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


}
