package com.ai.aiml10.controller;

import com.ai.aiml10.dto.BloodTestDTO;
import com.ai.aiml10.enums.Status;
import com.ai.aiml10.service.BloodTestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bloodTest")
public class BloodTestController {

    private final BloodTestService bloodTestService ;

    public BloodTestController(BloodTestService bloodTestService) {
        this.bloodTestService = bloodTestService;
    }

    @GetMapping("/athlete/{athleteId}")
    public ResponseEntity<List<BloodTestDTO>> findForParticularAthlete(@PathVariable String athleteId) {
        return ResponseEntity.ok(bloodTestService.findAllBloodTestReportOfAnAthlete(athleteId)) ;
    }

    @GetMapping("/{bloodTestId}")
    public ResponseEntity<BloodTestDTO> findParticularBloodTest(@PathVariable String bloodTestId){
        return ResponseEntity.ok(bloodTestService.findBloodTestById(bloodTestId));
    }

    @PostMapping
    public ResponseEntity<BloodTestDTO> assignBloodTestToAthlete(@RequestBody BloodTestDTO bloodTestDTO){
        return ResponseEntity.ok(bloodTestService.addNewBloodTest(bloodTestDTO)) ;
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<BloodTestDTO>> getBloodTestByStatus(@PathVariable("status") Status status){
        return ResponseEntity.ok(bloodTestService.findAllBloodReportAccordingStatus(status));
    }

}
