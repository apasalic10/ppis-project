package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.ReviewDTO;
import ba.unsa.etf.nwt.DTO.ReviewPostDTO;
import ba.unsa.etf.nwt.DTO.ServiceAgreementDTO;
import ba.unsa.etf.nwt.entity.Review;
import ba.unsa.etf.nwt.repository.ReviewRepository;
import ba.unsa.etf.nwt.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewRepository reviewRepository, ReviewService reviewService) {
        this.reviewRepository = reviewRepository;
        this.reviewService = reviewService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findReviewById(@PathVariable Long id) {
        try{
            ReviewDTO dto = reviewService.getReviewDTOById(id);
            return ResponseEntity.ok().body(dto);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error message", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> createReview(@RequestBody ReviewPostDTO dto) {
        try {
            String createdReview = reviewService.createReview(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error message", e.getMessage()));
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateReviewField(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        try {
            ReviewDTO updatedDTO = reviewService.updateReviewField(id, updates);
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
