package com.example.companyms.Services;

import com.example.companyms.DTO.CompanyDTO;
import com.example.companyms.DTO.UserDTO;
import com.example.companyms.Entities.Company;
import com.example.companyms.Entities.UserData;
import com.example.companyms.repos.UserRepo;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public UserDTO toDto(UserData userData){
        UserDTO userDTO = modelMapper.map(userData, UserDTO.class);
        return userDTO;
    }

    public UserData toEntity( UserDTO  userDTO){
        UserData userData = modelMapper.map( userDTO,UserData.class);
        return userData;
    }

    public UserDTO register(UserDTO userDTO){
        userDTO.setRole("ROLE_USER");
        return toDto(userRepo.save(toEntity(userDTO)));
    }


}
