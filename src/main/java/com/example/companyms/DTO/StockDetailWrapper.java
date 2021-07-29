package com.example.companyms.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class StockDetailWrapper {

    @Valid
    @NotNull
    private List<StockDetailDTO> stockDetails;
}
