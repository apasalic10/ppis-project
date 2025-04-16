package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.LearningRequestPostDTO;
import ba.unsa.etf.nwt.DTO.ListingDTO;
import ba.unsa.etf.nwt.DTO.TeachingOfferingPostDTO;
import ba.unsa.etf.nwt.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/listings")
public class ListingController {

    private final ListingService listingService;

    @Autowired
    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @PostMapping("/teaching-offering")
    public ResponseEntity<String> createTeachingOffering(@Valid @RequestBody TeachingOfferingPostDTO teachingOfferingDTO) {
        try {
            String uuid = listingService.createTeachingOffering(teachingOfferingDTO);
            return new ResponseEntity<>(uuid, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/learning-request")
    public ResponseEntity<String> createLearningRequest(@Valid @RequestBody LearningRequestPostDTO learningRequestDTO) {
        try {
            String uuid = listingService.createLearningRequest(learningRequestDTO);
            return new ResponseEntity<>(uuid, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListingDTO> getListingById(@PathVariable("id") Long id) {
        try {
            ListingDTO listingDTO = listingService.getListingDTOById(id);
            return new ResponseEntity<>(listingDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<ListingDTO>> getAllListings() {
        try {
            List<ListingDTO> listings = listingService.getAllListings();
            return new ResponseEntity<>(listings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
