package com.example.companyms.Services;

import com.example.companyms.DTO.AvgResponseDTO;
import com.example.companyms.DTO.StockDetailDTO;
import com.example.companyms.DTO.StockDetailWrapper;
import com.example.companyms.Entities.StockDetail;
import com.example.companyms.Entities.StockDetailId;
import com.example.companyms.Entities.StockExchange;
import com.example.companyms.repos.StockDetailRepo;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockDetailService {

    @Autowired
    StockDetailRepo stockDetailRepo;

    @Autowired
    StockExchangeService stockExchangeService;

    public StockDetail toEntity(StockDetailDTO stockDetailDTO){
        StockDetail stockDetail= new StockDetail();
        StockDetailId stockDetailId = new
                StockDetailId(
                        stockDetailDTO.getCompanyId(),
                        stockDetailDTO.getStockExchangeName(),
                        stockDetailDTO.getTime());
        stockDetail.setId(stockDetailId);
        stockDetail.setStockPrice(stockDetailDTO.getStockPrice());
        return stockDetail;
    }

    public StockDetailDTO toDTO(StockDetail stockDetail){
        StockDetailDTO stockDetailDTO = new StockDetailDTO();
        stockDetailDTO.setCompanyId(stockDetail.getId().getCompanyId());
        stockDetailDTO.setStockExchangeName(stockDetail.getId().getStockExchangeName());
        stockDetailDTO.setTime(stockDetail.getId().getTime());
        stockDetailDTO.setStockPrice(stockDetail.getStockPrice());
        return stockDetailDTO;
    }

    public void importData(List<StockDetailDTO> stockDetailDTOS) throws Exception{
        List<String> companyCodes = stockDetailDTOS.stream().map(sd -> sd.getCompanyCode()).collect(Collectors.toList());
        stockExchangeService.validateCompanyCodes(companyCodes);
        List<StockDetail> stockDetails =
                stockDetailDTOS.
                        stream().map(sd-> toEntity(sd))
                        .collect(Collectors.toList());
        stockDetailRepo.saveAll(stockDetails);
    }

    public List<StockDetailDTO> getAll(){
        return stockDetailRepo.findAll().stream().map(sd -> toDTO(sd)).collect(Collectors.toList());
    }

    public List<StockDetailDTO> getCompanyStockData(Long companyId,LocalDateTime startTime,LocalDateTime endTime){
        return stockDetailRepo.getCompanyStockData(companyId,startTime,endTime).stream()
                .map(sd -> toDTO(sd)).collect(Collectors.toList());
    }

    public AvgResponseDTO getCompanyAvg(Long companyId,LocalDateTime startTime,LocalDateTime endTime){
        Double avg = stockDetailRepo.getCompanyAvg(companyId,startTime,endTime);
        Double min =  stockDetailRepo.getCompanyMin(companyId,startTime,endTime);
        Double max =  stockDetailRepo.getCompanyMax(companyId,startTime,endTime);
        return new AvgResponseDTO(min,max,avg);
    }

    public AvgResponseDTO getSectorAvg(Long sectorId,LocalDateTime startTime,LocalDateTime endTime) throws Exception{
        List<Long> companyIds = stockExchangeService.getCompanyIdOfSector(sectorId);
        Double min = stockDetailRepo.getSectorMin(companyIds,startTime,endTime);
        Double max = stockDetailRepo.getSectorMax(companyIds,startTime,endTime);
        Double avg = stockDetailRepo.getSectorAvg(companyIds,startTime,endTime);
        return new AvgResponseDTO(min,max,avg);


    }

}
