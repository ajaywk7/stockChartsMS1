package com.example.companyms.repos;

import com.example.companyms.Entities.CompanyToStockExchangeMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockExchangeMapRepo extends JpaRepository<CompanyToStockExchangeMap,String> {
    List<CompanyToStockExchangeMap> findBySectorId(long sectorId);
}
