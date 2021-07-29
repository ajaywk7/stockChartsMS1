package com.example.companyms.repos;

import com.example.companyms.Entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Long> {

    @Query("select c from Company c where lower(c.companyName) like concat('%',lower(:searchText),'%') ")
    List<Company> search(@Param("searchText") String searchText);
}
