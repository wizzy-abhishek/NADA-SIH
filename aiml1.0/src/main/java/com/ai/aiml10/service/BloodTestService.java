package com.ai.aiml10.service;

import com.ai.aiml10.dto.AthleteDTO;
import com.ai.aiml10.dto.BiologicalPassportDTO;
import com.ai.aiml10.dto.BloodTestDTO;
import com.ai.aiml10.entity.AthleteEntity;
import com.ai.aiml10.entity.BiologicalPassportEntity;
import com.ai.aiml10.entity.BloodTestEntity;
import com.ai.aiml10.enums.Status;
import com.ai.aiml10.exceptions.DuplicateIdException;
import com.ai.aiml10.exceptions.ResourceNotFoundException;
import com.ai.aiml10.repo.BloodTestRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BloodTestService {

    private final BloodTestRepo bloodTestRepo ;
    private final ModelMapper modelMapper ;
    private final AthleteService athleteService ;
    private final BiologicalPassportService biologicalPassportService ;

    public BloodTestService(BloodTestRepo bloodTestRepo, ModelMapper modelMapper, AthleteService athleteService, BiologicalPassportService biologicalPassportService) {
        this.bloodTestRepo = bloodTestRepo;
        this.modelMapper = modelMapper;
        this.athleteService = athleteService;
        this.biologicalPassportService = biologicalPassportService;
    }

    public boolean doesAthleteExist(String athleteId){
        return athleteService.doesExist(athleteId) ;
    }

    public boolean doesBloodTestIdExist(String bloodTestId){
        return bloodTestRepo.findById(bloodTestId).isEmpty();
    }

    public BloodTestDTO addNewBloodTest(BloodTestDTO bloodTestDTO){

        System.out.println("In addNewBloodTest Service");

        if(!doesAthleteExist(bloodTestDTO.getAthleteId())){
            System.out.println("Athlete doesn't exist");
            throw new ResourceNotFoundException("Athlete with id :" + bloodTestDTO.getAthleteId() + " unavailable") ;
        }

        if(!doesBloodTestIdExist(bloodTestDTO.getTestId())){
            System.out.println("Blood Test ID already exists");
            throw new DuplicateIdException("BLOOD REPORT WITH ID : "+ bloodTestDTO.getTestId() + " ALREADY PRESENT");
        }

        BloodTestEntity bloodTestEntity = modelMapper.map(bloodTestDTO , BloodTestEntity.class);
        bloodTestRepo.save(bloodTestEntity);

        System.out.println(bloodTestEntity.getTestId());

        AthleteEntity athleteEntity = modelMapper.map(athleteService.findAthleteById(bloodTestEntity.getAthleteId()) , AthleteEntity.class);
        athleteEntity.getBloodTestIds().add(bloodTestEntity.getTestId());
        athleteService.updateAthleteEntity(modelMapper.map(athleteEntity , AthleteDTO.class));

        BiologicalPassportEntity biologicalPassportEntity = modelMapper.map( biologicalPassportService.getBiologicalPassport(bloodTestEntity.getAthleteId()) , BiologicalPassportEntity.class ) ;
        biologicalPassportEntity.getBloodTests().add(bloodTestEntity);
        biologicalPassportEntity.setLastUpdated(new Date());
        biologicalPassportService.updateExistingBloodAndUrineList(modelMapper.map(biologicalPassportEntity , BiologicalPassportDTO.class));

        return findBloodTestById(bloodTestEntity.getTestId()) ;
    }

    public List<BloodTestDTO> findAllBloodTestReportOfAnAthlete(String athleteId){

        List<BloodTestEntity> bloodTestEntityList =  bloodTestRepo.findByAthleteId(athleteId);

        return bloodTestEntityList.stream()
                .map(bloodTestEntity -> modelMapper.map(bloodTestEntity , BloodTestDTO.class))
                .collect(Collectors.toList());
    }

    public BloodTestDTO findBloodTestById(String bloodTestId ){
        return modelMapper.map(bloodTestRepo.findById(bloodTestId).orElseThrow(() -> new ResourceNotFoundException("Blood test with id : " + bloodTestId + " unavailable")) , BloodTestDTO.class);
    }

    public List<BloodTestDTO> findAllBloodReportAccordingStatus(Status status){
        List<BloodTestEntity> bloodTestEntityList = bloodTestRepo.findByCondition(status);

        return bloodTestEntityList.stream()
                .map(bloodTestEntity -> modelMapper.map(bloodTestEntity , BloodTestDTO.class))
                .collect(Collectors.toList());
    }
}