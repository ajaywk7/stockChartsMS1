package com.example.companyms.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockDetailId implements Serializable {

    private Long companyId;
    private String stockExchangeName;
    private LocalDateTime time;

}
