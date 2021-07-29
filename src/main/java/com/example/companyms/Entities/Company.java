package com.example.companyms.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private Double turnover;

    @Column(nullable = false)
    private String ceo;

    @Column(nullable = false)
    private String boardOfDirectors;

    @Column(nullable = false)
    private String companyBrief;

    private boolean isDeactivated = false;

    @ManyToOne(optional = false)
    private Sector sector;

    @OneToMany(mappedBy = "company")
    private List<CompanyToStockExchangeMap> companyToStockExchangeMaps;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private IPODetail ipoDetaiil;



}
