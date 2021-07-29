package com.example.companyms.repos;

import com.example.companyms.Entities.IPODetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPODetailRepo extends JpaRepository<IPODetail,Long> {
}
