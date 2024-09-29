package com.ai.aiml10.service;

import com.ai.aiml10.dto.BiologicalPassportDTO;
import com.ai.aiml10.entity.BiologicalPassportEntity;
import com.ai.aiml10.repo.BiologicalPassportRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class BiologicalPassportService {

    private final BiologicalPassportRepo biologicalPassportRepo ;
    private final ModelMapper modelMapper ;

    public BiologicalPassportService(BiologicalPassportRepo biologicalPassportRepo, ModelMapper modelMapper) {
        this.biologicalPassportRepo = biologicalPassportRepo;
        this.modelMapper = modelMapper;
    }



    public boolean doesBiologicalPassportExists(String biologicalPassportId){
        return biologicalPassportRepo.existsById(biologicalPassportId);
    }

    public BiologicalPassportDTO addNewBiologicalPassport( BiologicalPassportEntity biologicalPassportEntity){

        boolean doesExist = doesBiologicalPassportExists(biologicalPassportEntity.getPassportId());

        if(doesExist){
            return null;
        }

        System.out.println("In Biological Passport addition method");
        BiologicalPassportEntity savedBiologicalPassportEntity = biologicalPassportRepo.save(biologicalPassportEntity);

        return  modelMapper.map(savedBiologicalPassportEntity, BiologicalPassportDTO.class);
    }

    public BiologicalPassportDTO getBiologicalPassport(String passportId){
        BiologicalPassportEntity getBiologicalPassport = biologicalPassportRepo.findById(passportId).orElse(null);
        return modelMapper.map(getBiologicalPassport , BiologicalPassportDTO.class) ;
    }

    public BiologicalPassportDTO updateBiologicalPassportPartially(String passportId , Map<String, Object> updateDetails){

        boolean doesExist = doesBiologicalPassportExists(passportId);

        if(!doesExist){
            return null ;
        }

        BiologicalPassportEntity biologicalPassportEntity = biologicalPassportRepo.findById(passportId).get();

        updateDetails.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(BiologicalPassportEntity.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, biologicalPassportEntity, value);
            }
        });
        return modelMapper.map(biologicalPassportRepo.save(biologicalPassportEntity) , BiologicalPassportDTO.class);
    }

    public BiologicalPassportDTO updateExistingBloodAndUrineList(BiologicalPassportDTO biologicalPassportDTO){

        BiologicalPassportEntity biologicalPassportEntity = modelMapper.map(biologicalPassportDTO , BiologicalPassportEntity.class);
        biologicalPassportRepo.save(biologicalPassportEntity);
        return modelMapper.map(biologicalPassportRepo.findById(biologicalPassportDTO.getPassportId()) , BiologicalPassportDTO.class) ;
    }
}