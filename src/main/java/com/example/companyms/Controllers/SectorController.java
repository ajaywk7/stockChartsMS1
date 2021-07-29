package com.example.companyms.Controllers;

import com.example.companyms.DTO.CompanyDTO;
import com.example.companyms.Entities.Company;
import com.example.companyms.Entities.Sector;
import com.example.companyms.Services.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/sector")
public class SectorController {

    @Autowired
    SectorService sectorService;

    @PostMapping
    public ResponseEntity<Sector> create(@RequestBody Sector sector) throws Exception{
        Sector result = sectorService.create(sector);
        return new ResponseEntity<Sector>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Sector> retrieve(@RequestParam Long id) throws Exception{
        return new ResponseEntity<Sector>(sectorService.retrive(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Sector>> retrieveAll() throws Exception{
        return new ResponseEntity<List<Sector>>(sectorService.retriveAll(), HttpStatus.OK);
    }

    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDTO>> retrieveAllCompanies(@RequestParam Long id) throws Exception{
        return new ResponseEntity<List<CompanyDTO>>(sectorService.retriveAllCompanies(id), HttpStatus.OK);
    }

}
