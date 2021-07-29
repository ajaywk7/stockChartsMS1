package com.example.companyms.Services;

import com.example.companyms.DTO.CompanyDTO;
import com.example.companyms.Entities.Company;
import com.example.companyms.Entities.CompanyToStockExchangeMap;
import com.example.companyms.Entities.Sector;
import com.example.companyms.Entities.StockExchange;
import com.example.companyms.repos.StockExchangeMapRepo;
import com.example.companyms.repos.StockExchangeRepo;
import com.example.companyms.repos.StockExchangeRepo;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockExchangeService {
    @Autowired
    private StockExchangeRepo stockExchangeRepo;

    @Autowired
    private StockExchangeMapRepo stockExchangeMapRepo;

    @Autowired
    private CompanyService companyService;


    @Transactional
    StockExchange find(Long id) throws NotFoundException
    {
        Optional<StockExchange> stockExchange = stockExchangeRepo.findById(id);
        if(stockExchange.isEmpty()){
            System.out.println("coming here");
            throw new NotFoundException("StockExchange not found");
        }
        return stockExchange.get();
    }

    @Transactional
    public boolean validateCompanyCodes(List<String> companyCodes) throws NotFoundException
    {
        for (String companyCode:companyCodes) {
            if( !stockExchangeMapRepo.existsById(companyCode))
            {
                throw new NotFoundException("record for "+companyCode+" does not exist !");
            }
        }
        return true;
    }

    @Transactional
    public List<Long> getCompanyIdOfSector(Long sectorId) throws NotFoundException
    {
        return stockExchangeMapRepo.findBySectorId(sectorId).stream()
                .map(sdm -> sdm.getCompany().getId()).collect(Collectors.toList());
    }

    @Transactional
    public StockExchange create(StockExchange stockExchange) {
        return stockExchangeRepo.save(stockExchange);
    }

    @Transactional
    public StockExchange retrive(Long id) throws Exception {
        return find(id);
    }

    @Transactional
    public List<StockExchange> retriveAll() throws Exception {
        return stockExchangeRepo.findAll();
    }

    @Transactional
    public List<CompanyDTO> retriveAllCompanies(Long id) throws Exception {
        StockExchange stockExchange = find(id);
        return stockExchange.getCompanyToStockExchangeMaps().stream().map(s -> companyService.toDto( s.getCompany())).collect(Collectors.toList());
    }

    @Transactional
    public CompanyToStockExchangeMap addCompany(Long companyId,Long stockExchangeId) throws Exception {
        Company company = companyService.findCompany(companyId);
        StockExchange stockExchange = find(stockExchangeId);
        String mapId =companyId.toString()+"."+stockExchange.getName();
        CompanyToStockExchangeMap map =
                new CompanyToStockExchangeMap(mapId,company.getSector().getId(),company,stockExchange);
        return stockExchangeMapRepo.save(map);
    }

    @Transactional
    public StockExchange update(Long id,StockExchange stockExchange) throws Exception {
        stockExchange.setId(id);
        return stockExchangeRepo.save(stockExchange) ;
    }
}
