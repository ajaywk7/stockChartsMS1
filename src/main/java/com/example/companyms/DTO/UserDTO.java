package com.example.companyms.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;



@Getter
@Setter
public class UserDTO {




    private Long id;

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private String role;

    private String email;

    private boolean emailVerified = true;

}