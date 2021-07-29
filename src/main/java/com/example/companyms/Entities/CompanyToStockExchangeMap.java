package com.example.companyms.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CompanyToStockExchangeMap {
    @Id
    private String id;

    @Column(nullable = false)
    private Long sectorId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    private StockExchange stockexchange;

    public CompanyToStockExchangeMap(String id ,Long sectorId, Company company, StockExchange stockexchange) {
        this.id = id;
        this.sectorId = sectorId;
        this.company = company;
        this.stockexchange = stockexchange;
    }
}
