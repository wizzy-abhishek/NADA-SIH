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
@Document(collection = "BloodTest")
public class BloodTestEntity {

    @Id
    private String testId;        // Unique identifier for the test

    private String athleteId;     // Reference to the Athlete

    private Date testDate;        // Date of the test

    private double hemoglobin;    // Hemoglobin level (g/dL)  Normal Range: Men: 13.5 to 17.5 g/dL Women: 12.0 to 15.5 g/dL

    private double hematocrit;    // Hematocrit percentage (%)  Normal Range:Men: 41% to 50% Women: 36% to 44%

    private double redBloodCells; // Red blood cell count (million cells/µL)

    private double reticulocytes; // Reticulocytes percentage (%) Normal Range: 0.5% to 2.5% of total red blood cells.

    private double epoLevel;      // Erythropoietin hormone level (mIU/mL)  Normal Range: 4 to 24 mIU/mL

    private Status condition;     // Result of the doping test (POSITIVE/NEGATIVE/SUSPICIOUS)

}
