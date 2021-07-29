package com.example.companyms.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
public class StockExchange {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false,unique = true)
    private String Name;

    @Column(nullable = false)
    private String brief;

    @OneToMany(mappedBy="stockexchange",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<CompanyToStockExchangeMap> companyToStockExchangeMaps;



}
