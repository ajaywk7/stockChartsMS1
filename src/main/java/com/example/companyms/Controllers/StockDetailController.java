package com.example.companyms.Controllers;

import com.example.companyms.DTO.AvgResponseDTO;
import com.example.companyms.DTO.CompanyDTO;
import com.example.companyms.DTO.StockDetailDTO;
import com.example.companyms.DTO.StockDetailWrapper;
import com.example.companyms.Exceptions.SuccessResponse;
import com.example.companyms.Services.StockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/stockDetails")
public class StockDetailController {

    @Autowired
    private StockDetailService stockDetailService;

    @GetMapping
    public ResponseEntity<List<StockDetailDTO>> getAll() throws Exception{
        List<StockDetailDTO> stockDetailDTOList=  stockDetailService.getAll();
        return new ResponseEntity<List<StockDetailDTO>>(stockDetailDTOList, HttpStatus.OK);
    }

    @GetMapping("/company/range")
    public ResponseEntity<List<StockDetailDTO>>
            getCompanyData(@RequestParam Long companyId,
                   @RequestParam String startTime,
                   @RequestParam String endTime) throws Exception
    {
        List<StockDetailDTO> stockDetailDTOList=
                stockDetailService.getCompanyStockData(companyId, LocalDateTime.parse(startTime),
                        LocalDateTime.parse(endTime));
        return new ResponseEntity<List<StockDetailDTO>>(stockDetailDTOList, HttpStatus.OK);
    }


    @GetMapping("/company/avg")
    public ResponseEntity<AvgResponseDTO>
    getCompanyAvg(@RequestParam Long companyId,
                   @RequestParam String startTime,
                   @RequestParam String endTime) throws Exception
    {
        AvgResponseDTO result=  stockDetailService.getCompanyAvg(
                companyId, LocalDateTime.parse(startTime),
                LocalDateTime.parse(endTime)
        );
        return new ResponseEntity<AvgResponseDTO>(result, HttpStatus.OK);
    }

    @GetMapping("/sector/avg")
    public ResponseEntity<AvgResponseDTO>
    getSectorAvg(@RequestParam Long sectorId,
                  @RequestParam String startTime,
                  @RequestParam String endTime) throws Exception
    {
        AvgResponseDTO result=  stockDetailService.getSectorAvg(
                sectorId, LocalDateTime.parse(startTime),
                LocalDateTime.parse(endTime)
        );
        return new ResponseEntity<AvgResponseDTO>(result, HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<SuccessResponse> importData(@Valid @RequestBody StockDetailWrapper stockDetailWrapper) throws Exception{
        stockDetailService.importData(stockDetailWrapper.getStockDetails());
        return new ResponseEntity<SuccessResponse>(new SuccessResponse("Successfully updloaded"), HttpStatus.OK);
    }


}
