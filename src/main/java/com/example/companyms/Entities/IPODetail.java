package com.example.companyms.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class IPODetail {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Double pricePerShare;

    @Column(nullable = false)
    private Long totalNumberOfShares;

    private LocalDateTime openDateTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Company company;

    @ManyToMany
    @JsonIgnore
    private List<StockExchange> stockExchanges = new ArrayList<>();

}
