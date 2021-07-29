package com.example.companyms.Services;

import com.example.companyms.DTO.CompanyDTO;
import com.example.companyms.DTO.IPODetailDTO;
import com.example.companyms.Entities.Company;
import com.example.companyms.Entities.IPODetail;
import com.example.companyms.repos.CompanyRepo;
import com.example.companyms.repos.IPODetailRepo;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    IPODetailRepo ipoDetailRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SectorService sectorService;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Transactional
    Company findCompany(Long id) throws  NotFoundException
    {
        Optional<Company> company = companyRepo.findById(id);
        if(company.isEmpty()){
            System.out.println("coming here");
            throw new  NotFoundException("Company not found");
        }
        return company.get();
    }

    @Transactional
    IPODetail findIPO(Long id) throws  NotFoundException
    {
        Optional<IPODetail> ipoDetail = ipoDetailRepo.findById(id);
        if(ipoDetail.isEmpty()){
            System.out.println("coming here");
            throw new  NotFoundException("IPO not found");
        }
        return ipoDetail.get();
    }

    @Transactional
    public CompanyDTO toDto(Company company){
        CompanyDTO companyDTO = modelMapper.map(company, CompanyDTO.class);
        companyDTO.setSectorId(company.getSector().getId());
        if(company.getCompanyToStockExchangeMaps()!=null)
        {
            List<String> stockExchangeList =  company.getCompanyToStockExchangeMaps().stream()
                    .map(s -> s.getStockexchange().getName()).collect(Collectors.toList());
            companyDTO.setStockExchangeList(stockExchangeList);
        }
        return companyDTO;
    }

    public Company toEntity( CompanyDTO  companyDTO) throws NotFoundException{
        Company company = modelMapper.map( companyDTO,Company.class);
        company.setSector(sectorService.find(companyDTO.getSectorId()));
        return company;
    }

    public IPODetailDTO IPOtoDto(IPODetail ipoDetail){
        IPODetailDTO ipoDetailDTO = modelMapper.map(ipoDetail, IPODetailDTO.class);
        ipoDetailDTO.setCompanyId(ipoDetail.getCompany().getId());
        return ipoDetailDTO;
    }

    public IPODetail IPOtoEntity( IPODetailDTO ipoDetailDTO) throws NotFoundException{
        IPODetail ipoDetail = modelMapper.map(ipoDetailDTO, IPODetail.class);
        ipoDetail.setCompany(findCompany(ipoDetailDTO.getCompanyId()) );
        return ipoDetail;

    }


    @Transactional
    public  CompanyDTO addCompany( CompanyDTO  companyDTO) throws NotFoundException {
        companyDTO.setId(null);
        Company company = companyRepo.save(toEntity( companyDTO));
        return toDto(company);
    }


    @Transactional
    public  CompanyDTO editCompany(Long id, CompanyDTO  companyDTO) throws  NotFoundException {
        Company company = findCompany(id);
        companyDTO.setId(id);
        company.setCompanyName(companyDTO.getCompanyName());
        company.setCeo(companyDTO.getCeo());
        company.setTurnover(companyDTO.getTurnover());
        company.setBoardOfDirectors(companyDTO.getBoardOfDirectors());
        company.setCompanyBrief(companyDTO.getCompanyBrief());
        company.setSector(sectorService.find(companyDTO.getSectorId()));
        Company result = companyRepo.save(company);
        return toDto(result);
    }

    @Transactional
    public  CompanyDTO deactivateCompany(Long id) throws  NotFoundException {
        Company company = findCompany(id);
        company.setDeactivated(!company.isDeactivated());
        company = companyRepo.save(company);
        return toDto(company);
    }

    public  CompanyDTO getCompanyDetails(Long id) throws  NotFoundException {
        Company company = findCompany(id);
        return toDto(company);
    }

    public List< CompanyDTO> getMatchingCompanies(String pattern) {
        return companyRepo
                .search(pattern)
                .stream()
                .map(s-> toDto(s))
                .collect(Collectors.toList());
    }

    public List< CompanyDTO> getAllCompanies() {

        return companyRepo
                .findAll()
                .stream()
                .map(s-> toDto(s))
                .collect(Collectors.toList());
    }

    public List<String> getStockExchanges(Long id) throws Exception{
        Company company = findCompany(id);
        return company.getCompanyToStockExchangeMaps().stream()
                .map(s -> s.getStockexchange().getName()).collect(Collectors.toList());
    }

    @Transactional
    public IPODetailDTO addIPO(IPODetailDTO  ipoDetailDTO) throws Exception {
        IPODetail ipoDetail= IPOtoEntity(ipoDetailDTO);
        if(ipoDetail.getCompany().getIpoDetaiil()!=null)
        {
            if(ipoDetail.getCompany().getIpoDetaiil().getId() != ipoDetailDTO.getId())
            {
                throw new Exception("Company already has IPO");

            }
        }
        ipoDetail = ipoDetailRepo.save(ipoDetail);
        return IPOtoDto(ipoDetail);
    }

    @Transactional
    public IPODetailDTO getIpo(Long id) throws NotFoundException {
        return IPOtoDto(findIPO(id));
    }

    @Transactional
    public List<IPODetailDTO> getAllIpo() throws NotFoundException {
        return ipoDetailRepo.findAll().stream()
                .map(i -> IPOtoDto(i)).collect(Collectors.toList());
    }

    @Transactional
    public List<IPODetailDTO> getUpcomingIpo() throws NotFoundException {
        return ipoDetailRepo.findAll().stream()
                .filter(i -> i.getOpenDateTime().isAfter(LocalDateTime.now()) )
                .map(i -> IPOtoDto(i)).collect(Collectors.toList());
    }

    @Transactional
    public IPODetailDTO getCompanyIpo(Long companyId) throws NotFoundException {
        Company company = findCompany(companyId);
        return IPOtoDto( company.getIpoDetaiil());
    }

}
