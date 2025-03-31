package ba.unsa.etf.nwt.controller;


import ba.unsa.etf.nwt.DTO.ServiceAgreementDTO;
import ba.unsa.etf.nwt.DTO.ServiceAgreementPostDTO;
import ba.unsa.etf.nwt.entity.ServiceAgreement;
import ba.unsa.etf.nwt.repository.ServiceAgreementRepository;
import ba.unsa.etf.nwt.service.ServiceAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

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

    @PostMapping
    public ResponseEntity<Object> createServiceAgreement(@RequestBody ServiceAgreementPostDTO dto) {
        try {
            String response = serviceAgreementService.createServiceAgreement(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("Error message", ex.getMessage()));
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateServiceAgreementField(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        try {
            ServiceAgreementDTO updatedDTO = serviceAgreementService.updateServiceAgreementField(id, updates);
            return ResponseEntity.ok(updatedDTO);
        }
        catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("Error message", ex.getMessage()));
        }
        catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Error message", ex.getMessage()));
        }
    }

}
