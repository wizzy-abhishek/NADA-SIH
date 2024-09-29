package com.ai.aiml10.controller;

import com.ai.aiml10.dto.UrineTestDTO;
import com.ai.aiml10.enums.Status;
import com.ai.aiml10.service.UrineTestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/urineTest")
public class UrineTestController {

    private final UrineTestService urineTestService ;

    public UrineTestController(UrineTestService urineTestService) {
        this.urineTestService = urineTestService;
    }

    @GetMapping("/athlete/{athleteId}")
    public List<UrineTestDTO> findAllUrineTestOfIndividualAthlete(@PathVariable String athleteId){
        return urineTestService.findAllUrineTestOfIndividual(athleteId);
    }

    @GetMapping("/{urineTestId}")
    public UrineTestDTO findParticularUrineTest(@PathVariable String urineTestId){
        return urineTestService.findUrineTestById(urineTestId);
    }

    @PostMapping
    public UrineTestDTO addUrineTestRequest(@RequestBody UrineTestDTO urineTestDTO ){
        return urineTestService.addNewUrineTest(urineTestDTO);
    }

    @GetMapping("/status/{status}")
    public List<UrineTestDTO> getUrineTestByStatus(@PathVariable("status")Status status){
        return urineTestService.findAllUrineTestByStatus(status);
    }
}
