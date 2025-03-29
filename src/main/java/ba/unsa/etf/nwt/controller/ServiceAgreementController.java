package ba.unsa.etf.nwt.controller;


import ba.unsa.etf.nwt.DTO.ServiceAgreementDTO;
import ba.unsa.etf.nwt.entity.ServiceAgreement;
import ba.unsa.etf.nwt.repository.ServiceAgreementRepository;
import ba.unsa.etf.nwt.service.ServiceAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/serviceAgreement")
public class ServiceAgreementController {
    private final ServiceAgreementRepository serviceAgreementRepository;
    private final ServiceAgreementService serviceAgreementService;

    @Autowired
    public ServiceAgreementController(ServiceAgreementRepository serviceAgreementRepository, ServiceAgreementService serviceAgreementService) {
        this.serviceAgreementRepository = serviceAgreementRepository;
        this.serviceAgreementService = serviceAgreementService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getServiceAgreementById(@PathVariable long id) {
        try{
            ServiceAgreementDTO dto = serviceAgreementService.getServiceAgreementDTOById(id);
            return ResponseEntity.ok(dto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Error message", e.getMessage()));
        }
    }
}
