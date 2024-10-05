package com.ai.aiml10.service;

import com.ai.aiml10.dto.SignUpDTO;
import com.ai.aiml10.dto.UserDTO;
import com.ai.aiml10.entity.UserEntity;
import com.ai.aiml10.exceptions.ResourceNotFoundException;
import com.ai.aiml10.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository ;
    private final ModelMapper modelMapper ;
    private final PasswordEncoder passwordEncoder ;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public UserDetails loadUserByUsername(String username){
        return userRepository.findByEmailIgnoreCase(username).orElseThrow(() -> new BadCredentialsException("User with Email : " + username + " not found"));
    }

    public UserEntity findUserById(String userId){
       return userRepository.findById(userId)
                .orElseThrow(()-> new BadCredentialsException("User with id : " + userId + " not found")) ;
    }

    public UserDTO signUp(SignUpDTO signUpDTO){

        Optional<UserEntity> user = userRepository.findByEmailIgnoreCase(signUpDTO.getEmail());

        if (user.isPresent()){
            throw new BadCredentialsException("Email already present " + signUpDTO.getEmail());
        }

        UserEntity toBeCreatedUser = modelMapper.map(signUpDTO , UserEntity.class);
        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));

        UserEntity savingUser = userRepository.save(toBeCreatedUser);
        return modelMapper.map(savingUser , UserDTO.class);

    }
}
