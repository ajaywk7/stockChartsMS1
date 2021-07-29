package com.example.companyms.repos;

import com.example.companyms.Entities.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockExchangeRepo extends JpaRepository<StockExchange,Long> {

}