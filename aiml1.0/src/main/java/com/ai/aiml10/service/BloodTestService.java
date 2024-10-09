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
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public BloodTestDTO addNewBloodTest(BloodTestDTO bloodTestDTO){

        if(!doesAthleteExist(bloodTestDTO.getAthletesID())){
            throw new ResourceNotFoundException("Athlete with id :" + bloodTestDTO.getAthletesID() + " unavailable") ;
        }

        if(!doesBloodTestIdExist(bloodTestDTO.getTestID())){
            throw new DuplicateIdException("BLOOD REPORT WITH ID : "+ bloodTestDTO.getTestID() + " ALREADY PRESENT");
        }

        BloodTestEntity bloodTestEntity = modelMapper.map(bloodTestDTO , BloodTestEntity.class);
        bloodTestRepo.save(bloodTestEntity);

        System.out.println(bloodTestEntity.getTestID());

        AthleteEntity athleteEntity = modelMapper.map(athleteService.findAthleteById(bloodTestEntity.getAthletesID()) , AthleteEntity.class);

        BiologicalPassportEntity biologicalPassportEntity = modelMapper.map( biologicalPassportService.getBiologicalPassport(bloodTestEntity.getAthletesID()) , BiologicalPassportEntity.class ) ;
        biologicalPassportEntity.getBloodTests().add(bloodTestEntity);
        biologicalPassportEntity.setLastUpdated(new Date());
        biologicalPassportService.updateExistingBloodAndUrineList(modelMapper.map(biologicalPassportEntity , BiologicalPassportDTO.class));

        athleteEntity.getBloodTestIds().add(bloodTestEntity.getTestID());
        athleteService.updateAthleteEntity(modelMapper.map(athleteEntity , AthleteDTO.class));

        return findBloodTestById(bloodTestEntity.getTestID()) ;
    }

    @Transactional()
    public List<BloodTestDTO> findAllBloodTestReportOfAnAthlete(String athleteId){

        List<BloodTestEntity> bloodTestEntityList =  bloodTestRepo.findByAthletesID(athleteId);

        return bloodTestEntityList.stream()
                .map(bloodTestEntity -> modelMapper.map(bloodTestEntity , BloodTestDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public BloodTestDTO findBloodTestById(String bloodTestId ){
        return modelMapper.map(bloodTestRepo.findById(bloodTestId).orElseThrow(() -> new ResourceNotFoundException("Blood test with id : " + bloodTestId + " unavailable")) , BloodTestDTO.class);
    }

    @Transactional
    public List<BloodTestDTO> findAllBloodReportAccordingStatus(Status status){
        List<BloodTestEntity> bloodTestEntityList = bloodTestRepo.findByCondition(status);

        return bloodTestEntityList.stream()
                .map(bloodTestEntity -> modelMapper.map(bloodTestEntity , BloodTestDTO.class))
                .collect(Collectors.toList());
    }
}