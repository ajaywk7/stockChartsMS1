package com.example.companyms.controllerTests;

import com.example.companyms.Controllers.CompanyController;
import com.example.companyms.DTO.CompanyDTO;
import com.example.companyms.Entities.Company;
import com.example.companyms.Entities.Sector;
import com.example.companyms.Entities.StockExchange;
import com.example.companyms.Services.CompanyService;
import com.example.companyms.repos.CompanyRepo;
import com.example.companyms.repos.SectorRepo;
import com.example.companyms.repos.StockExchangeRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class companyTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    CompanyService companyService;


    private static CompanyDTO companyDTO = new CompanyDTO();
    private static Sector sector;
    private static StockExchange se;

    @BeforeClass
    static public void beforeTestClass()
    {
        companyDTO.setCompanyName("ajay");
        companyDTO.setTurnover(100d);
        companyDTO.setBoardOfDirectors("a,b");
        companyDTO.setCeo("ceo");
        companyDTO.setCompanyBrief("chumma");
        companyDTO.setDeactivated(false);
        companyDTO.setSectorId(1l );
    }

    @Test
    public void addCompany() throws Exception
    {

        mvc.perform( MockMvcRequestBuilders
                .post("/company/admin/")
                .content(Utils.asJsonString(companyDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void editCompany() throws Exception
    {
        CompanyDTO companyDTOT= companyService.addCompany(companyDTO);
        companyDTOT.setCompanyName("ajay1");
        mvc.perform( MockMvcRequestBuilders
                .put("/company/admin/?id="+companyDTOT.getId().toString())
                .content(Utils.asJsonString(companyDTOT))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyName").value("ajay1"));
    }

    @Test
    public void deactivateCompany() throws Exception
    {
        CompanyDTO companyDTOT= companyService.addCompany(companyDTO);
        mvc.perform( MockMvcRequestBuilders
                .delete("/company/admin/?id="+companyDTOT.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.deactivated").value(true));
    }

    @Test
    public void getCompany() throws Exception
    {
        CompanyDTO companyDTOT= companyService.addCompany(companyDTO);
        mvc.perform( MockMvcRequestBuilders
                .get("/company/?id="+companyDTOT.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void getMatchingCompanies() throws Exception
    {
        companyDTO.setCompanyName("xaxa");
        CompanyDTO companyDTO1= companyService.addCompany(companyDTO);
        CompanyDTO companyDTO2= companyService.addCompany(companyDTO);
        mvc.perform( MockMvcRequestBuilders
                .get("/company/search?searchText="+companyDTO.getCompanyName())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getALlCompanies() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .get("/company/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }




}
