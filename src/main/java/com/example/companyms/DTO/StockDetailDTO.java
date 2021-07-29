package com.example.companyms.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
public class StockDetailDTO {
    @NotNull
    @Min(1)
    private Long companyId;
    @NotBlank
    private String stockExchangeName;
    @NotNull
    @Min(1)
    private Double stockPrice;
    @NotNull
    private LocalDateTime time;

    public String getCompanyCode() {
        return this.companyId.toString()+"."+this.stockExchangeName;
    }
}
