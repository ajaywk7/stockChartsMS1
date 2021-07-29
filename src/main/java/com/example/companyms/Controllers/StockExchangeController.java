package com.example.companyms.Controllers;

import com.example.companyms.DTO.CompanyDTO;
import com.example.companyms.Entities.Company;
import com.example.companyms.Entities.CompanyToStockExchangeMap;
import com.example.companyms.Entities.StockExchange;
import com.example.companyms.Exceptions.SuccessResponse;
import com.example.companyms.Services.StockExchangeService;
import com.example.companyms.Services.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/stockexchange")
public class StockExchangeController {

    @Autowired
    StockExchangeService stockExchangeService;

    @PostMapping
    public ResponseEntity<StockExchange> create(@Valid @RequestBody StockExchange stockExchange) throws Exception{
        StockExchange result = stockExchangeService.create(stockExchange);
        return new ResponseEntity<StockExchange>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<StockExchange> retrieve(@RequestParam Long id) throws Exception{
        return new ResponseEntity<StockExchange>(stockExchangeService.retrive(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StockExchange>> retrieveAll() throws Exception{
        return new ResponseEntity<List<StockExchange>>(stockExchangeService.retriveAll(), HttpStatus.OK);
    }

    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDTO>> retrieveAllCompanies(@RequestParam Long id) throws Exception{
        return new ResponseEntity<List<CompanyDTO>>(stockExchangeService.retriveAllCompanies(id), HttpStatus.OK);
    }

    @GetMapping("/addCompany")
    public ResponseEntity<SuccessResponse> addCompany(@RequestParam Long companyId, @RequestParam Long stockExchangeId) throws Exception{
        stockExchangeService.addCompany(companyId,stockExchangeId);
        return new ResponseEntity<SuccessResponse>(new SuccessResponse("Mapped successfully"), HttpStatus.OK);
    }

}
