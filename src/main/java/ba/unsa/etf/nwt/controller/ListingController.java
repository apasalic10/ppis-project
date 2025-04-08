package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.ListingDTO;
import ba.unsa.etf.nwt.DTO.TeachingOfferingPostDTO;
import ba.unsa.etf.nwt.DTO.LearningRequestPostDTO;
import ba.unsa.etf.nwt.entity.Listing;
import ba.unsa.etf.nwt.service.ListingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<ListingDTO> updateListing(@PathVariable Long id,
                                                    @RequestBody JsonPatch patch) {
        try {
            Listing listing = listingService.getListingById(id);
            if (listing == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            Listing patchedListing = listingService.applyPatchToListing(patch, listing);
            patchedListing = listingService.save(patchedListing);
            ListingDTO listingDTO = listingService.convertToDto(patchedListing);
            return new ResponseEntity<>(listingDTO, HttpStatus.OK);
        } catch (JsonPatchException | JsonProcessingException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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
