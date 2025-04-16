package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.TeachingOfferingPostDTO;
import ba.unsa.etf.nwt.entity.TeachingOffering;
import ba.unsa.etf.nwt.service.TeachingOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/teaching-offerings")
public class TeachingOfferingController {

    private final TeachingOfferingService teachingOfferingService;

    @Autowired
    public TeachingOfferingController(TeachingOfferingService teachingOfferingService) {
        this.teachingOfferingService = teachingOfferingService;
    }

    @PostMapping
    public ResponseEntity<TeachingOffering> createTeachingOffering(@Valid @RequestBody TeachingOfferingPostDTO dto) {
        try {
            TeachingOffering teachingOffering = teachingOfferingService.createTeachingOffering(dto);
            return new ResponseEntity<>(teachingOffering, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
