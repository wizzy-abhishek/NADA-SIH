package com.ai.aiml10.controller;

import com.ai.aiml10.dto.BloodTestDTO;
import com.ai.aiml10.enums.Status;
import com.ai.aiml10.service.BloodTestService;
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
    public List<BloodTestDTO> findForParticularAthlete(@PathVariable String athleteId) {
        return bloodTestService.findAllBloodTestReportOfAnAthlete(athleteId);
    }

    @GetMapping("/{bloodTestId}")
    public BloodTestDTO findParticularBloodTest(@PathVariable String bloodTestId){
        return bloodTestService.findBloodTestById(bloodTestId);
    }

    @PostMapping
    public BloodTestDTO assignBloodTestToAthlete(@RequestBody BloodTestDTO bloodTestDTO){
        return bloodTestService.addNewBloodTest(bloodTestDTO);
    }

    @GetMapping("/status/{status}")
    public List<BloodTestDTO> getBloodTestByStatus(@PathVariable("status") Status status){
        return bloodTestService.findAllBloodReportAccordingStatus(status);
    }

}
