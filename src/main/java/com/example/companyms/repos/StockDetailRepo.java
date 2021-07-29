package com.example.companyms.repos;

import com.example.companyms.Entities.Company;
import com.example.companyms.Entities.StockDetail;
import com.example.companyms.Entities.StockDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StockDetailRepo extends JpaRepository<StockDetail, StockDetailId> {
    @Query("select s from StockDetail s where s.id.companyId = :companyId " +
            "and s.id.time between :startTime and :endTime")
    List<StockDetail> getCompanyStockData(Long companyId,
                                         LocalDateTime startTime,
                                            LocalDateTime endTime );

    @Query("select avg(s.stockPrice) from StockDetail s where s.id.companyId = :companyId " +
            "and s.id.time between :startTime and :endTime")
    Double getCompanyAvg(@Param("companyId") Long companyId,
                                    @Param("startTime") LocalDateTime startTime,
                                    @Param("endTime") LocalDateTime endTime );

    @Query("select min(s.stockPrice) from StockDetail s where s.id.companyId = :companyId " +
            "and s.id.time between :startTime and :endTime")
    Double getCompanyMin(@Param("companyId") Long companyId,
                         @Param("startTime") LocalDateTime startTime,
                         @Param("endTime") LocalDateTime endTime );

    @Query("select max(s.stockPrice) from StockDetail s where s.id.companyId = :companyId " +
            "and s.id.time between :startTime and :endTime")
    Double getCompanyMax(@Param("companyId") Long companyId,
                         @Param("startTime") LocalDateTime startTime,
                         @Param("endTime") LocalDateTime endTime );

    @Query("select avg(s.stockPrice) from StockDetail s where s.id.companyId in :companyIds " +
            "and s.id.time between :startTime and :endTime")
    Double getSectorAvg(@Param("companyIds") List<Long> companyIds,
                         @Param("startTime") LocalDateTime startTime,
                         @Param("endTime") LocalDateTime endTime );

    @Query("select min(s.stockPrice) from StockDetail s where s.id.companyId in :companyIds " +
            "and s.id.time between :startTime and :endTime")
    Double getSectorMin(@Param("companyIds") List<Long> companyIds,
                        @Param("startTime") LocalDateTime startTime,
                        @Param("endTime") LocalDateTime endTime );

    @Query("select max(s.stockPrice) from StockDetail s where s.id.companyId in :companyIds " +
            "and s.id.time between :startTime and :endTime")
    Double getSectorMax(@Param("companyIds") List<Long> companyIds,
                        @Param("startTime") LocalDateTime startTime,
                        @Param("endTime") LocalDateTime endTime );

}
