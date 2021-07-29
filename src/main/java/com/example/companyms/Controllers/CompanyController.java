package com.example.companyms.Controllers;

import com.example.companyms.DTO.CompanyDTO;
import com.example.companyms.DTO.IPODetailDTO;
import com.example.companyms.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @PostMapping("/admin")
    public ResponseEntity<CompanyDTO> addCompany(@Valid @RequestBody  CompanyDTO  companyDTO) throws Exception{
         CompanyDTO result = companyService.addCompany( companyDTO);
        return new ResponseEntity< CompanyDTO>(result, HttpStatus.OK);
    }

    @PutMapping("/admin")
    public ResponseEntity< CompanyDTO> editCompany(@RequestParam Long id,@Valid @RequestBody  CompanyDTO  companyDTO) throws Exception {
//        System.out.println(companyDTO.getCompanyBrief());
//        return null;
        CompanyDTO result = companyService.editCompany(id, companyDTO);
        return new ResponseEntity< CompanyDTO>(result, HttpStatus.OK);
    }

    @DeleteMapping("/admin")
    public  CompanyDTO deactivateCompany(@RequestParam Long id) throws Exception{
        return companyService.deactivateCompany(id);
    }

    @GetMapping("/")
    public ResponseEntity< CompanyDTO> getCompany(@RequestParam Long id) throws Exception{
        return new ResponseEntity< CompanyDTO>(companyService.getCompanyDetails(id), HttpStatus.OK );
    }

    @GetMapping("/search")
    public ResponseEntity<List< CompanyDTO>> getMatchingCompanies(@RequestParam String searchText){
        return new ResponseEntity<List< CompanyDTO>>(companyService.getMatchingCompanies(searchText), HttpStatus.OK );
    }



    @GetMapping("/all")
    public ResponseEntity<List< CompanyDTO>> getCompanies() throws Exception{
        return new ResponseEntity<List< CompanyDTO>>(companyService.getAllCompanies(), HttpStatus.OK );
    }

    @GetMapping("/stockExchanges")
    public ResponseEntity<List<String>> getStockExchanges(@RequestParam Long companyId) throws Exception{
        return new ResponseEntity<List<String>>(companyService.getStockExchanges(companyId) , HttpStatus.OK );
    }

    @PostMapping("/admin/ipo")
    public ResponseEntity<IPODetailDTO> addIPO(@Valid @RequestBody  IPODetailDTO  ipoDetailDTO) throws Exception{
        IPODetailDTO result = companyService.addIPO( ipoDetailDTO);
        return new ResponseEntity< IPODetailDTO>(result, HttpStatus.OK);
    }

    @GetMapping("/ipo")
    public ResponseEntity<IPODetailDTO> getIPO(@RequestParam Long ipoId ) throws Exception{
        return new ResponseEntity<IPODetailDTO>(companyService.getIpo(ipoId), HttpStatus.OK);
    }

    @GetMapping("/ipos")
    public ResponseEntity<List<IPODetailDTO>> getAllIPO() throws Exception{
        return new ResponseEntity<List<IPODetailDTO>>(companyService.getAllIpo(), HttpStatus.OK);
    }

    @GetMapping("/ipos/upcoming")
    public ResponseEntity<List<IPODetailDTO>> getUpcomingIPO() throws Exception{
        return new ResponseEntity<List<IPODetailDTO>>(companyService.getUpcomingIpo(), HttpStatus.OK);
    }

}
