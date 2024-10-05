package com.ai.aiml10.entity;

import com.ai.aiml10.enums.Gender;
import com.ai.aiml10.enums.Sport;
import com.ai.aiml10.enums.Status;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@ToString
@RequiredArgsConstructor
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

}
