package com.example.companyms.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CompanyDTO {
    private Long id;
    @NotBlank
    private String companyName;
    @Min(0)
    @NotNull
    private Double turnover;
    @NotBlank
    private String ceo;

    @NotBlank
    private String boardOfDirectors;

    @NotBlank
    private String companyBrief;

    private boolean isDeactivated = false;


    @Min(0)
    @NotNull
    private Long sectorId;

    private List<String> stockExchangeList = new ArrayList<>();


}
