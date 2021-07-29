package com.example.companyms.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AvgResponseDTO {

    private Double min;
    private Double max;
    private Double avg;

}
