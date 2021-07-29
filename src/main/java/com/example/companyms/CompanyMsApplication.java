package com.example.companyms;

import com.example.companyms.Entities.Company;
import com.example.companyms.Entities.Sector;
import com.example.companyms.Entities.StockExchange;
import com.example.companyms.Entities.UserData;
import com.example.companyms.Services.CompanyService;
import com.example.companyms.Services.SectorService;
import com.example.companyms.Services.StockExchangeService;
import com.example.companyms.repos.CompanyRepo;
import com.example.companyms.repos.SectorRepo;
import com.example.companyms.repos.StockExchangeRepo;
import com.example.companyms.repos.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

@SpringBootApplication
public class CompanyMsApplication implements CommandLineRunner {

    @Autowired
    SectorRepo sectorRepo;

    @Autowired
    StockExchangeRepo stockExchangeRepo;

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    StockExchangeService stockExchangeService;
    @Override
    @Transactional
    public void run(String... args) throws Exception {



        Sector sector = new Sector();
        sector.setName("Agri");
        sector.setBrief("Agri");
        sector =sectorRepo.save(sector);

        Sector sector2 = new Sector();
        sector2.setName("Fintech");
        sector2.setBrief("Fintech");
        sector2 =sectorRepo.save(sector2);

        StockExchange se = new StockExchange();
        se.setName("nse");
        se.setBrief("nse");
        se = stockExchangeRepo.save(se);

        Company company = new Company();
        company.setSector(sector);
        company.setCompanyName("company1");
        company.setBoardOfDirectors("a,b");
        company.setCompanyBrief("");
        company.setCeo("ceo1");
        company.setTurnover(100d);
        company = companyRepo.save(company);

        Company company1 = new Company();
        company1.setSector(sector2);
        company1.setCompanyName("company2");
        company1.setBoardOfDirectors("a,b");
        company1.setCompanyBrief("");
        company1.setCeo("ceo2");
        company1.setTurnover(200d);
        company1 = companyRepo.save(company1);
        for (int i=3;i<10;i++){
            Company temp = new Company();
            temp.setSector(sector);
            temp.setCompanyName("company"+i);
            temp.setBoardOfDirectors("a,b");
            temp.setCompanyBrief("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            temp.setCeo("ceo"+i);
            temp.setTurnover(200d);
            temp = companyRepo.save(temp);
        }
        stockExchangeService.addCompany(company.getId(),se.getId());
        stockExchangeService.addCompany(company1.getId(),se.getId());

        UserData admin = new UserData();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setEmail("ajaywk7@gmail.com");
        admin.setRole("ROLE_ADMIN");
        userRepo.save(admin);

        UserData user = new UserData();
        user.setUsername("user");
        user.setPassword("user");
        user.setEmail("ajaywk7@gmail.com");
        user.setRole("ROLE_USER");
        userRepo.save(user);



    }

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder encoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    public static void main(String[] args) {
        SpringApplication.run(CompanyMsApplication.class, args);
    }

}
