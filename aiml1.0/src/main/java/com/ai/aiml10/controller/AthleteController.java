package com.ai.aiml10.controller;

import com.ai.aiml10.dto.AthleteDTO;
import com.ai.aiml10.enums.Status;
import com.ai.aiml10.service.AthleteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/athlete")
public class AthleteController {

    private final AthleteService athleteService ;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @PostMapping()
    public AthleteDTO addAthlete(@RequestBody AthleteDTO athleteDTO){

        System.out.println("In athlete controller");

        return athleteService.addNewAthlete(athleteDTO);
    }

    @GetMapping()
    public List<AthleteDTO> getAllAthletes(){
        return athleteService.getAllAthletes();
    }

    @PatchMapping("/{athletesID}")
    public  AthleteDTO updatePartially(@PathVariable String athletesID ,  @RequestBody Map<String, Object> updateDetails){
        return athleteService.updatePartialInfoOfAthlete(athletesID , updateDetails) ;
    }

    @GetMapping("/status/{status}")
    public List<AthleteDTO> getByStatus(@PathVariable("status") Status status){
        return athleteService.findAllByStatus(status);
    }
}
