package com.epam.generator.controller;

import com.epam.generator.facade.DataGeneratorFacade;
import com.epam.generator.facade.ImpexGeneratorFacade;
import com.epam.generator.model.SolutionEnum;
import com.epam.generator.model.dto.ContractDTO;
import com.epam.generator.model.jsresponse.ContractJsonResponse;
import com.epam.generator.model.dto.TestDataDTO;
import com.epam.generator.model.jsresponse.SolutionJsonResponse;
import com.epam.generator.service.impl.ContractDataCreationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.epam.generator.model.SolutionEditionEnum.BYD_CLASSIC;
import static com.epam.generator.model.SolutionEditionEnum.BYD_SIMPL;
import static com.epam.generator.model.SolutionEditionEnum.CLOUD_PART_PROF;
import static com.epam.generator.model.SolutionEditionEnum.CLOUD_PART_START;
import static com.epam.generator.model.SolutionEditionEnum.CLOUD_SAP_PROF;
import static com.epam.generator.model.SolutionEditionEnum.CLOUD_SAP_START;
import static com.epam.generator.model.SolutionEditionEnum.PREM_PROF;
import static com.epam.generator.model.SolutionEditionEnum.PREM_START;

@ComponentScan(basePackages = "com.epam.generator")
@Controller
public class MainController {

    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);
    private static final String TEST_DATA_KEY = "testData";
    private static final String CONTRACT_DATA_KEY = "contractData";


    @Autowired
    DataGeneratorFacade dataGeneratorFacade;
    @Autowired
    ImpexGeneratorFacade impexGeneratorFacade;
    @Autowired
    ContractDataCreationService contractDataCreationService;

    TestDataDTO testDataDTOClass = new TestDataDTO();
    ContractDTO contractDTOClass = new ContractDTO();


    @GetMapping("/register")
    public String showForm(Model model) {

        model.addAttribute(TEST_DATA_KEY, testDataDTOClass);

        return "register_form";
    }

    @PostMapping("/register")
    public String submitForm(Model model, @ModelAttribute(TEST_DATA_KEY) TestDataDTO testDataDTO) {

        LOG.info(".... Registration ....");

        getDataGeneratorFacade().generate(testDataDTO);
        testDataDTOClass = testDataDTO;

        model.addAttribute(TEST_DATA_KEY, testDataDTOClass);
        model.addAttribute(CONTRACT_DATA_KEY, contractDTOClass);

        return "register_success";
    }

    @GetMapping("/generate")
    public String generateFile(Model model) {

        LOG.info(".... GENERATION ....");

        getImpexGeneratorFacade().generateImpexes(testDataDTOClass);

        return "generate_success";
    }

    @PostMapping(value = "/contract/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ContractJsonResponse addContract(@RequestBody ContractDTO contractDTO) {
        ContractJsonResponse contractJsonResponse = new ContractJsonResponse();

        LOG.info("-----CONTROLLER METHOD ADD------");

        getContractDataCreationService().addContractToCustomer(testDataDTOClass, contractDTO);
        testDataDTOClass.getCustomers()
                .forEach(customerDTO -> contractJsonResponse.addContractDTOs(customerDTO.getContracts()));
        if (!CollectionUtils.isEmpty(contractJsonResponse.getContractDTOList())) {
            contractJsonResponse.setValidated(true);
        }
        return contractJsonResponse;
    }

    @PostMapping(value = "/solutionEdition", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public SolutionJsonResponse getSolutionEdition(@RequestBody String solution) {
        SolutionJsonResponse solutionJsonResponse = new SolutionJsonResponse();

        LOG.info("-----CONTROLLER METHOD GET SOLUTION------");
        LOG.info(solution);
        switch (SolutionEnum.valueOf(solution.replaceAll("\"", ""))) {
            case PREM:
                solutionJsonResponse.setSolutionEditions(Arrays.asList(PREM_START.getValue(), PREM_PROF.getValue()));
                break;
            case CLOUD_SAP:
                solutionJsonResponse.setSolutionEditions(Arrays.asList(CLOUD_SAP_START.getValue(), CLOUD_SAP_PROF.getValue()));
                break;
            case CLOUD_PARTNER:
                solutionJsonResponse.setSolutionEditions(Arrays.asList(CLOUD_PART_START.getValue(), CLOUD_PART_PROF.getValue()));
                break;
            case BYD_CLOUD:
                solutionJsonResponse.setSolutionEditions(Arrays.asList(BYD_CLASSIC.getValue(), BYD_SIMPL.getValue()));
                break;
            default:
                LOG.warn("Unknown solution");
                break;
        }
        if (!CollectionUtils.isEmpty(solutionJsonResponse.getSolutionEditions())) {
            solutionJsonResponse.setValidated(true);
        }

        return solutionJsonResponse;
    }

    public DataGeneratorFacade getDataGeneratorFacade() {
        return dataGeneratorFacade;
    }

    public void setDataGeneratorFacade(DataGeneratorFacade dataGeneratorFacade) {
        this.dataGeneratorFacade = dataGeneratorFacade;
    }

    public ImpexGeneratorFacade getImpexGeneratorFacade() {
        return impexGeneratorFacade;
    }

    public void setImpexGeneratorFacade(ImpexGeneratorFacade impexGeneratorFacade) {
        this.impexGeneratorFacade = impexGeneratorFacade;
    }

    public ContractDataCreationService getContractDataCreationService() {
        return contractDataCreationService;
    }

    public void setContractDataCreationService(ContractDataCreationService contractDataCreationService) {
        this.contractDataCreationService = contractDataCreationService;
    }
}
