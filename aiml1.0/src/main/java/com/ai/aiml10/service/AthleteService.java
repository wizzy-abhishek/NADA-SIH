package com.ai.aiml10.service;

import com.ai.aiml10.dto.AthleteDTO;
import com.ai.aiml10.entity.AthleteEntity;
import com.ai.aiml10.entity.BiologicalPassportEntity;
import com.ai.aiml10.enums.Sport;
import com.ai.aiml10.enums.Status;
import com.ai.aiml10.repo.AthleteRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AthleteService {

    private final AthleteRepo athleteRepo;
    private final ModelMapper modelMapper;
    private final BiologicalPassportService biologicalPassportService ;

    public AthleteService(AthleteRepo athleteRepo, ModelMapper modelMapper, BiologicalPassportService biologicalPassportService) {
        this.athleteRepo = athleteRepo;
        this.modelMapper = modelMapper;
        this.biologicalPassportService = biologicalPassportService;
    }

    public boolean doesExist(String athleteId){

        AthleteEntity athleteEntity = athleteRepo.findById(athleteId).orElse(null);

        if (athleteEntity != null){
            return true ;
        }
        return false ;
    }

    public AthleteDTO addNewAthlete(AthleteDTO athleteDTO) {

        if (doesExist(athleteDTO.getAthletesID())){
            return null ;
        }

        System.out.println("IN SAVE ATHLETE : SERVICE CLASS");

        AthleteEntity athleteEntity = modelMapper.map(athleteDTO, AthleteEntity.class);
        athleteEntity.setAthletesID(athleteEntity.getAthletesID());
        athleteRepo.save(athleteEntity);

        BiologicalPassportEntity biologicalPassportEntity = new BiologicalPassportEntity();
        biologicalPassportEntity.setPassportId(athleteEntity.getAthletesID());
        biologicalPassportEntity.setAthleteId(athleteEntity.getAthletesID());
        biologicalPassportEntity.setStartDate(new Date());
        biologicalPassportEntity.setSuspicious(false);

        biologicalPassportService.addNewBiologicalPassport(biologicalPassportEntity);

        athleteEntity.setBiologicalPassportId(biologicalPassportEntity.getPassportId());
        AthleteEntity savedEntity = athleteRepo.save(athleteEntity);

        return modelMapper.map(savedEntity, AthleteDTO.class);  // Map the saved entity to DTO
    }

    public List<AthleteDTO> getAllAthletes(){
        List<AthleteEntity> listOfAthletesEntity = athleteRepo.findAll();

        return listOfAthletesEntity.stream()
                .map(athleteEntity -> modelMapper.map(athleteEntity, AthleteDTO.class)) //mapping for individual entity
                .collect(Collectors.toList());
    }

    public AthleteDTO updatePartialInfoOfAthlete(String athleteId, Map<String, Object> updateDetails) {
        AthleteEntity athlete = athleteRepo.findById(athleteId)
                .orElseThrow(() -> new RuntimeException("Athlete not found"));

        updateDetails.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(AthleteEntity.class, key);
            if (field != null) {
                field.setAccessible(true);

                // Check if the field is the 'sport' field
                if ("sport".equals(key)) {
                    // Convert the string to the enum type Sport
                    Sport sportEnum = Sport.valueOf((String) value);
                    ReflectionUtils.setField(field, athlete, sportEnum);
                } else {
                    ReflectionUtils.setField(field, athlete, value);
                }
            }
        });

        return modelMapper.map(athleteRepo.save(athlete) , AthleteDTO.class);
    }

    public AthleteDTO findAthleteById(String athleteId){
        System.out.println("In Athlete Service && inside findAthleteById");
        return modelMapper.map(athleteRepo.findById(athleteId).orElse(null) , AthleteDTO.class);
    }

    public void updateAthleteEntity(AthleteDTO athleteDTO){
        System.out.println("In Athlete Service && inside updateAthleteEntity");
        athleteRepo.save(modelMapper.map(athleteDTO , AthleteEntity.class));
    }

    public List<AthleteDTO> findAllByStatus(Status status){

        List<AthleteEntity> athleteEntityList = athleteRepo.findBySuspicion(status);

        return athleteEntityList.stream()
                .map(athleteEntity -> modelMapper.map(athleteEntity , AthleteDTO.class))
                .collect(Collectors.toList());

    }

}