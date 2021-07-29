package com.example.companyms.Services;

import com.example.companyms.DTO.CompanyDTO;
import com.example.companyms.DTO.SectorDTO;
import com.example.companyms.Entities.Company;
import com.example.companyms.Entities.Sector;
import com.example.companyms.repos.SectorRepo;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectorService {

    @Autowired
    private SectorRepo sectorRepo;

    @Autowired
    private CompanyService companyService;

    @Transactional
    Sector find(Long id) throws NotFoundException
    {
        Optional<Sector> sector = sectorRepo.findById(id);
        if(sector.isEmpty()){
            System.out.println("coming here");
            throw new NotFoundException("sector not found");
        }
        return sector.get();
    }

    @Transactional
    public Sector create(Sector sector) {
        return sectorRepo.save(sector);
    }

    @Transactional
    public Sector retrive(Long id) throws Exception {
        return find(id);
    }

    @Transactional
    public List<Sector> retriveAll() throws Exception {
        return sectorRepo.findAll();
    }

    @Transactional
    public List<CompanyDTO> retriveAllCompanies(Long id) throws Exception {
        Sector sector = find(id);
        return sector.getCompanies().stream().map(c -> companyService.toDto(c)).collect(Collectors.toList());
    }

    @Transactional
    public Sector update(Long id,Sector sector) throws Exception {
        sector.setId(id);
        return sectorRepo.save(sector) ;
    }


}
